import { useState, useEffect } from "react";
import "./Category.css";

import CardCategory from "../utils/CardCategory";
import { getCategory } from "../../core/category/get-category.actions";

export const Category = ({ filtroPorCategoria }) => {
  const [categorias, setCategorias] = useState([]);

  useEffect(() => {
    const fetchCategory = async () => {
      const data = await getCategory();
      setCategorias(data);
    };
    fetchCategory();
  }, []);

  return (
    <div className="card-container-category">
      {categorias.map((item) => (
        <CardCategory
          key={item.id}
          categoria={item}
          filtroPorCategoria={filtroPorCategoria}
        />
      ))}
    </div>
  );
};
