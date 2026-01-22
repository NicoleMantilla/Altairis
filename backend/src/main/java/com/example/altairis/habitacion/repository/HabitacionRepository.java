package com.example.altairis.habitacion.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.altairis.habitacion.model.Habitacion;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
    List<Habitacion> findByHotelId(Long hotelId);
}