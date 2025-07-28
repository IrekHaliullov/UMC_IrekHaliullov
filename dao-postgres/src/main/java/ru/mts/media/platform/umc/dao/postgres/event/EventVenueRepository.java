package ru.mts.media.platform.umc.dao.postgres.event;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mts.media.platform.umc.dao.postgres.common.EventVenuePk;

public interface EventVenueRepository extends JpaRepository<EventVenueEntity, EventVenuePk> {

    List<EventVenueEntity> findByEventId(String eventId);

    List<EventVenueEntity> findByVenueReferenceId(String referenceId);
}
