import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { ProductDetail } from "../Components/Product/ProductDetail";
import { CaracteristicasProduc } from "../Components/CaracteristicasProduc/CaracteristicasProduc";
import { getProductsById } from "../core/product/get-product-by-id.actions";
import { useRecipeState } from "../Context/global.context";
import { CalendarContainer } from "react-datepicker";
import CalendarioParaReserva from "../Components/CalendarioReserva/CalendarioParaReserva";

export const Detail = () => {
  const { state } = useRecipeState();
  const { user } = state;
  const [product, setProduct] = useState({});
  const params = useParams();
  const id = params.id;
  const navigation = useNavigate();

  const [alquilar, setAlquilar] = useState(false);

  //console.log(params)
  useEffect(() => {
    const fetchProduct = async () => {
      const data = await getProductsById(id);

      setProduct(data);
    };
    fetchProduct();
  }, []);

  const isAlquilar = () => {
    if (!user) {
      navigation("/login");
    } else {
      setAlquilar(true);
    }
  };

  console.log(alquilar);
  return (
    <>
      <>
        {product && product.imagenes ? (
          <ProductDetail
            titulo={product.nombre}
            imagen={product.imagenes[0].ruta}
            descripcion={product.descripcion}
          />
        ) : (
          <p>Cargando producto...</p>
        )}
        <CaracteristicasProduc
          caracteristicas={product.caracteristicas}
          isAlquilar={isAlquilar}
          alquilar={alquilar}
        />

        {alquilar && <CalendarioParaReserva id={product.id} />}
      </>
    </>
  );
};
