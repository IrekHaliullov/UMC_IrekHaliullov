package ru.mts.media.platform.umc.api.gql.event;

import java.util.List;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import lombok.RequiredArgsConstructor;
import ru.mts.media.platform.umc.domain.event.EventSot;
import ru.mts.media.platform.umc.domain.gql.types.Event;

@DgsComponent
@RequiredArgsConstructor
public class EventDgsQuery {

    private final EventSot eventSot;

    @DgsQuery
    public List<Event> events() {
        return eventSot.getAllEvents();
    }
}
