import { useNavigate } from "react-router-dom";
import "../Product/detail.css";
import { FaArrowLeft } from "react-icons/fa";

export const ProductDetail = (producto) => {
  const { titulo, imagen, descripcion } = producto;
  const navigate = useNavigate();

  return (
    <div>
      <div>
        <div className="contenedorEncabezado">
          <h1 className="tituloDetalle"> {titulo} </h1>
          <FaArrowLeft
            size={25}
            onClick={() => navigate(-1)}
            className="iconoFlecha"
          />
        </div>
        <div className="containerDetail">
          <div>
            <img className="imgDetalle" src={imagen} alt="" />
          </div>
          <div className="containerText">
            <div>
              <p>{descripcion}</p>
            </div>
            <div className="button">
              <button className="alquilar">ALQUILAR</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
