import { Category } from "../Components/Body/Category";
import { Recomendation } from "../Components/Body/Recomendation";
import { Search } from "../Components/Body/Search";

export const Home = () => {
  const filtroPorCategoria = (nombre) => {
    console.log({ nombre });
  };

  return (
    <>
      <Search />
      <Category filtroPorCategoria={filtroPorCategoria} />
      <Recomendation />
    </>
  );
};
