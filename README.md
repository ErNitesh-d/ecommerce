# 🧾 E-Commerce API Documentation

---



### 1. Items -Task - API Endpoints

- `GET /api/items` – Retrieve all items.
- `GET /api/items/{itemId}` – Retrieve a specific item by ID.
- `POST /api/items` – Create a new item.
- `PUT /api/items/{itemId}` – Update an existing item.
- `DELETE /api/items/{itemId}` – Delete an item.

---

## CATEGORIES

### ➕ Add Sub-Category
**POST /api/categories**  
**Body:**
```json
{
  "name": "Mobile Phones",
  "parentId": 1
}

```
**Response Body:**
```json

{
    "id": 2,
    "name": "Mobile Phones",
    "parent": {
        "id": 1,
        "name": "Electronics",
        "parent": null
    }
}
```
### ➕ Add Top-Level Category
```json
{
  "name": "Electronics",
  "parentId": null
}

```
**Response Body:**
```json

{
    "id": 1,
    "name": "Electronics",
    "parent": null
}
```

- `GET /api/categories` – Fetch all categories

**Response Body:**
```json

[
    {
        "id": 1,
        "name": "Electronics",
        "parent": null
    },
    {
        "id": 2,
        "name": "Mobile Phones",
        "parent": {
            "id": 1,
            "name": "Electronics",
            "parent": null
        }
    },
    {
        "id": 3,
        "name": "I Phone",
        "parent": null
    }
]
```

## ITEMS

### ➕ Add Item
**POST /api/items**  
**Body:**

```json
{
  "name": "I Phone",
  "description": "Ergonomic wireless mouse with long battery life",
  "price": 1299.00,
  "categoryIds": [1,2]
}
```

**Response Body:**
```json
{
    "id": 1,
    "name": "I Phone",
    "description": "Ergonomic wireless mouse with long battery life",
    "price": 1299.0,
    "categories": [
        {
            "name": "Electronics",
            "id": 1
        },
        {
            "name": "Mobile Phones",
            "id": 2
        }
    ]
}
```

### ✏️ Update Item

**PUT /api/items/{itemId}**  

**Example:**

**PUT /api/items/1**  

**Body:**
```json
{
  "name": "Updated I Phone",
  "description": "Updated long battery life",
  "price": 1500.00,
  "categoryIds": [1]
}
```

**Response Body:**
```json
{
    "id": 1,
    "name": "Updated I Phone",
    "description": "Updated long battery life",
    "price": 1500.0,
    "categories": [
        {
            "id": 1,
            "name": "Electronics",
            "parent": null
        }
    ]
}
```

### ❌ Delete Item

`DELETE /api/items/{itemId}`

**Example:**

`DELETE /api/items/1`

### 📦 Fetch Items

- `GET /api/items` – All Items

**Response Body:**
```json
[
    {
        "id": 1,
        "name": "I Phone",
        "description": "Ergonomic wireless mouse with long battery life",
        "price": 1299.0,
        "categories": [
            {
                "id": 1,
                "name": "Electronics",
                "parent": null
            }
        ]
    },
    {
        "id": 2,
        "name": "2 I Phone",
        "description": "Ergonomic wireless mouse with long battery life",
        "price": 1299.0,
        "categories": [
            {
                "id": 1,
                "name": "Electronics",
                "parent": null
            }
        ]
    }
]
```
- `GET /api/items/{itemId}` – By ID

   **Example:**

- `GET /api/items/2` – By ID

**Response Body:**

```json
{
    "id": 2,
    "name": "2 I Phone",
    "description": "Ergonomic wireless mouse with long battery life",
    "price": 1299.0,
    "categories": [
        {
            "id": 1,
            "name": "Electronics",
            "parent": null
        }
    ]
}
```
- `GET /api/items/category/{categoryName}` – By Category

  **Example:**

- `GET /api/items/category/Electronics` – By Category

