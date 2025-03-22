import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { ProductDetail } from "../Components/Product/ProductDetail";
import { CaracteristicasProduc } from "../Components/CaracteristicasProduc/CaracteristicasProduc";
import { getProductsById } from "../core/product/get-product-by-id.actions";

export const Detail = () => {
  const [product, setProduct] = useState({});
  const params = useParams();
  const id = params.id;

  //console.log(params)
  useEffect(() => {
    const fetchProduct = async () => {
      const data = await getProductsById(id);

      setProduct(data);
    };
    fetchProduct();
  }, []);

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
        <CaracteristicasProduc caracteristicas={product.caracteristicas} />
      </>
    </>
  );
};
