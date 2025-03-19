import { useState } from "react";
import FormInput from "../utils/FormInput";
import { createProduct } from "../../core/product/create-product.actions";
import { useNavigate } from "react-router-dom";
import toast from "react-hot-toast";
import "./RegistrarProducto.css";

const CrearProductos = () => {
  const [formData, setFormData] = useState({
    nombre: "",
    descripcion: "",
    imagen: null,
    categoria: "",
    precioAlquiler:""
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

    if (!formData.imagen) {
      newErrors.imagen = "Debes subir al menos una imagen";
      return false;
    }

    else if (!/^\d*$/.test(formData.precioAlquiler))
      newErrors.precioAlquiler = "Debes ingresar solo caracteres numéricos";

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleRegister = async (e) => {
    e.preventDefault();
    setApiError("");
  
    if (!validateForm()) return;
  
    const formDataToSend = new FormData();
    Object.entries(formData).forEach(([key, value]) => {
      formDataToSend.append(key, value);
    });
  
    try {
      const response = await createProduct(formDataToSend);
  
      if (response.status === 409) {
        setApiError("El nombre ya está en uso");
      } else {
        toast.success("Producto agregado con éxito!");
        setTimeout(() => navigate("/productos"), 2000);
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

          <label htmlFor="opciones">Categoria:</label>
          <select id="opciones" name="categoria" value={formData.categoria} onChange={handleChange}>
            <option value="">-- Selecciona una opción --</option>
            <option value="opcion1">Deportes Acuaticos</option>
            <option value="opcion2">Camping</option>
            <option value="opcion3">Deportes de invierno</option>
            <option value="opcion4">Escalar y otros</option>
          </select>
          <FormInput
            label={"Precio de alquiler"}
            type={"text"}
            name={"precioAlquiler"}
            value={formData.precioAlquiler}
            onChange={handleChange}
            errors={errors?.precioAlquiler}
          />

          <button type="submit" className="submit-button">
            Registrar Producto
          </button>

          {apiError && <p className="error">{apiError}</p>}
        </form>
      </div>
    </div>
  );
};

export default CrearProductos;
