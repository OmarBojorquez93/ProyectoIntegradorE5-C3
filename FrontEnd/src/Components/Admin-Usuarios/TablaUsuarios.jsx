import "./TablaUsuario.css";

const TablaUsuarios = ({ usuarios }) => {
  const handleAdminToggle = async (id, isAdmin) => {
    console.log(id);
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
          {usuarios?.map((user, index) => (
            <tr key={user.id}>
              <td>{index + 1}</td>
              <td>{user.nombre}</td>
              <td>{user.apellido}</td>
              <td>{user.email}</td>
              <td>{user.admin ? "Si" : "No"}</td>
              <td>
                <input
                  type="checkbox"
                  checked={user.admin}
                  onChange={(e) => handleAdminToggle(user.id, e.target.checked)}
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
