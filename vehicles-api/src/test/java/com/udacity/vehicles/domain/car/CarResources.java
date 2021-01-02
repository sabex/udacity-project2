/* (C)2021 */
package com.udacity.vehicles.domain.car;

import lombok.Data;

/** hacked container class because of issues parsing hateoas Resources class
 * even though org.springframework.hateoas.Resources defines the body as "content" its getting sent as "_embedded" so jackson
 * cannot deserialize.
 * Built this wrapper class to bypass issue (see also CarCollection class)
 * actual sample response below
 * {"_embedded":{"carList":[{"id":1,"createdAt":null,"modifiedAt":null,"condition":"USED","details":{"body":"sedan","model":"Impala","manufacturer":{"code":101,"name":"Chevrolet"},"numberOfDoors":4,"fuelType":"Gasoline","engine":"3.6L V6","mileage":32280,"modelYear":2018,"productionYear":2018,"externalColor":"white"},"location":{"lat":40.73061,"lon":-73.935242,"address":null,"city":null,"state":null,"zip":null},"price":null,"_links":{"self":{"href":"http://localhost/cars/1"},"cars":{"href":"http://localhost/cars"}}}]},"_links":{"self":{"href":"http://localhost/cars"}}}
 */
@Data
public class CarResources {
  private CarCollection _embedded;
}
