package edu.utn.utnphones.service;

import edu.utn.utnphones.dao.RateDao;
import edu.utn.utnphones.domain.Rate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateService {

    private RateDao rateDao;


    @Autowired
    public RateService(RateDao rateDao) {
        this.rateDao = rateDao;
    }

    public List<Rate> getAll(){
        return rateDao.findAll();
    }


    public Rate add(Rate rate) {
        return rateDao.save(rate);
    }

    public Rate getRate (int idFrom, int idTo){
        return rateDao.getRate(idFrom, idTo);

    }

    public List<Rate> getByIdFrom(int idFrom){
        return rateDao.getByIdFrom(idFrom);

    }
}
