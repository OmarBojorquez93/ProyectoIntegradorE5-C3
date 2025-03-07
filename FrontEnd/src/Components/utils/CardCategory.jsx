import { Link } from "react-router-dom";
import "./CardCategory.css";
const CardCategory = (product) => {
  return (
      <Link
        to={`category/${product.product?.category}`}
        className={`card-category`}
        >
        <img
          src={` /img${product.product?.img}`}
          alt={product.category}
          width={20}
          height={20}
          className="img-category"
        />
        <h2>{product.product?.category}</h2>
    </Link>
    
  );
};

export default CardCategory;
