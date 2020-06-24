package edu.utn.utnphones.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class CallDto {

    String lineFrom;
    String lineTo;
    Date date;
    Integer seg;
}
