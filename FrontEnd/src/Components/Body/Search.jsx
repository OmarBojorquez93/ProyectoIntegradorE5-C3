import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSearch } from "@fortawesome/free-solid-svg-icons";
import "./Search.css";
export const Search = () => {
  return (
    <div className="search-container">
      <input type="text" placeholder="Buscar..." className="search-input" />
      <FontAwesomeIcon icon={faSearch} className="search-icon" />
    </div>
  );
};
