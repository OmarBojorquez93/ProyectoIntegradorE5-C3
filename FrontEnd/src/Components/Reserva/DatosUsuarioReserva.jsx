import "./datosReserva.css"

const DatosUsuarioReserva = ({ user }) => {
  const { nombre, apellido, email } = user;
  return (
    <div className="contReserva">
      <h4>Datos de usuario</h4>
      <div>
        <p>
          <strong>Nombre:</strong> {nombre}
        </p>
        <p>
          <strong>Apellido:</strong> {apellido}
        </p>
        <p>
          <strong>Email:</strong> {email}
        </p>
      </div>
    </div>
  );
};

export default DatosUsuarioReserva;
