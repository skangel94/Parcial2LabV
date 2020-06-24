package edu.utn.utnphones.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "line_types")
public class LineType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "line_type_id")
    private Integer id;

    @Column(name = "line_type")
    private String lineType;

    @OneToMany(mappedBy = "lineType")
    @JsonBackReference(value = "linetype")
    private List<PhoneLine> linePhones;

}
