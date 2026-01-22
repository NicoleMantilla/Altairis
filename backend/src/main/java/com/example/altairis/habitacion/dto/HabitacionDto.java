package com.example.altairis.habitacion.dto;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HabitacionDto {
    private Long id;
    private Long hotelId;
    private String nombre;
    private Integer capacidad;
    private Double precio;
}