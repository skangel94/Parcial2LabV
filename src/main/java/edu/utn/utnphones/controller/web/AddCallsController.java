package edu.utn.utnphones.controller.web;

import edu.utn.utnphones.controller.CallController;
import edu.utn.utnphones.dto.CallDto;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/calls")
public class AddCallsController {

    private CallController callController;

    @Autowired
    public AddCallsController(CallController callController) {
        this.callController = callController;
    }

    @PostMapping
    public ResponseEntity addCall(@RequestHeader("User") @NotNull String user, @RequestHeader("Pass")  @NotNull String pass, @RequestBody @Valid CallDto callDto){
        ResponseEntity responseEntity;
        if(user.matches("infra") &&  pass.matches("1234")){
           callController.addCall(callDto.getLineFrom(),callDto.getLineTo(),callDto.getSeg(),callDto.getDate());
           responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(callDto);
        }else{
            responseEntity = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return responseEntity;
    }

}
