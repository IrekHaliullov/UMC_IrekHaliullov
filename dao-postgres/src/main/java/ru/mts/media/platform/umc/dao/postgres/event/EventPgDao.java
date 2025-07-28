package ru.mts.media.platform.umc.dao.postgres.event;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.mts.media.platform.umc.dao.postgres.venue.VenuePgMapper;
import ru.mts.media.platform.umc.dao.postgres.venue.VenuePgRepository;
import ru.mts.media.platform.umc.domain.event.EventSot;
import ru.mts.media.platform.umc.domain.gql.types.Event;
import ru.mts.media.platform.umc.domain.gql.types.Venue;

@Component
@RequiredArgsConstructor
class EventPgDao implements EventSot {

    private final EventPgRepository eventRepository;
    private final EventVenueRepository eventVenueRepository;
    private final VenuePgRepository venuePgRepository;
    private final EventPgMapper eventMapper;
    private final VenuePgMapper venueMapper;

    @Override
    public void saveEvent(Event event, Venue venue) {
        eventRepository.save(eventMapper.asEntity(event));
        eventVenueRepository.save(eventMapper.mapToLink(event, venue));
    }

    @Override
    public List<Event> getAllEvents() {
        List<EventPgEntity> events = eventRepository.findAll();

        Map<String, List<String>> eventIdToVenueRefs = eventVenueRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        EventVenueEntity::getEventId,
                        Collectors.mapping(EventVenueEntity::getVenueReferenceId, Collectors.toList())
                ));

        Map<String, Venue> venuesByRefId = venuePgRepository.findAll().stream()
                .map(venueMapper::asModel)
                .collect(Collectors.toMap(Venue::getId, Function.identity()));

        return events.stream()
                .map(e -> {
                    Event model = eventMapper.asModel(e);
                    List<Venue> linkedVenues = Optional.ofNullable(eventIdToVenueRefs.get(e.getId()))
                            .orElse(List.of())
                            .stream()
                            .map(venuesByRefId::get)
                            .filter(Objects::nonNull)
                            .toList();

                    model.setVenue(linkedVenues);
                    return model;
                })
                .toList();
    }

    @Override
    public List<Event> getEventsByVenueReferenceId(String venueReferenceId) {

        List<EventVenueEntity> links = eventVenueRepository.findByVenueReferenceId(venueReferenceId);
        if (links.isEmpty()) return List.of();

        Set<String> eventIds = links.stream()
                .map(EventVenueEntity::getEventId)
                .collect(Collectors.toSet());

        List<EventPgEntity> events = eventRepository.findAllById(eventIds);

        Venue venue = Optional.ofNullable(venuePgRepository.findByReferenceId(venueReferenceId))
                .map(venueMapper::asModel)
                .orElse(null);
        if (venue == null) return List.of();

        return events.stream()
                .map(eventPgEntity -> {
                    Event event = eventMapper.asModel(eventPgEntity);
                    event.setVenue(List.of(venue));
                    return event;
                })
                .toList();
    }
}
