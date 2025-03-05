import { baseUrlApi } from "../api/urlApi";

export const removerAdmin = async (id, sessionId) => {
  console.log({ id, sessionId });

  try {
    const { data } = await baseUrlApi.put(`/usuario/${id}/remover-admin`, {
      headers: {
        "session-id": sessionId,
      },
    });

    console.log({ data });
    return data;
  } catch (error) {
    throw new Error("No se pudo remover administrador");
  }
};
