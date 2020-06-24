package edu.utn.utnphones.controller;

import edu.utn.utnphones.controller.web.CallsByDuration;
import edu.utn.utnphones.domain.Call;
import edu.utn.utnphones.exception.IllegalDurationException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CallsByDurationTest {
    CallController callController;
    CallsByDuration callsByDuration;
    Call call;

    @Before
    public void setUp(){
        callController = mock(CallController.class);
        call = mock(Call.class);
        callsByDuration = new CallsByDuration(callController);
    }

    @Test
    public void getCallsByDurationOk(){
        List<Call> callList = new ArrayList<>();
        callList.add(call);
        when(callController.getCallsByDuration(60)).thenReturn(callList);

        ResponseEntity<List<Call>> responseEntity = callsByDuration.getCallsByDuration(60);

        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertEquals(callList,responseEntity.getBody());
    }

    @Test
    public void getCallsByDurationNoContent(){
        List<Call> callList = Collections.emptyList();
        when(callController.getCallsByDuration(1)).thenReturn(callList);

        ResponseEntity<List<Call>> responseEntity = callsByDuration.getCallsByDuration(1);
        assertEquals(HttpStatus.NO_CONTENT,responseEntity.getStatusCode());
    }

    @Test(expected = IllegalDurationException.class)
    public void getCallsByDurationException(){
        when(callController.getCallsByDuration(-1)).thenThrow(new IllegalDurationException("The duration must be greater than 0"));
        ResponseEntity<List<Call>> responseEntity = callsByDuration.getCallsByDuration(-1);
        assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());
    }
}
