package edu.utn.utnphones.dao;

import edu.utn.utnphones.domain.LineType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineTypeDao extends JpaRepository<LineType,Integer> {
}
