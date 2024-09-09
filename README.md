# Curso RabbitMQ - Microserviço de Análise de Crédito

## Descrição
Projeto desenvolvido durante o curso "Microsserviços com Spring e RabbitMQ" de Matheus Pieropan. 
O objetivo do projeto é utilizar uma arquitetura event-driven para permitir o cadastro de uma proposta de crédito pelo microsserviço de proposta, simular se o cliente será aprovado ou reprovado com base em um sistema de pontuação pelo microsserviço de análise de crédito e retornar o status do cliente para a aplicação de proposta. Durante essas transações, o microsserviço de notificacao será responsável por notificar o usuário via e-mail sobre o status de sua solicitação. 

<p><img src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&amp;logo=spring&amp;logoColor=white" alt="shields"><img src="https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&amp;logo=postgresql&amp;logoColor=white" alt="shields"><img src="https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&amp;logo=docker&amp;logoColor=white" alt="shields"><img src="https://img.shields.io/badge/Rabbitmq-FF6600?style=for-the-badge&amp;logo=rabbitmq&amp;logoColor=white" alt="shields"></p>

Este é o microsserviço responsável por processar a solicitação, observando a fila do RabbitMQ (publicada pelo microsserviço proposta-app), fazendo o cálculo de pontos do usuário de acordo com valores randômicos de forma a simular diferentes cenários, e retornando se a solicitação foi aprovada ou reprovada. Utiliza o Design Pattern Strategy para cálculo dos pontos e faz uso de Exception personalizada para interromper o fluxo caso o cliente se enquadre em situações que o reprovarão automaticamente. Após o processamento, publica um evento no RabbitMQ para duas filas, uma observada pelo microsserviço de proposta-app e outro pelo microsserviço de notificação.  

### Microsserviços do Projeto

- [config-server](https://github.com/pnakano/proposta-config)
- [proposta-app](https://github.com/pnakano/proposta-app)
- [analise-credito](https://github.com/pnakano/proposta-analisecredito)
- [notificacao](https://github.com/pnakano/proposta-notificacao)

### Aplicação Front-End
- [proposta-web](https://github.com/pnakano/proposta-web)

### Conteúdo do curso

- Implementação do ecossistema Spring
- Compreensão de como funciona uma arquitetura de microsserviços
- Entender como funciona comunicação assíncrona
- Utilização de Docker para acesso ao RabbitmQ e Postgres
- Criação de filas/exchange/dlq no rabbitMQ. Visualização no painel administrativo
- Abordagem de alguns conceitos de programação em Java (Utilização de Strategy, criação de exceções, etc)
- <s>Implementação de notificação via SMS utilizando o SNS da AWS</s> (Não implementado)

#### Implementações Adicionais:

- Implementação do Config Server via repositório Git
- Serviço de notificação por e-mail utilizando o RabbitMQ e Spring Email
- Configuração do docker-compose para que a aplicação funcione totalmente em containers
- Aplicação Front-end em Angular para cadastro das propostas

## Requisitos

- [JDK 17](https://www.oracle.com/br/java/technologies/javase/jdk17-archive-downloads.html)
- [Docker](https://hub.docker.com/)
- [config-server](https://github.com/pnakano/proposta-config)
- [proposta-app](https://github.com/pnakano/proposta-app)

## Build

Criação dos container do RabbitMQ

```shell
docker run --name rabbit-mq -d -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

## Run

`com.pamelanakano.proposta_analisecredito.PropostaAnalisecreditoApplication`

## Links

* [UDEMY | Microsserviços com Spring e RabbitMQ + AWS](https://www.udemy.com/course/microsservicos-com-spring-e-rabbitmq-aws/)
