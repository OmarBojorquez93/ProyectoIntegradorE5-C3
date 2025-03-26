import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getProductsById } from "../../core/product/get-product-by-id.actions";
import FormInput from "../utils/FormInput";
import { getCategory } from "../../core/category/get-category.actions";
import { useRecipeState } from "../../Context/global.context";
import { updateProduct } from "../../core/product/update-product.actions";
import toast from "react-hot-toast";
import "./EditarProducto.css";

const EditarProductoPorId = () => {
  const { id } = useParams();
  const { state } = useRecipeState();
  const { session } = state;
  const navigate = useNavigate();
  const [product, setProduct] = useState(null);
  const [categorias, setCategorias] = useState([]);
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

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        const data = await getProductsById(id);
        setProduct(data);

        // **Actualizar el formulario con los datos del producto**
        setFormData({
          nombre: data.nombre || "",
          descripcion: data.descripcion || "",
          categoria: data.categoria || "",
          precioAlquiler: data.precio_alquiler || "",
          marca:
            data.caracteristicas?.find((c) => c.nombre === "Marca")
              ?.descripcion || "",
          peso:
            data.caracteristicas?.find((c) => c.nombre === "Peso")
              ?.descripcion || "",
          capacidad:
            data.caracteristicas?.find((c) => c.nombre === "Capacidad")
              ?.descripcion || "",
          material:
            data.caracteristicas?.find((c) => c.nombre === "Material")
              ?.descripcion || "",
          color:
            data.caracteristicas?.find((c) => c.nombre === "Color")
              ?.descripcion || "",
          alto:
            data.caracteristicas
              ?.find((c) => c.nombre === "Dimensiones")
              ?.descripcion?.match(/(\d+)cm/)[1] || "",
          ancho:
            data.caracteristicas
              ?.find((c) => c.nombre === "Dimensiones")
              ?.descripcion?.match(/X (\d+)cm/)[1] || "",
        });
      } catch (error) {
        setApiError("Error al obtener el producto");
      }
    };

    fetchProduct();
  }, [id]);

  useEffect(() => {
    const fetchCategory = async () => {
      const data = await getCategory();
      setCategorias(data);
    };
    fetchCategory();
  }, []);

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

  const validateForm = () => {
    const newErrors = {};

    if (!formData.nombre) newErrors.nombre = "El nombre es requerido";
    else if (formData.nombre.length < 3)
      newErrors.nombre = "Debe tener al menos 3 caracteres";

    if (!formData.descripcion)
      newErrors.descripcion = "La descripción es requerida";
    else if (formData.descripcion.length < 10)
      newErrors.descripcion = "Debe tener al menos 10 caracteres";

    if (!formData.precioAlquiler)
      newErrors.precioAlquiler = "El precio de alquiler es requerido";
    else if (!/^\d+$/.test(formData.precioAlquiler))
      newErrors.precioAlquiler = "Debes ingresar solo caracteres numéricos";

    if (!formData.marca) newErrors.marca = "El nombre es requerido";
    else if (formData.marca.length < 3)
      newErrors.marca = "Debe tener al menos 3 caracteres";

    if (!formData.color) newErrors.color = "El color es requerido";
    else if (formData.color.length < 3)
      newErrors.color = "Debe tener al menos 3 caracteres";

    if (!formData.alto) newErrors.alto = "El alto es requerido";
    else if (!/^\d+$/.test(formData.alto))
      newErrors.alto = "Debes ingresar solo caracteres numéricos";

    if (!formData.ancho) newErrors.ancho = "El ancho es requerido";
    else if (!/^\d+$/.test(formData.ancho))
      newErrors.ancho = "Debes ingresar solo caracteres numéricos";

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleUpdateProduct = async (e) => {
    e.preventDefault();

    setApiError("");

    if (!validateForm()) return;
    try {
      const productoActualizado = {
        ...formData,
        imagen: product?.imagenes?.ruta, // Mantener imagen actual si no hay nueva
      };
      const response = await updateProduct(id, session, productoActualizado);

      toast.success("Producto actualizado con éxito!");
      setTimeout(() => navigate("/panelAdmin/productos"), 2000);
    } catch (error) {
      setApiError(error.message);
    }
  };

  if (!product) return <p>Cargando producto...</p>;

  return (
    <div className="container">
      <h2>Editar producto</h2>
  
        <form onSubmit={handleUpdateProduct}>
          <div className="form">
            <div className="seccionUno">
              <div className="datosBasicos">
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
                  type={"textarea"}
                  name={"descripcion"}
                  value={formData.descripcion}
                  onChange={handleChange}
                  errors={errors?.descripcion}
                  />
                  <div className="selector">
                    <label htmlFor="opciones">Categoria:</label>
                    <select
                      id="opciones"
                      name="categoria"
                      value={formData.categoria}
                      onChange={handleChange}
                    >
                      <option value="">-- Selecciona una opción --</option>
                      {categorias.map((categoria) => (
                        <option key={categoria.id} value={categoria.nombre}>
                          {categoria.nombre}
                        </option>
                      ))}
                    </select>

                    </div>

              </div>
                <img src={product?.imagenes?.ruta} alt={product.nombre} />
            </div>
            <div className="seccionDos">
            <FormInput
              label={"Marca"}
              type={"text"}
              name={"marca"}
              value={formData.marca}
              onChange={handleChange}
              errors={errors?.marca}
            />
            <FormInput
              label={"Peso (grs)"}
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
              label={"Alto (cm)"}
              type={"number"}
              name={"alto"}
              value={formData.alto}
              onChange={handleChange}
              errors={errors?.alto}
            />
            <FormInput
              label={"Ancho (cm)"}
              type={"number"}
              name={"ancho"}
              value={formData.ancho}
              onChange={handleChange}
              errors={errors?.ancho}
            />

            <FormInput
              label={"Color"}
              type={"text"}
              name={"color"}
              value={formData.color}
              onChange={handleChange}
              errors={errors?.color}
            />

            </div>
        </div> 
            
            <button type="submit" className="submit-button">
              Guardar Cambios
            </button>

            {apiError && <p className="error">{apiError}</p>}
      </form>
         
        
    </div>
    
  );
};

export default EditarProductoPorId;
