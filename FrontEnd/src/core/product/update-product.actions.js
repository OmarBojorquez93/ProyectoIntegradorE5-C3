import { baseUrlApi } from "../api/urlApi";

export const updateProduct = async (id, sessionId, producto) => {
  try {
    const headers = {
      "session-id": sessionId,
    };

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

    const { data } = await baseUrlApi.put(`/admin/producto/${id}`, formData, {
      headers,
    });

    return data;
  } catch (error) {
    if (error.response) {
      console.error("Respuesta del servidor:", error.response.data);
    } else {
      console.error("Error de red:", error.message);
    }
    throw new Error(error);
  }
};
