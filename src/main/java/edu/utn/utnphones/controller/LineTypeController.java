package edu.utn.utnphones.controller;

import edu.utn.utnphones.domain.LineType;
import edu.utn.utnphones.exception.ResourcesNotExistException;
import edu.utn.utnphones.service.LineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class LineTypeController {

    private LineTypeService lineTypeService;

    @Autowired
    public LineTypeController(LineTypeService lineTypeService) {
        this.lineTypeService = lineTypeService;
    }

    public List<LineType> getAllLineTypes(){
        return lineTypeService.getAll();
    }

    public LineType getById(int id) throws ResourcesNotExistException {
        return lineTypeService.getById(id);
    }

    public LineType addLineType(LineType lineType){
        return lineTypeService.add(lineType);
    }
}
