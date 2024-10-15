// TotalPriceButton.jsx

import React from 'react';

const TotalPriceButton = ({ calcularTotal }) => {
  return (
    <button onClick={calcularTotal}>Calcular Total</button>
  );
};

export default TotalPriceButton;
