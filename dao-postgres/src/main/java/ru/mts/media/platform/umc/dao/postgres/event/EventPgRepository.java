package ru.mts.media.platform.umc.dao.postgres.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface EventPgRepository extends JpaRepository<EventPgEntity, String> {

}
