import { useState } from "react";
import FormInput from "../utils/FormInput";
import ImageUploader from "../utils/Imageuploader";

const AgregarProducto = () => {
  const [selectedImage, setSelectedImage] = useState(null);

  return (
    <form>
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

      <button>Crear producto</button>
    </form>
  );
};

export default AgregarProducto;
