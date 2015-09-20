# Commands:
# Build source into class files in the `bin` directory:
# > make build
# (Is also the default.)
# > make
# Build & Run program:
# > make run

all: build

# Building source
build:
	mkdir -p bin
	javac -d bin -sourcepath src src/timedconnections/TimedServer.java

run: build
	java -classpath bin timedconnections.TimedServer
