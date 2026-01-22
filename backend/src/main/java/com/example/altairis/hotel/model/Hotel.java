package com.example.altairis.hotel.model;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor; // Constructor vacío
import lombok.AllArgsConstructor; // Constructor con todos los argumentos



@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private String ciudad;

    @Column(nullable = false)
    private Integer estrellas;
}
