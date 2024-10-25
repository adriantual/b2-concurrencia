// ProductList.js

import React, { useEffect, useState } from 'react';
import { listarProductos } from '../services/productService';
import { useNavigate } from 'react-router-dom';

const ProductList = () => {
  const [productos, setProductos] = useState([]);
  const [carrito, setCarrito] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchProductos = async () => {
      try {
        const data = await listarProductos();
        setProductos(data);
      } catch (error) {
        console.error('Error al listar productos:', error);
      }
    };
    fetchProductos();
  }, []);

  const manejarProducto = (producto) => {
    const productoSeleccionado = carrito.some(item => item.id === producto.id)
      ? carrito.filter(item => item.id !== producto.id)
      : [...carrito, producto];

    setCarrito(productoSeleccionado);
  };

  const handleEditarProducto = (productoId) => {
    navigate(`/editar-producto/${productoId}`);
  };

  return (
    <div>
      <h2>Productos</h2>
      <table>
        <thead>
          <tr>
            <th>Marca</th>
            <th>Categoría</th>
            <th>Descripción</th>
            <th>Precio</th>
            <th>Seleccionar</th>
            <th>Editar</th>
          </tr>
        </thead>
        <tbody>
          {productos.length > 0 ? (
            productos.map((producto) => (
              <tr key={producto.id}>
                <td>{producto.marca.nombre}</td>
                <td>{producto.categoria.nombre}</td>
                <td>{producto.descripcion}</td>
                <td>${producto.precio.toFixed(2)}</td>
                <td>
                  <input
                    type="checkbox"
                    checked={carrito.some((item) => item.id === producto.id)} 
                    onChange={() => manejarProducto(producto)} 
                  />
                </td>
                <td>
                  <button onClick={() => handleEditarProducto(producto.id)}>Editar Producto</button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="6">No hay productos disponibles</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default ProductList;
