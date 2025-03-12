import { Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEdit } from "@fortawesome/free-solid-svg-icons";
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
          <td>1</td>
          <td>Producto 1</td>
          <td>apellido</td>
          <td>Si</td>
          <td>
            <Link to={"/panelAdmin/editar-producto/1"}>
              <FontAwesomeIcon icon={faEdit} />
            </Link>
          </td>
        </tr>
      </tbody>
    </table>
  );
};

export default TablaProductos;
