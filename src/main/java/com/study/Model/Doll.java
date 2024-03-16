package com.study.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

@RequiredArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Papusile")
public class Doll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Papusa")
    private Integer id;
    @NaturalId
    @Column(name = "codPapusa")
    @NonNull
    private String cod;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ID_Jucarie")
    @NonNull
    private Toy toy;
    @Column(name = "Material")
    @NonNull
    private String material;
    @Column(name = "Inaltime")
    @NonNull
    private Double height;
}
