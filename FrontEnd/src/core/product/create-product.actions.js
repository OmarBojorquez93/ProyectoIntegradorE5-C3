import { baseUrlApi } from "../api/urlApi";

export const createProduct = async (producto, imagen, sessionId) => {
  try {
    const formData = new FormData();

    // Agregar los datos como un string en formato JSON
    formData.append(
      "peticion",
      JSON.stringify({
        nombre: producto.nombre,
        descripcion: producto.descripcion,
        precio_alquiler: producto.precio_alquiler,
        categoria: producto.categoria,
        caracteristicas: producto.caracteristicas,
      })
    );

    // Adjuntar la imagen
    formData.append("imagen", imagen);

    // Configurar los encabezados
    const headers = {
      "session-id": sessionId,
      "Content-Type": "multipart/form-data",
    };

    // Realizar la petición POST
    const { data } = await baseUrlApi.post(`/admin/producto`, formData, {
      headers,
    });

    return data;
  } catch (error) {
    console.error("Error al crear el producto:", error);
    throw new Error("Ocurrió un error al crear el producto");
  }
};
