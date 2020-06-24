package edu.utn.utnphones.dao;

import edu.utn.utnphones.domain.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeDao extends JpaRepository<UserType,Integer> {
}
