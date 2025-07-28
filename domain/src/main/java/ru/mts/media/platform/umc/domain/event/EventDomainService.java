package ru.mts.media.platform.umc.domain.event;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.mts.media.platform.umc.domain.gql.types.Event;
import ru.mts.media.platform.umc.domain.gql.types.SaveEventInput;
import ru.mts.media.platform.umc.domain.gql.types.Venue;
import ru.mts.media.platform.umc.domain.venue.VenueSot;

@Service
@RequiredArgsConstructor
public class EventDomainService {
    private final ApplicationEventPublisher eventPublisher;
    private final EventSot eventSot;
    private final VenueSot venueSot;
    private final EventDomainServiceMapper mapper;

    public EventSave save(SaveEventInput input) {
        Event event = mapper.toEntity(input);
        event.setId(java.util.UUID.randomUUID().toString());

        Venue venue = venueSot.getVenueByReferenceId(input.getVenueReferenceId())
                .orElseThrow();

        eventSot.saveEvent(event, venue);
        event.setVenue(List.of(venue));

        var eventWithVenue = new EventSave(event);
        eventPublisher.publishEvent(eventWithVenue);

        return eventWithVenue;
    }
}
