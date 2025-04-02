import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { ProductDetail } from "../Components/Product/ProductDetail";
import { CaracteristicasProduc } from "../Components/CaracteristicasProduc/CaracteristicasProduc";
import { getProductsById } from "../core/product/get-product-by-id.actions";
import { useRecipeState } from "../Context/global.context";
import CalendarioParaReserva from "../Components/CalendarioReserva/CalendarioParaReserva";

export const Detail = () => {
  const { state } = useRecipeState();
  const { user } = state;
  const [product, setProduct] = useState({});
  const params = useParams();
  const id = params.id;
  const navigation = useNavigate();

  const [alquilar, setAlquilar] = useState(false);

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

  return (
    <>
      <>
        {product && product.imagen ? (
          <>
            <ProductDetail
              titulo={product.nombre}
              imagen={product.imagen.ruta}
              descripcion={product.descripcion}
            />
            <CaracteristicasProduc
              caracteristicas={product.caracteristicas}
              isAlquilar={isAlquilar}
              alquilar={alquilar}
            />

            {alquilar && <CalendarioParaReserva id={product.id} />}
          </>
        ) : (
          <p>Cargando producto...</p>
        )}
      </>
    </>
  );
};
