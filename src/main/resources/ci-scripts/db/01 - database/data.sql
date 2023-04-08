-- Francois

insert into laptime (driver, track, car, game, laptime, laptimeString, carNumber, date) value
    ('François', 'karting.capmalo', 'kart', 'IRL', 51.367, '51.367', '5', '2023-05-04T20:50:00.000');
insert into laptime (driver, track, car, game, laptime, laptimeString, carNumber, date) value
    ('François', 'karting.capmalo', 'kart', 'IRL', 48.299, '48.299', '5', (select max(date) + INTERVAL 51.367 SECOND from laptime l2 where l2.driver='François' group by l2.driver));
insert into laptime (driver, track, car, game, laptime, laptimeString, carNumber, date) value
    ('François', 'karting.capmalo', 'kart', 'IRL', 47.803, '47.803', '5',  (select max(date) + INTERVAL 48.299 SECOND from laptime l2 where l2.driver='François' group by l2.driver));
insert into laptime (driver, track, car, game, laptime, laptimeString, carNumber, date) value
    ('François', 'karting.capmalo', 'kart', 'IRL', 49.143, '49.143', '5', (select max(date) + INTERVAL 47.803 SECOND from laptime l2 where l2.driver='François' group by l2.driver));
insert into laptime (driver, track, car, game, laptime, laptimeString, carNumber, date) value
    ('François', 'karting.capmalo', 'kart', 'IRL', 47.331, '47.331', '5', (select max(date) + INTERVAL 49.143 SECOND from laptime l2 where l2.driver='François' group by l2.driver));
insert into laptime (driver, track, car, game, laptime, laptimeString, carNumber, date) value
    ('François', 'karting.capmalo', 'kart', 'IRL', 47.165, '47.165', '5', (select max(date) + INTERVAL 47.331 SECOND from laptime l2 where l2.driver='François' group by l2.driver));
insert into laptime (driver, track, car, game, laptime, laptimeString, carNumber, date) value
    ('François', 'karting.capmalo', 'kart', 'IRL', 47.170, '47.170', '5', (select max(date) + INTERVAL 47.165 SECOND from laptime l2 where l2.driver='François' group by l2.driver));
insert into laptime (driver, track, car, game, laptime, laptimeString, carNumber, date) value
    ('François', 'karting.capmalo', 'kart', 'IRL', 46.852, '46.852', '5', (select max(date) + INTERVAL 47.170 SECOND from laptime l2 where l2.driver='François' group by l2.driver));
insert into laptime (driver, track, car, game, laptime, laptimeString, carNumber, date) value
    ('François', 'karting.capmalo', 'kart', 'IRL', 46.659, '46.659', '5', (select max(date) + INTERVAL 46.852 SECOND from laptime l2 where l2.driver='François' group by l2.driver));
insert into laptime (driver, track, car, game, laptime, laptimeString, carNumber, date) value
    ('François', 'karting.capmalo', 'kart', 'IRL', 47.689, '47.689', '5', (select max(date) + INTERVAL 46.659 SECOND from laptime l2 where l2.driver='François' group by l2.driver));
insert into laptime (driver, track, car, game, laptime, laptimeString, carNumber, date) value
    ('François', 'karting.capmalo', 'kart', 'IRL', 47.287, '47.287', '5', (select max(date) + INTERVAL 47.689 SECOND from laptime l2 where l2.driver='François' group by l2.driver));
insert into laptime (driver, track, car, game, laptime, laptimeString, carNumber, date) value
    ('François', 'karting.capmalo', 'kart', 'IRL', 47.234, '47.234', '5', (select max(date) + INTERVAL 47.287 SECOND from laptime l2 where l2.driver='François' group by l2.driver));

-- Jeremy
insert into laptime (driver, track, car, game, laptime, laptimeString, carNumber, date) value
    ('Jeremy', 'karting.capmalo', 'kart', 'IRL', 69.718, '69.718', '16', '2023-05-04T20:50:00.000');
insert into laptime (driver, track, car, game, laptime, laptimeString, carNumber, date) value
    ('Jeremy', 'karting.capmalo', 'kart', 'IRL', 58.858, '58.858', '16', (select max(date) + INTERVAL 69.718 SECOND from laptime l2 where l2.driver='Jeremy' group by l2.driver));
insert into laptime (driver, track, car, game, laptime, laptimeString, carNumber, date) value
    ('Jeremy', 'karting.capmalo', 'kart', 'IRL', 56.703, '56.703', '16', (select max(date) + INTERVAL 58.858 SECOND from laptime l2 where l2.driver='Jeremy' group by l2.driver));
