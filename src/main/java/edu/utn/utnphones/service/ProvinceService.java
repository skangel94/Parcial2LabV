package edu.utn.utnphones.service;

import edu.utn.utnphones.dao.ProvinceDao;
import edu.utn.utnphones.domain.Province;
import edu.utn.utnphones.exception.ResourcesNotExistException;
import edu.utn.utnphones.exception.UserNotexistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinceService {

    private ProvinceDao provinceDao;

    @Autowired
    public ProvinceService(ProvinceDao provinceDao) {
        this.provinceDao = provinceDao;
    }

    public Province add(Province province){
        return provinceDao.save(province);
    }

    public List<Province> getAll() {
        return provinceDao.findAll();
    }

    public Province getById(int id) throws ResourcesNotExistException {
        return provinceDao.findById(id).orElseThrow(ResourcesNotExistException::new);
    }
}
