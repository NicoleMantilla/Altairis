package com.example.altairis.hotel.dto;

import com.example.altairis.hotel.model.Hotel;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class HotelResponse {

    private Long id;
    private String nombre;
    private String direccion;
    private String ciudad;
    private Integer estrellas;

    //constructor que mapea Entidad -> DTO
    public HotelResponse(Hotel hotel) {
        this.id = hotel.getId();
        this.nombre = hotel.getNombre();
        this.direccion = hotel.getDireccion();
        this.ciudad = hotel.getCiudad();
        this.estrellas = hotel.getEstrellas();
    }
}