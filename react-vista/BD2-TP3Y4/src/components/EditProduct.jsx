// EditProduct.js

import React, { useState, useEffect } from 'react';
import { obtenerProductoPorId, listarMarcas, modificarProducto, listarCategorias } from '../services/productService';
import { useParams, useNavigate } from 'react-router-dom';

const EditProduct = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  
  const [producto, setProducto] = useState({
    id: '',
    codigo: '',
    descripcion: '',
    precio: 0,
    marca: '', // Aquí guardaremos el ID de la marca
    categoria: '', // Aquí guardaremos el ID de la categoría
  });

  const [categorias, setCategorias] = useState([]);
  const [marcas, setMarcas] = useState([]);
  const [error, setError] = useState('');

  // Efecto para cargar el producto, las categorías y las marcas
  useEffect(() => {
    const fetchProducto = async () => {
      try {
        const data = await obtenerProductoPorId(id);
        setProducto(data);
      } catch (error) {
        console.error('Error al obtener el producto:', error);
        setError('Error al cargar el producto');
      }
    };

    const fetchCategorias = async () => {
      try {
        const categoriasData = await listarCategorias();
        setCategorias(categoriasData);
      } catch (error) {
        console.error('Error al obtener las categorías:', error);
      }
    };

    const fetchMarcas = async () => {
      try {
        const marcasData = await listarMarcas();
        setMarcas(marcasData);
      } catch (error) {
        console.error('Error al obtener las marcas:', error);
      }
    };

    fetchProducto();
    fetchCategorias();
    fetchMarcas();
  }, [id]);

  // Manejar cambios en los inputs
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setProducto({ ...producto, [name]: value });
  };

  // Guardar cambios del producto
  const handleGuardar = async () => {
    try {
      await modificarProducto(
        producto.id, 
        producto.codigo, 
        producto.descripcion, 
        producto.precio, 
        producto.categoria,  // Aquí enviamos el ID de la categoría
        producto.marca  // Aquí enviamos el ID de la marca
      );
      alert('Producto modificado exitosamente');
      navigate('/tp5');
    } catch (error) {
      console.error('Error al modificar el producto:', error);
      setError('Error al modificar el producto. ' + error.message);
    }
  };

  return (
    <div>
      <h2>Editar Producto</h2>
      {error && <p style={{ color: 'red' }}>{error}</p>}
      <form>
        <label>Id del Producto: </label>
        <input type="text" value={producto.id} disabled />

        <label>Código: </label>
        <input type="text" name="codigo" value={producto.codigo} onChange={handleInputChange} />

        <label>Descripción: </label>
        <input type="text" name="descripcion" value={producto.descripcion} onChange={handleInputChange} />

        <label>Precio: </label>
        <input type="number" name="precio" value={producto.precio} onChange={handleInputChange} />

        <label>Marca: </label>
        <select
          value={producto.marca}
          onChange={(e) => setProducto({ ...producto, marca: e.target.value })} // Almacena el ID de la marca seleccionada
        >
          <option value="">Seleccione una marca</option>
          {marcas.map((marca) => (
            <option key={marca.id} value={marca.id}>
              {marca.nombre}
            </option>
          ))}
        </select>

        <label>Categoría: </label>
        <select
          value={producto.categoria}
          onChange={(e) => setProducto({ ...producto, categoria: e.target.value })} // Almacena el ID de la categoría seleccionada
        >
          <option value="">Seleccione una categoría</option>
          {categorias.map((categoria) => (
            <option key={categoria.id} value={categoria.id}>
              {categoria.nombre}
            </option>
          ))}
        </select>

        <button type="button" onClick={handleGuardar}>Guardar Cambios</button>
      </form>
    </div>
  );
};

export default EditProduct;
