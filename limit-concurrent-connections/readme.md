# Project 2: Limiting concurrent connections

A server that closes connections after 10 seconds. It should only allow a few
connections at a time and reject the rest.

## Build

```bash
$ make build
```

## Run

Start a server somewhere on localhost:

```bash
$ make run
```

Spawn a bunch of clients:

```bash
$ for i in $(seq 1 20) ; do nc localhost 2500 & ; done ; wait
```

## License

[MIT](../LICENSE)
