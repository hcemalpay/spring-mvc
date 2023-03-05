package com.example.mvc.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "car")
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Column(name = "plate")
    private String plate;

    @Column(name = "power")
    private Integer power;

    @Lob
    @Column(name = "insurancefile")
    private byte[] insurancefile;

    @Column(name = "insurancefilename")
    private String insurancefilename;

    public Car(String plate, Integer power) {
        this.plate = plate;
        this.power = power;
    }

    public Car(Long id, String plate, Integer power) {
        this.id = id;
        this.plate = plate;
        this.power = power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Car car = (Car) o;
        return id != null && Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
