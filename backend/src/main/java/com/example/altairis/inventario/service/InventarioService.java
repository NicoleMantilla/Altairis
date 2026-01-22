package com.example.altairis.inventario.service;

import com.example.altairis.inventario.model.Inventario;
import com.example.altairis.habitacion.model.Habitacion;
import com.example.altairis.inventario.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    // generar inventario inicial de 30 dias para una nueva habitación
    public void generarInventarioInicial(Habitacion habitacion, int cantidadInicial) {
        List<Inventario> registros = new ArrayList<>();
        LocalDate hoy = LocalDate.now();

        for (int i = 0; i < 30; i++) {
            Inventario inv = new Inventario();
            inv.setHabitacion(habitacion);
            inv.setFecha(hoy.plusDays(i));
            inv.setDisponibilidad(cantidadInicial); // Ej: 10 habitaciones disponibles cada día
            registros.add(inv);
        }
        inventarioRepository.saveAll(registros);
    }

    // miramos disponibilidad para un hotel en un rango de fechas.
    public List<Inventario> consultarDisponibilidad(Long hotelId, LocalDate inicio, LocalDate fin) {
        return inventarioRepository.findByHabitacionIdAndFechaBetween(hotelId, inicio, fin);
    }

    public void restarDisponibilidad(Long habitacionId, LocalDate fecha) {
        // busco el registro de inventario para esa habitación y esa fecha
        Inventario inv = inventarioRepository.findByHabitacionIdAndFecha(habitacionId, fecha)
                .orElseThrow(() -> new RuntimeException("No hay inventario para la fecha: " + fecha));

        if (inv.getDisponibilidad() > 0) {
            inv.setDisponibilidad(inv.getDisponibilidad() - 1);
            inventarioRepository.save(inv);
        } else {
            throw new RuntimeException("Sin disponibilidad");
        }
    }
}