const DetalleProductoReserva = ({ product }) => {
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
      <img
        src={product?.imagenes?.ruta}
        alt={product.nombre}
        width={100}
        height={100}
      />

      {product && product.caracteristicas ? (
        <div>
          {product?.caracteristicas.map(({ id, nombre, descripcion }) => (
            <p key={id}>
              <strong>{nombre}:</strong> {descripcion}
            </p>
          ))}
        </div>
      ) : null}
    </div>
  );
};

export default DetalleProductoReserva;
