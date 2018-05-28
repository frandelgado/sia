Darwin
------

<!-- TOC -->

- [Tooling](#tooling)
    - [Java](#java)
    - [Docker](#docker)
- [Building](#building)
    - [Con Maven](#con-maven)
    - [Con Docker](#con-docker)
- [Running](#running)
    - [Con Java](#con-java)
    - [Con Docker](#con-docker-1)
        - [Items](#items)
        - [Config](#config)
        - [CMD](#cmd)

<!-- /TOC -->

# Tooling

## Java
Utilizamos Maven 3.5 y Java 10 para nuestro código. Referirse a las distintas
guías on-line para la instalación del tooling. Para mac, recomendamos:

```bash
    $ brew cask install java
    $ brew install maven
```

## Docker
Utilizamos Docker 16.05 o superior ya que tiene soporte para multi-stage builds.

# Building

## Con Maven

Hacer `mvn clean package`. En la carpeta `/target` se encontrarán dos jar files,
uno con el nombre de `genetics-SNAPSHOT...jar` y otro con el de
`genetics-...-jar-with-dependencies.jar`. A no ser que ya cuentes con todas las
librerías en el classpath, usar el que tiene `jar-with-dependencies`.

## Con Docker
Proveemos una imagen de Docker. Basta ejecutar el siguiente comando en el
directorio donde está el `Dockerfile` para construir el proyecto:

```bash
docker build -t genetics-g4:latest .
```

# Running

## Con Java
`java -jar genetics...jar -f config.json -v`

## Con Docker
`docker run --rm -it genetics-g4:latest`

### Items
Pueden agregarse items a una carpeta dentro del container bajo el volumen
montable `/genetics/items`.

### Config
Puede cambiarse la configuración usando un archivo `config.json` en una carpeta
montada bajo el volument `/genetics/config`.


### CMD
El `ENTRYPOINT` del `Dockerfile` es `java -jar genetics...jar`. El `CMD` por
default es `-f /genetics/config/config.json`.
