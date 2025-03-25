import { Link } from "react-router-dom";

const CalendarioParaReserva = ({ id }) => {
  return (
    <div>
      <h5>Fechas disponibles</h5>
      <p>CALENDARIO</p>
      <Link to={`/reserva/${id}`}>Reservar</Link>
    </div>
  );
};

export default CalendarioParaReserva;
