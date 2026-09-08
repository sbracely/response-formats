# response-formats

A Spring Boot demo showing how to customize response serialization for different media types.

## What this project demonstrates

- Spring MVC controller returning a simple `Order` entity
- default JSON response including `null` properties
- custom JSON response excluding `null` properties
- XML response with a root element
- custom media type `application/vnd.sbracely.wrapped+json` using a wrapped root object
- content negotiation based on `Accept` header

## Project structure

- `src/main/java/io/github/sbracely/responseformats/controller/OrderController.java`
- `src/main/java/io/github/sbracely/responseformats/model/Order.java`
- `src/main/java/io/github/sbracely/responseformats/media/MediaTypes.java`
- `src/main/java/io/github/sbracely/responseformats/config/HttpMessageConvertersConfig.java`
- `src/test/java/io/github/sbracely/responseformats/controller/OrderControllerTest.java`

## Endpoint

### GET `/order`

Returns an `Order` with id `1`.

#### JSON

Accept: `application/json`

```json
{
  "id": "1",
  "customerId": null
}
```

#### XML

Accept: `application/xml`

```xml
<OrderValue xmlns="OrderNamespace">
    <customerId xmlns="" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:nil="true"/>
    <id xmlns="">1</id>
</OrderValue>
```

#### Wrapped JSON

Accept: `application/vnd.sbracely.wrapped+json`

```json
{
  "OrderValue": {
    "id": "1",
    "customerId": null
  }
}
```

#### JSON without null properties

Accept: `application/vnd.sbracely.non-null+json`

```json
{
  "id": "1"
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
curl -H "Accept: application/vnd.sbracely.wrapped+json" http://localhost:8080/order
curl -H "Accept: application/vnd.sbracely.non-null+json" http://localhost:8080/order
```

## Test

```bash
./mvnw test
```
