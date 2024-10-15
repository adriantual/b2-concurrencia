// ventaService.js

import axios from 'axios';

const BASE_URL = 'http://localhost:8080/ventas';



// Registrar una nueva venta
export const registrarVenta = async (ventaDTO) => {
  try {
    const respuesta = await axios.post(`${BASE_URL}/registrar`, ventaDTO);
    return respuesta.data; // Retornar la respuesta del backend
  } catch (error) {
    throw new Error('Error al registrar la venta: ' + error.message);
  }
};

// Calcular el total de una compra
export const calcularTotalCompra = async (ventaDTO) => {
  try {
    const response = await axios.post(`${BASE_URL}/calcular-total`, ventaDTO);
    return response.data;
  } catch (error) {
    throw new Error('Error al calcular el total: ' + error.message);
  }
};

// Listar todas las ventas
export const listarVentas = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/listar`);
    return response.data;
  } catch (error) {
    throw new Error('Error al listar las ventas: ' + error.message);
  }
};

// Listar ventas de un cliente específico
export const listarVentasPorCliente = async (clienteId) => {
  try {
    const response = await axios.get(`${BASE_URL}/cliente/${clienteId}/ventas`);
    return response.data;
  } catch (error) {
    throw new Error('Error al listar las ventas del cliente: ' + error.message);
  }
};
