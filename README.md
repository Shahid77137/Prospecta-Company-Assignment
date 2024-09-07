# Prospecta-Company-Assignment

# Product Management API
 The Product Management API is a Spring Boot-based RESTful service designed to interact with the FakeStoreAPI for managing product data. It enables users to perform key operations such as retrieving product listings by category, adding new products, and managing product information efficiently.


## Features
1. List Products by Category: Fetch product details based on the provided category detail.
2. Add New Product: Add a new product to the FakeStore database using POST requests.
   
## Technologies Used
- Java
- Spring Boot
- RestTemplate for API calls

 ### Setup and Installation

 **Clone the repository:**

```
git clone https://github.com/your-username/product-management-api.git
cd product-management-api

```

#### The application will be accessible at http://localhost:8888

### Build and Run the Application
```
mvn clean install
mvn spring-boot:run
```

### API Endpoints
- **List of Products by Category:** `GET /products/category/{category}`

##### Request
```

GET  `http://localhost:8080/products/category/electronics`

```
##### Response

```

[
    {
        "id": 9,
        "title": "WD 2TB Elements Portable External Hard Drive - USB 3.0 ",
        "price": 64,
        "description": "USB 3.0 and USB 2.0 Compatibility Fast data transfers Improve PC Performance High Capacity; Compatibility Formatted NTFS for Windows 10, Windows 8.1, Windows 7; Reformatting may be required for other operating systems; Compatibility may vary depending on user’s hardware configuration and operating system",
        "category": "electronics",
        "image": "https://fakestoreapi.com/img/61IBBVJvSDL._AC_SY879_.jpg",
        "rating": {
            "rate": 3.3,
            "count": 203
        }
    },
    {
        "id": 10,
        "title": "SanDisk SSD PLUS 1TB Internal SSD - SATA III 6 Gb/s",
        "price": 109,
        "description": "Easy upgrade for faster boot up, shutdown, application load and response Performance may vary depending upon drive capacity, host device, OS and application.)",
        "category": "electronics",
        "image": "https://fakestoreapi.com/img/61U7T1koQqL._AC_SX679_.jpg",
        "rating": {
            "rate": 2.9,
            "count": 470
        }
    }
]

```
- **Add New Product:** `POST /products`

##### Request Body:

```
{
    "title": "HP Laptop",
    "price": 30000,
    "description": "A new product HP Laptop added to the store",
    "category": "electronics",
    "image": "https://m.media-amazon.com/images/I/4143CSktumL._SX300_SY300_QL70_FMwebp_.jpg"
}


```
##### Response:
```

{
    "id": 21,
    "title": "HP Laptop",
    "price": 30000,
    "description": "A new product HP Laptop added to the store",
    "image": "https://m.media-amazon.com/images/I/4143CSktumL._SX300_SY300_QL70_FMwebp_.jpg",
    "category": "electronics"
}

```
# CSV Handling
1. Process CSV with Formulas
- **Endpoint:** POST `http://localhost:8080/csv/process`
- **Description:** Upload a CSV file with values and formulas. The API will evaluate the formulas and return a processed CSV file with the results.
- **Request Body:**  CSV file upload
- **Input CSV:**
 ```
  A1, B1, C1
 5, 10, =A1+B1
 7, 8, =A2+B2
 9, =4+5, =C2+B3

 ```
- **Response CSV:**
 ```
 5,3,10
 7,8,15
 9,9,24

 ```


