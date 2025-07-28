package ru.mts.media.platform.umc.dao.postgres.common;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;

@Data
public class EventVenuePk implements Serializable {
    private String eventId;
    private String venueReferenceId;
}
