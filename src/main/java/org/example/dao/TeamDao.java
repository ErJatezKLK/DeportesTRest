package org.example.dao;

import org.example.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TeamDao extends JpaRepository<TeamEntity, String> {


    List<TeamEntity> findTeamBySportId(Integer sportId);

    TeamEntity getById(Integer teamdId);

    TeamEntity getTeamWithMembersById(Integer id);
}
