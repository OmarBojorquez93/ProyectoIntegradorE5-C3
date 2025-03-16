import productos from "../Components/utils/Products.json";
import { useParams } from "react-router-dom";
import { ProductDetail } from "../Components/Product/ProductDetail";
import { CaracteristicasProduc } from "../Components/CaracteristicasProduc/CaracteristicasProduc";
import { useEffect, useState } from "react";
import { getProductsById } from "../core/product/get-product-by-id.actions";

export const Detail = () => {
  const [product, setProduct] = useState("");
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
  //const product = productos.find((producto) => producto.id == id);
  //console.log(product);

  return (
    <>
      <ProductDetail
        titulo={product.nombre}
        imagen={product.imagenes.ruta}
        descripcion={product.descripcion}
      />
      <CaracteristicasProduc />
    </>
  );
};
