CREATE TABLE routes (
    id               bigserial PRIMARY KEY,
    number           varchar(10)  NOT NULL,
    name             varchar(200) NOT NULL,
    type             varchar(20)  NOT NULL,
    interval_minutes integer      NOT NULL,
    first_departure  time         NOT NULL,
    last_departure   time         NOT NULL,
    CONSTRAINT uq_routes_number UNIQUE (number),
    CONSTRAINT ck_routes_type CHECK (type IN ('BUS', 'TROLLEYBUS', 'TRAM')),
    CONSTRAINT ck_routes_interval CHECK (interval_minutes > 0)
);

CREATE TABLE vehicles (
    id           bigserial PRIMARY KEY,
    board_number varchar(20) NOT NULL,
    type         varchar(20) NOT NULL,
    capacity     integer     NOT NULL,
    route_id     bigint,
    CONSTRAINT uq_vehicles_board_number UNIQUE (board_number),
    CONSTRAINT ck_vehicles_type CHECK (type IN ('BUS', 'TROLLEYBUS', 'TRAM')),
    CONSTRAINT ck_vehicles_capacity CHECK (capacity > 0),
    CONSTRAINT fk_vehicles_route FOREIGN KEY (route_id) REFERENCES routes (id)
);

CREATE TABLE schedule_entries (
    id             bigserial PRIMARY KEY,
    route_id       bigint NOT NULL,
    departure_time time   NOT NULL,
    CONSTRAINT fk_schedule_route FOREIGN KEY (route_id) REFERENCES routes (id) ON DELETE CASCADE,
    CONSTRAINT uq_schedule_route_time UNIQUE (route_id, departure_time)
);

CREATE INDEX idx_vehicles_route_id ON vehicles (route_id);
CREATE INDEX idx_schedule_route_id ON schedule_entries (route_id);
