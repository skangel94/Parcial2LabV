package edu.utn.utnphones.controller;

import edu.utn.utnphones.controller.web.CallsByProvinceAndDni;
import edu.utn.utnphones.domain.Call;
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

public class CallsByProvinceAndDniTest {
    private CallController callController;
    private CallsByProvinceAndDni callsByProvinceAndDni;
    private Call call;

    @Before
    public void setUp(){
        callController = mock(CallController.class);
        call = mock(Call.class);
        callsByProvinceAndDni = new CallsByProvinceAndDni(callController);
    }

    @Test
    public void testCallsByProvinceDniOkEven(){
        List<Call> callList = new ArrayList<>();
        callList.add(call);
        when(callController.getCallsByProvinceDniEven("Buenos Aires")).thenReturn(callList);

        ResponseEntity<List<Call>> responseEntity = callsByProvinceAndDni.getCallsByProvinceDni("Buenos Aires","Even");

        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertEquals(callList,responseEntity.getBody());
    }

    @Test
    public void testCallsByProvinceDniOkOdd(){
        List<Call> callList = new ArrayList<>();
        callList.add(call);
        when(callController.getCallsByProvinceDniOdd("Buenos Aires")).thenReturn(callList);

        ResponseEntity<List<Call>> responseEntity = callsByProvinceAndDni.getCallsByProvinceDni("Buenos Aires","Odd");

        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertEquals(callList,responseEntity.getBody());
    }

    @Test
    public void testCallsByProvinceDniNoContent(){
        List<Call> callList = Collections.emptyList();
        when(callController.getCallsByProvinceDniEven("Buenos Aires")).thenReturn(callList);

        ResponseEntity<List<Call>> responseEntity = callsByProvinceAndDni.getCallsByProvinceDni("Buenos Aires","Even");
        assertEquals(HttpStatus.NO_CONTENT,responseEntity.getStatusCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCallsByProvinceDniProvinceEmpty(){
        when(callController.getCallsByProvinceDniEven("")).thenThrow(new IllegalArgumentException("The province cannot be empty"));
        ResponseEntity<List<Call>> responseEntity = callsByProvinceAndDni.getCallsByProvinceDni("","even");
        assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCallsByProvinceDniParityEmpty(){
        when(callController.getCallsByProvinceDniEven("Buenos Aires")).thenThrow(new IllegalArgumentException("The parity cannot be empty"));
        ResponseEntity<List<Call>> responseEntity = callsByProvinceAndDni.getCallsByProvinceDni("Buenos Aires","");
        assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCallsByProvinceDniParityBad(){
        when(callController.getCallsByProvinceDniEven("Buenos Aires")).thenThrow(new IllegalArgumentException("The parity must be even or odd"));
        ResponseEntity<List<Call>> responseEntity = callsByProvinceAndDni.getCallsByProvinceDni("Buenos Aires","bad");
        assertEquals(HttpStatus.BAD_REQUEST,responseEntity.getStatusCode());
    }
}
