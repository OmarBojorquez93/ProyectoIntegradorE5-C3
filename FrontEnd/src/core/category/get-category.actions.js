import { baseUrlApi } from "../api/urlApi";

export const getCategory = async () => {
  try {
    const { data } = await baseUrlApi.get("");
    console.log({ data });

    return data;
  } catch (error) {
    throw new Error("No se pudieron cargar las categorias");
  }
};
