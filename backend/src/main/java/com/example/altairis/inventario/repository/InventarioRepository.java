package com.example.altairis.inventario.controller.repository;

import com.example.altairis.inventario.controller.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    // Para la vista operativa del frontend (calendario de disponibilidad)
    List<Inventario> findByTipoHabitacionHotelIdAndFechaBetween(Long hotelId, LocalDate inicio, LocalDate fin);
}