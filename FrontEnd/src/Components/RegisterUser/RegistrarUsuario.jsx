import { authRegister } from "../../core/auth/auth-actions";
import { useState } from "react";
import "./RegistrarUsuario.css";

const RegistrarUsuario = () => {
  const [formData, setFormData] = useState({
    nombre: "",
    apellido: "",
    email: "",
    password: "",
  });

  const [errors, setErrors] = useState({});
  const [apiError, setApiError] = useState(""); // Guarda error de API
  const [isPosting, setIsPosting] = useState(false);

  // Manejar cambios en los inputs
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));

    // Limpiar error específico si se está corrigiendo
    if (errors[name]) {
      setErrors((prev) => ({
        ...prev,
        [name]: "",
      }));
    }
  };

  // Validar el formulario
  const validateForm = () => {
    const newErrors = {};

    if (!formData.nombre) newErrors.nombre = "El nombre es requerido";
    else if (formData.nombre.length < 3)
      newErrors.nombre = "El nombre debe tener al menos 3 caracteres";

    if (!formData.apellido) newErrors.apellido = "El apellido es requerido";
    else if (formData.apellido.length < 4)
      newErrors.apellido = "El apellido debe tener al menos 4 caracteres";

    if (!formData.email) {
      newErrors.email = "El correo electrónico es requerido";
    } else if (!/\S+@\S+\.\S+/.test(formData.email)) {
      newErrors.email = "El correo electrónico no es válido";
    }

    if (!formData.password) {
      newErrors.password = "La contraseña es requerida";
    } else if (formData.password.length < 6) {
      newErrors.password = "Debe tener al menos 6 caracteres";
    } else if (!/(?=.*[A-Z])/.test(formData.password)) {
      newErrors.password = "Debe contener al menos una mayúscula";
    } else if (!/(?=.*[a-z])/.test(formData.password)) {
      newErrors.password = "Debe contener al menos una minúscula";
    } else if (!/(?=.*\d)/.test(formData.password)) {
      newErrors.password = "Debe contener al menos un número";
    } else if (!/(?=.*[\W_])/.test(formData.password)) {
      newErrors.password = "Debe contener al menos un símbolo";
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0; // Retorna true si no hay errores
  };

  const handlerRegister = async (e) => {
    e.preventDefault();
    setApiError(""); // Resetear error de API

    if (!validateForm()) return; // Si hay errores, no enviar

    setIsPosting(true);
    try {
      const response = await authRegister(
        formData.nombre,
        formData.apellido,
        formData.email,
        formData.password
      );

      if (response.status === 500 || response.status === 0) {
        setApiError(response.message); // Mostrar error debajo del botón
      } else {
        console.log("Registro exitoso:", response);
      }
    } catch (error) {
      setApiError("Ocurrió un error inesperado, intenta nuevamente.");
    } finally {
      setIsPosting(false);
    }
  };

  return (
    <div className="registrar-usuario-container">
      <h2>Regístrate</h2>
      <form onSubmit={handlerRegister}>
        <div className="form-group">
          <label>Nombre:</label>
          <input
            type="text"
            name="nombre"
            value={formData.nombre}
            onChange={handleChange}
          />
          {errors.nombre && <span className="error">{errors.nombre}</span>}
        </div>
        <div className="form-group">
          <label>Apellido:</label>
          <input
            type="text"
            name="apellido"
            value={formData.apellido}
            onChange={handleChange}
          />
          {errors.apellido && <span className="error">{errors.apellido}</span>}
        </div>
        <div className="form-group">
          <label>Correo electrónico:</label>
          <input
            type="text"
            name="email"
            value={formData.email}
            onChange={handleChange}
          />
          {errors.email && <span className="error">{errors.email}</span>}
        </div>
        <div className="form-group">
          <label>Contraseña:</label>
          <input
            type="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
          />
          {errors.password && <span className="error">{errors.password}</span>}
        </div>
        <button type="submit" className="submit-button">
          Registrarse
        </button>

        {apiError && <p className="error">{apiError}</p>}
      </form>
    </div>
  );
};

export default RegistrarUsuario;
