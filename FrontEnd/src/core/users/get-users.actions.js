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
