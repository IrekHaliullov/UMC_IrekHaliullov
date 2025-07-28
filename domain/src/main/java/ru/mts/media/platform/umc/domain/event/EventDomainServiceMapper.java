package ru.mts.media.platform.umc.domain.event;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.mts.media.platform.umc.domain.gql.types.Event;
import ru.mts.media.platform.umc.domain.gql.types.SaveEventInput;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
interface EventDomainServiceMapper {

    @Mapping(target = "venue", ignore = true)
    Event toEntity(SaveEventInput dto);

}
