import { baseUrlApi } from "../api/urlApi";

export const createProduct = async (productos) => {
  const { producto } = productos;

  try {
    const { data } = await baseUrlApi.post("", { producto });

    return data;
  } catch (error) {
    throw new Error("Ocurrio un error al crear el producto");
  }
};
