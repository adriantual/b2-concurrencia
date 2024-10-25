// src/components/HomePage.jsx
import React from 'react';
import { useNavigate } from 'react-router-dom';

const HomePage = () => {
  const navigate = useNavigate();

  const handleTp1To4Click = () => {
    navigate('/tp1-al-4'); // Redirige a la vista del TP1 al 4
  };

  const handleTp5Click = () => {
    navigate('/tp5'); // Redirige a la vista del TP5
  };

  return (
    <div>
      <h1>Página Principal</h1>
      <button onClick={handleTp1To4Click}>Frontend TP1 al 4</button>
      <button onClick={handleTp5Click}>Frontend TP5</button>
    </div>
  );
};

export default HomePage;
