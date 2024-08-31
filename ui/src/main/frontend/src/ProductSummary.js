import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';

export default function ProductSummary({ product }) {
  return (
    <Card border="primary">
      {product.url != null && <Card.Img src={product.url} />}
      <Card.Title>{product.name}</Card.Title>
      <Card.Body>
        <Card.Text>{product.price == null ? 'No price found.' : '$' + product.price.toFixed(2)}</Card.Text>
      </Card.Body>
      <Card.Footer>
        <Button>View details</Button>
      </Card.Footer>
    </Card>
  );
}
