"use client";

import { useEffect, useState } from "react";
import Link from "next/link";

interface Hotel {
  id: number;
  nombre: string;
  direccion: string;
  ciudad: string;
  estrellas: number;
}

export default function HomePage() {
  const [hoteles, setHoteles] = useState<Hotel[]>([]);
  const [loading, setLoading] = useState(true);
  const [filtro, setFiltro] = useState("");

  useEffect(() => {
    // llamada a api de Spring Boot
    const url = filtro
      ? `http://localhost:8080/api/hoteles?filtro=${filtro}&page=0&size=10`
      : `http://127.0.0.1:8080/api/hoteles`;

    fetch(url)
      .then((res) => res.json())
      .then((data) => {
        // Page<Hotel>, los datos vienen en data.content
        setHoteles(data.content || data);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Error cargando hoteles:", err);
        setLoading(false);
      });
  }, [filtro]);

  return (
    <main className="min-h-screen bg-gray-50 p-8">
      <div className="max-w-6xl mx-auto">
        {/* Cabecera */}
        <header className="mb-12 text-center">
          <h1 className="text-7xl font-titulo text-[#3d4a59] tracking-[0.2em] uppercase font-light">
            Altairis Hotels
          </h1>
        </header>

        {/* Barra de Búsqueda */}
        <div className="mb-8 flex justify-center text-gray-900">
          <input
            type="text"
            placeholder="Buscar por nombre o ciudad..."
            className="w-full max-w-md p-3 rounded-lg border border-gray-300 shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500"
            onChange={(e) => setFiltro(e.target.value)}
          />
        </div>

        {/* Listado de Hoteles */}
        {loading ? (
          <div className="text-center text-xl">Cargando hoteles...</div>
        ) : (
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
            {hoteles.map((hotel) => (
              <div
                key={hotel.id}
                className="bg-white rounded-xl shadow-md overflow-hidden hover:shadow-lg transition-shadow border border-gray-100"
              >
                <div className="p-6">
                  <div className="flex justify-between items-start">
                    <h2 className="text-xl font-bold text-gray-800">
                      {hotel.nombre}
                    </h2>
                    <span className="bg-yellow-100 text-yellow-700 px-2 py-1 rounded text-sm font-bold">
                      {hotel.estrellas} ★
                    </span>
                  </div>
                  <p className="text-gray-500 mt-2">{hotel.ciudad}</p>
                  <p className="text-sm text-gray-400 italic">
                    {hotel.direccion}
                  </p>

                  <Link
                    href={`/hotel/${hotel.id}`}
                    className="mt-6 block text-center bg-[#3d4a59] text-white py-2 rounded-lg font-semibold hover:bg-blue-500 transition-colors"
                  >
                    Ver Disponibilidad
                  </Link>
                </div>
              </div>
            ))}
          </div>
        )}

        {!loading && hoteles.length === 0 && (
          <p className="text-center text-gray-500 mt-10">
            No se encontraron hoteles con esos criterios.
          </p>
        )}
      </div>
    </main>
  );
}
