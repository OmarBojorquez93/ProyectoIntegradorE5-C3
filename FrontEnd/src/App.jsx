import { Routes, Route } from "react-router-dom";
import { Home } from "./Routes/Home";
import { Detail } from "./Routes/Detail";
import { Header } from "./Components/Header/Header";
import { Footer } from "./Components/Footer/Footer";
import "./App.css";
import { Login } from "./Routes/Login";
import { CrearCuenta } from "./Routes/CrearCuenta";
function App() {
  return (
    <>
      <Header />
      <div className="main-content">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/detail/:id" element={<Detail />} />
          <Route path="/login" element={<Login />} />
          <Route path="/crearCuenta" element={<CrearCuenta />} />
        </Routes>
      </div>
      <Footer />
    </>
  );
}

export default App;
