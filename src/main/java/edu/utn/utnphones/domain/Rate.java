package edu.utn.utnphones.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "rates")
@IdClass(value = RateCompositeKey.class)
public class Rate{


    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_from_id")
    private City cityFrom;

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_to_id")
    private City cityTo;

    @Column(name = "rate_value")
    private Float value;

}
