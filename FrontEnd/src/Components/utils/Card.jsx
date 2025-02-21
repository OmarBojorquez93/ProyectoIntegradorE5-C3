import { Link } from "react-router-dom";
import { FaStar, FaRegStar } from "react-icons/fa"; // Importamos iconos de estrellas
import "./Card.css";

export const Card = (product) => {
  const hasCategory = !!product.products?.category;
  const hasProduct = product.product?.disponible;

  // Función para renderizar las estrellas según la puntuación
  const renderStars = (rating) => {
    const stars = [];
    const totalStars = 5; // Máximo de estrellas
    for (let i = 1; i <= totalStars; i++) {
      if (i <= rating) {
        stars.push(<FaStar key={i} className="star-icon filled" />); // Estrella llena
      } else {
        stars.push(<FaRegStar key={i} className="star-icon" />); // Estrella vacía
      }
    }
    return stars;
  };

  return (
    <Link
      to={
        hasCategory
          ? `/${product.products?.category}`
          : `/detail/${product.product?.id}`
      }
      className={`card ${hasCategory ? "category-card" : "default-card"}`}
    >
      {hasCategory ? (
        <div className="category-header">
          <h2>{product.products?.category}</h2>
        </div>
      ) : null}
      <img
        src={`/img${
          hasCategory ? product.products?.img : product.product?.img
        }`}
        alt={hasCategory ? product.products?.category : product.product?.title}
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
          <div className="card-price-rating">
            <span className="card-price">${product.product?.precio?.toFixed(2)}</span>
            <div className="card-rating">
              {renderStars(product.product?.rating || 0)} {/* Renderiza las estrellas */}
              <span>({product.product?.rating || 0})</span> {/* Muestra el número de rating */}
            </div>
          </div>
          {product.product?.disponible ? (
            <p className="card-availability available">Disponible</p>
          ) : (
            <p className="card-availability sold-out">Agotado</p>
          )}
        </div>
      )}
    </Link>
  );
};