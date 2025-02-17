import { Link } from "react-router-dom";
import "./Header.css";
import { Button } from "./Button";

export const Header = () => {
  return (
    <nav className="navBar">
      <Link to="/">
        <img src={"/img/Logo.png"} alt="Impulse Logo" height={50} width={120} />
      </Link>

      <div className="buttons">
        <Button
          ruta={"/crearCuenta"}
          className={"button"}
          title={"Crear Cuenta"}
        />
        <Button
          ruta={"/login"}
          className={"button login"}
          title={"Iniciar sesión"}
        />
      </div>
    </nav>
  );
};
