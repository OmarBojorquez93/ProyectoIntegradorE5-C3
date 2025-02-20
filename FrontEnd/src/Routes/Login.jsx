export const Login = () => {
  return <div>Login</div>;
};


// import { useState } from 'react';
// export const Login = () => {
//   const [username, setUsername] = useState('');
//   const [password, setPassword] = useState('');
//   const [errors, setErrors] = useState({});

//   const validateForm = () => {
//     const newErrors = {};

//     // Validación del campo username
//     if (!username.trim()) {
//       newErrors.username = 'El nombre de usuario es requerido';
//     }

//     // Validación del campo password
//     if (!password.trim()) {
//       newErrors.password = 'La contraseña es requerida';
//     } else if (password.length < 6) {
//       newErrors.password = 'La contraseña debe tener al menos 6 caracteres';
//     }

//     setErrors(newErrors);
//     return Object.keys(newErrors).length === 0; // Retorna true si no hay errores
//   };

//   const handleSubmit = (e) => {
//     e.preventDefault();

//     if (validateForm()) {
//       // Aquí puedes agregar la lógica para manejar el login
//       console.log('Username:', username);
//       console.log('Password:', password);
//       // Limpiar el formulario después de enviar
//       setUsername('');
//       setPassword('');
//       setErrors({});
//     } else {
//       console.log('Formulario inválido');
//     }
//   };

//   return (
//     <div>
//       <h2>Login</h2>
//       <form onSubmit={handleSubmit}>
//         <div>
//           <label htmlFor="username">Username:</label>
//           <input
//             type="text"
//             id="username"
//             value={username}
//             onChange={(e) => setUsername(e.target.value)}
//           />
//           {errors.username && <p style={{ color: 'red' }}>{errors.username}</p>}
//         </div>
//         <div>
//           <label htmlFor="password">Password:</label>
//           <input
//             type="password"
//             id="password"
//             value={password}
//             onChange={(e) => setPassword(e.target.value)}
//           />
//           {errors.password && <p style={{ color: 'red' }}>{errors.password}</p>}
//         </div>
//         <button type="submit">Login</button>
//       </form>
//     </div>
//   );
// };




