package edu.utn.utnphones.controller;

import edu.utn.utnphones.domain.Call;

import edu.utn.utnphones.dto.CallDto;
import edu.utn.utnphones.exception.ResourcesNotExistException;
import edu.utn.utnphones.service.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import javax.print.DocFlavor;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
public class CallController {
    private CallService callService;

    @Autowired
    public CallController(CallService callService) {
        this.callService = callService;
    }

    public List<Call> getAllCalls(){
        return callService.getAll();
    }

    public void addCall(String lineFrom, String lineTo, int seg , Date dateTime){
        callService.add(lineFrom,lineTo,seg,dateTime);
    }

    public Call getById(int id) throws ResourcesNotExistException {
        return callService.getById(id);
    }

    public List<Call> getCallsByDate(Date dateFrom, Date dateTo, int userId){
        return callService.getCallsByDate(dateFrom,dateTo,userId);
    }

    public List<Call> getCallsByDuration(int duration){
        return callService.getCallsByDuration(duration);
    }

    public List<Call> getCallsByProvinceDniEven(String province){
        return callService.getCallsByProvinceDniEven(province);
    }

    public List<Call> getCallsByProvinceDniOdd(String province){
        return callService.getCallsByProvinceDniOdd(province);
    }
}
