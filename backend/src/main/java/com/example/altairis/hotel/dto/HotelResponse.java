package com.example.altairis.hotel.dto;

import com.example.altairis.hotel.model.Hoteles;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HotelesResponse {

    private Long id;
    private String nombre;
    private String categoria; // Lo devolvemos como String para evitar líos con el Enum en el JSON
    private String logoUrl;
    private String descripcion;

    // DATOS SEGUROS DEL ENTRENADOR (Solo lo necesario)
    private Long entrenadorId;
    private String nombreEntrenador;

    // Constructor que mapea de Entidad -> DTO
    public HotelesResponse(Hoteles hoteles) {
        this.id = hoteles.getId();
        this.nombre = hoteles.getNombre();
        this.logoUrl = hoteles.getLogoUrl();
        this.descripcion = hoteles.getDescripcion();

        // Convertimos el Enum a String
        if (hoteles.getCategoria() != null) {
            this.categoria = hoteles.getCategoria().toString();
        }

        // Mapeo SEGURO del Entrenador (Evita NullPointerException si no hay entrenador)
        if (hoteles.getEntrenador() != null) {
            this.entrenadorId = hoteles.getEntrenador().getId();

            // getNombre() y getApellidos() vienen heredados de Usuario
            this.nombreEntrenador = hoteles.getEntrenador().getNombre() + " " + hoteles.getEntrenador().getApellidos();
        }
    }
}