import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSearch } from "@fortawesome/free-solid-svg-icons";
import "./Search.css";
import SearchInput from "../utils/searchInput"

import { useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

export const Search = () => {
  const [startDate, setStartDate] = useState(null);
  const [endDate, setEndDate] = useState(null);
  // const [searchTerm, setSearchTerm] = useState("");

  return (
    <div className="cont">
      <h1>Deporte sin límites, alquiler sin complicaciones!</h1>
      <h4>Miles de productos en un solo lugar...</h4>
      <div className="search-container">
      
      {/* <input 
        type="text" 
        placeholder="Buscar..." 
        value={searchTerm} 
        onChange={(e) => setSearchTerm(e.target.value)} 
        className="inputBuscar"
        
      /> */}
      <SearchInput />

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
      <button className="buscador"><FontAwesomeIcon icon={faSearch} className="search-icon" /></button>
    </div>

    </div>
    
  );
};
