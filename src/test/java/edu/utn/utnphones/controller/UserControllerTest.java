package edu.utn.utnphones.controller;


import edu.utn.utnphones.domain.City;
import edu.utn.utnphones.domain.User;
import edu.utn.utnphones.domain.UserType;
import edu.utn.utnphones.exception.UserNotexistException;
import edu.utn.utnphones.exception.ValidationException;
import edu.utn.utnphones.service.UserService;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


public class UserControllerTest {

    UserController userController;
    UserService service;


    @Before
    public void setUp() {
        service = mock(UserService.class);
        userController = new UserController(service);
    }

    @Test
    public void testLoginOk() throws UserNotexistException, ValidationException {
        User loggedUser = new User(1, "nme", "lastname", "12345678", "username", "password", City.builder().cityId(1).build(), UserType.builder().Id(1).build(),null);
        //Cuando llame al mock service.login devuelvo el logged user
        when(service.login("user", "pwd")).thenReturn(loggedUser);
        User returnedUser = userController.login("user", "pwd");

        //Hacemos los assert
        assertEquals(loggedUser.getUserId(), returnedUser.getUserId());
        assertEquals(loggedUser.getUsername(), returnedUser.getUsername());
        verify(service, times(1)).login("user", "pwd");
    }

    @Test(expected = UserNotexistException.class)
    public void testLoginUserNotFound() throws UserNotexistException, ValidationException {
        when(service.login("user", "pwd")).thenThrow(new UserNotexistException());
        userController.login("user", "pwd");
    }

    @Test(expected = ValidationException.class)
    public void testLoginInvalidData() throws UserNotexistException, ValidationException{
        when(service.login(null, "pwd")).thenThrow(new ValidationException("username and password must have a value"));
        userController.login(null, "pwd");
    }

    @Test
    public void testGetUserByIdOk() throws UserNotexistException {
        User user = new User(1, "name", "lastname", "12345678", "username", "password", City.builder().cityId(1).build(), UserType.builder().Id(1).build(),null);
        when(service.getUser(1)).thenReturn(user);
        userController.getUserById(1);
    }

    @Test(expected = UserNotexistException.class)
    public void testGetUserByIdNotExists() throws UserNotexistException {
        when(service.getUser(1)).thenThrow(new UserNotexistException());
        userController.getUserById(1);
    }

    @Test
    public void testRemoveUserOk() throws UserNotexistException {
        User userToRemove = new User(1, "nme", "lastname", "12345678", "username", "password", City.builder().cityId(1).build(), UserType.builder().Id(1).build(),null);
        doNothing().when(service).removeUser(userToRemove);
        userController.removeUser(userToRemove);
        verify(service, times(1)).removeUser(userToRemove);
    }
}
