package edu.utn.utnphones.service;

import edu.utn.utnphones.dao.InvoiceDao;
import edu.utn.utnphones.domain.Invoice;
import edu.utn.utnphones.exception.ResourcesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    private InvoiceDao invoiceDao;

    @Autowired
    public InvoiceService(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }

    public List<Invoice> getAll(){
        return invoiceDao.findAll();
    }

    public Invoice add(Invoice invoice){
        return invoiceDao.save(invoice);
    }

    public Invoice getById(int id) throws ResourcesNotExistException {
        return invoiceDao.findById(id).orElseThrow(ResourcesNotExistException::new);
    }
}
