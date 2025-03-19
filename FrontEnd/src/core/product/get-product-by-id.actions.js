import { baseUrlApi } from "../api/urlApi";

export const getProductsById = async (id) => {
  console.log(id);
  try {
    const { data } = await baseUrlApi.get(`/public/producto/${id}`);
    console.log({ data });

    return data;
  } catch (error) {
    throw new Error("No se pudieron cargar las categorias");
  }
};
