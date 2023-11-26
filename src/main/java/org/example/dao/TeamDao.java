package org.example.dao;

import org.example.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TeamDao extends JpaRepository<Team, String> {


    List<Team> findTeamBySportId(Integer sportId);
}
