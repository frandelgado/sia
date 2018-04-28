# Search Solver

<!-- TOC -->

* [Search Solver](#search-solver)
* [Installation](#installation)
  * [Get the right dependencies](#get-the-right-dependencies)
  * [How do I add jars to my local repo?](#how-do-i-add-jars-to-my-local-repo)
  * [How do I compile the jars?](#how-do-i-compile-the-jars)
  * [Where can I find the files?](#where-can-i-find-the-files)

<!-- /TOC -->

# Installation

## Get the right dependencies

You need:

* Maven 3.5 or later
* Java 1.9 SDK
* IntelliJ (because it's the only reasonable option)

## How do I add jars to my local repo?

Like this:

`mvn install:install-file -DlocalRepositoryPath=repo -DcreateChecksum=true -Dpackaging=jar -Dfile=GPS.jar -DgroupId=ar.com.itba.sia -DartifactId=search-interfaces -Dversion=1.0`

## How do I compile the jars?

`mvn clean package`

## Where can I find the files?

Look for them in the corresponding `target` directories.
