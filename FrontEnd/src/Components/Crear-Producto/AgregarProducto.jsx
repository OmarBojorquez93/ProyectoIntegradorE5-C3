import { useState } from "react";
import FormInput from "../utils/FormInput";
import ImageUploader from "../utils/ImageUploader";
import "./AgregarProducto.css";

const AgregarProducto = () => {
  const [selectedImage, setSelectedImage] = useState(null);

  return (
    <div className="nuevoProduc">
      <h3>Nuevo Producto</h3>
      <form className="formNuevoProducto">
      <FormInput
        label={"Titulo del producto"}
        type={"text"}
        name={"producto"}
        value={""}
        onChange={""}
        errors={""}
      />
      <FormInput
        label={"Descripcion"}
        type={"text"}
        name={"descripcion"}
        value={""}
        onChange={""}
        errors={""}
      />
      <ImageUploader onImageUpload={(file) => setSelectedImage(file)} />

      <p>Iria el selctor de las categorias</p>
      <FormInput
        label={"Precio"}
        type={"number"}
        name={"precio"}
        value={""}
        onChange={""}
        errors={""}
      />

      <button className="buttonCrearPro">Crear producto</button>
    </form>

    </div>
    
  );
};

export default AgregarProducto;
