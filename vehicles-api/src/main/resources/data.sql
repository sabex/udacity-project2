insert into manufacturer (code, name) values (1, 'me');
insert into manufacturer (code, name) values (2, 'an-other');


insert into car (id, condition, created_at, body,engine, external_color, fuel_type, mileage, model, model_year, number_of_doors, production_year, lat, lon, manufacturer_code)
values(1, 'NEW', CURRENT_TIMESTAMP(), 'coupe', 'battery', 'red', 'gas', 153, 'x', 1998, 4, 1998, 20.0, 30.0, 1);
insert into car (id, condition, created_at, body,engine, external_color, fuel_type, mileage, model, model_year, number_of_doors, production_year, lat, lon, manufacturer_code)
values(2, 'NEW', CURRENT_TIMESTAMP(), 'coupe', 'battery', 'blue', 'gas', 153, 'xplorer', 2004, 4, 1998, 20.0, 30.0, 2);
insert into car (id, condition, created_at, body,engine, external_color, fuel_type, mileage, model, model_year, number_of_doors, production_year, lat, lon, manufacturer_code)
values(3, 'NEW', CURRENT_TIMESTAMP(), 'coupe', 'battery', 'red', 'electric', 153, 'A3', 2013, 4, 1998, 20.0, 30.0, 1);

