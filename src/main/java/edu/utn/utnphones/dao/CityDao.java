package edu.utn.utnphones.dao;

import edu.utn.utnphones.domain.City;
import edu.utn.utnphones.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CityDao extends JpaRepository<City,Integer> {

    @Query(value = "SELECT * FROM cities WHERE city_id = ?1", nativeQuery = true)
    City getById(int cityId);
}
