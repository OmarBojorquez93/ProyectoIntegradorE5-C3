import { useEffect, useState } from "react";
import DatosUsuarioReserva from "../Components/Reserva/DatosUsuarioReserva";
import DetalleFechaReserva from "../Components/Reserva/DetalleFechaReserva";
import DetalleProductoReserva from "../Components/Reserva/DetalleProductoReserva";
import { useRecipeState } from "../Context/global.context";
import { useParams } from "react-router-dom";
import { getProductsById } from "../core/product/get-product-by-id.actions";

const Reserva = () => {
  const { state } = useRecipeState();
  const { user } = state;
  const { id } = useParams();
  const [product, setProduct] = useState({});

  useEffect(() => {
    const fetchProduct = async () => {
      const data = await getProductsById(id);
      setProduct(data);
    };
    fetchProduct();
  }, []);

  return (
    <div>
      <h2>Confirma tu reserva</h2>
      {product && <DetalleProductoReserva product={product} />}
      <DatosUsuarioReserva user={user} />
      <DetalleFechaReserva />
    </div>
  );
};

export default Reserva;
