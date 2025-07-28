package ru.mts.media.platform.umc.dao.postgres.event;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;
import ru.mts.media.platform.umc.dao.postgres.common.EventVenuePk;

@Entity
@Table(name = "event_venue")
@IdClass(EventVenuePk.class)
@Data
public class EventVenueEntity {

    @Id
    private String eventId;

    @Id
    private String venueReferenceId;
}
