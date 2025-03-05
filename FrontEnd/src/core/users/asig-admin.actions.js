import { baseUrlApi } from "../api/urlApi";

export const asignarAdmin = async (id) => {
  console.log({ id });

  try {
    const { data } = await baseUrlApi.put(
      `/api/usuario/${id}/asignar-admin`,
      {}
    );

    console.log({ data });
    return data;
  } catch (error) {
    throw new Error("No se pudo asignar como administrador");
  }
};
