import { useState } from "react";
import { Category } from "../Components/Body/Category";
import { Recomendation } from "../Components/Body/Recomendation";
import SearchPage from "../Components/Body/SearchPage"

export const Home = () => {
  const [category, setCategory] = useState("");

  const filtroPorCategoria = (nombre) => {
    setCategory(nombre);
  };

  return (
    <>
      <SearchPage/>
      <Category filtroPorCategoria={filtroPorCategoria} />
      <Recomendation category={category} />
    </>
  );
};
