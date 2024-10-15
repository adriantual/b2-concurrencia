// DiscountList.jsx

import React, { useEffect, useState } from 'react';
import { listarDescuentos } from '../services/discountService';

const DiscountList = ({ onDiscountSelect }) => {
  const [descuentos, setDescuentos] = useState([]);

  useEffect(() => {
    const fetchDescuentos = async () => {
      try {
        const descuentosData = await listarDescuentos();
        setDescuentos(descuentosData);
      } catch (error) {
        console.error('Error al cargar descuentos:', error.message);
      }
    };

    fetchDescuentos();
  }, []);

  const handleDiscountChange = (e) => {
    const selectedDiscountId = e.target.value;
    const selectedDiscount = descuentos.find((discount) => discount.id === parseInt(selectedDiscountId));
    onDiscountSelect(selectedDiscount);
  };

  return (
    <div>
      <h3>Seleccionar Descuento</h3>
      <select onChange={handleDiscountChange}>
        <option value="">Seleccione un descuento</option>
        {descuentos.map((descuento) => (
          <option key={descuento.id} value={descuento.id}>
            {descuento.nombre} - {descuento.porcentaje}%
          </option>
        ))}
      </select>
    </div>
  );
};

export default DiscountList;
