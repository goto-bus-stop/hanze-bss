# Project 1: Naming Service

A small DNS proxy thing with a local server and a local client app.

## Build

```bash
# just server or client
$ make build-server
$ make build-client
# both
$ make build
```

## Run

Start a server somewhere on localhost:

```bash
$ ./server
```

Ask the server for things:

```bash
$ ./client [domain]
# e.g.
$ ./client github.com
192.30.252.128
```

## License

[MIT](../LICENSE)
