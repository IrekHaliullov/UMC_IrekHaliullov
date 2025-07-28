package ru.mts.media.platform.umc.domain.event;

import java.util.List;

import ru.mts.media.platform.umc.domain.gql.types.Event;
import ru.mts.media.platform.umc.domain.gql.types.Venue;

public interface EventSot {
    void saveEvent(Event event, Venue venue);

    List<Event> getEventsByVenueReferenceId(String venueReferenceId);

    List<Event> getAllEvents();
}
