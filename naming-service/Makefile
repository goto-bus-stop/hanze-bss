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
	javac -d bin -sourcepath src src/Main.java

# Ensures stuff is built before running it
run: build
	java -classpath bin Main
