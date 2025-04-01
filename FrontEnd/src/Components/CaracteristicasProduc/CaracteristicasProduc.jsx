import { GiWeight } from "react-icons/gi";
import { IoMdPricetags, IoMdColorPalette } from "react-icons/io";
import { FaPerson } from "react-icons/fa6";
import { RxDimensions } from "react-icons/rx";
import { MdTexture } from "react-icons/md";
import "../CaracteristicasProduc/caracteristicas.css";

const iconos = {
  Marca: <IoMdPricetags size={20} />,
  Peso: <GiWeight size={20} />,
  Capacidad: <FaPerson size={20} />,
  Dimensiones: <RxDimensions size={20} />,
  Material: <MdTexture size={20} />,
  Color: <IoMdColorPalette size={20} />,
};

export const CaracteristicasProduc = ({
  caracteristicas,
  isAlquilar,
  alquilar,
}) => {
  return (
    <>
      <h2 className="subTitle">Características</h2>
      <div className="containerGrid">
        {caracteristicas?.map(({ id, nombre, descripcion }) => (
          <div key={id} className="caracteristica">
            {iconos[nombre] || <IoMdPricetags size={20} />}

            <h4 className="ItemC">
              {nombre}: {descripcion}
            </h4>
          </div>
        ))}
      </div>
      <div className="buttonAlquilar">
        {!alquilar && (
          <button className="alquilar" onClick={() => isAlquilar()}>
            Ver fechas disponibles
          </button>
        )}
      </div>
    </>
  );
};
