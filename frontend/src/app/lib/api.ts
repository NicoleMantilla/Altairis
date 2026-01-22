// lib/api.ts
const API_BASE_URL = "http://localhost:8080/api";

export interface Hotel {
  id: number;
  nombre: string;
  direccion: string;
  ciudad: string;
  estrellas: number;
}

export const fetchHoteles = async (): Promise<Hotel[]> => {
  const res = await fetch(`${API_BASE_URL}/hoteles`);
  if (!res.ok) throw new Error("Error al cargar hoteles");
  const data = await res.json();

  return data.content || data; 
};