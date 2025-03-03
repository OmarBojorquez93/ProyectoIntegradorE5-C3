import { useState } from "react";
import FormInput from "../utils/FormInput";
import { authRegister } from "../../core/auth/auth-actions";
import { useNavigate } from "react-router-dom";
import toast from "react-hot-toast";
import "./RegistrarUsuario.css";

const RegistrarUsuario = () => {
  const [formData, setFormData] = useState({
    nombre: "",
    apellido: "",
    email: "",
    password: "",
  });

  const [errors, setErrors] = useState({});
  const [apiError, setApiError] = useState("");
  const navigation = useNavigate();

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

    try {
      const response = await authRegister(
        formData.nombre,
        formData.apellido,
        formData.email,
        formData.password
      );

      if (
        response.status === 500 ||
        response.status === 0 ||
        response.status === 409
      ) {
        setApiError(response.message); // Mostrar error debajo del botón
      } else {
        toast.success("Usuario creado con éxito!", {
          duration: 4000, // Se muestra por 5 segundos
          position: "top-right",
        });
        setTimeout(() => {
          navigation("/login");
        }, 2000);
      }
    } catch (error) {
      setApiError("Ocurrió un error inesperado, intenta nuevamente.");
    }
  };

  return (
    <div className="registrar-usuario-container">
      <h2>Regístrate</h2>
      <form onSubmit={handlerRegister}>
        <FormInput
          label={"Nombre"}
          type={"text"}
          name={"nombre"}
          value={formData.nombre}
          onChange={handleChange}
          errors={errors?.nombre}
        />

        <FormInput
          label={"Apellido"}
          type={"text"}
          name={"apellido"}
          value={formData.apellido}
          onChange={handleChange}
          errors={errors?.apellido}
        />

        <FormInput
          label={"Correo Electrónico"}
          type={"text"}
          name={"email"}
          value={formData.email}
          onChange={handleChange}
          errors={errors?.email}
        />

        <FormInput
          label={"Contraseña"}
          type={"password"}
          name={"password"}
          value={formData.password}
          onChange={handleChange}
          errors={errors?.password}
        />

        <button type="submit" className="submit-button">
          Registrarse
        </button>

        {apiError && <p className="error">{apiError}</p>}
      </form>
    </div>
  );
};

export default RegistrarUsuario;
