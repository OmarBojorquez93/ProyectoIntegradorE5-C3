import { Link } from "react-router-dom";
import "./CardCategory.css";
const CardCategory = (product) => {
  console.log(product);
  return (
    <Link to={`category/${product.product?.category}`} className={``}>
      <div className="">
        <h2>{product.product?.category}</h2>
      </div>

      <img
        src={` /img${product.product?.img}`}
        alt={product.category}
        width={50}
        height={50}
        className={``}
      />
    </Link>
  );
};

export default CardCategory;
