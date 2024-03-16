package com.study.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "Jucarii")
@RequiredArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@NamedStoredProcedureQuery(
        name = "Exclude",
        procedureName = "Exclude",
        resultClasses = Toy.class
)
@NamedStoredProcedureQuery(
        name = "InsertMoldova",
        procedureName = "InsertMoldova",
        resultClasses = Toy.class
)
public class Toy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Jucarie")
    private Integer id;
    @NaturalId
    @Column(name = "codJucarii")
    @NonNull
    private String cod;
    @Column(name = "Nume_Jucarie")
    @NonNull
    private String name;
    @Column(name = "Pret")
    @NonNull
    private Double price;
    @Column(name = "Cantitate")
    @NonNull
    private Integer quantity;
    @Column(name = "Tara_Productie")
    @NonNull
    private String country;
    @Column(name = "Varsta_Minima")
    @NonNull
    private Integer minimAge;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ID_Categorie")
    @NonNull
    private Category category;

    @Override
    public String toString() {
        return getCod();
    }
}