import { baseUrlApi } from "../api/urlApi";

export const createProduct = async (producto, sessionId) => {
  console.log("product: ", producto);
  console.log("sessionID: ", sessionId);
  try {
    const formData = new FormData();

    // Agregar los datos como un string en formato JSON
    formData.append(
      "peticion",
      JSON.stringify({
        nombre: producto.nombre,
        descripcion: producto.descripcion,
        precio_alquiler: producto.precioAlquiler,
        categoria: producto.categoria,
        caracteristicas: [
          { nombre: "Caracteristica xxx", descripcion: "Descripcion xxx" },
          { nombre: "Caracteristica yyy", descripcion: "Descripcion yyy" },
        ],
      })
    );

    // Adjuntar la imagen
    if (producto.imagen instanceof File) {
      formData.append("imagen", producto.imagen);
    } else {
      console.error("El archivo de imagen no es válido");
    }

    // Configurar los encabezados
    const headers = {
      "session-id": sessionId,
    };

    // Realizar la petición POST
    const { data } = await baseUrlApi.post("/admin/producto", formData, {
      headers,
    });

    return data;
  } catch (error) {
    if (error.response) {
      console.error("Respuesta del servidor:", error.response.data);
    } else {
      console.error("Error de red:", error.message);
    }
    throw new Error("Ocurrió un error al crear el producto");
  }
};
