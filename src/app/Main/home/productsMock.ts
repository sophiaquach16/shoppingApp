export interface Product {
    product_id: number;
    product_name: string;
    retail_price: number;
    description: string;
    product_category_tree: string;
    image: string;
  }
  

  // for testing only
  export const products = [
    {
      id: 1,
      name: 'Item1',
      price: 799,
      description: 'A large phone with one of the best screens',
      quantity: 10,
      imgUrl: 'https://cdn11.bigcommerce.com/s-hsi95a83fz/product_images/uploaded_images/5903-beach-shorts-orange-thumb.jpg?t=1585629450&_ga=2.88311448.2069277356.1585516345-1869486607.1578452111'
    },
    {
      id: 2,
      name: 'Item2',
      price: 699,
      description: 'A great phone with one of the best cameras',
      quantity: 10,
      imgUrl: 'https://cdn11.bigcommerce.com/s-hsi95a83fz/product_images/uploaded_images/5903-beach-shorts-orange-thumb.jpg?t=1585629450&_ga=2.88311448.2069277356.1585516345-1869486607.1578452111'
    
    },
    {
      id: 3,
      name: 'Item3',
      price: 299,
      description: '',
      quantity: 10,
      imgUrl: 'https://cdn11.bigcommerce.com/s-hsi95a83fz/product_images/uploaded_images/5903-beach-shorts-orange-thumb.jpg?t=1585629450&_ga=2.88311448.2069277356.1585516345-1869486607.1578452111'
    
    },

    {
      id: 4,
      name: 'Item4',
      price: 299,
      description: '',
      quantity: 10,
      imgUrl: 'https://cdn11.bigcommerce.com/s-hsi95a83fz/product_images/uploaded_images/5903-beach-shorts-orange-thumb.jpg?t=1585629450&_ga=2.88311448.2069277356.1585516345-1869486607.1578452111'
    
    },

    {
      id: 5,
      name: 'Item5',
      price: 299,
      description: '',
      quantity: 10,
      imgUrl: 'https://cdn11.bigcommerce.com/s-hsi95a83fz/product_images/uploaded_images/5903-beach-shorts-orange-thumb.jpg?t=1585629450&_ga=2.88311448.2069277356.1585516345-1869486607.1578452111'
    
    },

    {
      id: 6,
      name: 'Item6',
      price: 299,
      description: '',
      quantity: 10,
      imgUrl: 'https://cdn11.bigcommerce.com/s-hsi95a83fz/product_images/uploaded_images/5903-beach-shorts-orange-thumb.jpg?t=1585629450&_ga=2.88311448.2069277356.1585516345-1869486607.1578452111'
    
    }
  ];