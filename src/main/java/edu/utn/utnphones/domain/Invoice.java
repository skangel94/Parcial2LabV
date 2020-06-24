package edu.utn.utnphones.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private int id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "invoice_line_id")
    private PhoneLine lineId;

    @NotNull
    @Column(name = "invoice_quantity_calls")
    private int quantityCalls;

    @NotNull
    @Column(name = "invoice_cost_price")
    private float costPrice;

    @NotNull
    @Column(name = "invoice_total_price")
    private float totalPrice;

    @NotNull
    @Column(name = "invoice_date")
    private Date date;

    @NotNull
    @Column(name = "invoice_due_date")
    private Date dueDate;

    @OneToMany(mappedBy = "invoice")
    @JsonBackReference(value = "invoice")
    private List<Call> calls;

}
