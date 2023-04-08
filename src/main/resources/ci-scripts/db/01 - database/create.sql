CREATE DATABASE 'laptimes';
-- CREATE DATABASE 'laptimes_dev';

CREATE USER IF NOT EXISTS ''@localhost IDENTIFIED BY '';
GRANT ALL PRIVILEGES ON laptimes.* TO ''@localhost IDENTIFIED BY '';
-- GRANT ALL PRIVILEGES ON laptimes_dev.* TO ''@localhost IDENTIFIED BY '';

-- CREATE TABLES

use laptimes;
-- use laptimes_dev;

create table if not exists laptime
(
    id int primary key not null auto_increment,
    driver varchar(100) not null,
    track varchar(100) not null,
    car varchar(100) not null,
    game varchar(100) not null,
    laptime float not null,
    laptimeString varchar(20),
    carNumber varchar(20),
    date datetime,
    constraint unique unique_driver_track_car_game_time(driver, track, car, game, laptime)
);
