package com.example.altairis.hoteles.model;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor; // Constructor vacío
import lombok.AllArgsConstructor; // Constructor con todos los argumentos



@Entity
@Getter
@Setter
@NoArgsConstructor // Constructor vacío
@AllArgsConstructor // Constructor con todos los campos
@Table(name = "hoteles")
public class Hoteles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hoteles_id", nullable = false)
    private Hoteles hoteles;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "ciudad", nullable = false)
    private String ciudad;

    @Column(name = "estrellas", nullable = false)
    private Integer estrellas;



    //constructor
    public Hoteles(Hoteles hoteles, String direccion,String nombre, String ciudad, Integer estrellas ) {
        this.hoteles = hoteles;
        this.direccion = direccion;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.estrellas = estrellas;
    }
}
//-- 1. Hoteles: Información básica
//CREATE TABLE hoteles (
//        id BIGINT AUTO_INCREMENT PRIMARY KEY,
//        nombre VARCHAR(255) NOT NULL,
//direccion VARCHAR(255),
//ciudad VARCHAR(100),
//estrellas INT,
//INDEX (nombre) -- Para búsqueda rápida con volumen elevado
//);
//
//        -- 2. Tipos de Habitación: Asociados a un hotel
//CREATE TABLE tipos_habitacion (
//        id BIGINT AUTO_INCREMENT PRIMARY KEY,
//        hotel_id BIGINT,
//        nombre VARCHAR(100), -- Ej: Doble, Suite, Individual
//capacidad INT,
//precio_base DECIMAL(10, 2),
//FOREIGN KEY (hotel_id) REFERENCES hoteles(id)
//        );
//
//        -- 3. Inventario: La clave de la operativa (Disponibilidad por fecha)
//CREATE TABLE inventario (
//        id BIGINT AUTO_INCREMENT PRIMARY KEY,
//        tipo_habitacion_id BIGINT,
//        fecha DATE NOT NULL,
//        cantidad_disponible INT NOT NULL, -- Cuántas de este tipo quedan ese día
//                FOREIGN KEY (tipo_habitacion_id) REFERENCES tipos_habitacion(id),
//UNIQUE (tipo_habitacion_id, fecha) -- Evita duplicar registros para el mismo día
//);
//
//        -- 4. Reservas: Registro de transacciones
//CREATE TABLE reservas (
//        id BIGINT AUTO_INCREMENT PRIMARY KEY,
//        tipo_habitacion_id BIGINT,
//        fecha_inicio DATE NOT NULL,
//        fecha_fin DATE NOT NULL,
//        nombre_cliente VARCHAR(255),
//estado VARCHAR(50), -- Ej: CONFIRMADA, CANCELADA
//FOREIGN KEY (tipo_habitacion_id) REFERENCES tipos_habitacion(id)
//        );