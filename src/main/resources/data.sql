/**
 * CREATE Script for init of DB
 */
-- Create 3 cars
insert into car (id, date_created, deleted, license_plate, seat_count, convertible, rating, engine_type, manufacturer) values (1, now(), false, 'KL 07 AS 4444', 3, false, 4.6, 'ELECTRIC', 'Mercedes'); 
insert into car (id, date_created, deleted, license_plate, seat_count, convertible, rating, engine_type, manufacturer) values (2, now(), false, 'KL 26 BC 1123',  3, false, 3.9, 'GAS', 'Mercedes'); 
insert into car (id, date_created, deleted, license_plate, seat_count, convertible, rating, engine_type, manufacturer) values (3, now(), false, 'TN 03 CE 6657',  3, false, 4.1, 'GAS', 'BMW');
insert into car (id, date_created, deleted, license_plate, seat_count, convertible, rating, engine_type, manufacturer) values (4, now(), false, 'MH 03 ZZ 8888',  3, true, 4.3, 'HYBRID', 'BMW');
insert into car (id, date_created, deleted, license_plate, seat_count, convertible, rating, engine_type, manufacturer) values (5, now(), false, 'GA 03 AZ 1212',  3, true, 4.2, 'GAS', 'FIAT');

-- Create 3 OFFLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username) values (1, now(), false, 'OFFLINE',
'driver01pw', 'Driver01');

insert into driver (id, date_created, deleted, online_status, password, username) values (2, now(), false, 'OFFLINE',
'driver02pw', 'driver02');

insert into driver (id, date_created, deleted, online_status, password, username) values (3, now(), false, 'OFFLINE',
'driver03pw', 'driver03');


-- Create 3 ONLINE drivers

insert into driver (id, date_created, deleted, online_status, password, username) values (4, now(), false, 'ONLINE',
'driver04pw', 'driver04');

insert into driver (id, date_created, deleted, online_status, password, username) values (5, now(), false, 'ONLINE',
'driver05pw', 'driver05');

insert into driver (id, date_created, deleted, online_status, password, username) values (6, now(), false, 'ONLINE',
'driver06pw', 'driver06');

-- Create 1 OFFLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username)
values
 (7,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'OFFLINE',
'driver07pw', 'driver07');

-- Create 1 ONLINE driver with coordinate(longitude=9.5&latitude=55.954)

insert into driver (id, coordinate, date_coordinate_updated, date_created, deleted, online_status, password, username, selected_car_id)
values
 (8,
 'aced0005737200226f72672e737072696e676672616d65776f726b2e646174612e67656f2e506f696e7431b9e90ef11a4006020002440001784400017978704023000000000000404bfa1cac083127', now(), now(), false, 'ONLINE',
'driver08pw', 'driver08', 1);