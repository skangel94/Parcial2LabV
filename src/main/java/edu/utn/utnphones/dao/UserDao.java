package edu.utn.utnphones.dao;

import edu.utn.utnphones.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {

    //Query nativa JPQL
    @Query(value = "SELECT * FROM users u WHERE u.username = ?1 and u.userpass = ?2", nativeQuery = true)
    User getByUsername(String username, String password);
}
