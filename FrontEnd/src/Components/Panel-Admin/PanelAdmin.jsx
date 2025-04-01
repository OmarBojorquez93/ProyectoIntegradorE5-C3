import { Link } from "react-router-dom";
import { FaCogs } from "react-icons/fa";
import "./PanelAdmin.css";
import { useWindowSize } from "../../hooks/useWindowSize";

const PanelAdministrador = () => {
  const { width } = useWindowSize();

  if (width < 1024) {
    return (
      <>
        <h1>
          El panel de administrador solo esta disponible para el modo de
          Escritorio
        </h1>
        <Link to={"/"}>Volver al Inicio</Link>
      </>
    );
  }

  return (
    <div className="admin-container">
      <Link className="admin-card" to={"/panelAdmin/usuarios"}>
        <FaCogs size={30} className="admin-icon" />
        <span>Administrar usuarios</span>
      </Link>
      <Link className="admin-card" to={"/panelAdmin/productos"}>
        <FaCogs size={30} className="admin-icon" />
        <span>Configurar productos</span>
      </Link>
    </div>
  );
};

export default PanelAdministrador;
