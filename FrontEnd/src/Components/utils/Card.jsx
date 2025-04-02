import { Link } from "react-router-dom";

import "./Card.css";

export const Card = (product) => {
  console.log(product);

  function truncateText(text, maxLength) {
    return text.length > maxLength ? text.slice(0, maxLength) + "..." : text;
  }
  return (
    <Link to={`/detail/${product.product?.id}`} className={`card default-card`}>
      <img
        src={product.product?.imagen?.ruta}
        alt={product.product?.nombre}
        className={"card-image image-no-category"}
      />

      <div className="card-content">
        <h3 className="card-title">{product.product?.nombre}</h3>
        {product.product?.descripcion && (
          <p className="card-description">
            {truncateText(product.product?.descripcion, 100)}
          </p>
        )}
      </div>
    </Link>
  );
};
