// App.jsx
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import ClienteForm from './components/ClienteForm';
import EditProduct from './components/EditProduct';
import ProductList from './components/ProductList';
import HomePage from './components/HomePage'; // Importar el nuevo componente HomePage
import Tienda from './components/Tienda'; // Suponiendo que Tienda es la vista después de seleccionar el cliente

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage />} /> {/* Ruta de la pantalla principal */}
        <Route path="/tp1-al-4" element={<ClienteForm />} /> {/* Ruta para el TP1 al 4 */}
        <Route path="/tp5" element={<ProductList />} /> {/* Ruta para el TP5 */}
        <Route path="/tienda" element={<Tienda />} /> {/* Ruta para la tienda */}
        <Route path="/editar-producto/:id" element={<EditProduct />} /> {/* Cerrado correctamente */}
      </Routes>
    </Router>
  );
}

export default App;
