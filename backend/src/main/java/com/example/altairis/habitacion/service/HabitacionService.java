package com.example.altairis.habitacion.service;

import com.example.altairis.hotel.model.Hotel;
import com.example.altairis.habitacion.model.Habitacion;
import com.example.altairis.hotel.repository.HotelRepository;
import com.example.altairis.habitacion.repository.HabitacionRepository;
import com.example.altairis.inventario.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HabitacionService {

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private InventarioService inventarioService;

    public Habitacion crearHabitacion(Long hotelId, Habitacion habitacion) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("Hotel no encontrado"));

        habitacion.setHotel(hotel);
        Habitacion guardada = habitacionRepository.save(habitacion);

        // datos para el calendario
        inventarioService.generarInventarioInicial(guardada, 10); // 10 es un ejemplo de stock

        return guardada;
    }

    public Habitacion actualizarHabitacion(Long id, Habitacion datosNuevos) {
        Habitacion hab = habitacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));

        hab.setNombre(datosNuevos.getNombre());
        hab.setCapacidad(datosNuevos.getCapacidad());
        hab.setPrecioBase(datosNuevos.getPrecioBase());

        return habitacionRepository.save(hab);
    }

    public List<Habitacion> listarPorHotel(Long hotelId) {
        return habitacionRepository.findByHotelId(hotelId);
    }
}