**Response Body:**
```json
[
    {
        "id": 1,
        "name": "I Phone",
        "description": "Ergonomic wireless mouse with long battery life",
        "price": 1299.0,
        "categories": [
            {
                "name": "Electronics",
                "id": 1
            }
        ]
    },
    {
        "id": 2,
        "name": "2 I Phone",
        "description": "Ergonomic wireless mouse with long battery life",
        "price": 1299.0,
        "categories": [
            {
                "name": "Electronics",
                "id": 1
            }
        ]
    }
]
```

- `GET /api/items/name/{itemName}` – By Name

**Example:**
- `GET /api/items/name/Freeze` – By Name
  
 **Response Body:**
```json
[
    {
        "id": 2,
        "name": "Freeze",
        "description": "Ergonomic wireless mouse with long battery life",
        "price": 15000.0,
        "categories": [
            {
                "name": "Electronics",
                "id": 1
            }
        ]
    }
]
``` 
- `GET /api/items/price/{price}` – By Price

**Example:**
  - `GET /api/items/price/15000` – By Price

 **Response Body:**
```json
[
    {
        "id": 2,
        "name": "Freeze",
        "description": "Ergonomic wireless mouse with long battery life",
        "price": 15000.0,
        "categories": [
            {
                "name": "Electronics",
                "id": 1
            }
        ]
    }
]

``` 

---

---

### 2. Orders -Task - API Endpoints

- `POST /api/orders` – Create a new order.
- `GET /api/orders` – Retrieve all orders.
- `GET /api/orders/{orderId}` – Retrieve a specific order by ID.
- `PUT /api/orders/{orderId}` – Update an existing order.
- `DELETE /api/orders/{orderId}` – Cancel an order.

---

## ORDERS

### 1. Create a New Order
**POST /api/orders**  
**Request Body:**
```json
{
  "itemIds": [1, 2, 3]
}
```
**Response:**
```json
{
  "id": 10,
  "orderDate": "2025-04-22T12:30:00",
  "totalAmount": 2798.00,
  "items": [
    { "id": 1, "name": "iPhone", "price": 1299.00 },
    { "id": 2, "name": "AirPods", "price": 499.00 }
  ]
}
```

### 🔹 2. Get All Orders
**GET /api/orders**  
**Response:**
```json
[
  {
    "id": 10,
    "orderDate": "2025-04-22T12:30:00",
    "totalAmount": 2798.00,
    "items": [
      { "id": 1, "name": "iPhone" },
      { "id": 2, "name": "AirPods" }
    ]
  }
]
```

### 🔹 3. Get Order by ID
**GET /api/orders/{orderId}**  
**Example:** `/api/orders/10`  
**Response:**
```json
{
  "id": 10,
  "orderDate": "2025-04-22T12:30:00",
  "totalAmount": 2798.00,
  "items": [
    { "id": 1, "name": "iPhone" },
    { "id": 2, "name": "AirPods" }
  ]
}
```

### 🔹 4. Update Order by ID
**PUT /api/orders/{orderId}**  
**Request Body:**
```json
{
  "itemIds": [3, 4]
}
```
**Response:**
```json
{
  "id": 10,
  "orderDate": "2025-04-22T12:30:00",
  "totalAmount": 1399.00,
  "items": [
    { "id": 3, "name": "MacBook Charger" },
    { "id": 4, "name": "USB Cable" }
  ]
}
```

### 🔹 5. Delete/Cancel Order by ID
**DELETE /api/orders/{orderId}**  
**Response:** `204 No Content` (Successful deletion)

---

## 🛒 CART (Optional)

### ➕ Create Cart for a User
**POST /api/carts**  
**Body:**
```json
{
  "userId": 1
}
```

### ➕ Add Item to Cart
**POST /api/carts/1/items**  
**Body:**
```json
{
  "userId": 1,
  "itemId": 1,
  "quantity": 2
}
```

### 📋 Get Cart Items
**GET /api/carts/{cartId}?userId=1**

### ❌ Remove Item from Cart
**DELETE /api/carts/{cartId}/items/{itemId}?userId=1**

### ✅ Checkout Cart
**POST /api/carts/{cartId}/checkout?userId=1**

---
