
import { GiWeight} from "react-icons/gi";
import { IoMdPricetags, IoMdColorPalette } from "react-icons/io";
import { FaPerson } from "react-icons/fa6";
import { RxDimensions } from "react-icons/rx";
import { MdTexture } from "react-icons/md";
import "../CaracteristicasProduc/caracteristicas.css"
export const CaracteristicasProduc = () => {
  return (
    <>
        <h2 className="subTitle">Características</h2>
        <div className="containerGrid" >
            <div className="caracteristica">
                <IoMdPricetags size={20} />
                <h4>Marca: Golty</h4>
            </div>
            <div className="caracteristica">
                <GiWeight size={20} />
                <h4>Peso: 1.2 kg</h4>

            </div>
            <div className="caracteristica">
                <FaPerson size={20}  /> 
                <h4>Capacidad: 1 persona</h4> 

            </div>
            <div className="caracteristica">
                <RxDimensions  size={20} />
                <h4>Dimensiones: 125cm * 140cm</h4>

            </div>
            <div className="caracteristica">
                <MdTexture size={20}  />
                 <h4>Material: lona</h4>

            </div>
            <div className="caracteristica">
                <IoMdColorPalette size={20}/>
                
                <h4>Color: verde</h4>

            </div>
            

        </div>
        <div className="button">
              <button className="alquilar">ALQUILAR</button>
        </div>

    </>
    
  )
}

