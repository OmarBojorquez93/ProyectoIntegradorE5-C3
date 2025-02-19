import Productos from "../utils/Products.json";
import { Card } from "../utils/Card";
import "./Recomendation.css";

export const Recomendation = () => {
  return (
    <>
      <h2 className="recomendation">Recomendaciones</h2>
      <div className="card-container">
        {Productos.sort(() => Math.random() - 0.5).map((item) => (
          <Card product={item} key={item.id} />
        ))}
      </div>
    </>
  );
};
