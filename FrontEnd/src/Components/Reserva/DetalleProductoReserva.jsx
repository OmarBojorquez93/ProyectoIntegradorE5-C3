import "./detalleProducto.css"

const DetalleProductoReserva = ({ product }) => {
  return (
    <div className="detalleProduct">
      <h4>Detalle del producto</h4>
      <div className="info">
      <div className="datos">
        <div className="dBasicos">
          <p>
            <strong>Nombre:</strong>
          </p>
          <p>{product?.nombre}</p>
          <p>
            <strong>Descripcion:</strong>
          </p>
          <p>{product?.descripcion}</p>

        </div>
        <div className="dCaracteristicas">
          {product && product.caracteristicas ? (
          <div className="itemCaract">
            {product?.caracteristicas.map(({ id, nombre, descripcion }) => (
              <p key={id}>
                <strong>{nombre}:</strong> {descripcion}
              </p>
            ))}
          </div>
        ) : null}
          
        </div>
       
      </div>
      <div className="imagenP">
        <img
          src={product?.imagenes?.ruta}
          alt={product.nombre}
          width={250}
          height={250}
        />

      </div>
        
      </div>
     
     
    </div>
  );
};

export default DetalleProductoReserva;
