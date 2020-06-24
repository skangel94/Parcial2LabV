package edu.utn.utnphones.controller;


import edu.utn.utnphones.domain.UserType;
import edu.utn.utnphones.exception.ResourcesNotExistException;
import edu.utn.utnphones.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserTypeController {
    private UserTypeService userTypeService;

    @Autowired
    public UserTypeController(UserTypeService userTypeService) {
        this.userTypeService = userTypeService;
    }

    public List<UserType> getAllUserType(){
        return userTypeService.getAll();
    }

    public UserType getById(int id) throws ResourcesNotExistException {
        return userTypeService.getById(id);
    }

    public UserType addUserType(UserType userType){
        return userTypeService.add(userType);
    }
}
