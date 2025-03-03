import { useEffect, useState } from "react";
import { useRecipeState } from "../../Context/global.context";
import { getUsers } from "../../core/users/get-users.actions";

const ListUsuario = () => {
  const { state } = useRecipeState();
  const { session } = state;
  const [usuarios, setUsuarios] = useState([]);

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const data = await getUsers(session);
        setUsuarios(data); // Aquí sí seteamos la data correctamente
        console.log("Usuarios obtenidos:", data);
      } catch (error) {
        console.error("Error al obtener usuarios:", error);
      }
    };

    fetchUsers();
  }, [session]);

  console.log(usuarios);

  return <div>ListUsuario w</div>;
};

export default ListUsuario;
