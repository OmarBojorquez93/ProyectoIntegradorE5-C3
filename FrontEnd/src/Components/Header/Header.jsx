import { Link } from "react-router-dom";
import "./Header.css";

export const Header = () => {
  return (
    <nav className="navBar">
      {/* Logo */}

      <Link to="/">
        <img src={"/img/Logo.png"} alt="Impulse Logo" height={50} width={120} />
      </Link>

      <div className="buttons">
        <Link to="/crearCuenta" className="button">
          Crear Cuenta
        </Link>
        <Link to="/login" className="button login">
          Iniciar sesión
        </Link>
      </div>
    </nav>
  );
};
