import { useState } from "react";
import FormInput from "../utils/FormInput";
import { createProduct } from "../../core/product/create-product.actions";
import { useNavigate } from "react-router-dom";
import toast from "react-hot-toast";
import "./RegistrarProducto.css";
import { useRecipeState } from "../../Context/global.context";

const CrearProductos = () => {
  const { state } = useRecipeState();
  const { session } = state;
  const [formData, setFormData] = useState({
    nombre: "",
    descripcion: "",
    imagen: null,
    categoria: "",
    precioAlquiler: "",
    marca: "",
    peso: "",
    capacidad: "",
    material: "",
    color: "",
    alto: "",
    ancho: "",
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
    } else if (!/^\d*$/.test(formData.precioAlquiler))
      newErrors.precioAlquiler = "Debes ingresar solo caracteres numéricos";

    if (!formData.marca) newErrors.marca = "El nombre es requerido";
    else if (formData.marca.length < 3)
      newErrors.marca = "Debe tener al menos 3 caracteres";
    if (!formData.color) newErrors.color = "El color es requerido";
    else if (formData.color.length < 3)
      newErrors.color = "Debe tener al menos 3 caracteres";

    if (!/^\d*$/.test(formData.alto))
      newErrors.precioAlquiler = "Debes ingresar solo caracteres numéricos";
    if (!/^\d*$/.test(formData.ancho))
      newErrors.precioAlquiler = "Debes ingresar solo caracteres numéricos";

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleRegister = async (e) => {
    e.preventDefault();
    setApiError("");

    if (!validateForm()) return;

    try {
      const response = await createProduct(formData, session);

      if (response.status === 409) {
        setApiError("El nombre ya está en uso");
      } else {
        toast.success("Producto agregado con éxito!");
        setTimeout(() => navigate("/panelAdmin/productos"), 2000);
      }
    } catch (error) {
      setApiError(error.message);
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
          <select
            id="opciones"
            name="categoria"
            value={formData.categoria}
            onChange={handleChange}
          >
            <option value="">-- Selecciona una opción --</option>
            <option value="opcion1">Deportes Acuaticos</option>
            <option value="opcion2">Camping</option>
            <option value="opcion3">Deportes de invierno</option>
            <option value="opcion4">Escalar y otros</option>
          </select>

          <FormInput
            label={"Marca:"}
            type={"text"}
            name={"marca"}
            value={formData.marca}
            onChange={handleChange}
            errors={errors?.marca}
          />
          <FormInput
            label={"Peso:"}
            type={"number"}
            name={"peso"}
            value={formData.peso}
            onChange={handleChange}
            errors={errors?.peso}
          />
          <FormInput
            label={"Capacidad"}
            type={"number"}
            name={"capacidad"}
            value={formData.capacidad}
            onChange={handleChange}
            errors={errors?.capacidad}
          />
          <FormInput
            label={"Material"}
            type={"text"}
            name={"material"}
            value={formData.material}
            onChange={handleChange}
            errors={errors?.material}
          />
          <FormInput
            label={"Color"}
            type={"text"}
            name={"color"}
            value={formData.color}
            onChange={handleChange}
            errors={errors?.color}
          />
          <FormInput
            label={"Alto"}
            type={"text"}
            name={"alto"}
            value={formData.alto}
            onChange={handleChange}
            errors={errors?.alto}
          />
          <FormInput
            label={"Ancho"}
            type={"text"}
            name={"ancho"}
            value={formData.ancho}
            onChange={handleChange}
            errors={errors?.ncho}
          />

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
