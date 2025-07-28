package ru.mts.media.platform.umc.dao.postgres.event;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.mts.media.platform.umc.domain.gql.types.Event;
import ru.mts.media.platform.umc.domain.gql.types.Venue;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface EventPgMapper {

    @Mapping(target = "startTime", source = "startTime", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    @Mapping(target = "endTime", source = "endTime", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    EventPgEntity asEntity(Event event);

    @Mapping(target = "venue", ignore = true)
    @Mapping(target = "startTime", source = "startTime", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    @Mapping(target = "endTime", source = "endTime", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    Event asModel(EventPgEntity entity);

    @Mapping(target = "eventId", source = "event.id")
    @Mapping(target = "venueReferenceId", source = "venue.id")
    EventVenueEntity mapToLink(Event event, Venue venue);
}
