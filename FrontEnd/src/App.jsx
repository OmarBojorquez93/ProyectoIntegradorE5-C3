import { Routes, Route } from "react-router-dom";
import { Home } from "./Routes/Home";
import { Detail } from "./Routes/Detail";
import { Header } from "./Components/Header/Header";
import Footer from "./Components/Footer/Footer";
import "./App.css";
import { Login } from "./Routes/Login";
import { CrearCuenta } from "./Routes/CrearCuenta";
import { Categoty } from "./Routes/Categoty";
import { Toaster } from "react-hot-toast";
import PanelAdmin from "./Routes/PanelAdmin";

function App() {
  return (
    <div className="app-container">
      <Toaster position="top-right" />
      <Header />
      <div className="main-content">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/detail/:id" element={<Detail />} />
          <Route path="/login" element={<Login />} />
          <Route path="/crearCuenta" element={<CrearCuenta />} />
          <Route path="/category/:category" element={<Categoty />} />
          <Route path="/panelAdmin" element={<PanelAdmin />} />
        </Routes>
      </div>
      <Footer />
    </div>
  );
}

export default App;
