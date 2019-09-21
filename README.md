# GraalVM Polyglot Example

An example spring project that uses GraalVM to run R and javascript code.

## Requirements

- Install GraalVM Community Edition ([instruction](https://www.graalvm.org/docs/getting-started/#install-graalvm))
- Run the following command in cli to install the required languages in Graal:

```bash
gu install native-image
gu install r
```

## Running

Make sure you use GraalVM:

```bash
java -version
openjdk version "1.8.0_222"
OpenJDK Runtime Environment (build 1.8.0_222-20190711112007.graal.jdk8u-src-tar-gz-b08)
OpenJDK 64-Bit GraalVM CE 19.2.0 (build 25.222-b08-jvmci-19.2-b02, mixed mode)
```

Then run the application like a typical SpringBoot application:

```bash
mvn spring-boot:run
```

or

```bash
java -jar your-jar.jar
```
