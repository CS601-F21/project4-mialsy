import { BASE_URL } from "../constants/Constant";

export const getAxiosOptions = (method, path, data) => {
  return {
    method: method,
    withCredentials: true,
    url: `${BASE_URL}${path}`,
    body: data,
  };
};
