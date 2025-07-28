CREATE TABLE event
(
    id           VARCHAR(255) NOT NULL,
    name         VARCHAR(255) NOT NULL,
    start_time   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    end_time     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE event_venue
(
    event_id           VARCHAR(255) NOT NULL,
    venue_reference_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (event_id, venue_reference_id),
    CONSTRAINT fk_event_venue_event FOREIGN KEY (event_id) REFERENCES event (id) ON DELETE CASCADE,
    CONSTRAINT fk_event_venue_venue FOREIGN KEY (venue_reference_id) REFERENCES venue (reference_id) ON DELETE CASCADE
);