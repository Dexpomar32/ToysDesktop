package com.study.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.sql.Date;

@RequiredArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Vanzari")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Vanzare")
    private Integer id;
    @NaturalId
    @Column(name = "codVanzare")
    @NonNull
    private String cod;
    @ManyToOne
    @JoinColumn(name = "ID_Jucarie")
    @NonNull
    private Toy toy;
    @Column(name = "Data_Vanzare")
    @NonNull
    private Date saleDate;
    @Column(name = "Cantitate_Vanduta")
    @NonNull
    private Integer quantity;
}
