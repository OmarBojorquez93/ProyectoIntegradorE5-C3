import { useState, useEffect } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import "./Search.css";
import { FaSearch } from "react-icons/fa";
// import axios from "axios";
import {baseUrlApi} from "../../core/api/urlApi"

const Search = ({ onSearch }) => {
  const [query, setQuery] = useState("");
  const [suggestions, setSuggestions] = useState([]);
  const [filteredSuggestions, setFilteredSuggestions] = useState([]);
  const [startDate, setStartDate] = useState(null);
  const [endDate, setEndDate] = useState(null);


  useEffect(() => {
    baseUrlApi
      .get("/public/producto")
      .then((response) => {
        setSuggestions(response.data);
      })
      .catch((error) => {
        console.error("Error al obtener sugerencias:", error);
      });
  }, []);

 
  useEffect(() => {
    if (query.length > 1) {
      const queryLower = query.toLowerCase();
      const filtered = suggestions.filter((item) =>
        item.nombre.toLowerCase().includes(queryLower)
      );
      setFilteredSuggestions(filtered);
    } else {
      setFilteredSuggestions([]);
    }
  }, [query, suggestions]);


  const handleSuggestionClick = (nombre) => {
    setQuery(nombre);
    setTimeout(()=> setFilteredSuggestions([]),100); 
  };

 
  const handleSearch = () => {
    if (!query.trim()) {
      alert("Escribe un producto para buscar.");
      return;
    }

    if (!startDate || !endDate) {
      alert("Debes seleccionar una fecha inicial y una fecha final.");
      return;
    }

    const params = {
      texto: query,
      fechaDesde: startDate.toISOString().split("T")[0],
      fechaHasta: endDate.toISOString().split("T")[0],
    };

    onSearch(params);
  };

  return (
    <div className="search-bar">
      <div className="search-field">
        <input
          type="text"
          placeholder="Buscar producto..."
          value={query}
          onChange={(e) => setQuery(e.target.value)}
          className="input-buscar"
        />
        {filteredSuggestions.length > 0 && (
          <ul className="suggestions">
            {filteredSuggestions.map((item) => (
              <li key={item.id} onClick={() => handleSuggestionClick(item.nombre)}>
                {item.nombre}
              </li>
            ))}
          </ul>
        )}
      </div>

      <DatePicker
        selected={startDate}
        onChange={(date) => setStartDate(date)}
        selectsStart
        startDate={startDate}
        endDate={endDate}
        minDate={new Date()}
        dateFormat="yyyy-MM-dd"
        placeholderText="Inicio"
      />

      <DatePicker
        selected={endDate}
        onChange={(date) => setEndDate(date)}
        selectsEnd
        startDate={startDate}
        endDate={endDate}
        minDate={startDate} 
        dateFormat="yyyy-MM-dd"
        placeholderText="Fin"
      />

      <button onClick={handleSearch} className="buscador">
        <FaSearch className="search-icon" />
      </button>
    </div>
  );
};

export default Search;