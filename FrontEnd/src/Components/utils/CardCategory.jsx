import "./CardCategory.css";
const CardCategory = ({ categoria, filtroPorCategoria }) => {
  const { nombre } = categoria;

  const imagen = (nombre) => {
    if (nombre == "Deportes Acuaticos") return "/icono-surf.png";
    if (nombre == "Camping") return "/icono-camping.png";
    if (nombre == "Deportes de invierno") return "invierno.png";
    else return "icono-senderismo.png";
  };

  return (
    <button
      onClick={() => filtroPorCategoria(nombre)}
      className="card-category"
    >
      <img
        src={`/img/${imagen(nombre)}`}
        alt={nombre}
        width={20}
        height={20}
        className="img-category"
      />
      <h2>{nombre}</h2>
    </button>
  );
};

export default CardCategory;
