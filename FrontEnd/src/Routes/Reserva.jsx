import { useEffect, useState } from "react";
import DatosUsuarioReserva from "../Components/Reserva/DatosUsuarioReserva";
import DetalleFechaReserva from "../Components/Reserva/DetalleFechaReserva";
import DetalleProductoReserva from "../Components/Reserva/DetalleProductoReserva";
import { useRecipeState } from "../Context/global.context";
import { useLocation, useNavigate, useParams } from "react-router-dom";
import { getProductsById } from "../core/product/get-product-by-id.actions";
import "../App.css";
import { crearReserva } from "../core/reservas/crear-reserva";
const Reserva = () => {
  const { state } = useRecipeState();
  const { user, session } = state;
  const { id } = useParams();
  const location = useLocation();
  const navigation = useNavigate();
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

  const calcularDias = (start, end) => {
    if (!start || !end) return 0;
    const diffMs = end.getTime() - start.getTime();
    return Math.ceil(diffMs / (1000 * 60 * 60 * 24)) + 1;
  };

  const totalDias = calcularDias(selectedDates.start, selectedDates.end);

  const handleReserva = async () => {
    if (!selectedDates.start || !selectedDates.end) {
      alert("Por favor, selecciona las fechas de reserva.");
      return;
    }

    try {
      const response = await crearReserva(
        id,
        selectedDates.start.toISOString().split("T")[0], // Formato YYYY-MM-DD
        selectedDates.end.toISOString().split("T")[0],
        session
      );

      if (response) {
        navigation("/reserva-confirmada");
      }
    } catch (error) {
      console.error("Error al realizar la reserva", error);
      alert("Hubo un error al hacer la reserva. Intenta de nuevo.");
    }
  };

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
      <div className="bConfirmar">
        <button className="BconfirmarReserva" onClick={handleReserva}>Confirmar</button>
      </div>
      
    </div>
  );
};

export default Reserva;
