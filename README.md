# Reactor Flatmap Util

Everyone loves reactive programming with io.projectreactor for its speed. However, the problem is that we are losing readability. If many methods in a project like ours return Monos and Fluxes, flatMap after flatMap is callback hell. We looked at the code written after two days, and no one can make heads and tails of it. That scared us concerning project maintainability.

In this scenario, there are only a few options.

The project should be moved back to Spring Web MVC from Spring Web Flux.
Move from Java to Kotlin, using co-routines to improve readability.

Both these options have their drawbacks. This util will save your code readability as it does for us. Below is an example.

## Before Reactor Flatmap Util

```java
public Mono<AuthenticationResponse> authenticate(AuthenticationRequest authRequest,
            ServerHttpRequest request,
	        ServerHttpResponse response) {

    // Retrive the Client id based on the URL
    Mono<ULong> clientId = this.clientService.getClientId(request);

    // Retrive the User by searching in Client's umbrella
    Mono<User> user = clientId.flatMap(cid ->
        userService.findByClientIdsUserName(cid, authRequest.getUserName(), authRequest.getIdentifierType()));

    // Retrive the User's Client object
    Mono<Client> client = user.flatMap(u -> this.clientService.readInternal(u.getClientId()));

    URI uri = request.getURI();

    InetSocketAddress inetAddress = request.getRemoteAddress();
    final String setAddress = inetAddress == null ? null : inetAddress.getHostString();

    // Map the user to check password
    return user.flatMap(u ->
        checkPassword(authRequest, u).flatMap(e ->

            // Map the client to get client's password policy
            client.flatMap(c ->
                clientService.getClientPasswordPolicy(c.getId()).flatMap(pol ->

                    // Map to check if the user reached maximum attempts based on the policy
                    checkFailedAttempts(u, pol).flatMap(s ->
                    {
                        userService.resetFailedAttempt(u.getId())
                                .subscribe();

                        soxLogService.create(new SoxLog().setObjectId(u.getId())
                                .setActionName(SecuritySoxLogActionName.LOGIN)
                                .setObjectName(SecuritySoxLogObjectName.USER)
                                .setDescription("Successful"))
                                .subscribe();

                        // If everything checksout, return the created token
                        return clientId.flatMap(cid -> makeToken(authRequest, response, uri, setAddress, u, c, cid));

                    })))))
            // If for some reason any of the above checks fails throw a credential error
            .switchIfEmpty(Mono.defer(this::credentialError));
	}
```

## After Reactor Flatmap Util

```java
public Mono<AuthenticationResponse> authenticate(AuthenticationRequest authRequest,
            ServerHttpRequest request,
	        ServerHttpResponse response) {

    return flatMapMono(

        () -> this.clientService.getClientId(request),

        (clientId) -> userService.findByClientIdsUserName(clientId, authRequest.getUserName(),
                authRequest.getIdentifierType()),

        (clientId, user) -> this.clientService.readInternal(user.getClientId()),

        (clientId, user, client) -> this.checkPassword(authRequest, user),

        (clientId, user, client, passwordChecked) -> clientService.getClientPasswordPolicy(client.getId()),

        (clientId, user, client, passwordChecked, policy) -> this.checkFailedAttempts(user, policy),

        (clientId, user, client, passwordChecked, policy, j) ->
        {

            userService.resetFailedAttempt(user.getId())
                    .subscribe();

            soxLogService.create(new SoxLog().setObjectId(user.getId())
                    .setActionName(SecuritySoxLogActionName.LOGIN)
                    .setObjectName(SecuritySoxLogObjectName.USER)
                    .setDescription("Successful"))
                    .subscribe();

            URI uri = request.getURI();

            InetSocketAddress inetAddress = request.getRemoteAddress();
            final String hostAddress = inetAddress == null ? null : inetAddress.getHostString();

            return makeToken(authRequest, response, uri, hostAddress, user, client, clientId);
        }).switchIfEmpty(Mono.defer(this::credentialError));
	}
```

## Dependency

