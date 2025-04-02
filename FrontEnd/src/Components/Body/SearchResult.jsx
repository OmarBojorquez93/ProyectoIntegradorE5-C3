import { Card } from "../utils/Card";
import "./searchResult.css"

const SearchResults = ({ results }) => {
    return (
      <div className="search-results">
        <h3>Resultados de búsqueda:</h3>
        {results.length === 0 ? (
          <p>No se encontraron productos.</p>
        ) : (
            <div className="cardBusquedas">    
            {results.map((product) => (
                <Card product={product} key={product.id} />
        
            ))}
            </div>
        )}
      </div>
    );
  };
  
  export default SearchResults;