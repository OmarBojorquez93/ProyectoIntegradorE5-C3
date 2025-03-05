import { Link } from "react-router-dom";

export const Button = ({ ruta, className, title, onClick }) => {
  return ruta ? (
    <Link to={ruta} className={className}>
      {title}
    </Link>
  ) : (
    <button onClick={onClick} className={className}>
      {title}
    </button>
  );
};
