// productService.js

import axios from 'axios';

const BASE_URL = 'http://localhost:8080/productos';

// Obtener todos los productos
export const listarProductos = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/listar`);
    return response.data;
  } catch (error) {
    throw new Error('Error al listar los productos: ' + error.message);
  }
};

// Crear un nuevo producto
export const crearProducto = async (productoDTO) => {
  try {
    const response = await axios.post(`${BASE_URL}/crear`, productoDTO);
    return response.data;
  } catch (error) {
    throw new Error('Error al crear el producto: ' + error.message);
  }
};

// Obtener producto por ID
export const obtenerProductoPorId = async (id) => {
  try {
    const response = await axios.get(`${BASE_URL}/${id}`);
    return response.data;
  } catch (error) {
    throw new Error('Error al obtener el producto: ' + error.message);
  }
};

// Modificar un producto existente
export const modificarProducto = async (id, codigo, descripcion, precio, categoria, marca) => {
  try {
    const response = await axios.put(`${BASE_URL}/modificar/${id}`, {
      codigo,
      descripcion,
      precio,
      categoriaId: categoria,
      marcaId: marca
    });
    return response.data;
  } catch (error) {
    throw new Error('Error al modificar el producto: ' + error.message);
  }
};

export const listarCategorias = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/categorias`);
    return response.data;
  } catch (error) {
    console.error('Error al listar las categorías:', error);
    throw new Error('Error al listar las categorías: ' + error.message);
  }
};

export const listarMarcas = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/marcas`);
    return response.data;
  } catch (error) {
    console.error('Error al listar las marcas:', error);
    throw new Error('Error al listar las marcas: ' + error.message);
  }
};