package fr.iocean.species.model;
import fr.iocean.species.enums.Sex;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "color")
    private String color;
    @Column(name = "name")
    private String name;

    @Column(name = "sex")
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @ManyToOne
    @JoinColumn(name = "species_id")
    private Species species;

    @ManyToMany
    @JoinTable(
            name = "person_animals",
            joinColumns = @JoinColumn(name = "animals_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> owner;

    public Animal(String couleur, String nom, Sex sexe, Species species) {
        this.color = couleur;
        this.name = nom;
        this.sex = sexe;
        this.species = species;
    }

    public Animal(){}

    public List<Person> getOwner() {
        return owner;
    }

    public void setOwner(List<Person> owner) {
        this.owner = owner;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }
}