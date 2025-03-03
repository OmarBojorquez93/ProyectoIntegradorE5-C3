import { baseUrlApi } from "../api/urlApi";

export const authRegister = async (nombre, apellido, email, password) => {
  email = email.toLocaleLowerCase();

  try {
    const { data } = await baseUrlApi.post("/registro", {
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

const returnUser = (data) => {
  const { nombre, apellido, email, admin, session } = data;

  const user = { nombre, apellido, email, admin };

  return {
    user,
    session,
  };
};

export const authLogin = async (email, password) => {
  email = email.toLocaleLowerCase();

  try {
    const { data } = await baseUrlApi.post("/login", { email, password });

    return returnUser(data);
  } catch (error) {
    if (error.response) {
      const status = error.response.status;
      const message =
        error.response.data?.error?.message || "Error al iniciar sesión";
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
