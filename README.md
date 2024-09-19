# VR Mini Autorizador

## Funcionalidades

* Cartao
  * Criar
  * Consultar
* Transacao 
  * Transferir/Pagar

## Tenologias utilizadas
* Spring
  * Spring boot 
  * Spring data 
  * Mapstructs
  * Lombok 
  * Swagger
  * Test Container
  * Docker 
  * Docker Compose

## Executar 
    * build da aplicaćão 
		- mvn clean install
	* build da imagem Docker 
		- docker compose build
		- ou **docker-compose build 
	* subir a aplicaćão 
		- docker compose up
		- ou docker-compose up

## API Local
* http://localhost:8080/cartoes/
* http://localhost:8080/transacoes/

## Swagger Local
* http://localhost:8080/swagger-ui.html