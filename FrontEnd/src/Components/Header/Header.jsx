import { Link } from "react-router-dom";
import { Button } from "./Button";
import { useRecipeState } from "../../Context/global.context";
import { useNavigate } from "react-router-dom";
import { FaRegUser } from "react-icons/fa";
import "./Header.css";
import { useState } from "react";
import IconBack from "../utils/IconBack"



export const Header = () => {
  const { state, logout } = useRecipeState();
  const { status, user } = state;
  const navigation = useNavigate();
  const [isOpen, setIsOpen]= useState(false);

  
  const handleLogout = () => {
    logout();
    navigation("/");
  };

  return (
    <header>
      <div className="containerHeader">
        <div className="backIcon">
          <IconBack/>
        </div>
        <div className="logoC">
          <Link to="/">
              <img
                src={"/img/Logo.png"}
                alt="Impulse Logo"
                className="logoNav"
                onClick={()=>setIsOpen(false)}
              />
              
            </Link>
        </div>
        <nav className={`navBar ${isOpen && "open"}`}>


          {status === "authenticated" ? (
            <div className="buttons" onClick={()=>setIsOpen(false)}>
              {user && user.admin && (
                <Button
                  ruta={"/panelAdmin"}
                  className="button"
                  title={"Panel de Administrador"}
                />
              )}
              <button onClick={handleLogout} className="button buttonClose">
                Cerrar sesión
              </button>
              {user && (
                <div className="user-info">
                  <p>
                    {user.nombre} {user.apellido}
                  </p>
                  <div className="user-icon">
                    <FaRegUser />
                  </div>
                </div>
              )}
            </div>
          ) : (
            <div className="buttons" onClick={()=>setIsOpen(false)}>
              <Button

                ruta={"/crearCuenta"}
                className="button"
                title={"Crear Cuenta"}

              />
              <Button
                ruta={"/login"}
                className="button"
                title={"Iniciar sesión"}
                // onClick={()=>setIsOpen(false)}

              />
            </div>
          )}
        </nav>
        <div className={`menu_hambur ${isOpen && "open"}`} onClick={()=>setIsOpen(!isOpen)}>
          <span></span>
          <span></span>
          <span></span>
        </div>
      </div>
    </header>
  );
};
