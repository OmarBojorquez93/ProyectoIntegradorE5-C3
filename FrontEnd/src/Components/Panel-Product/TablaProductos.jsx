import { Link } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faEdit } from "@fortawesome/free-solid-svg-icons";
import "./TablaProductos.css";
const TablaProductos = (productos) => {
  const { producto, index } = productos;

  const limitarTexto = (texto, limite = 30) =>
    texto.length > limite ? texto.slice(0, limite) + "..." : texto;

  return (
    <tr>
      <td data-label="#">{index + 1}</td>
      <td data-label="Producto">{limitarTexto(producto.nombre, 30)}</td>
      <td data-label="Descripción">{producto.descripcion}</td>
      <td data-label="Precio Alquiler">{producto.precio_alquiler}</td>
      <td data-label="Categoría">{producto.categoria}</td>
      <td data-label="Editar">
        <Link to={`/panelAdmin/editar-producto/${producto.id}`}>
          <FontAwesomeIcon icon={faEdit} color="#13b2b2" />
        </Link>
      </td>
    </tr>
  );
};

export default TablaProductos;
