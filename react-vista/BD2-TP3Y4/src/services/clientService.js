// clientService.js

import axios from 'axios';

const BASE_URL = 'http://localhost:8080/clientes';


// Crear un nuevo cliente
export const crearCliente = async (clienteDTO) => {
  try {
    const response = await axios.post(`${BASE_URL}/crear`, clienteDTO);
    return response.data;
  } catch (error) {
    throw new Error('Error al crear el cliente: ' + error.message);
  }
};

// Obtener cliente por ID
export const obtenerClientePorId = async (id) => {
  try {
    const response = await axios.get(`${BASE_URL}/${id}`);
    return response.data;
  } catch (error) {
    throw new Error('Error al obtener el cliente: ' + error.message);
  }
};

export const listarTaiyurjetas = async (clienteId) => {
  const response = await axios.get(`/api/clientes/${clienteId}/tarjetas`);
  return response.data;
};


// src/services/clientService.js

export const listarTarjetas = async (clienteId) => {
  const response = await fetch(`http://localhost:8080/clientes/${clienteId}/tarjetas`);
  const data = await response.json();
  return data;
};




// src/services/clientService.js
export const listarClientes = async () => { 
  try {
    const response = await fetch('http://localhost:8080/clientes/listar'); // Asegúrate de usar la URL correcta
    if (!response.ok) {
      throw new Error('Error al listar clientes');
    }
    const data = await response.json();
    return data;
  } catch (error) {
    console.error('Error:', error);
    return [];
  }
};
