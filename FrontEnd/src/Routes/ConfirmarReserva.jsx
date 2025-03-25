import { Link } from "react-router-dom";

const ConfirmarReserva = () => {
  return (
    <div>
      <h2>Gracias por su referirnos!!!!</h2>
      <h3>Tu reserva se ha realizado con éxito</h3>
      <Link to={"/"}>SEGUIR ALQUILANDO</Link>
    </div>
  );
};

export default ConfirmarReserva;
