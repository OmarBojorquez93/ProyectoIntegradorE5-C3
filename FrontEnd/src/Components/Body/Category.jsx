import { Card } from "../utils/Card";
import Categorias from "../utils/Categogory.json";
import { Swiper, SwiperSlide } from "swiper/react";
import "swiper/css";
import "swiper/css/pagination";

import "./Category.css";

// import required modules
import { Pagination } from "swiper/modules";

export const Category = () => {
  return (
    <Swiper
      spaceBetween={50}
      slidesPerView={4}
      pagination={{
        clickable: true,
      }}
      modules={[Pagination]}
    >
      {Categorias.map((item) => (
        <SwiperSlide key={item.id}>
          <Card products={item} />
        </SwiperSlide>
      ))}
    </Swiper>
  );
};
