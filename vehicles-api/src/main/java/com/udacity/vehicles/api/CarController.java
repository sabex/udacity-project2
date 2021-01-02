/* (C)2020-2021 */
package com.udacity.vehicles.api;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.udacity.vehicles.domain.car.Car;
import com.udacity.vehicles.service.CarService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** Implements a REST-based controller for the Vehicles API. */
@RestController
@RequestMapping("/cars")
@ApiResponses(
    value = {
      @ApiResponse(
          code = 400,
          message =
              "This is a bad request, please follow the API documentation for the proper request format."),
      @ApiResponse(
          code = 500,
          message =
              "The server is down. Please make sure that the Vehicles database and underlying services are running.")
    })
class CarController {

  private final CarService carService;
  private final CarResourceAssembler assembler;

  CarController(CarService carService, CarResourceAssembler assembler) {
    this.carService = carService;
    this.assembler = assembler;
  }

  /**
   * Creates a list to store any vehicles.
   *
   * @return list of vehicles
   */
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Vehicle list retrieved successfully")})
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  Resources<Resource<Car>> list() {
    List<Resource<Car>> resources =
        carService.list().stream().map(assembler::toResource).collect(Collectors.toList());
    return new Resources<>(resources, linkTo(methodOn(CarController.class).list()).withSelfRel());
  }

  /**
   * Gets information of a specific car by ID.
   *
   * @param id the id number of the given vehicle
   * @return all information for the requested vehicle
   */
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Vehicle retrieved successfully")})
  @GetMapping("/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  Resource<Car> get(@PathVariable Long id) {
    return assembler.toResource(carService.findById(id));
  }

  /**
   * Posts information to create a new vehicle in the system.
   *
   * @param car A new vehicle to add to the system.
   * @return response that the new vehicle was added to the system
   * @throws URISyntaxException if the request contains invalid fields or syntax
   */
  @ApiResponses(value = {@ApiResponse(code = 201, message = "Vehicle created successfully")})
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  ResponseEntity<?> post(@Valid @RequestBody Car car) throws URISyntaxException {
    Resource<Car> resource = assembler.toResource(carService.save(car));
    return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
  }

  /**
   * Updates the information of a vehicle in the system.
   *
   * @param id The ID number for which to update vehicle information.
   * @param car The updated information about the related vehicle.
   * @return response that the vehicle was updated in the system
   */
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Vehicle updated successfully")})
  @PutMapping("/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  ResponseEntity<?> put(@PathVariable Long id, @Valid @RequestBody Car car) {
    car.setId(id);
    Resource<Car> resource = assembler.toResource(carService.save(car));
    return ResponseEntity.ok(resource);
  }

  /**
   * Removes a vehicle from the system.
   *
   * @param id The ID number of the vehicle to remove.
   * @return response that the related vehicle is no longer in the system
   */
  @ApiResponses(value = {@ApiResponse(code = 204, message = "Vehicle deleted successfully")})
  @DeleteMapping("/{id}")
  @ResponseStatus(code = HttpStatus.NO_CONTENT)
  ResponseEntity<?> delete(@PathVariable Long id) {
    carService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
