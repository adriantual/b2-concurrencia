// useDiscounts.js

import { useState, useEffect } from 'react';
import { listarDescuentos } from '../services/discountService';

const useDiscounts = () => {
  const [descuentos, setDescuentos] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchDescuentos = async () => {
      try {
        const descuentosData = await listarDescuentos();
        setDescuentos(descuentosData);
      } catch (error) {
        setError(error.message);
      } finally {
        setLoading(false);
      }
    };

    fetchDescuentos();
  }, []);

  return { descuentos, loading, error };
};

export default useDiscounts;
