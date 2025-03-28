import { baseUrlApi } from "../api/urlApi";

export const crearReserva = async (
  idProducto,
  fechaDesde,
  fechaHasta,
  sessionId
) => {
  try {
    const headers = {
      "session-id": sessionId,
    };
    const body = {
      idProducto: idProducto,
      fechaDesde: fechaDesde,
      fechaHasta: fechaHasta,
    };

    const { data } = await baseUrlApi.post("/reserva", body, { headers });

    return data;
  } catch (error) {
    console.log(error);
  }
};
