import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSearch } from "@fortawesome/free-solid-svg-icons";
import "./Search.css";
export const Search = () => {
  return (
    <div className="cont">
      <h2>Deporte sin límites, alquiler sin complicaciones!</h2>
      <h4>Miles de productos en un solo lugar...</h4>
      <div className="search-container">
      <input type="text" placeholder="Buscar..." className="search-input" />
      <FontAwesomeIcon icon={faSearch} className="search-icon" />
    </div>

    </div>
    
  );
};
