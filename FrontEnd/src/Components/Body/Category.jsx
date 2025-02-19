import { Card } from "../utils/Card";
import Categorias from "../utils/Categogory.json";
import { Swiper, SwiperSlide } from "swiper/react";
import "swiper/css";

export const Category = () => {
  return (
    <Swiper spaceBetween={50} slidesPerView={4}>
      {Categorias.map((item) => (
        <SwiperSlide key={item.id}>
          <Card products={item} />
        </SwiperSlide>
      ))}
    </Swiper>
  );
};
