package edu.utn.utnphones.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private int cityId;

    @NotNull
    @Column(name = "city_name")
    private String cityName;

    @NotNull
    @Column(name = "city_prefix")
    private String cityPrefix;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_province_id")
    private Province province;

    @OneToMany(mappedBy = "city")
    @JsonBackReference(value = "city")
    private List<User> users;


    @OneToMany(mappedBy = "cityTo")
    @JsonBackReference(value = "cityTo")
    private List<Rate> ratesTo;

    @OneToMany(mappedBy = "cityFrom")
    @JsonBackReference(value = "cityFrom")
    private List<Rate> ratesFrom;

}
