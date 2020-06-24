package edu.utn.utnphones.service;


import edu.utn.utnphones.dao.PhoneLineDao;
import edu.utn.utnphones.domain.PhoneLine;
import edu.utn.utnphones.exception.ResourcesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneLineService {

    private PhoneLineDao linePhoneDao;

    @Autowired
    public PhoneLineService(PhoneLineDao linePhoneDao) {
        this.linePhoneDao = linePhoneDao;
    }

    public List<PhoneLine> getAll(){
        return linePhoneDao.findAll();
    }

    public PhoneLine getById(int id) throws ResourcesNotExistException {
        return linePhoneDao.findById(id).orElseThrow(ResourcesNotExistException::new);
    }

    public PhoneLine add(PhoneLine linePhone){
       return linePhoneDao.save(linePhone);
    }
}
