import { baseUrlApi } from "../api/urlApi";

export const removerAdmin = async (id) => {
  console.log({ id });

  try {
    const { data } = await baseUrlApi.put(`/api/usuario/${id}/remover-admin`);

    console.log({ data });
    return data;
  } catch (error) {
    throw new Error("No se pudo remover administrador");
  }
};
