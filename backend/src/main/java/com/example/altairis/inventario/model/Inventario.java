package com.example.altairis.inventario.controller;
import com.example.altairis.habitacion.model.Habitacion;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor; // Constructor vacío
import lombok.AllArgsConstructor;


@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "inventario")
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tipo_habitacion_id")
    private Habitacion tipoHabitacion;

    private LocalDate fecha;
    private Integer disponibilidad; // Cantidad de habitaciones físicas libres ese día
}