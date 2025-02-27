import { useState } from 'react';
import { FaApple, FaFacebook, FaGoogle } from 'react-icons/fa'; // Importar iconos
import "./RegistrarUsuario.css";

export const RegistrarUsuario = () => {
  const [formData, setFormData] = useState({
    nombre: '',
    apellido: '',
    email: '',
    password: '',
  });

  const [errors, setErrors] = useState({});

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const validateForm = () => {
    const newErrors = {};

    if (!formData.nombre) newErrors.nombre = 'El nombre es requerido';
    if (!formData.apellido) newErrors.apellido = 'El apellido es requerido';
    if (!formData.email) {
      newErrors.email = 'El correo electrónico es requerido';
    } else if (!/\S+@\S+\.\S+/.test(formData.email)) {
      newErrors.email = 'El correo electrónico no es válido';
    }
    if (!formData.password) {
      newErrors.password = 'La contraseña es requerida';
    } else if (formData.password.length < 6) {
      newErrors.password = 'La contraseña debe tener al menos 6 caracteres';
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (validateForm()) {
      console.log('Formulario enviado:', formData);
      // Aquí puedes enviar los datos a tu API o hacer lo que necesites
    } else {
      console.log('Formulario con errores');
    }
  };

  return (
    <div className="registrar-usuario-container">
      <h2>Regístrate</h2>
      <form onSubmit={handleSubmit}>
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
            type="email"
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
        <button type="submit" className="submit-button">Registrarse</button>
      </form>

      <div className="social-buttons">
        <p>O regístrate con:</p>
        <button className="social-button apple">
          <FaApple /> Apple
        </button>
        <button className="social-button facebook">
          <FaFacebook /> Facebook
        </button>
        <button className="social-button google">
          <FaGoogle /> Google
        </button>
      </div>
    </div>
  );
};