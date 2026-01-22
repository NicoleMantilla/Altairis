"use client";
import { useParams } from "next/navigation";
import { useEffect, useState } from "react";
import InventarioTable from "../../../components/InventarioTable";

interface Hotel {
  id: number;
  nombre: string;
  ciudad: string;
  direccion: string;
}

interface Habitacion {
  id: number;
  nombre: string;
  capacidad: number;
  precioBase?: number;
  precio?: number;
}

export default function HotelDetailPage() {
  const { id } = useParams();
  const [hotel, setHotel] = useState<Hotel | null>(null);
  const [habitaciones, setHabitaciones] = useState<Habitacion[]>([]);
  // Estado para saber qué habitación ha seleccionado el usuario
  const [habitacionSeleccionada, setHabitacionSeleccionada] = useState<
    number | null
  >(null);

  useEffect(() => {
    if (id) {
      // cargar datos del Hotel
      fetch(`http://localhost:8080/api/hoteles/${id}`)
        .then((res) => res.json())
        .then((data) => setHotel(data))
        .catch((err) => console.error("Error al cargar el hotel:", err));

      // cargar habitaciones de este hotel
      fetch(`http://localhost:8080/api/habitaciones/hotel/${id}`)
        .then((res) => res.json())
        .then((data) => {
          setHabitaciones(data);
          // Por defecto, seleccionamos la primera si existen
          if (data.length > 0) setHabitacionSeleccionada(data[0].id);
        })
        .catch((err) => console.error("Error al cargar habitaciones:", err));
    }
  }, [id]);

  if (!hotel)
    return <div className="p-10 text-center">Cargando detalles...</div>;

  return (
    <main className="min-h-screen bg-gray-50 p-8">
      <div className="max-w-4xl mx-auto">
        <div className="bg-white rounded-2xl shadow-xl overflow-hidden border border-gray-100">
          <div className="bg-[#3d4a59] p-8 text-white">
            <h1 className="text-4xl font-bold">{hotel.nombre}</h1>
            <p className="mt-2 text-[#3d4a59]lue-100">
              {hotel.ciudad} — {hotel.direccion}
            </p>
          </div>

          <div className="p-8">
            {/* SECCIÓN DE HABITACIONES */}
            <h2 className="text-2xl font-semibold mb-4 text-gray-800">
              Tipos de Habitación
            </h2>
            <div className="grid grid-cols-1 sm:grid-cols-2 gap-4 mb-8">
              {habitaciones.map((hab) => (
                <div
                  key={hab.id}
                  onClick={() => setHabitacionSeleccionada(hab.id)}
                  className={`p-4 border-2 rounded-xl cursor-pointer transition-all ${
                    habitacionSeleccionada === hab.id
                      ? "border-blue-500 bg-blue-50"
                      : "border-gray-100 hover:border-blue-200"
                  }`}
                >
                  <div className="w-20 h-20 bg-gray-200 rounded-lg flex-shrink-0 flex items-center justify-center text-2xl">
                    {hab.nombre.toLowerCase().includes("suite") ? "🏨" : "🛏️"}
                  </div>
                  <div>
                    <h3 className="font-bold text-lg text-gray-800">
                      {hab.nombre}
                    </h3>
                    <p className="text-sm text-gray-500">
                      Capacidad: {hab.capacidad} pers.
                    </p>
                    <p className="text-[#3d4a59] font-bold mt-1">
                      {hab.precioBase || hab.precio || 0}€{" "}
                      <span className="text-xs text-gray-400 font-normal">
                        / noche
                      </span>
                    </p>
                  </div>
                </div>
              ))}
            </div>

            <hr className="my-8" />

            {/* SECCIÓN DE CALENDARIO */}
            <h2 className="text-2xl font-semibold mb-4 text-gray-800">
              Disponibilidad
            </h2>
            {habitacionSeleccionada ? (
              <>
                <p className="text-gray-800 mb-6">
                  Viendo calendario para la habitación seleccionada:
                </p>
                <InventarioTable hotelId={habitacionSeleccionada} />
              </>
            ) : (
              <p className="text-red-500">
                Este hotel no tiene habitaciones configuradas.
              </p>
            )}
          </div>
        </div>
      </div>
    </main>
  );
}
