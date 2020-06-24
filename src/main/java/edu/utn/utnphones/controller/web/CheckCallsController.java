package edu.utn.utnphones.controller.web;

import edu.utn.utnphones.controller.CallController;
import edu.utn.utnphones.domain.Call;
import edu.utn.utnphones.domain.User;
import edu.utn.utnphones.exception.UserNotexistException;
import edu.utn.utnphones.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/calls")
public class CheckCallsController {

    private CallController callController;
    private SessionManager sessionManager;

    @Autowired
    public CheckCallsController(CallController callController, SessionManager sessionManager) {
        this.callController = callController;
        this.sessionManager = sessionManager;
    }

    @GetMapping
    public ResponseEntity<List<Call>> getCallsByDate(@RequestHeader ("Authorization") String sessionToken, @RequestParam(value = "from") String from, @RequestParam(value = "to") String to) throws UserNotexistException, ParseException {
        ResponseEntity<List<Call>> responseEntity = null;
        User currentUser = getCurrentUser(sessionToken);
        if ( currentUser.getUserType().getType().matches("Client")) {
            if ((from != null) && (to != null)) {
                Date dateFrom = new SimpleDateFormat("yyyy/MM/dd").parse(from);
                Date dateTo = new SimpleDateFormat("yyyy/MM/dd").parse(to);
                List<Call> callList = callController.getCallsByDate(dateFrom, dateTo, currentUser.getUserId());
                if (!callList.isEmpty()) {
                    responseEntity = ResponseEntity.ok().body(callList);
                } else {
                    responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                }
            } else {
                responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }else{
            responseEntity = ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return responseEntity;
    }

    private User getCurrentUser(String sessionToken) throws UserNotexistException {
        return Optional.ofNullable(sessionManager.getCurrentUser(sessionToken)).orElseThrow(UserNotexistException::new);
    }

}
