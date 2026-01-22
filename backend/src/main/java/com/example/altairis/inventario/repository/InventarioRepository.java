package com.example.altairis.inventario.repository;

import com.example.altairis.inventario.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    List<Inventario>findByHabitacionIdAndFechaBetween(Long habitacionId, LocalDate inicio, LocalDate fin);

    Optional<Inventario> findByHabitacionIdAndFecha(Long habitacionId, LocalDate fecha);

}
