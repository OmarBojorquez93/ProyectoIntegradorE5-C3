import { Routes, Route } from "react-router-dom";
import "./App.css";
import { Home } from "./Routes/Home";
import { Detail } from "./Routes/Detail";
import { Header } from "./Components/Header/Header";
import { Footer } from "./Components/Footer/Footer";

function App() {
  return (
    <>
      <Header />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/detail/:id" element={<Detail />} />
      </Routes>
      <Footer />
    </>
  );
}

export default App;
