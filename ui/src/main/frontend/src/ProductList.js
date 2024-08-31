import Container from 'react-bootstrap/Container'
import ProductSummary from "./ProductSummary";

export default function ProductList({ products }) {

  const productList = products.map(product => {
    return <ProductSummary key={product.id} product={product} />
  });

  return (
    <div>
      <h2>Products</h2>
      <Container className="container" fluid>
        {productList}
      </Container>
    </div>
  )
}
