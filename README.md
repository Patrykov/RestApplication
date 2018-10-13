# RestApplication

# Technologies used

* Java/J2EE
* Spring framework
* H2

# Functionality

* In the method in with obtain single record HATEOS added.
  Url redirects to the list of all resources
* Rest Endpoints

| Http Verb     | URL           |	Description                           | Status Codes |
| ------------- | ------------- | ------------------------------------- | ------------ |
| `GET`         | http://localhost:8080/rest/list | Obtains a list of all existing orders |  `200 OK`     |
| `GET`         | http://localhost:8080/rest/list/{id} | Obtains the order corresponding to the supplied order ID |  `200 OK` if order exists    `BaseNotFoundException` if order does not exist |
| `POST`        | http://localhost:8080/rest/list | Creates a new order based on the payload contained in the request body |  `201 Created` if order successfully created |
| `POST`        | http://localhost:8080/rest/list/search | Find order that contains searches characters |  `200 OK` |
| `PUT`         | http://localhost:8080/rest/list/{id} | Updated an existing order with the data contained in the request body |  `204 No Content` if order succesfully updated  `BaseNotFoundException` if order does not exist |
| `DELETE`      | http://localhost:8080/rest/list/{id} | Deletes an existing order that corresponds to the supplied order ID |   `204 No Content` if order succesfully deleted   `BaseNotFoundException` if order does not exist |
