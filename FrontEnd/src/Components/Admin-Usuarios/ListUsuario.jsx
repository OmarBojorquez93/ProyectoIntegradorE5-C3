import { useEffect, useState, useCallback } from "react";
import { useRecipeState } from "../../Context/global.context";
import { getUsers } from "../../core/users/get-users.actions";
import TablaUsuarios from "./TablaUsuarios";
import "./ListUsuario.css";

const ListUsuario = () => {
  const { state } = useRecipeState();
  const { session } = state;
  const [usuarios, setUsuarios] = useState([]);

  const fetchUsers = useCallback(async () => {
    try {
      const data = await getUsers(session);
      setUsuarios(data);
    } catch (error) {
      console.error("Error al obtener usuarios:", error);
    }
  }, [session]);

  useEffect(() => {
    fetchUsers();
  }, [fetchUsers]);

  return (
    <div>
      <h3>Usuarios Registrados</h3>
      <TablaUsuarios usuarios={usuarios} refreshUsers={fetchUsers} />
    </div>
  );
};

export default ListUsuario;
