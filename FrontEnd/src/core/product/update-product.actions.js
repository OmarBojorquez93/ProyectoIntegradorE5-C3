import { baseUrlApi } from "../api/urlApi";

const updateProduct = async (productos) => {
  const { product } = productos;

  try {
    const { data } = await baseUrlApi.patch(`/products/${id}`, {
      product,
    });

    return data;
  } catch (error) {
    throw new Error("Error al actualizar el producto");
  }
};
