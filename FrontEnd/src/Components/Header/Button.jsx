import { Link } from "react-router-dom";

export const Button = ({ ruta, className, title }) => {
  return (
    <Link to={ruta} className={className}>
      {title}
    </Link>
  );
};
