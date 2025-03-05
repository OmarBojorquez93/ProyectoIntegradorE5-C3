import { useRecipeState } from "../../Context/global.context";
import { asignarAdmin } from "../../core/users/asig-admin.actions";
import { removerAdmin } from "../../core/users/remov-admin.actions";
import "./TablaUsuario.css";

const TablaUsuarios = ({ usuarios, refreshUsers }) => {
  const { state } = useRecipeState();
  const { session, user } = state;
  console.log(state.user);

  const handlerAgregarAdmin = async (id) => {
    if (!session) {
      console.error("Error: No hay sessionID");
      return;
    }

    try {
      await asignarAdmin(id, session);
      refreshUsers();
    } catch (error) {
      console.error("Error al eliminar admin:", error.message);
    }
  };

  const handlerEliminarAdmin = async (id) => {
    if (!session) {
      console.error("Error: No hay sessionID");
      return;
    }

    try {
      await removerAdmin(id, session);
      refreshUsers();
    } catch (error) {
      console.error("Error al eliminar admin:", error.message);
    }
  };

  if (!Array.isArray(usuarios)) {
    return <p>No hay usuarios disponibles.</p>;
  }
  return (
    <div>
      <table>
        <thead>
          <tr>
            <th>#</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Email</th>
            <th>Administrador</th>
            <th>Volver Admin</th>
          </tr>
        </thead>
        <tbody>
          {usuarios?.map((users, index) => (
            <tr key={users.id}>
              <td>{index + 1}</td>
              <td>{users.nombre}</td>
              <td>{users.apellido}</td>
              <td>{users.email}</td>
              <td>{users.admin ? "Si" : "No"}</td>
              <td>
                <input
                  disabled={users.email == user.email}
                  type="checkbox"
                  checked={users.admin}
                  onChange={(e) => {
                    users.admin
                      ? handlerEliminarAdmin(users.id, e.target.checked)
                      : handlerAgregarAdmin(users.id, e.target.checked);
                  }}
                />
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default TablaUsuarios;
