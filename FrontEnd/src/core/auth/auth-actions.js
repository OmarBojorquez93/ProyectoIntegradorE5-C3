import { baseUrlApi } from "../api/urlApi";

export const authRegister = async (nombre, apellido, email, password) => {
  email = email.toLocaleLowerCase();

  try {
    const { data } = await baseUrlApi.post("/usuario", {
      nombre,
      apellido,
      email,
      password,
    });

    return data;
  } catch (error) {
    console.log(error.response);
    // Error de respuesta de la API

    if (error.response) {
      const status = error.response.status;
      const message =
        error.response.data?.error?.message ||
        "Error al autentificar el usuario";
      return { status, message };
    }

    // Error de red
    if (error.request) {
      return {
        status: 0,
        message:
          "No se puede conectar con el servidor. verifica tu conexión a internet",
      };
    }

    // Error interno
    return {
      status: 500,
      message: "Ocurrió un error inesperado. Por favor, inténtalo nuevamente",
    };
  }
};