**Maven**

```xml
    <dependency>
        <groupId>com.fincity.nocode</groupId>
        <artifactId>reactor-flatmap-util</artifactId>
        <version>1.2.0</version>
    </dependency>
```

**Gradle**

```gradle
compile "com.fincity.nocode:reactor-flatmap-util:1.2.0"
```

## Getting Started

`FlatMapUtil` has only three different types of methonds, `flatMapMono`, `flatMapConsolidate`, and `flatMapFlux`.

### flatMapMono

`flatMapMono` takes 2 to 10 functions as parameters. Each parameter is a function with increasing number of parameters. First parameter is a `Supplier<Mono<F>>`, second is a `Function<F, Mono<S>>` are mandatory and the next parameters are functions with increasing number of parameters. Each argument's function's parameter is the value of Mono returned from the previous function argument. `flatMapMono` call will return a Mono of last function argument's return Mono.

**Example 1:**

```Java
    Mono<Integer> four = flatMapMono(
        // First mono simply gives a one.
        () -> Mono.just(1),

        // Second mono consumes one and adds 1.
        (one) -> Mono.just(one + 1),

        // Third mono consumes one from first, two from second and adds 1.
        (one, two) -> Mono.just(one + two + 1),

        // Third mono consumes one from first, two from second, three from third and adds 1.
        (one, two, three) -> Mono.just(one + two + three + 1)

        // Finally it returns the last mono that is returned.
    );
```

**Example 2 :**

```Java
    Mono<Boolean> roundMono = flatMapMono(

        // First argument is always a supplier, with 0 parameters,
        // The method "generateNumberBetween" say returns a Mono<Integer>.
        () -> this.generateANumberBetween(1, 10),

        // Second argument is a function, with 1 parameter,
        // It will be the integer that will be in the Mono.
        // The method "addFloatValue" say returns a Mono<Float>.
        (iNum) -> this.addFloatValue(iNum, 0.4f),


        // Third argument is a function, with 2 parameters,
        // One will be the number returned from the first Mono,
        // Second is the float value returned from the second Mono.
        // The method "isRoundFloorValue" returns a Mono<Boolean>.
        (iNum, fNum) -> this.isRoundFloorValue(fNum)
    );
```

### flatMapConsolidate

`flatMapConsolidate` exactly works like `flatMapMono` except that it returns all the values retruned by all the monos. It works like a flatMap Zip operation.

**Example 1:**

```Java
    Mono<Tuple4<Integer, Integer, Integer, Integer>> allFour = flatMapConsolidate(
        // First mono simply gives a one.
        () -> Mono.just(1),

        // Second mono consumes one and adds 1.
        (one) -> Mono.just(one + 1),

        // Third mono consumes one from first, two from second and adds 1.
        (one, two) -> Mono.just(one + two + 1),

        // Third mono consumes one from first, two from second, three from third and adds 1.
        (one, two, three) -> Mono.just(one + two + three + 1)

        // Finally it returns a tuple with all the all values from each argument.
    );
```

**Example 2 :**

```Java
    Mono<Tuple3<Integer, Float, Boolean>> roundMono = flatMapConsolidate(

        // First argument is always a supplier, with 0 parameters,
        // The method "generateNumberBetween" say returns a Mono<Integer>.
        () -> this.generateANumberBetween(1, 10),

        // Second argument is a function, with 1 parameter,
        // It will be the integer that will be in the Mono.
        // The method "addFloatValue" say returns a Mono<Float>.
        (iNum) -> this.addFloatValue(iNum, 0.4f),


        // Third argument is a function, with 2 parameters,
        // One will be the number returned from the first Mono,
        // Second is the float value returned from the second Mono.
        // The method "isRoundFloorValue" returns a Mono<Boolean>.
        (iNum, fNum) -> this.isRoundFloorValue(fNum)

        // Finally returns a tuple with all three values from each argument.
    );
```

### flatMapFlux

`flatMapFlux` is exactly like `flatMapMono` except it is for the Flux.
