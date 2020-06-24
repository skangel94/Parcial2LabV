package edu.utn.utnphones.service;

import edu.utn.utnphones.dao.UserTypeDao;
import edu.utn.utnphones.domain.User;
import edu.utn.utnphones.domain.UserType;
import edu.utn.utnphones.exception.ResourcesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTypeService {

    private UserTypeDao userTypeDao;

    @Autowired
    public UserTypeService(UserTypeDao userTypeDao) {
        this.userTypeDao = userTypeDao;
    }

    public List<UserType> getAll(){
        return userTypeDao.findAll();
    }

    public UserType getById(int id) throws ResourcesNotExistException {
        return userTypeDao.findById(id).orElseThrow(ResourcesNotExistException::new);
    }

    public UserType add(UserType userType){
        return userTypeDao.save(userType);
    }
}
