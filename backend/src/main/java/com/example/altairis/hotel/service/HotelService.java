package com.example.altairis.hotel.service;

import com.example.altairis.hotel.dto.HotelResponse;
import com.example.altairis.hotel.model.Hotel;
import com.example.altairis.hotel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public Page<HotelResponse> buscarHoteles(String filtro, Pageable pageable) {
        // buscar las entidades en la Base de Datos.
        Page<Hotel> hoteles = hotelRepository.findByNombreContainingIgnoreCaseOrCiudadContainingIgnoreCase(
                filtro, filtro, pageable
        );

        // convertir cada Hotel en un HotelResponse (DTO)
        return hoteles.map(HotelResponse::new);
    }

    public HotelResponse guardarHotel(Hotel hotel) {
        Hotel guardado = hotelRepository.save(hotel);
        return new HotelResponse(guardado);
    }

    public Hotel actualizar(Long id, Hotel datosNuevos) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel no encontrado"));

        hotel.setNombre(datosNuevos.getNombre());
        hotel.setCiudad(datosNuevos.getCiudad());
        hotel.setDireccion(datosNuevos.getDireccion());
        hotel.setEstrellas(datosNuevos.getEstrellas());

        return hotelRepository.save(hotel);
    }
}