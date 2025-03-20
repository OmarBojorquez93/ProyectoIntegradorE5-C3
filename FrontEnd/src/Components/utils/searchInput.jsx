import { useState } from "react";
import useProductSuggestions from "./useProductSuggestions";
import "./searchInput.css";

const SearchInput = () => {
  const [inputValue, setInputValue] = useState("");
  const [showSuggestions, setShowSuggestions] = useState(false); // Controla la visibilidad

  const { suggestions, loading } = useProductSuggestions(inputValue);

  // Manejar selección y ocultar la lista
  const handleSelectSuggestion = (suggestion) => {
    setInputValue(suggestion);
    setShowSuggestions(false); // Oculta la lista
  };

  return (
    <div className="relative">
      <input
        type="text"
        value={inputValue}
        onChange={(e) => {
          setInputValue(e.target.value);
          setShowSuggestions(true); // Mostrar sugerencias cuando se escribe
        }}
        className="w-full p-2 border rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
        placeholder="Buscar productos..."
      />
      {loading && <p className="text-gray-500 text-sm">Cargando...</p>}
      {showSuggestions && suggestions.length > 0 && (
        <ul className="absolute">
          {suggestions.map((suggestion, index) => (
            <li
              key={index}
              onClick={() => handleSelectSuggestion(suggestion)}
              className="p-2 hover:bg-gray-200 cursor-pointer"
            >
              {suggestion}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default SearchInput;