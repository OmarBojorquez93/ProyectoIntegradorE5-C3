import Productos from "../utils/Products.json";
import { Card } from "../utils/Card";
import "./Recomendation.css";

export const Recomendation = () => {
  return (
    <div>
      <h2>Recomendation</h2>
      <div className="card-container">
        {Productos.map((item) => (
          <Card product={item} key={item.id} />
        ))}
      </div>
    </div>
  );
};
