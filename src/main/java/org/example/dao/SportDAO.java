package org.example.dao;

import org.example.entity.SportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportDAO extends JpaRepository<SportEntity, String> {

}
