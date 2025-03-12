import { Link } from "react-router-dom";
import TablaProductos from "./TablaProductos";

const ListarProductos = () => {
  return (
    <div>
      <h3>Usuarios Registrados</h3>
      <Link to={"/panelAdmin/crear-producto"}>Crear Producto </Link>
      <TablaProductos />
    </div>
  );
};

export default ListarProductos;
