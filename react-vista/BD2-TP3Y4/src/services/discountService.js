// discountService.js

import axios from 'axios';

const BASE_URL = 'http://localhost:8080/descuentos';

// Obtener todos los descuentos
export const listarDescuentos = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/listar`);
    return response.data;
  } catch (error) {
    throw new Error('Error al listar los descuentos: ' + error.message);
  }
};

// Crear descuento sobre medio de pago
export const crearDescuentoMedioDePago = async (descuentoMedioDePagoDTO) => {
  try {
    const response = await axios.post(`${BASE_URL}/crear/medioDePago`, descuentoMedioDePagoDTO);
    return response.data;
  } catch (error) {
    throw new Error('Error al crear descuento sobre medio de pago: ' + error.message);
  }
};

// Crear descuento sobre producto
export const crearDescuentoProducto = async (descuentoProductoDTO) => {
  try {
    const response = await axios.post(`${BASE_URL}/crear/producto`, descuentoProductoDTO);
    return response.data;
  } catch (error) {
    throw new Error('Error al crear descuento sobre producto: ' + error.message);
  }
};
