package com.example.altairis.inventario.model;
import com.example.altairis.habitacion.model.Habitacion;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Table(name = "inventario")
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "habitacion_id")
    private Habitacion habitacion;

    private LocalDate fecha;
    private Integer disponibilidad; // cantidad de habitaciones físicas libres ese día
}