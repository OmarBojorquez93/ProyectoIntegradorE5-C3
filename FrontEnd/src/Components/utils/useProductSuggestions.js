import { useState, useEffect } from "react";

const useProductSuggestions = (query) => {
  const [suggestions, setSuggestions] = useState([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    if (!query || query.length < 2) {
      setSuggestions([]);
      return;
    }

    const fetchSuggestions = async () => {
      setLoading(true);
      try {
        const response = await fetch(`http://localhost:8080/public/producto`);
        if (!response.ok) throw new Error("Error al obtener los productos");

        const data = await response.json();

        // Filtrar productos que coincidan con el query y obtener solo los nombres
        const productNames = data
          .filter((product) => product.nombre.toLowerCase().includes(query.toLowerCase()))
          .map((product) => product.nombre);

        setSuggestions(productNames);
      } catch (error) {
        console.error("Error obteniendo sugerencias:", error);
        setSuggestions([]); // En caso de error, devolvemos un array vacío
      } finally {
        setLoading(false);
      }
    };

    fetchSuggestions();
  }, [query]); // Se ejecuta cuando `query` cambia

  return { suggestions, loading };
};

export default useProductSuggestions;