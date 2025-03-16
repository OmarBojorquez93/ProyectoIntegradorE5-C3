import { useState } from "react";
import FormInput from "../utils/FormInput";
import { createProduct } from "../../core/product/create-product.actions";
import { useNavigate } from "react-router-dom";
import toast from "react-hot-toast";
import "./RegistrarProducto.css";

const CrearProducto = () => {
  const [formData, setFormData] = useState({
    nombre: "",
    descripcion: "",
    imagen: null,
  });

  const [errors, setErrors] = useState({});
  const [apiError, setApiError] = useState("");
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));

    if (errors[name]) {
      setErrors((prev) => ({
        ...prev,
        [name]: "",
      }));
    }
  };

  const handleImageChange = (e) => {
    setFormData((prev) => ({
      ...prev,
      imagen: e.target.files[0],
    }));
  };

  const validateForm = () => {
    const newErrors = {};

    if (!formData.nombre) newErrors.nombre = "El nombre es requerido";
    else if (formData.nombre.length < 3)
      newErrors.nombre = "Debe tener al menos 3 caracteres";

    if (!formData.descripcion)
      newErrors.descripcion = "La descripción es requerida";
    else if (formData.descripcion.length < 10)
      newErrors.descripcion = "Debe tener al menos 10 caracteres";

    if (!formData.imagen)
      newErrors.imagen = "Debes subir al menos una imagen";

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleRegister = async (e) => {
    e.preventDefault();
    setApiError("");

    if (!validateForm()) return;

    try {
      const response = await createProduct(formData);

      if (response.status === 409) {
        setApiError("El nombre ya está en uso");
      } else {
        toast.success("Producto agregado con éxito!");
        setTimeout(() => {
          navigate("/productos");
        }, 2000);
      }
    } catch (error) {
      setApiError("Ocurrió un error inesperado, intenta nuevamente.");
    }
  };

  return (
    <div className="formulario-container">
      <div className="formulario-box">
        <h2>Registrar Producto</h2>
        <form onSubmit={handleRegister}>
          <FormInput
            label={"Nombre"}
            type={"text"}
            name={"nombre"}
            value={formData.nombre}
            onChange={handleChange}
            errors={errors?.nombre}
          />

          <FormInput
            label={"Descripción"}
            type={"text"}
            name={"descripcion"}
            value={formData.descripcion}
            onChange={handleChange}
            errors={errors?.descripcion}
          />

          <div className="form-group">
            <label>Imagen del producto</label>
            <input type="file" accept="image/*" onChange={handleImageChange} />
            {errors.imagen && <p className="error">{errors.imagen}</p>}
          </div>

          <button type="submit" className="submit-button">
            Registrar Producto
          </button>

          {apiError && <p className="error">{apiError}</p>}
        </form>
      </div>
    </div>
  );
};

export default CrearProducto;
