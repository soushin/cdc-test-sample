# Consumer Driven Contract testing sample

This project contains demo of using ['Consumer Driven Contract testing'](https://martinfowler.com/articles/consumerDrivenContracts.html) to verify the interactions between microservices, and test is based on [Pact](https://github.com/realestate-com-au/pact) framework. Also challenged to test the between microservices of different language.

## Microservice, consumer side
Consumer side is made by golang, then Pact framework using ['SEEK-Jobs/pact-go'](https://github.com/seek-jobs/pact-go) library.
If you how to use or haw to make pact file, please see 'SEEK-Jobs/pact-go' page.

## Microservice, provider side
Provider side is made by kotlin and spring-boot-framework, then Pact framework using ['DiUS/pact-jvm'](https://github.com/DiUS/pact-jvm/tree/master/pact-jvm-provider-junit) library.
If you how to use or haw to make pact file, please see 'DiUS/pact-jvm' page.

## Additional Reports
[this post](http://naruto-io.hatenablog.com/) reported how to build this project, sorry that supported only japanese.
I hope to this project helps to your cdc-test.
