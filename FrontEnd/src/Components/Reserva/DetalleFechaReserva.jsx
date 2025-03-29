import "./datosReserva.css";
const DetalleFechaReserva = ({ startDate, endDate, valorTotal, totalDias }) => {
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
        <strong>Total dias alquilados:</strong>{" "}
        {totalDias ? totalDias : "No seleccionada"}
      </p>
      <p>
        <strong>Valor total:</strong> ${" "}
        {valorTotal ? valorTotal : "No seleccionado"}
      </p>
    </div>
  );
};

export default DetalleFechaReserva;
