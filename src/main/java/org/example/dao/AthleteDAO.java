package org.example.dao;

import org.example.entity.AthleteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AthleteDAO extends JpaRepository<AthleteEntity, String> {
    List<AthleteEntity> findBySportId(Integer sportId);

    List<AthleteEntity> findByTeamId(Integer teamId);


}
