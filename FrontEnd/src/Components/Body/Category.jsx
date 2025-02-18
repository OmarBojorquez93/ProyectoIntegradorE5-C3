import { useState } from "react";
import { Card } from "../utils/Card";

import Categorias from "../utils/Categogory.json";
import { Carousel } from "primereact/carousel";

export const Category = () => {
  const [products, setProducts] = useState(Categorias);
  const responsiveOptions = [
    {
      breakpoint: "1400px",
      numVisible: 2,
      numScroll: 1,
    },
    {
      breakpoint: "1199px",
      numVisible: 3,
      numScroll: 1,
    },
    {
      breakpoint: "767px",
      numVisible: 2,
      numScroll: 1,
    },
    {
      breakpoint: "575px",
      numVisible: 1,
      numScroll: 1,
    },
  ];

  return (
    <div>
      <Carousel
        value={products}
        numVisible={4}
        numScroll={1}
        /* autoplayInterval={3000}
        circular */
        responsiveOptions={responsiveOptions}
        itemTemplate={Card}
      />
    </div>
  );
};
