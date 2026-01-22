package com.example.altairis.habitacion.model;

import com.example.altairis.hotel.model.Hotel;
import jakarta.persistence.*;

import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "tipos_habitacion")
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    private String nombre;
    private Integer capacidad;
    private Double precioBase;
}