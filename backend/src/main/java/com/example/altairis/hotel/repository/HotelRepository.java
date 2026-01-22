package com.example.altairis.hotel.repository;

import com.example.altairis.hotel.model.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    //buscar por nombre y ciudad con paginación
    Page<Hotel> findByNombreContainingIgnoreCaseOrCiudadContainingIgnoreCase(
            String nombre,
            String ciudad,
            Pageable pageable
    );
}
