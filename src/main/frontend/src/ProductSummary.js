import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';


export default function ProductSummary({ product }) {
  return (
    <Card border="primary">
      <Card.Title>{product.name}</Card.Title>
      <Card.Body>
        <Card.Text>Product Price here.</Card.Text>
      </Card.Body>
      <Card.Footer>
        <Button>View details</Button>
      </Card.Footer>
    </Card>
  );
}
