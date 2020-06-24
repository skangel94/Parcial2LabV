package edu.utn.utnphones.service;


import edu.utn.utnphones.dao.UserDao;
import edu.utn.utnphones.domain.City;
import edu.utn.utnphones.domain.User;
import edu.utn.utnphones.domain.UserType;
import edu.utn.utnphones.exception.UserNotexistException;
import edu.utn.utnphones.exception.ValidationException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


public class UserServiceTest {

    UserService service;

    @Mock
    UserDao dao;

    @Before
    public void setUp() {
            initMocks(this);
            service = new UserService(dao);
    }

    @Test
    public void testLoginOk() throws UserNotexistException, ValidationException {
        User loggedUser = new User(1, "nme", "lastname", "12345678", "username", "password", City.builder().cityId(1).build(), UserType.builder().Id(1).build(),null);
        when(dao.getByUsername("user","pwd")).thenReturn(loggedUser);
        User returnedUser = service.login("user","pwd");
        assertEquals(loggedUser.getUserId(), returnedUser.getUserId());
        assertEquals(loggedUser.getUsername(), returnedUser.getUsername());
        verify(dao, times(1)).getByUsername("user","pwd");
    }

    @Test(expected = UserNotexistException.class)
    public void testLoginUserNotFound() throws UserNotexistException, ValidationException {
        when(dao.getByUsername("user","pwd")).thenReturn(null);
        service.login("user", "pwd");
    }

    @Test(expected = ValidationException.class)
    public void testLoginInvalidData() throws UserNotexistException, ValidationException {
        when(dao.getByUsername(null,"pwd")).thenReturn(null);
        service.login(null, "pwd");
    }

    @Test
    public void testGetUserOK() throws UserNotexistException {
        User user = new User(1, "nme", "lastname", "12345678", "username", "password", City.builder().cityId(1).build(), UserType.builder().Id(1).build(),null);
        when(dao.findById(1)).thenReturn(Optional.of(user));
        service.getUser(1);
    }

    @Test(expected = UserNotexistException.class)
    public void testGetUserNotExists() throws UserNotexistException {
        User user = null;
        when(dao.findById(1)).thenReturn(Optional.ofNullable(user));
        service.getUser(1);
    }

}
