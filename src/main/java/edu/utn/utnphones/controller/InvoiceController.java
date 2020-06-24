package edu.utn.utnphones.controller;

import edu.utn.utnphones.domain.Invoice;
import edu.utn.utnphones.exception.ResourcesNotExistException;
import edu.utn.utnphones.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class InvoiceController {
    private InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    public List<Invoice> getAllInvoice(){
        return invoiceService.getAll();
    }

    public Invoice getById(int id) throws ResourcesNotExistException {
        return invoiceService.getById(id);
    }

    public Invoice addInvoice(Invoice invoice){
        return invoiceService.add(invoice);
    }

}
