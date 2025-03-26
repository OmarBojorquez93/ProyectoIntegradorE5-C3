import { Link } from "react-router-dom";
import DatePicker from "react-datepicker";
import { useState } from "react";

const CalendarioParaReserva = ({ id }) => {
  const [dateRange, setDateRange] = useState([null, null]);
  const [startDate, endDate] = dateRange;

  // Fechas no disponibles
  const unavailableStart = new Date(2025, 3, 8); // 8 de abril de 2025
  const unavailableEnd = new Date(2025, 3, 15); // 15 de abril de 2025

  // Función para deshabilitar fechas en el rango
  const isDisabled = (date) => {
    return date >= unavailableStart && date <= unavailableEnd;
  };

  return (
    <div className="calendar-container">
      <h5>Fechas disponibles</h5>
      <DatePicker
        selected={startDate}
        onChange={(update) => setDateRange(update)}
        startDate={startDate}
        endDate={endDate}
        selectsRange
        inline
        monthsShown={2} // Mostrar dos meses
        minDate={new Date()} // No permitir seleccionar fechas pasadas
        filterDate={(date) => !isDisabled(date)} // Deshabilitar fechas no disponibles
      />
      <div>
        <Link
          to={`/reserva/${id}?start=${startDate?.toISOString()}&end=${endDate?.toISOString()}`}
        >
          Reservar
        </Link>
      </div>
    </div>
  );
};

export default CalendarioParaReserva;
