# Consumer Driven Contract testing sample

This project contains demo of using ['Consumer Driven Contract testing'](https://martinfowler.com/articles/consumerDrivenContracts.html) to verify the interactions between microservices, and test is based on [Pact](https://github.com/realestate-com-au/pact) framework. Also challenged to test the between microservices of different language.

## Microservice, consumer side
Consumer side is made by golang, then Pact framework using ['SEEK-Jobs/pact-go'](https://github.com/seek-jobs/pact-go) library.
If you how to use or haw to make pact file, have to see 'SEEK-Jobs/pact-go' page.

## Microservice, provider side
Provider side is made by kotlin and spring-boot-framework, then Pact framework using ['DiUS/pact-jvm'](https://github.com/DiUS/pact-jvm/tree/master/pact-jvm-provider-junit) library.
If you how to use or haw to make pact file, have to see 'DiUS/pact-jvm' page.

## Pact Broker
Also, include ['Pact Broker'](https://github.com/bethesque/pact_broker) that builds up ['Pact Broker Container'](https://hub.docker.com/r/dius/pact_broker/).
You can set up using following steps.

requirements
* ['dind'](https://hub.docker.com/_/docker/)
* ['direnv'](https://github.com/direnv/direnv)

**1:setup**
```
sh ./setup.sh <MACKEREL_API_KEY>
```
then setup a key into `./pact-broker-host/docker-compose.yml`

**2:build up host(using dind)**
```
cd (path-to-'cdc-test-sample')
docker-compose up -d
```

**3:build up pact_broker**
```
cd (path-to-'cdc-test-sample')
cd ./pact-broker-host/
docker-compose up -d
```
then you will be able to access 'http://localhost:8080/ui/relationships'.

## Additional Reports
[This post](http://naruto-io.hatenablog.com/entry/2017/01/28/215229) reported how to build this project, sorry that supported only japanese.
I hope to this project helps to your cdc-test.
