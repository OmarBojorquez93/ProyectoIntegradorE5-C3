import { Link } from "react-router-dom";
import { FaCogs } from "react-icons/fa";
import "./PanelAdmin.css";

const PanelAdministrador = () => {
  return (
    <div className="admin-container">
      <Link className="admin-card" to={"/panelAdmin/usuarios"}>
        <FaCogs className="admin-icon" />
        <span>Administrar usuarios</span>
      </Link>
      <Link className="admin-card">
        <FaCogs className="admin-icon" />
        <span>Configurar productos</span>
      </Link>
    </div>
  );
};

export default PanelAdministrador;