insert into laptime (driver, track, car, game, laptime, laptimeString, carNumber, date) value
    ('Jeremy', 'karting.capmalo', 'kart', 'IRL', 59.266, '59.266', '16', (select max(date) + INTERVAL 56.703 SECOND from laptime l2 where l2.driver='Jeremy' group by l2.driver));
insert into laptime (driver, track, car, game, laptime, laptimeString, carNumber, date) value
    ('Jeremy', 'karting.capmalo', 'kart', 'IRL', 53.523, '53.523', '16', (select max(date) + INTERVAL 59.266 SECOND from laptime l2 where l2.driver='Jeremy' group by l2.driver));
insert into laptime (driver, track, car, game, laptime, laptimeString, carNumber, date) value
    ('Jeremy', 'karting.capmalo', 'kart', 'IRL', 54.206, '54.206', '16', (select max(date) + INTERVAL 53.523 SECOND from laptime l2 where l2.driver='Jeremy' group by l2.driver));
insert into laptime (driver, track, car, game, laptime, laptimeString, carNumber, date) value
    ('Jeremy', 'karting.capmalo', 'kart', 'IRL', 54.787, '54.787', '16', (select max(date) + INTERVAL 54.206 SECOND from laptime l2 where l2.driver='Jeremy' group by l2.driver));
insert into laptime (driver, track, car, game, laptime, laptimeString, carNumber, date) value
    ('Jeremy', 'karting.capmalo', 'kart', 'IRL', 58.117, '58.117', '16', (select max(date) + INTERVAL 54.787 SECOND from laptime l2 where l2.driver='Jeremy' group by l2.driver));
insert into laptime (driver, track, car, game, laptime, laptimeString, carNumber, date) value
    ('Jeremy', 'karting.capmalo', 'kart', 'IRL', 51.324, '51.324', '16', (select max(date) + INTERVAL 58.117 SECOND from laptime l2 where l2.driver='Jeremy' group by l2.driver));
insert into laptime (driver, track, car, game, laptime, laptimeString, carNumber, date) value
    ('Jeremy', 'karting.capmalo', 'kart', 'IRL', 51.431, '51.431', '16', (select max(date) + INTERVAL 51.324 SECOND from laptime l2 where l2.driver='Jeremy' group by l2.driver));

-- Benjamin
insert into laptime (driver, track, car, game, laptime, laptimeString, carNumber, date) value
    ('Benjamin', 'karting.capmalo', 'kart', 'IRL', 83.589, '83.589', '10', '2023-05-04T20:50:00.000');
insert into laptime (driver, track, car, game, laptime, laptimeString, carNumber, date) value
    ('Benjamin', 'karting.capmalo', 'kart', 'IRL', 79.512, '79.512', '10', (select max(date) + INTERVAL 83.589 SECOND from laptime l2 where l2.driver='Benjamin' group by l2.driver));
insert into laptime (driver, track, car, game, laptime, laptimeString, carNumber, date) value
    ('Benjamin', 'karting.capmalo', 'kart', 'IRL', 78.262, '78.262', '10', (select max(date) + INTERVAL 79.512 SECOND from laptime l2 where l2.driver='Benjamin' group by l2.driver));
insert into laptime (driver, track, car, game, laptime, laptimeString, carNumber, date) value
    ('Benjamin', 'karting.capmalo', 'kart', 'IRL', 74.241, '74.241', '10', (select max(date) + INTERVAL 78.262 SECOND from laptime l2 where l2.driver='Benjamin' group by l2.driver));
insert into laptime (driver, track, car, game, laptime, laptimeString, carNumber, date) value
    ('Benjamin', 'karting.capmalo', 'kart', 'IRL', 69.532, '69.532', '10', (select max(date) + INTERVAL 74.241 SECOND from laptime l2 where l2.driver='Benjamin' group by l2.driver));
insert into laptime (driver, track, car, game, laptime, laptimeString, carNumber, date) value
    ('Benjamin', 'karting.capmalo', 'kart', 'IRL', 63.322, '63.322', '10', (select max(date) + INTERVAL 69.532 SECOND from laptime l2 where l2.driver='Benjamin' group by l2.driver));
insert into laptime (driver, track, car, game, laptime, laptimeString, carNumber, date) value
    ('Benjamin', 'karting.capmalo', 'kart', 'IRL', 61.044, '61.044', '10', (select max(date) + INTERVAL 63.322 SECOND from laptime l2 where l2.driver='Benjamin' group by l2.driver))
