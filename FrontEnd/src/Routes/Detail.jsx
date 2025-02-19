import productos from "../Components/utils/Products.json";
import { useParams } from "react-router-dom"
import { ProductDetail } from "../Components/Product/ProductDetail";
export const Detail = () => {
 
  const params = useParams()
  console.log(params)
  const id= params.id
  const product = productos.find(producto => producto.id==id)
  console.log(product.img)
  const rutaBase= "/public/img"
  return (
   
    <ProductDetail titulo={product.title}  imagen= {`${rutaBase}${product.img}`} descripcion ={product.descripcion} />
  );
};