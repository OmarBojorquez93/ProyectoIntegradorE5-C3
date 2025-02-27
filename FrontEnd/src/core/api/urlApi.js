import axios from "axios";

export const baseUrlApi = axios.create({
  baseURL: "http://localhost:8080",
});
