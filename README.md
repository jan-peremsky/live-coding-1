# live-coding-1

Simple [Quarkus](Quarkus.md)/Maven based Java 17 project for the live coding sessions.

## Initial steps

### IDE

Install Java 17 - from [Adoptium](https://adoptium.net/).

Get Intellij Idea (community of Enterprise) - from [the Intellj site](https://www.jetbrains.com/idea/download/#section=windows).

Enable/install the following plugins:

1. Maven
2. Git
3. Quarkus

### Sources

Sources are located in the https://github.com/jan-peremsky/live-coding-1.git. Clone it and import into the IDE.

- Build the project
- Run unit tests from the IDE
- Run the project using the `com.wag.LiveCodingRunner` class or using maven command `mvn compile quarkus:dev`

## Coding sessions

There are 2 main coding sessions. They both build upon the skeleton project downloaded and initialised in the previous steps.

Each session consists of the main task and some bonus sub-tasks. There are always multiple solutions each bringing in some pros and cons.

You'll try to come up witch your own solution which you'll discuss with us and implement yourself.
Live coding session is not just about coding and Java skills - it's also about ideas, communication, design, patterns, proof ...

### Background

Let's pretend we have a REST API based microservice which processes transaction commands - `TXCommand`s.
Each `TXCommand` has a unique ID, its type and also the amount (we are using `Double` type to make it simpler).
Based on the properties of a particular `TXCommand` instance, our service:

1. stores it into DB (simulated)
2. finds a corresponding `Account` in the DB
3. applies the command to the account - it changes the amount stored on that account
4. stores the changes account
5. nothing more, nothing less - in the nutshell

... but things get more complicated ...

## Session #1 - processing several types of `TXCommand`s one by one

In reality, we have many types of of `TXCommand` or subtypes if you will.
Some examples might be:

- Deposit command - represents the positive money transfer - you are adding to the corresponding account
- Withdrawal command - you are spending money and thus lowering the amount on the corresponding account
- Tax refund command - it's like the deposit command, but money is stored on a different (dedicated) account

To sum up:

- each command type influences possibly a different account of the corresponding client
- each command type influences the related account possibly in a different way (+/-/*/%)
- each command type might (ad will) contain additional than the basic attributes.

### Tasks

1. propose data structures to allow for a growing set of `TXCommand` types each possibly with some extra attributes
2. propose the way of processing of various `TXCommand`s. Remember - the variety of types and their impact on accounts will only grow in the future.

## Session #2 - let's parallelize `TXCommand` batch processing

We are able to process `TXCommand`s one by one as they come via particular HTTP requests. This has some drawbacks:

- we have to ensure the subsequent requests for one particular client (by `clientId`) are processed in order. And as our REST APi is capable of processing multiple concurrent requests at once, we cannot ensure this requirement.
- so we need to firs enqueue the requests and then process them one by one, which is slow - different client requests should be processed in parallel

So let's implement a parallel batch processing...

It should work like this:

1. the level of parallelism is configured via the `LiveCodingConfig#parallelism` property - lets call it `P`
2. we will receive a list of `TXCommand`s (1-50 ordered items) via the REST API
3. we want to split this list into the `P` sub-lists based on the `clientId` property
4. in the sub-lists we need to ensure the correct order
5. we will submit these sub-lists for the execution (not implemented - just a stub for now)

### Tasks

1. implement the splitting part
2. write a unit test to be sure the implementation is correct


