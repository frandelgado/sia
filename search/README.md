Search Solver
=============


# Installation

## Get the right dependencies

You need:

* Maven 3.5 or later
* Java 1.9 SDK
* IntelliJ (because it's the only reasonable option)

## How do I add jars to my local repo?

Like this:

`mvn install:install-file -DlocalRepositoryPath=repo -DcreateChecksum=true -Dpackaging=jar -Dfile=GPS.jar  -DgroupId=ar.com.itba.sia -DartifactId=search-interfaces -Dversion=1.0`

## How do I compile the jars?

`mvn clean package`

## Where can I find the files?

Look for them in the corresponding `target` directories.