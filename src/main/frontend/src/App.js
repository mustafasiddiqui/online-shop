import { useState, useEffect } from 'react'
import './App.css';
import ProductList from './ProductList';

function App() {
  const [products, setProducts] = useState([])

  useEffect(() => {
    let ignore = false;
    setProducts([]);
    fetch('http://localhost:8080/api/product')
    .then(response => response.json())
    .then(data => {
      if (!ignore) {
           setProducts(data);
      }
    });
    return () => {
      ignore = true;
    };
  }, []);

  return (
    <div className="App">
      <ProductList products={products} />
    </div>
  );
}

export default App;
