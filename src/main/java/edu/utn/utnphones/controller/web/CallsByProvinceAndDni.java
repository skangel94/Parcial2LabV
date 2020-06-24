package edu.utn.utnphones.controller.web;

import edu.utn.utnphones.controller.CallController;
import edu.utn.utnphones.domain.Call;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test/callsProvinceDni")
@Api(value = "Users microservice", description = "Get calls by province where DNI is par or impar")
public class CallsByProvinceAndDni {
    private CallController callController;

    @Autowired
    public CallsByProvinceAndDni(CallController callController) {
        this.callController = callController;
    }

    
    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Calls by province and dni even or odd", notes = "Return list of calls" )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 400, message = "Bad request")
    }
    )
    public ResponseEntity<List<Call>> getCallsByProvinceDni(@RequestParam(value = "province") String province, @RequestParam(value = "parity") String parity) {
        ResponseEntity<List<Call>> responseEntity;
        List<Call> callList;
        if (!province.isEmpty()){
            province = province.replace("%20"," ");
            if (!parity.isEmpty()){
                if (parity.toUpperCase().equals("EVEN") || parity.toUpperCase().equals("ODD")){
                    if (parity.toUpperCase().equals("EVEN")){
                        callList = callController.getCallsByProvinceDniEven(province);
                    }else{
                        callList = callController.getCallsByProvinceDniOdd(province);
                    }
                    if (!callList.isEmpty()) {
                        responseEntity = ResponseEntity.ok().body(callList);
                    } else {
                        responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                    }
                }else{
                    throw new IllegalArgumentException("The parity must be even or odd");
                }
            }else{
                throw new IllegalArgumentException("The parity cannot be empty");
            }

        }else{
            //responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            throw new IllegalArgumentException("The province cannot be empty");
        }
        return responseEntity;
    }
}
