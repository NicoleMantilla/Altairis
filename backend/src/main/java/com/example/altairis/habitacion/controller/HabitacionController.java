package com.example.altairis.habitacion.controller;

import com.example.altairis.habitacion.model.Habitacion;
import com.example.altairis.habitacion.service.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/habitaciones")

public class HabitacionController {

    @Autowired
    private HabitacionService tipoHabitacionService;

    // GET habitaciones de un hotel
    @GetMapping("/hotel/{hotelId}")
    public List<Habitacion> getHabitaciones(@PathVariable Long hotelId) {
        return tipoHabitacionService.listarPorHotel(hotelId);
    }

    // POST nueva habitación a un hotel
    @PostMapping("/hotel/{hotelId}")
    public Habitacion saveHabitacion(@PathVariable Long hotelId, @RequestBody Habitacion tipo) {
        return tipoHabitacionService.crearHabitacion(hotelId, tipo);
    }

    // PUT modificar habitación
    @PutMapping("/{id}")
    public ResponseEntity<Habitacion> actualizarHabitacion(@PathVariable Long id, @RequestBody Habitacion habitacion) {
        return ResponseEntity.ok(tipoHabitacionService.actualizarHabitacion(id, habitacion));
    }
}