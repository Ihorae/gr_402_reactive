INSERT INTO routes (number, name, type, interval_minutes, first_departure, last_departure) VALUES
    ('18',  'Вокзал - Лівобережна', 'BUS',        10, '06:00', '22:00'),
    ('Т-5', 'Центр - Оболонь',      'TROLLEYBUS', 12, '06:30', '23:00'),
    ('12',  'Подол - Борщагівка',   'TRAM',       15, '05:30', '23:30');

INSERT INTO vehicles (board_number, type, capacity, route_id) VALUES
    ('AA-101', 'BUS',        80,  1),
    ('AA-102', 'BUS',        80,  1),
    ('TR-201', 'TROLLEYBUS', 100, 2),
    ('TM-301', 'TRAM',       150, 3),
    ('TM-302', 'TRAM',       150, 3);

INSERT INTO schedule_entries (route_id, departure_time) VALUES
    (1, '06:00'), (1, '06:10'), (1, '06:20'),
    (2, '06:30'), (2, '06:42'), (2, '06:54'),
    (3, '05:30'), (3, '05:45'), (3, '06:00');
