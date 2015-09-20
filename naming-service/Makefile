# Commands:
# Build source into class files in the `bin` directory:
# > make build
# (Is also the default.)
# > make
# Build & Run program:
# > make run

all: build

# Building source
build-server:
	mkdir -p bin
	javac -d bin -sourcepath src src/ns/Server/Server.java

# Building source
build-client:
	mkdir -p bin
	javac -d bin -sourcepath src src/ns/Client/Client.java

build: build-server build-client
