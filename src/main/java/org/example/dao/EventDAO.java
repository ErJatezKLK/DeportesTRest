package org.example.dao;

import org.example.entity.EventSportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EventDAO extends JpaRepository<EventSportEntity, String> {
    List<EventSportEntity> findEventBySportId(Integer sportId);
}
