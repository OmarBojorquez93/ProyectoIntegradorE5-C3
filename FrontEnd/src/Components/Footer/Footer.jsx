import "./Footer.css"; // Importar estilos si es necesario

const Footer = () => {
  return (
    <footer className="footer">
        <div className="footer-content">
          <div>
            <p className="copyright">
              &copy; {new Date().getFullYear()} Impulse. All rights reserved.
            </p>
          </div>
          <div>
            <img src="/img/Logo.png" alt="Impulse Logo" className="logo" />
          </div>
          
        </div>

    </footer>
  );
};

export default Footer;
