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
          { nombre: "Marca", descripcion: producto.marca },
          { nombre: "Peso", descripcion: producto.peso },
          { nombre: "Capacidad", descripcion: producto.capacidad },
          {
            nombre: "Dimensiones",
            descripcion: `${producto.alto}cm X ${producto.ancho}cm`,
          },
          { nombre: "Material", descripcion: producto.material },
          { nombre: "Color", descripcion: producto.color },
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

    // Verificar lo que se está enviando
    for (let [key, value] of formData.entries()) {
      console.log(`${key}:`, value);
    }

    // Realizar la petición POST
    const { data } = await baseUrlApi.post("/admin/producto", formData, {
      headers,
    });

    return data;
  } catch (error) {
    if (error.response) {
      const status = error.response.status;

      if (status === 409) {
        alert("⚠️ Ya existe un producto con ese nombre. Por favor elige otro.");
        error.message =
          "⚠️ Ya existe un producto con ese nombre. Por favor elige otro.";
      } else if (status === 500) {
        alert(
          "Error interno del servidor. Revisa los datos enviados o intenta más tarde."
        );
        error.message =
          "Error interno del servidor. Revisa los datos enviados o intenta más tarde.";
      } else {
        alert(
          "Error del servidor: " +
            (error.response.data?.message || "Intenta nuevamente.")
        );
        error.message =
          "Error del servidor: " +
          (error.response.data?.message || "Intenta nuevamente.");
      }

      console.error("Respuesta del servidor:", error.response.data);
    } else {
      alert("Error de red: " + error.message);
      console.error("Error de red:", error.message);
    }

    throw new Error(error);
  }
};
