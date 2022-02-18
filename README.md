# iv-qikserve
Application used to calculate the total cost of a basket.

## Run the application.

I added two script.

Start -> to start the application and it's running into the port 8080.
Stop -> kill the application.	

## Stacks

- Java 11
- Spring
- Maven
- SQL
- JUnit
- Docker

## Requirements
- Maven 3.6+
- Java 8
- Docker 
- SQL -> Please create schema "qikserve" to run the application.

#End-points.

#Product

## URL -> http://localhost:8080/product
#### Method: GET.
#### Get all products.

## URL -> http://localhost:8080/product/{id}
#### Method: GET.
#### Get product by id.

#Basket

## URL -> http://localhost:8080/basket
#### Method: POST.
#### Create basket.

## URL -> http://localhost:8080/basket/add
#### Method: POST.
#### Add item to a basket.

## URL -> http://localhost:8080/basket/itens/{id}
#### Method: GET.
#### Get all itens.

## URL -> http://localhost:8080/basket/id
#### Method: GET.
#### Get basket by id.             

## URL -> http://localhost:8080/checkout/{id}
#### Method: GET.
#### Get checkout by id.             

## URL -> http://localhost:8080/order-details/{id}
#### Method: GET.
#### Get price order details by id.             

## 1. How long did you spend on the test?
	- Four days just working at night.

## 2. What would you add if you had more time?
	- Rotina programada para deletar cesta antigas para o banco não crescer muito com cestas inutilizaveis.
	-  Eu Colocaria os produtos em no cache como "Redis".
	- Subiria a aplicação na AWS.
	- Eu adicionaria terraform.
	- Eu adicionaria pipeline.

## 3. How would you improve the product APIs that you had to consume?
	- Host the API in a web application for better communication and possible problems between front-end and back-end.
	- I would add images for the frontend to consume and place on the screen.
	- I would add suppliers.
	- I would add branding to the product.

## 4. What did you find most difficult?
	- Creation of product logic for promotions.

## 5. How did you find the overall experience, any feedback for us?
	- I found the challenge very interesting, I love new challenges and this one was really cool. As a feedback, I found the calculation of the FLAT_PERCENT discount a little confusing.