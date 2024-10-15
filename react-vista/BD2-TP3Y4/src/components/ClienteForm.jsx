import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { listarClientes } from '../services/clientService'; // Asegúrate de tener la ruta correcta
import '../styles/ClienteForm.css'; // La importación correcta del archivo de estilos

const ClienteForm = () => {
  const [clientes, setClientes] = useState([]); // Estado para los clientes
  const [clienteSeleccionado, setClienteSeleccionado] = useState(null); // Estado para cliente seleccionado
  const navigate = useNavigate();

  // Cargar los clientes al montar el componente
  useEffect(() => {
    const fetchClientes = async () => {
      const clientesData = await listarClientes();
      setClientes(clientesData);
    };

    fetchClientes();
  }, []);

  const handleClienteSeleccionado = (cliente) => {
    setClienteSeleccionado(cliente);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (clienteSeleccionado) {
      navigate('/tienda', { state: { clienteId: clienteSeleccionado.id } });
    } else {
      alert('Debe seleccionar un cliente');
    }
  };

  return (
    <div>
      <h2>Seleccionar Cliente</h2>
      <form onSubmit={handleSubmit}>
        <table>
          <thead>
            <tr>
              <th>Nombre</th>
              <th>Apellido</th>
              <th>DNI</th>
              <th>Email</th>
              <th>Seleccionar</th>
            </tr>
          </thead>
          <tbody>
            {clientes.length > 0 ? (
              clientes.map((cliente) => (
                <tr key={cliente.id}>
                  <td>{cliente.nombre}</td>
                  <td>{cliente.apellido}</td>
                  <td>{cliente.dni}</td>
                  <td>{cliente.email}</td>
                  <td>
                    <input
                      type="radio"
                      name="cliente"
                      value={cliente.id}
                      onChange={() => handleClienteSeleccionado(cliente)}
                    />
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="5">No hay clientes disponibles</td>
              </tr>
            )}
          </tbody>
        </table>
        <button type="submit">Ingresar a la Tienda</button>
      </form>
    </div>
  );
};

export default ClienteForm;
