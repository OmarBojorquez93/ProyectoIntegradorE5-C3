import { Link } from "react-router-dom";
import TablaProductos from "./TablaProductos";
import "./ListarProductos.css";
import { useEffect, useState } from "react";
import { getProducts } from "../../core/product/get-products.actions";

const ListarProductos = () => {
  const [productos, setProductos] = useState([]);

  useEffect(() => {
    const fetchProduct = async () => {
      const data = await getProducts();
      setProductos(data);
    };
    fetchProduct();
  }, []);

  console.log({ productos });
  return (
    <div>
      <h3>Productos Registrados</h3>
      <div className="crear">
        <Link to={"/panelAdmin/crear-producto"} className="linkCrear">
          Crear Producto{" "}
        </Link>
      </div>

      <table className="tablaProductos">
        <thead>
          <tr>
            <th>#</th>
            <th>Producto</th>
            <th>Descripción</th>
            <th>Precio Alquiler</th>
            <th>Categoría</th>
            <th>Editar</th>
          </tr>
        </thead>
        <tbody>
          {productos.map((producto, index) => (
            <TablaProductos producto={producto} key={index} index={index} />
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ListarProductos;
