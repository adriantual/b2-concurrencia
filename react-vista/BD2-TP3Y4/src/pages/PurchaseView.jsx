// PurchaseView.jsx

import React, { useState } from 'react';
import ProductList from '../components/ProductList';
import DiscountList from '../components/DiscountList';
import CreditCardSelect from '../components/CreditCardSelect';
import TotalPriceButton from '../components/TotalPriceButton';
import PurchaseButton from '../components/PurchaseButton';
import ErrorMessage from '../components/ErrorMessage';

import useProducts from '../hooks/useProducts';
import useDiscounts from '../hooks/useDiscounts';
import useCreditCards from '../hooks/useCreditCards';
import useTotalPrice from '../hooks/useTotalPrice';

const PurchaseView = () => {
  const { productos, loading: productosLoading, error: productosError } = useProducts();
  const { descuentos, loading: descuentosLoading, error: descuentosError } = useDiscounts();
  const [selectedProducts, setSelectedProducts] = useState([]);
  const [selectedDiscount, setSelectedDiscount] = useState(null);
  const [selectedCreditCard, setSelectedCreditCard] = useState(null);
  const [errorMessage, setErrorMessage] = useState(null);

  const totalPrice = useTotalPrice(selectedProducts, selectedDiscount);
  
  const handlePurchase = () => {
    if (selectedProducts.length === 0) {
      setErrorMessage('Debe seleccionar al menos un producto.');
      return;
    }
    if (!selectedCreditCard) {
      setErrorMessage('Debe seleccionar una tarjeta de crédito.');
      return;
    }
    setErrorMessage(null);
    // Aquí puedes llamar a un servicio para realizar la compra
    alert('Compra realizada con éxito!');
  };

  return (
    <div className="purchase-container">
      <h1>Realizar Compra</h1>
      
      {/* Error Message */}
      {errorMessage && <ErrorMessage message={errorMessage} />}

      {/* Lista de productos */}
      <section>
        <h2>Productos</h2>
        {productosLoading && <p>Cargando productos...</p>}
        {productosError && <ErrorMessage message={productosError} />}
        <ProductList productos={productos} selectedProducts={selectedProducts} setSelectedProducts={setSelectedProducts} />
      </section>

      {/* Lista de descuentos */}
      <section>
        <h2>Descuentos</h2>
        {descuentosLoading && <p>Cargando descuentos...</p>}
        {descuentosError && <ErrorMessage message={descuentosError} />}
        <DiscountList descuentos={descuentos} selectedDiscount={selectedDiscount} setSelectedDiscount={setSelectedDiscount} />
      </section>

      {/* Selección de tarjeta de crédito */}
      <section>
        <h2>Tarjetas de Crédito</h2>
        <CreditCardSelect selectedCreditCard={selectedCreditCard} setSelectedCreditCard={setSelectedCreditCard} />
      </section>

      {/* Botón para calcular el total */}
      <section>
        <TotalPriceButton totalPrice={totalPrice} />
      </section>

      {/* Botón para confirmar la compra */}
      <section>
        <PurchaseButton onPurchase={handlePurchase} />
      </section>
    </div>
  );
};

export default PurchaseView;
