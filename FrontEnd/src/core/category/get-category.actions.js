import { baseUrlApi } from "../api/urlApi";

export const getCategory = async () => {
  try {
    const { data } = await baseUrlApi.get("/public/categoria");
    console.log({ data });
    console.log("Sale esto");

    return data;
  } catch (error) {
    throw new Error("No se pudieron cargar las categorias");
  }
};
