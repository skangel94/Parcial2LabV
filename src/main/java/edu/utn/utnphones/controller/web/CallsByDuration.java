package edu.utn.utnphones.controller.web;

import edu.utn.utnphones.controller.CallController;
import edu.utn.utnphones.domain.Call;
import edu.utn.utnphones.exception.IllegalDurationException;
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
@RequestMapping("/test/duration")
@Api(value = "Users microservice", description = "Example")
public class CallsByDuration{
    private CallController callController;

    @Autowired
    public CallsByDuration(CallController callController) {
        this.callController = callController;
    }

    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Llamadas por duración", notes = "Retorna una lista de llamadas" )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    }
    )
    public ResponseEntity<List<Call>> getCallsByDuration(@RequestParam(value = "duration") int duration){
        ResponseEntity<List<Call>> responseEntity;
        List<Call> callList;
        if(duration > 0){
            callList = callController.getCallsByDuration(duration);
            if (!callList.isEmpty()) {
                responseEntity = ResponseEntity.ok().body(callList);
            } else {
                responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }else{
            throw new IllegalDurationException("The duration must be greater than 0");
        }
        return responseEntity;
    }
}
