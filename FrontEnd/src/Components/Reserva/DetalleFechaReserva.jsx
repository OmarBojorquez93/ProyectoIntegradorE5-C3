import "./datosReserva.css"
const DetalleFechaReserva = ({ startDate, endDate }) => {
  return (
    <div className="contReserva">
      <h4>Detalle de reserva</h4>
      <p>
        <strong>Fecha inicial:</strong>{" "}
        {startDate ? startDate.toLocaleDateString() : "No seleccionada"}
      </p>
      <p>
        <strong>Fecha final:</strong>{" "}
        {endDate ? endDate.toLocaleDateString() : "No seleccionada"}
      </p>
      <p>
        <strong>Valor total:</strong>
      </p>
    </div>
  );
};

export default DetalleFechaReserva;
