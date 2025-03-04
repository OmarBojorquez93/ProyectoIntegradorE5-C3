import { Link } from "react-router-dom";

const PanelAdministrador = () => {
  return (
    <div>
      <Link to={"/panelAdmin/usuarios"}>Administrar Usuarios</Link>
      <Link>Administrar Productos</Link>
    </div>
  );
};

export default PanelAdministrador;
