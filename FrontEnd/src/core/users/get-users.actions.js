import { baseUrlApi } from "../api/urlApi";

const getCookie = (name) => {
  return document.cookie
    .split("; ")
    .find((row) => row.startsWith(name + "="))
    ?.split("=")[1];
};
const jsessionId = getCookie("JSESSIONID");

export const getUsers = async (sessionId) => {
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
