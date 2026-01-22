package com.example.altairis.inventario.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter @Setter @AllArgsConstructor
public class InventarioDto {
    private LocalDate fecha;
    private Integer disponibilidad;
    private String nombreHabitacion;
}