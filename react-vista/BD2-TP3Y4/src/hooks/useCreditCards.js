// useCreditCards.js

import { useState, useEffect } from 'react';
import { listarTarjetas } from '../services/clientService';

const useCreditCards = (clienteId) => {
  const [tarjetas, setTarjetas] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchTarjetas = async () => {
      if (!clienteId) return;

      try {
        const tarjetasData = await listarTarjetas(clienteId);
        setTarjetas(tarjetasData);
      } catch (error) {
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchTarjetas();
  }, [clienteId]);

  return { tarjetas, loading, error };
};

export default useCreditCards;
