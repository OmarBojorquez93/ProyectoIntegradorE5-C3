import { Link } from "react-router-dom";
import "./Card.css";

export const Card = (product) => {
  return (
    <Link
      to={product.category ? `/${product.category}` : `/${product.id}`}
      className="card-container"
    >
      {product.category ? (
        <div className="category">
          <h2 className="category-title">{product.category}</h2>
        </div>
      ) : null}
      <img
        src={`/img${product.img}`}
        height={250}
        width={250}
        alt={product.category ? product.category : product.title}
      />
      {product.title ? <h3>{product.title}</h3> : null}
      {product.description ? <p>{product.description}</p> : null}
    </Link>
  );
};
