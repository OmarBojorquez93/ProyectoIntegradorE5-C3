import { Card } from "../utils/Card";
import "./Recomendation.css";
import { useEffect, useState } from "react";
import { getProducts } from "../../core/product/get-products.actions";

export const Recomendation = () => {
  const [product, setProduct] = useState([]);

  useEffect(() => {
    const fetchProduct = async () => {
      const data = await getProducts();
      setProduct(data);
    };
    fetchProduct();
  }, []);

  return (
    <>
      <h2 className="recomendation">Recomendaciones</h2>
      <div className="card-container">
        {product
          .sort(() => Math.random() - 0.5)
          .map((item) => (
            <Card product={item} key={item.id} />
          ))}
      </div>
    </>
  );
};
