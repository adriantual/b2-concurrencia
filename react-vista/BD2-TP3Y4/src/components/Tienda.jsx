import { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import { listarProductos } from '../services/productService';
import { listarDescuentos } from '../services/discountService';
import { listarTarjetas } from '../services/clientService';
import { calcularTotalCompra, registrarVenta } from '../services/ventaService';

const Tienda = () => {
  const { state } = useLocation();
  const clienteId = state?.clienteId; // Asegurarse de que el clienteId esté disponible
  const [productos, setProductos] = useState([]);
  const [descuentos, setDescuentos] = useState([]);
  const [tarjetas, setTarjetas] = useState([]);
  const [carrito, setCarrito] = useState([]);
  const [descuentosProductos, setDescuentosProductos] = useState([]);
  const [descuentosTarjetas, setDescuentosTarjetas] = useState([]);
  const [tarjetaSeleccionada, setTarjetaSeleccionada] = useState(null); 
  const [totalCompra, setTotalCompra] = useState(0);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const productosData = await listarProductos();
        const descuentosData = await listarDescuentos();

        setProductos(productosData);
        setDescuentos(descuentosData);

        // Filtrar descuentos por productos y tarjetas
        setDescuentosProductos(descuentosData.filter((descuento) => descuento.marca));
        setDescuentosTarjetas(descuentosData.filter((descuento) => descuento.bancoEmisor));

        if (clienteId) {
          const tarjetasCliente = await listarTarjetas(clienteId);
          setTarjetas(tarjetasCliente);
        }
      } catch (error) {
        console.error('Error al obtener los datos: ', error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [clienteId]);

  const manejarProducto = (producto) => {
    if (carrito.some((item) => item.id === producto.id)) {
      setCarrito(carrito.filter((item) => item.id !== producto.id));
    } else {
      setCarrito([...carrito, producto]);
    }
  };

  const manejarSeleccionTarjeta = (tarjetaId) => {
    setTarjetaSeleccionada(tarjetaId); 
  };

  const calcularTotal = async () => {
    if (!clienteId || carrito.length === 0 || !tarjetaSeleccionada) {
      alert('Por favor seleccione productos y una tarjeta.');
      return;
    }

    const productosIds = carrito.map((producto) => producto.id);
    const ventaDTO = {
      clienteId: clienteId,
      productos: productosIds,
      medioDePagoId: tarjetaSeleccionada,
    };

    try {
      const total = await calcularTotalCompra(ventaDTO); 
      setTotalCompra(total); 
      alert(`El total de su compra es: $${total.toFixed(2)}`);
    } catch (error) {
      console.error('Error al calcular el total: ', error.message);
      alert('Hubo un error al calcular el total de la compra.');
    }
  };

  const realizarCompra = async () => {
    if (!clienteId || carrito.length === 0 || !tarjetaSeleccionada) {
      alert('Por favor seleccione productos y una tarjeta.');
      return;
    }

    const productosIds = carrito.map((producto) => producto.id);
    const ventaDTO = {
      clienteId: clienteId,
      productos: productosIds,
      medioDePagoId: tarjetaSeleccionada,
    };

    try {
      await registrarVenta(ventaDTO); 
      alert('Compra realizada con éxito!');
      setCarrito([]);
      setTotalCompra(0);
    } catch (error) {
      console.error('Error al realizar la compra: ', error.message);
      alert('Hubo un error al realizar la compra.');
    }
  };

  if (loading) {
    return <div>Cargando...</div>;
  }

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
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="5">No hay productos disponibles</td>
            </tr>
          )}
        </tbody>
      </table>

      <h2>Descuentos en Productos</h2>
      <table>
        <thead>
          <tr>
            <th>Fecha Inicio</th>
            <th>Fecha Fin</th>
            <th>Descuento (%)</th>
            <th>Marca</th>
          </tr>
        </thead>
        <tbody>
          {descuentosProductos.length > 0 ? (
            descuentosProductos.map((descuento) => (
              <tr key={descuento.id}>
                <td>{descuento.fechaInicio}</td>
                <td>{descuento.fechaFin}</td>
                <td>{(descuento.porcentaje * 100).toFixed(2)}%</td>
                <td>{descuento.marca.nombre}</td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="4">No hay descuentos en productos</td>
            </tr>
          )}
        </tbody>
      </table>

      <h2>Tarjetas de Crédito</h2>
      <form>
        <table>
          <thead>
            <tr>
              <th>Número de Tarjeta</th>
              <th>Marca</th>
              <th>Seleccionar</th>
            </tr>
          </thead>
          <tbody>
            {tarjetas.length > 0 ? (
              tarjetas.map((tarjeta) => (
                <tr key={tarjeta.id}>
                  <td>{tarjeta.nro}</td>
                  <td>{tarjeta.marca}</td>
                  <td>
                    <input
                      type="radio"
                      name="tarjeta"
                      value={tarjeta.id}
                      checked={tarjetaSeleccionada === tarjeta.id} 
                      onChange={() => manejarSeleccionTarjeta(tarjeta.id)}
                    />
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="3">No hay tarjetas disponibles</td>
              </tr>
            )}
          </tbody>
        </table>
      </form>

      <h2>Descuentos en Tarjetas</h2>
      <table>
        <thead>
          <tr>
            <th>Fecha Inicio</th>
            <th>Fecha Fin</th>
            <th>Descuento (%)</th>
            <th>Tipo de Tarjeta</th>
          </tr>
        </thead>
        <tbody>
          {descuentosTarjetas.length > 0 ? (
            descuentosTarjetas.map((descuento) => (
              <tr key={descuento.id}>
                <td>{descuento.fechaInicio}</td>
                <td>{descuento.fechaFin}</td>
                <td>{(descuento.porcentaje * 100).toFixed(2)}%</td>
                <td>{descuento.bancoEmisor}</td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="4">No hay descuentos en tarjetas</td>
            </tr>
          )}
        </tbody>
      </table>

      <div>
        <button onClick={calcularTotal}>Calcular Total</button>
        <p>Total: ${totalCompra.toFixed(2)}</p>
      </div>

      <div>
        <button onClick={realizarCompra}>Realizar Compra</button>
      </div>
    </div>
  );
};

export default Tienda;
