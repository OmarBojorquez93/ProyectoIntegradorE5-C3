import Categorias from "../utils/Categogory.json";

import "./Category.css";

import CardCategory from "../utils/CardCategory";

export const Category = () => {
  return (
    <div className="card-container-category">
      {Categorias.map((item) => (
        <CardCategory key={item.id} product={item} />
      ))}
    </div>
  );
};
