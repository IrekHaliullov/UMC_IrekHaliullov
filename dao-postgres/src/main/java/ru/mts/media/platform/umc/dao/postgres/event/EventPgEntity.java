package ru.mts.media.platform.umc.dao.postgres.event;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;
import ru.mts.media.platform.umc.dao.postgres.common.FullExternalIdPk;

@Entity
@Data
@Table(name = "Event")
public class EventPgEntity {
    @Id
    private String id;

    private String name;

    private String startTime;

    private String endTime;
}
