package com.example.altairis.reserva.dto;

import lombok.*;

import java.time.LocalDate;

@Setter @Getter
public class ReservaRequest {
    private Long habitacionId;
    private String nombreCliente;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
