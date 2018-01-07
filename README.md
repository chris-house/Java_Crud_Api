# REST CRUD Api using Spring Boot and an embedded database.


### Requirements

- Maven
- JDK 7

### Running

To build and start the server simply type

```sh
$ mvn spring-boot:run
```

from the root directory.

### Using

You can see what urls are available using curl:

```sh
$ curl localhost:8080
```

You can view existing notes objects using a similar request:

```sh
$ curl localhost:8080/api/notes
```

and can create new ones using a POST:

```sh
$ curl -X POST -H "Content-Type:application/json" -d '{ "body" : "I am a note"}' localhost:8080/api/notes
```
