// ProductList.jsx

import React, { useEffect, useState } from 'react';
import { listarProductos } from '../services/productService';

const ProductList = ({ onProductSelect }) => {
  const [productos, setProductos] = useState([]);

  useEffect(() => {
    const fetchProductos = async () => {
      try {
        const productosData = await listarProductos();
        setProductos(productosData);
      } catch (error) {
        console.error('Error al cargar productos:', error.message);
      }
    };

    fetchProductos();
  }, []);

  const handleProductChange = (e) => {
    const selectedProductId = e.target.value;
    const selectedProduct = productos.find((product) => product.id === parseInt(selectedProductId));
    onProductSelect(selectedProduct);
  };

  return (
    <div>
      <h3>Seleccionar Producto</h3>
      <select onChange={handleProductChange}>
        <option value="">Seleccione un producto</option>
        {productos.map((producto) => (
          <option key={producto.id} value={producto.id}>
            {producto.nombre} - ${producto.precio}
          </option>
        ))}
      </select>
    </div>
  );
};

export default ProductList;
