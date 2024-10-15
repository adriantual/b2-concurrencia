// App.jsx
/*
import React from 'react';
import './styles/styles.css';  // Importar el archivo de estilos
import PurchaseView from './pages/PurchaseView';  // Importar la vista principal

function App() {
  return (
    <div className="App">
      <PurchaseView />
    </div>
  );
}

export default App;

*/

// src/App.jsx
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import ClienteForm from './components/ClienteForm';
import Tienda from './components/Tienda';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<ClienteForm />} />
        <Route path="/tienda" element={<Tienda />} />
      </Routes>
    </Router>
  );
}

export default App;
