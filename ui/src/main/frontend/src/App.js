import { useState, useEffect } from 'react'
import './App.css';
import ProductList from './ProductList';

function App() {
  const [products, setProducts] = useState([])

  useEffect(() => {
    let ignore = false;
    setProducts([]);
    const myHeaders = new Headers();
    myHeaders.append("X-API-KEY", "SHOP_API_KEY");
    fetch('http://localhost:9000/api/product', {
      headers: myHeaders
    })
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
