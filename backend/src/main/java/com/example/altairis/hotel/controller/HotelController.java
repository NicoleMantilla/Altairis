package com.example.altairis.hotel.controller;

import com.example.altairis.hotel.dto.HotelResponse;
import com.example.altairis.hotel.service.HotelService;
import com.example.altairis.hotel.model.Hotel;
import com.example.altairis.hotel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hoteles")

public class HotelController {
    //Inyectar servicios
    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelService hotelService;

    @GetMapping
    public ResponseEntity<Page<HotelResponse>> buscarHoteles(
            @RequestParam(required = false, defaultValue = "") String filtro,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable paging = PageRequest.of(page, size);
        return ResponseEntity.ok(hotelService.buscarHoteles(filtro, paging));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> obtenerPorId(@PathVariable Long id) {
        return hotelRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Hotel crearHotel(@RequestBody Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> actualizar(@PathVariable Long id, @RequestBody Hotel hotel) {
        return ResponseEntity.ok(hotelService.actualizar(id, hotel));
    }
}