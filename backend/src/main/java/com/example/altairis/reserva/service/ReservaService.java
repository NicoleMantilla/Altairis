package com.example.altairis.reserva.service;

import com.example.altairis.habitacion.model.Habitacion;
import com.example.altairis.inventario.model.Inventario;
import com.example.altairis.inventario.repository.InventarioRepository;
import com.example.altairis.habitacion.repository.HabitacionRepository;
import com.example.altairis.reserva.dto.ReservaRequest;
import com.example.altairis.reserva.model.Reserva;
import com.example.altairis.reserva.repository.ReservaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Transactional
    public Reserva crearReserva(ReservaRequest request) {
        if (request.getHabitacionId() == null) throw new RuntimeException("El ID de habitación es NULL");
        if (request.getFechaInicio() == null) throw new RuntimeException("La fecha de inicio es NULL");

        Habitacion hab = habitacionRepository.findById(request.getHabitacionId())
                .orElseThrow(() -> new RuntimeException("Habitación con ID " + request.getHabitacionId() + " no encontrada"));

        List<Inventario> diasAfectados = inventarioRepository.findByHabitacionIdAndFechaBetween(
                request.getHabitacionId(),
                request.getFechaInicio(),
                request.getFechaFin()
        );

        if (diasAfectados.isEmpty()) {
            throw new RuntimeException("No hay inventario para la fecha: " + request.getFechaInicio());
        }

        for (Inventario dia : diasAfectados) {
            if (dia.getDisponibilidad() <= 0) {
                throw new RuntimeException("Sin cupo para el día: " + dia.getFecha());
            }
            dia.setDisponibilidad(dia.getDisponibilidad() - 1);
        }

        inventarioRepository.saveAll(diasAfectados);

        Reserva nuevaReserva = new Reserva();
        nuevaReserva.setHabitacion(hab);
        nuevaReserva.setNombreCliente(request.getNombreCliente());
        nuevaReserva.setFechaInicio(request.getFechaInicio());
        nuevaReserva.setFechaFin(request.getFechaFin());

        return reservaRepository.save(nuevaReserva);
    }
}
