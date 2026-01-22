package com.example.altairis.inventario.controller;

import com.example.altairis.inventario.model.Inventario;
import com.example.altairis.inventario.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping("/hotel/{hotelId}")
    public List<Inventario> obtenerInventario(
            @PathVariable Long hotelId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {

        return inventarioService.consultarDisponibilidad(hotelId, inicio, fin);
    }
}