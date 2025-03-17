import { Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEdit } from "@fortawesome/free-solid-svg-icons";
import "./TablaProductos.css";
const TablaProductos = () => {
  return (
    <table>
      <thead>
        <tr>
          <th>#</th>
          <th>Imagen</th>
          <th>Producto</th>
          <th>Disponible</th>
          <th>Editar</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td data-label= "#">1</td>
          <td data-label= "Imagen">Producto 1</td>
          <td data-label= "Producto">Carpa Camping</td>
          <td data-label= "Disponible">Si</td>
          <td data-label= "Editar">
            <Link to={"/panelAdmin/editar-producto/1"}>
              <FontAwesomeIcon icon={faEdit} color="#13b2b2"/>
            </Link>
          </td>
        </tr>
      </tbody>
    </table>
  );
};

export default TablaProductos;
