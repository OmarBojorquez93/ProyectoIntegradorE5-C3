import React from "react";
import "./Footer.css"; // Importación de estilos externos

const Footer = () => {
  return (
    <footer className="footer">
      <div className="container">
        <div className="footer-content">
          <img
            src="/img/Logo.png"
            alt="Impulse Logo"
            className="logo"
          />
          <p className="copyright">&copy; {new Date().getFullYear()} Impulse. All rights reserved.</p>
        </div>
      </div>
    </footer>
  );
};

export default Footer;