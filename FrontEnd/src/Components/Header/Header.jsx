import { Link } from "react-router-dom";
import { Button } from "./Button";
import { useRecipeState } from "../../Context/global.context";
import { useNavigate } from "react-router-dom";
import "./Header.css";

export const Header = () => {
  const { state, logout } = useRecipeState();
  const { status, user } = state;
  const navigation = useNavigate();

  const handleLogout = () => {
    logout();
    navigation("/");
  };

  return (
    <header>
      <nav className="navBar">
        <Link to="/">
          <img
            src={"/img/Logo.png"}
            alt="Impulse Logo"
            height={50}
            width={120}
          />
        </Link>

        {status == "authenticated" ? (
          <div className="buttons">
            {user && user.admin ? (
              <Button
                ruta={"/panelAdmin"}
                className={"button login"}
                title={"Panel de Administrador"}
              />
            ) : null}
            <button onClick={handleLogout} className={"button login"}>
              Cerrar session
            </button>
            {user && (
              <div>
                <p>
                  {user.nombre} {user.apellido}
                </p>
              </div>
            )}
          </div>
        ) : (
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
        )}
      </nav>
    </header>
  );
};
