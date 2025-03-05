import { baseUrlApi } from "../api/urlApi";

export const getUsers = async (sessionId) => {
  console.log({ sessionId });
  try {
    const { data } = await baseUrlApi.get("/usuario", {
      headers: {
        "session-id": sessionId,
      },
    });
    return data;
  } catch (error) {
    throw new Error("No se pudieron cargar los usuarios");
  }
};

export const asignarAdmin = async (id) => {
  console.log({ id });

  try {
    const { data } = await baseUrlApi.patch(`/${id}/asignar-admin`, {});

    console.log({ data });
    return data;
  } catch (error) {
    throw new Error("No se pudo asignar como administrador");
  }
};

export const removerAdmin = async (id) => {
  console.log({ id });

  try {
    const { data } = await baseUrlApi.patch(`${id}/remover-admin`, {});

    console.log({ data });
    return data;
  } catch (error) {
    throw new Error("No se pudo remover administrador");
  }
};
