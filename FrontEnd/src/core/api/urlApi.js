import axios from "axios";

export const baseUrlApi = axios.create({
  baseURL: "http://100.26.99.192:8080/",
});
