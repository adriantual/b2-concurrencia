// CreditCardSelect.jsx

import React, { useEffect, useState } from 'react';
import { listarTarjetas } from '../services/clientService';

const CreditCardSelect = ({ clienteId, onCardSelect }) => {
  const [tarjetas, setTarjetas] = useState([]);

  useEffect(() => {
    const fetchTarjetas = async () => {
      try {
        const tarjetasData = await listarTarjetas(clienteId);
        setTarjetas(tarjetasData);
      } catch (error) {
        console.error('Error al cargar tarjetas:', error.message);
      }
    };

    if (clienteId) {
      fetchTarjetas();
    }
  }, [clienteId]);

  const handleCardChange = (e) => {
    const selectedCardId = e.target.value;
    const selectedCard = tarjetas.find((card) => card.id === parseInt(selectedCardId));
    onCardSelect(selectedCard);
  };

  return (
    <div>
      <h3>Seleccionar Tarjeta</h3>
      <select onChange={handleCardChange}>
        <option value="">Seleccione una tarjeta</option>
        {tarjetas.map((tarjeta) => (
          <option key={tarjeta.id} value={tarjeta.id}>
            {tarjeta.bancoEmisor} - {tarjeta.numero}
          </option>
        ))}
      </select>
    </div>
  );
};

export default CreditCardSelect;
