import { useState } from "react";
import Search from "./Search"
import SearchResults from "./SearchResult"
import "./SearchPage.css"
import {baseUrlApi} from "../../core/api/urlApi"
// import axios from "axios";


const SearchPage = () => {
  const [searchResults, setSearchResults] = useState(null);

  const handleSearch = (params) => {
 
    baseUrlApi
      .get("/public/producto/buscar", { params })
      .then((response) => {
        setSearchResults(response.data);
      })
      .catch((error) => {
        console.error("Error en la búsqueda:", error);
      });
  };

  return (
    <div>
       <h1>Deporte sin límites, alquiler sin complicaciones!</h1>
       <h4 className="subtitleHome">Miles de productos en un solo lugar...</h4>
       <Search onSearch={handleSearch} />
        {searchResults !== null && <SearchResults results={searchResults} />}
    </div>
  );
};

export default SearchPage;