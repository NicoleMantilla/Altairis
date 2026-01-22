"use client";
import { useEffect, useState } from 'react';

interface Inventario {
  id: number;
  fecha: string;
  disponibilidad: number;
}

export default function InventarioTable({ hotelId }: { hotelId: number }) {
  const [inventario, setInventario] = useState<Inventario[]>([]);
  const [loading, setLoading] = useState(true);

useEffect(() => {
  // definir el rango de fechas (hoy y 30 días después)
  const hoy = new Date().toISOString().split("T")[0];
  const futuro = new Date();
  futuro.setDate(futuro.getDate() + 30);
  const fechaFin = futuro.toISOString().split("T")[0];

  //llamada al endpoint con los parámetros de fecha
fetch(
  `http://127.0.0.1:8080/api/inventario/hotel/${hotelId}?inicio=${hoy}&fin=${fechaFin}`,
)
  .then((res) => res.json())
  .then((data: Inventario[]) => {
    // le decimos a TS que data es un array de Inventario
    setInventario(data);
    setLoading(false);
  })
  .catch((err) => {
    console.error("Error:", err);
    setLoading(false);
  });
}, [hotelId]);

  if (loading) return <p>Cargando disponibilidad...</p>;

  const hacerReserva = async (fecha: string) => {
    //pedimos el nombre al usuario para que sea dinámico
    const nombre = prompt("Introduce tu nombre para la reserva:");
    if (!nombre) return; // Si cancela, no hacemos nada

    // preparamos el objeto JSON que espera
    const datosReserva = {
      habitacionId: Number(hotelId),
      nombreCliente: nombre,
      fechaInicio: fecha,
      fechaFin: fecha,
    };

    console.log("Enviando datos:", datosReserva);
    
    try {
      const response = await fetch("http://127.0.0.1:8080/api/reservas", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(datosReserva),
      });

      if (response.ok) {
        alert("¡Reserva exitosa!");
        window.location.reload();
      } else {
        const errorText = await response.text();
        console.error("Error del servidor:", errorText);
        alert("Error al reservar. Revisa que el ID no sea null.");
      }
    } catch (error) {
      console.error("Error de red:", error);
    }
  };
  return (
    <div className="mt-4 border rounded-lg overflow-hidden">
      <table className="min-w-full divide-y divide-gray-200">
        <thead className="bg-gray-50">
          <tr>
            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Fecha</th>
            <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Disponibilidad</th>
            <th className="px-6 py-3 text-right text-xs font-medium text-gray-500 uppercase">Acción</th>
          </tr>
        </thead>
        <tbody className="bg-white divide-y divide-gray-200">
          {inventario.map((item) => (
            <tr key={item.id}>
              <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-900">
                {new Date(item.fecha).toLocaleDateString()}
              </td>
              <td className="px-6 py-4 whitespace-nowrap text-sm">
                <span className={`px-2 py-1 rounded-full font-bold ${
                  item.disponibilidad > 0 ? "bg-green-100 text-green-800" : "bg-red-100 text-red-800"
                }`}>
                  {item.disponibilidad} libres
                </span>
              </td>
              <td className="px-6 py-4 whitespace-nowrap text-right text-sm font-medium">
                <button 
                  disabled={item.disponibilidad <= 0}
                  className="text-[#3d4a59] hover:text-[#3d4a59]lue-900 font-semibold disabled:text-gray-400"
                  onClick={() => hacerReserva(item.fecha)}
                >
                  Reservar ahora
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}