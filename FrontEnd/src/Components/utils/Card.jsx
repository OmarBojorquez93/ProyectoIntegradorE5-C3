import { Link } from "react-router-dom";
import "./Card.css";

export const Card = (product) => {
  const hasCategory = !!product.products?.category;
  const hasProduct = product.product?.disponible;

  console.log(hasProduct);
  return (
    <Link
      to={
        hasCategory
          ? `/${product.products?.category}`
          : `/${product.product?.id}`
      }
      className={`card ${hasCategory ? "category-card" : "default-card"}`}
    >
      {hasCategory ? (
        <div className="category-header">
          <h2>{product.products?.category}</h2>
        </div>
      ) : null}
      <img
        src={` /img${
          hasCategory ? product.products?.img : product.product?.img
        }`}
        alt={product.category ? product.category : product.product?.title}
        className={`card-image ${
          hasCategory ? "image-with-category" : "image-no-category"
        }`}
      />
      {!hasCategory && (
        <div className="card-content">
          <h3 className="card-title">{product.product?.title}</h3>
          {product.product?.descripcion && (
            <p className="card-description">{product.product?.descripcion}</p>
          )}
          {product.product?.disponible ? <p>Disponible</p> : <p>Agotado</p>}
        </div>
      )}
    </Link>
  );
};
