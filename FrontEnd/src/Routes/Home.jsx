import { Category } from "../Components/Body/Category";
import { Recomendation } from "../Components/Body/Recomendation";
import { Search } from "../Components/Body/Search";

export const Home = () => {
  return (
    <div>
      <Search />
      <Category />
      <Recomendation />
    </div>
  );
};
