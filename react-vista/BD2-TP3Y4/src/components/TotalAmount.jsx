function TotalAmount({ selectedProducts, calculateTotal }) {
    return (
      <div>
        <h3>Total Amount</h3>
        <button onClick={calculateTotal}>Calculate Total</button>
      </div>
    );
  }
  
  export default TotalAmount;
  