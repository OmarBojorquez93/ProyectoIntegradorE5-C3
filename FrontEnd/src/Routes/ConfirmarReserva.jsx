import "../App.css";
import { Link } from "react-router-dom";

const ConfirmarReserva = () => {
  return (
    <div className="confirmacionContainer">
      <h2>Gracias por preferirnos!</h2>
      <h3>Tu reserva se ha realizado con éxito</h3>
      <Link className="volver" to={"/"}>Seguir alquilando</Link>
    </div>
  );
};

export default ConfirmarReserva;
