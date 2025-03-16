import { baseUrlApi } from "../api/urlApi";

export const getProducts = async () => {
  try {
    const { data } = await baseUrlApi.get("/public/producto");
    console.log({ data });

    return data;
  } catch (error) {
    throw new Error("No se pudieron cargar las categorias");
  }
};
