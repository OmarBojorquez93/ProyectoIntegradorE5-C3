import { Link } from "react-router-dom";
import DatePicker from "react-datepicker";
import { useEffect, useState } from "react";
import "../CalendarioReserva/calendarStyles.css";
import { reservasPorId } from "../../core/reservas/ver-reservas-por-id";

const CalendarioParaReserva = ({ id }) => {
  const [dateRange, setDateRange] = useState([null, null]);
  const [startDate, endDate] = dateRange;
  const [isInvalidRange, setIsInvalidRange] = useState(false);
  const [fechasDisponibles, setFechasDisponibles] = useState([]);

  useEffect(() => {
    const fetchReservas = async () => {
      const data = await reservasPorId(id);
      setFechasDisponibles(data);
    };
    fetchReservas();
  }, []);

  console.log(fechasDisponibles);

  // Fechas no disponibles
  const fechasNoDisponiblesParsed = fechasDisponibles.map(
    (fecha) => new Date(fecha)
  );

  // Función para deshabilitar fechas en el rango
  const isDisabled = (date) => {
    return fechasNoDisponiblesParsed.some(
      (disabledDate) =>
        date.getFullYear() === disabledDate.getFullYear() &&
        date.getMonth() === disabledDate.getMonth() &&
        date.getDate() === disabledDate.getDate()
    );
  };

  const validateDateRange = (start, end) => {
    if (!start || !end) {
      setIsInvalidRange(false);
      return;
    }

    // Generamos todas las fechas dentro del rango seleccionado
    let currentDate = new Date(start);
    let isRangeInvalid = false;

    while (currentDate <= end) {
      if (isDisabled(currentDate)) {
        isRangeInvalid = true;
        break;
      }
      currentDate.setDate(currentDate.getDate() + 1);
    }

    if (isRangeInvalid) {
      alert(
        "El rango seleccionado no es posible, ya que hay fechas no disponibles en el medio."
      );
    }

    setIsInvalidRange(isRangeInvalid);
  };

  return (
    <div className="calendar-container">
      <h2 className="fechasDisponibles">Fechas disponibles</h2>
      <div className="calendar">
        <DatePicker
          selected={startDate}
          onChange={(update) => {
            setDateRange(update);
            validateDateRange(update[0], update[1]);
          }}
          startDate={startDate}
          endDate={endDate}
          selectsRange
          inline
          monthsShown={2} // Mostrar dos meses
          minDate={new Date()} // No permitir seleccionar fechas pasadas
          filterDate={(date) => !isDisabled(date)} // Deshabilitar fechas no disponibles
        />
        <div className="reserva">
          <Link
            to={
              isInvalidRange
                ? "#"
                : `/reserva/${id}?start=${startDate?.toISOString()}&end=${endDate?.toISOString()}`
            }
            style={{
              pointerEvents: isInvalidRange ? "none" : "auto",
              opacity: isInvalidRange ? 0.5 : 1,
            }}
          >
            RESERVAR
          </Link>
        </div>
      </div>
    </div>
  );
};

export default CalendarioParaReserva;
