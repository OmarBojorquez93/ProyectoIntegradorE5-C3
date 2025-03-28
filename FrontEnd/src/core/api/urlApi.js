import axios from "axios";

export const baseUrlApi = axios.create({
  baseURL: "http://34.236.113.154:8080/",
});
