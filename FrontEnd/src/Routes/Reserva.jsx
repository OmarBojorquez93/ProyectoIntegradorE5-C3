import { useEffect, useState } from "react";
import DatosUsuarioReserva from "../Components/Reserva/DatosUsuarioReserva";
import DetalleFechaReserva from "../Components/Reserva/DetalleFechaReserva";
import DetalleProductoReserva from "../Components/Reserva/DetalleProductoReserva";
import { useRecipeState } from "../Context/global.context";
import { useLocation, useParams } from "react-router-dom";
import { getProductsById } from "../core/product/get-product-by-id.actions";

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

  return (
    <div>
      <h2>Confirma tu reserva</h2>
      {product && <DetalleProductoReserva product={product} />}
      <DatosUsuarioReserva user={user} />
      <DetalleFechaReserva
        startDate={selectedDates.start}
        endDate={selectedDates.end}
      />
    </div>
  );
};

export default Reserva;
