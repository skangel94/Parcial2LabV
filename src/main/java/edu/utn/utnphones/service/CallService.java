package edu.utn.utnphones.service;

import edu.utn.utnphones.dao.CallDao;
import edu.utn.utnphones.domain.Call;
import edu.utn.utnphones.dto.CallDto;
import edu.utn.utnphones.exception.ResourcesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class CallService {
    private CallDao callDao;

    @Autowired
    public CallService(CallDao callDao){this.callDao = callDao;}

    public List<Call> getAll(){
        return callDao.findAll();
    }

    public void add(String lineFrom, String lineTo, int seg , Date dateTime) {
         callDao.addCall(lineFrom,lineTo,seg,dateTime);
    }

    public Call getById(int id) throws ResourcesNotExistException {
        return callDao.findById(id).orElseThrow(ResourcesNotExistException::new);
    }

    public List<Call> getCallsByDate(Date dateFrom, Date dateTo, int userId){
        return callDao.getCallsByDate(dateFrom,dateTo,userId);
    }

    public List<Call> getCallsByDuration(int duration){
        return  callDao.getCallsByDuration(duration);
    }

    public List<Call> getCallsByProvinceDniEven(String province){
        return callDao.getCallsByProvinceDniEven(province);
    }

    public List<Call> getCallsByProvinceDniOdd(String province){
        return callDao.getCallsByProvinceDniOdd(province);
    }
}
