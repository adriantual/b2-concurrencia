// PurchaseButton.jsx

import React from 'react';

const PurchaseButton = ({ realizarCompra }) => {
  return (
    <button onClick={realizarCompra}>Realizar Compra</button>
  );
};

export default PurchaseButton;
