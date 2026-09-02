# wrap-response

A Spring Boot demo showing how to customize response serialization for different media types.

## What this project demonstrates

- Spring MVC controller returning a simple `Order` entity
- JSON response without wrapper
- XML response with a root element
- custom media type `application/vnd.example.wrapped+json` using a wrapped root object
- content negotiation based on `Accept` header

## Project structure

- `src/main/java/com/example/wrap/response/controller/OrderController.java`
- `src/main/java/com/example/wrap/response/entity/Order.java`
- `src/main/java/com/example/wrap/response/config/WrappedJsonConfig.java`
- `src/test/java/com/example/wrap/response/controller/OrderControllerTest.java`

## Endpoint

### GET `/order`

Returns an `Order` with id `1`.

#### JSON

Accept: `application/json`

```json
{
  "id": "1"
}
```

#### XML

Accept: `application/xml`

```xml
<OrderValue xmlns="OrderNamespace">
    <id xmlns="">1</id>
</OrderValue>
```

#### Wrapped JSON

Accept: `application/vnd.example.wrapped+json`

```json
{
  "OrderValue": {
    "id": "1"
  }
}
```

## Run locally

```bash
./mvnw spring-boot:run
```

Then call:

```bash
curl -H "Accept: application/json" http://localhost:8080/order
curl -H "Accept: application/xml" http://localhost:8080/order
curl -H "Accept: application/vnd.example.wrapped+json" http://localhost:8080/order
```

## Test

```bash
./mvnw test
```
