const DetalleProductoReserva = ({ product }) => {
  console.log(product);
  return (
    <div>
      <h4>Detalle del producto</h4>
      <p>
        <strong>Nombre:</strong>
      </p>
      <p>{product?.nombre}</p>
      <p>
        <strong>Descripcion:</strong>
      </p>
      <p>{product?.descripcion}</p>
      <img src={product?.imagenes?.ruta} alt={product.nombre} />

      <div>
        <div>
          <p>
            <strong>Marca:</strong> {product?.caracteristicas[0]?.descripcion}
          </p>
          <p>
            <strong>Peso (gr):</strong>{" "}
            {product?.caracteristicas[1]?.descripcion}
          </p>

          <p>
            <strong>Color:</strong> {product?.caracteristicas[5]?.descripcion}
          </p>
        </div>

        <div>
          <p>
            <strong>Capacidad:</strong>{" "}
            {product?.caracteristicas[2]?.descripcion}
          </p>
          <p>
            <strong>Dimensiones:</strong>{" "}
            {product?.caracteristicas[3]?.descripcion}
          </p>
          <p>
            <strong>Material:</strong>{" "}
            {product?.caracteristicas[4]?.descripcion}
          </p>
        </div>
      </div>
    </div>
  );
};

export default DetalleProductoReserva;
