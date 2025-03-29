import { baseUrlApi } from "../api/urlApi";

export const reservasPorId = async (id) => {
  try {
    const { data } = await baseUrlApi.get(
      `/public/producto/fechas-no-disponibles?idProducto=${id}`
    );

    return data;
  } catch (error) {
    console.log(error);
  }
};
