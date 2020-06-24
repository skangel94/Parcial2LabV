package edu.utn.utnphones.service;

import edu.utn.utnphones.dao.UserDao;
import edu.utn.utnphones.domain.User;
import edu.utn.utnphones.exception.UserAlreadyExistsException;
import edu.utn.utnphones.exception.UserNotexistException;
import edu.utn.utnphones.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User login(String username, String password) throws UserNotexistException, ValidationException {
        User us = null;
        if ((username != null) && (password != null)) {

             us = userDao.getByUsername(username, password);
            if (us == null){
                throw new UserNotexistException();
            }
        } else {
            throw new ValidationException("username and password must have a value");
        }
        return us;
    }

    public User createUser(User user) throws UserAlreadyExistsException {
        Optional<User> us = userDao.findById(user.getUserId()); //TODO cambiar la busqueda
        if (us == null) {
            return userDao.save(user);
        }else {
            throw new UserAlreadyExistsException();
        }
    }

    public void removeUser(User user) throws UserNotexistException {
        User us = getUser(user.getUserId());
        if (us != null){
            userDao.delete(user);
        }else {
            throw new UserNotexistException();
        }
    }

    public User updateUser(User user) throws UserNotexistException {
        User us = userDao.save( user);
        if (us != null) {
            return us;
        } else {
            throw new UserNotexistException();
        }
    }

    public User getUser(Integer userId) throws UserNotexistException {
       return userDao.findById(userId).orElseThrow(UserNotexistException::new);
    }

    public List<User> getAll() {
        return userDao.findAll();
    }
}
