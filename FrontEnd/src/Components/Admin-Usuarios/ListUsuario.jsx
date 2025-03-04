import { useEffect, useState } from "react";
import { useRecipeState } from "../../Context/global.context";
import { getUsers } from "../../core/users/get-users.actions";
import TablaUsuarios from "./TablaUsuarios";

const ListUsuario = () => {
  const { state } = useRecipeState();
  const { session } = state;
  const [usuarios, setUsuarios] = useState([]);

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const data = await getUsers(session);

        setUsuarios(data); // Aquí sí seteamos la data correctamente
      } catch (error) {
        console.error("Error al obtener usuarios:", error);
      }
    };

    fetchUsers();
  }, [session]);

  return (
    <div>
      <h3>Usuarios Registradso</h3>
      <TablaUsuarios usuarios={usuarios} />
    </div>
  );
};

export default ListUsuario;
