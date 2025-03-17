import { Link } from "react-router-dom";
import TablaProductos from "./TablaProductos";
import "./ListarProductos.css";

const ListarProductos = () => {
  return (
    <div>
      <h3>Productos Registrados</h3>
      <div className="crear">
      <Link to={"/panelAdmin/crear-producto"} className="linkCrear">Crear Producto </Link>
      </div>
      
      <TablaProductos />
    </div>
  );
};

export default ListarProductos;
