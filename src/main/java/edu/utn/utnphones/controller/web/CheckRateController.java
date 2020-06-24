package edu.utn.utnphones.controller.web;

import edu.utn.utnphones.controller.CityController;
import edu.utn.utnphones.controller.RateController;
import edu.utn.utnphones.domain.Rate;
import edu.utn.utnphones.domain.User;
import edu.utn.utnphones.exception.UserNotexistException;
import edu.utn.utnphones.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/rates")
public class CheckRateController {

     private RateController rateController;
     private SessionManager sessionManager;
     private CityController cityController;

    @Autowired
    public CheckRateController(RateController rateController, SessionManager sessionManager, CityController cityController) {
        this.rateController = rateController;
        this.sessionManager = sessionManager;
        this.cityController = cityController;
    }

    @GetMapping
    public ResponseEntity<List<Rate>> getAllRates(@RequestHeader("Authorization") String sessionToken) throws UserNotexistException {

        User currentUser = getCurrentUser(sessionToken);
        ResponseEntity<List<Rate>> responseEntity;

        if ( currentUser.getUserType().getType().matches("Employee")) {
            List<Rate> rateList;
            rateList = rateController.getAllRates();
            if(!rateList.isEmpty()){
                responseEntity = ResponseEntity.ok().body(rateList);
            }else{
                responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return responseEntity;
    }

    @GetMapping("/{idFrom}")
    public ResponseEntity<List<Rate>> getByIdFrom(@RequestHeader("Authorization") String sessionToken, @PathVariable int idFrom) throws UserNotexistException {

        User currentUser = getCurrentUser(sessionToken);
        ResponseEntity<List<Rate>> responseEntity;

        if ( currentUser.getUserType().getType().matches("Employee")) {
            List<Rate> rateList;
            rateList =  rateController.getByIdFrom(idFrom);
            if(!rateList.isEmpty()){
                responseEntity = ResponseEntity.ok().body(rateList);
            }else{
                responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return responseEntity;
    }

    @GetMapping("/{idFrom}/{idTo}")
    public ResponseEntity<Rate> getRate(@RequestHeader("Authorization") String sessionToken, @PathVariable int idFrom, @PathVariable int idTo) throws UserNotexistException {

        User currentUser = getCurrentUser(sessionToken);
        ResponseEntity<Rate> responseEntity;

        if ( currentUser.getUserType().getType().matches("Employee")) {
            Rate rate = rateController.getRate(idFrom,idTo);
            if((rate != null)){
                responseEntity = ResponseEntity.ok().body(rate);
            }else{
                responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } else {
            responseEntity = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return responseEntity;

    }

    private User getCurrentUser(String sessionToken) throws UserNotexistException {
        return Optional.ofNullable(sessionManager.getCurrentUser(sessionToken)).orElseThrow(UserNotexistException::new);
    }

}
