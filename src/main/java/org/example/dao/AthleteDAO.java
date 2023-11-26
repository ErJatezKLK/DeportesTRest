package org.example.dao;

import org.example.entity.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AthleteDAO extends JpaRepository<Athlete, String> {
    List<Athlete> findBySport_Id(Integer sportId);
}
