package edu.utn.utnphones.controller;

import edu.utn.utnphones.controller.web.CheckCallsController;
import edu.utn.utnphones.domain.*;
import edu.utn.utnphones.exception.UserNotexistException;
import edu.utn.utnphones.session.SessionManager;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyByte;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CheckCallsControllerTest {

    CheckCallsController checkCallsController;
    CallController callController;
    SessionManager sessionManager;
    Call call;

    @Before
    public void setUp() {
        callController = mock(CallController.class);
        sessionManager = mock(SessionManager.class);
        call = mock(Call.class);
        checkCallsController = new CheckCallsController(callController,sessionManager);
    }

    @Test
    public void getCallsByDateOk() throws UserNotexistException, ParseException {
        User user = User.builder().userId(1).build();
        user.setUserType(UserType.builder().type("Client").build());
        List<Call> callList = new ArrayList<>();
        Date from = new SimpleDateFormat("yyyy/MM/dd").parse("2020/05/28");
        Date to = new SimpleDateFormat("yyyy/MM/dd").parse("2020/06/30");
        when(sessionManager.getCurrentUser("token")).thenReturn(user);
        callList.add(call);
        when(callController.getCallsByDate(from,to,1)).thenReturn(callList);

        ResponseEntity<List<Call>> responseEntity = checkCallsController.getCallsByDate("token","2020/05/28","2020/06/30");

        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertEquals(callList,responseEntity.getBody());
    }

    @Test
    public void getCallsByDateNoContent() throws UserNotexistException, ParseException {
        User user = User.builder().userId(1).build();
        user.setUserType(UserType.builder().type("Client").build());
        List<Call> callList = new ArrayList<>();
        Date from = new Date();
        Date to = new Date(2) ;
        when(sessionManager.getCurrentUser("token")).thenReturn(user);

        callList.clear();
        when(callController.getCallsByDate(from,to,1)).thenReturn(callList);

        ResponseEntity<List<Call>> responseEntity = checkCallsController.getCallsByDate("token","2020/05/28","2020/06/30");

        assertEquals(HttpStatus.NO_CONTENT,responseEntity.getStatusCode());

    }

    @Test
    public void getCallsByDateNull() throws UserNotexistException, ParseException {
        User user = User.builder().userId(1).build();
        user.setUserType(UserType.builder().type("Client").build());
        List<Call> callList = new ArrayList<>();
        when(sessionManager.getCurrentUser("token")).thenReturn(user);
        when(callController.getCallsByDate(null,null,1)).thenReturn(callList);

        ResponseEntity<List<Call>> responseEntity = checkCallsController.getCallsByDate("token",null,null);
        assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());

    }

    @Test
    public void getCallsByDateForbidden() throws UserNotexistException, ParseException {
        User user = User.builder().userId(1).build();
        user.setUserType(UserType.builder().type("Employee").build());
        when(sessionManager.getCurrentUser("token")).thenReturn(user);

        ResponseEntity<List<Call>> responseEntity = checkCallsController.getCallsByDate("token",null,null);
        assertEquals(HttpStatus.FORBIDDEN,responseEntity.getStatusCode());

    }

}
