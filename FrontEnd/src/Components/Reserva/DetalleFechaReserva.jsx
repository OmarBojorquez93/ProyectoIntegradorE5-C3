import "./datosReserva.css";
const DetalleFechaReserva = ({ startDate, endDate, valorTotal, totalDias }) => {
  const formatoFecha = (fecha) => {
    return fecha ? fecha.toLocaleDateString("es-ES") : "No seleccionada";
  };

  return (
    <div className="contReserva">
      <h4>Detalle de reserva</h4>
      <p>
        <strong>Fecha inicial:</strong>{" "}
        {startDate ? formatoFecha(startDate) : "No seleccionada"}
      </p>
      <p>
        <strong>Fecha final:</strong>{" "}
        {endDate ? formatoFecha(endDate) : "No seleccionada"}
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
