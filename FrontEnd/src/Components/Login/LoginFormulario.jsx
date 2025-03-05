import { useState } from "react";
import FormInput from "../utils/FormInput";
import { useNavigate } from "react-router-dom";
import "./LoginFormaulario.css";
import { useRecipeState } from "../../Context/global.context";

const LoginFormulario = () => {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });
  const [errors, setErrors] = useState({});
  const [apiError, setApiError] = useState("");
  const navigation = useNavigate();
  const { login } = useRecipeState();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const validateForm = () => {
    const newErrors = {};

    if (!formData.email) {
      newErrors.email = "El correo electrónico es requerido";
    } else if (!/\S+@\S+\.\S+/.test(formData.email)) {
      newErrors.email = "El correo electrónico no es válido";
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setApiError("");

    if (!validateForm()) return;

    const resp = await login(formData.email, formData.password);

    if (!resp?.user) {
      setApiError(resp.message);
      return;
    } else {
      navigation("/");
    }
  };

  return (

    <div className="registrar-usuario-container">
      <h2>Iniciar sesión</h2>
      <form onSubmit={handleSubmit}>
        <FormInput
          label={"Correo electrónico"}
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
        />

        <button type="submit" className="submit-button">
          Iniciar sesión
        </button>

        {apiError && <p className="error">{apiError}</p>}
      </form>
    </div>
  );
};

export default LoginFormulario;
