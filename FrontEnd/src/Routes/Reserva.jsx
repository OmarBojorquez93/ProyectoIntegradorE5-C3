import { useEffect, useState } from "react";
import DatosUsuarioReserva from "../Components/Reserva/DatosUsuarioReserva";
import DetalleFechaReserva from "../Components/Reserva/DetalleFechaReserva";
import DetalleProductoReserva from "../Components/Reserva/DetalleProductoReserva";
import { useRecipeState } from "../Context/global.context";
import { useLocation, useParams } from "react-router-dom";
import { getProductsById } from "../core/product/get-product-by-id.actions";
import "../App.css";
const Reserva = () => {
  const { state } = useRecipeState();
  const { user } = state;
  const { id } = useParams();
  const location = useLocation();
  const [product, setProduct] = useState({});
  const [selectedDates, setSelectedDates] = useState({
    start: null,
    end: null,
  });

  useEffect(() => {
    // Obtener parámetros de la URL
    const params = new URLSearchParams(location.search);
    const start = params.get("start") ? new Date(params.get("start")) : null;
    const end = params.get("end") ? new Date(params.get("end")) : null;

    setSelectedDates({ start, end });

    // Cargar el producto
    const fetchProduct = async () => {
      const data = await getProductsById(id);
      setProduct(data);
    };
    fetchProduct();
  }, [id, location.search]);

  console.log(product);

  const clacularDias = (start, end) => {
    if (!start || !end) return 0;
    const diffMs = end.getTime() - start.getTime();
    return Math.ceil(diffMs / (1000 * 60 * 60 * 24));
  };

  const totalDias = clacularDias(selectedDates.start, selectedDates.end);

  console.log(totalDias);

  return (
    <div>
      <h2>Confirma tu reserva</h2>
      {product && <DetalleProductoReserva product={product} />}
      <div className="usuario-reserva">
        <DatosUsuarioReserva user={user} />
        <DetalleFechaReserva
          startDate={selectedDates.start}
          endDate={selectedDates.end}
          totalDias={totalDias}
          valorTotal={totalDias * product.precio_alquiler}
        />
      </div>
    </div>
  );
};

export default Reserva;
