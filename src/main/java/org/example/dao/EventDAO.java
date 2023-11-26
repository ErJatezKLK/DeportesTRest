package org.example.dao;

import org.example.entity.EventSport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EventDAO extends JpaRepository<EventSport, String> {
    List<EventSport> findEventBySportId(Integer sportId);
}
