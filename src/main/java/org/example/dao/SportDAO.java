package org.example.dao;

import org.example.entity.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SportDAO extends JpaRepository<Sport, String> {

}
