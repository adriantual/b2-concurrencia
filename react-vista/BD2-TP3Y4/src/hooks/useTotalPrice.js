// useTotalPrice.js

import { useState, useEffect } from 'react';

const useTotalPrice = (selectedProducts, selectedDiscount) => {
  const [total, setTotal] = useState(0);

  useEffect(() => {
    const calculateTotal = () => {
      if (!selectedProducts || selectedProducts.length === 0) return 0;

      const productTotal = selectedProducts.reduce((sum, product) => sum + product.precio, 0);

      if (selectedDiscount) {
        const discountAmount = productTotal * (selectedDiscount.porcentaje / 100);
        return productTotal - discountAmount;
      }

      return productTotal;
    };

    const totalAmount = calculateTotal();
    setTotal(totalAmount);
  }, [selectedProducts, selectedDiscount]);

  return total;
};

export default useTotalPrice;
