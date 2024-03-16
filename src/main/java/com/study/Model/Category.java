package com.study.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

@RequiredArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity()
@Table(name = "Categorii")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Categorie")
    private Integer id;
    @NaturalId
    @Column(name = "codCategorie")
    @NonNull
    private String cod;
    @Column(name = "Nume_Categorie")
    @NonNull
    private String name;

    @Override
    public String toString() {
        return getCod();
    }
}