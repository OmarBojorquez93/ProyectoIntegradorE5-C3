import { useState } from "react";
import { Category } from "../Components/Body/Category";
import { Recomendation } from "../Components/Body/Recomendation";
import { Search } from "../Components/Body/Search";

export const Home = () => {
  const [category, setCategory] = useState("");

  const filtroPorCategoria = (nombre) => {
    setCategory(nombre);
  };

  return (
    <>
      <Search />
      <Category filtroPorCategoria={filtroPorCategoria} />
      <Recomendation category={category} />
    </>
  );
};
