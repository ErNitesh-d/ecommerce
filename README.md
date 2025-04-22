# 🧾 E-Commerce API Documentation


---
TASK-API Endpoints
---

---
-
1. Items

GET /api/items: Retrieve all items.

GET /api/items/{itemId}: Retrieve a specific item by ID.

POST /api/items: Create a new item.

PUT /api/items/{itemId}: Update an existing item.

DELETE /api/items/{itemId}: Delete an item.


---
------------------------COMPLETED
---
------------------------

---
-------CATEGORIES
---

---
-----------------
Body (JSON):

### ADD SUB-CATEGORY
### /api/categories
{
  "name": "Mobile Phones",
  "parentId": 1
}


---

---
---
If it's a top-level category:
### ADD CATEGORY
{
  "name": "Electronics",
  "parentId": null
}

### FATCH CATEGORIESS
GET /api/categories


---

---
---


---
TASK-API Endpoints
---

---
-
2. Orders

POST /api/orders: Create a new order.

GET /api/orders: Retrieve all orders.

GET /api/orders/{orderId}: Retrieve a specific order by ID.

PUT /api/orders/{orderId}: Update an existing order.

DELETE /api/orders/{orderId}: Cancel an order.


---
-------ITEMS
---

---
-------------

### ADD ITEM
POST /api/items
{
    "name": "I Phone",
    "description": "Ergonomic wireless mouse with long battery life",
    "price": 1299.00,
    "categoryIds": [1,2] -->Item can be from One or more category
}



---

---

---

---
-------
### UPDATE ITEM WITH ID
PUT /api/items/{itemId}
  {
    "name": "UPDATE Logitech Wireless Mouse",
    "description": "Ergonomic wireless mouse with long battery life",
    "price": 1299.00,
    "categoryIds": [6]
  }
  

---

---

---

---
-------

### DELETE ITEMS WITH ID 
DELETE /api/items/{itemId}


---

---

---

---
-------

### FATCH ALL ITEMS 
GET /api/items

### FATCH ITEMS WITH ID 
GET /api/items/{itemId}

### FILTERING AND SEARCHING
### FATCH ITEMS WITH CATEGORY-NAME
GET /api/items/category/{categoryName}

### FATCH ITEMS WITH ITEM-NAME
GET /api/items/name/{itemName}

### FATCH ITEMS WITH PRICE
GET /api/items/price/{price}


---

---

---

---
-------


---
-------ORDERS
---

---
-------------

 1. Create a New Order
**URL:** POST /api/orders
**Request Body:**

```json
{
  "itemIds": [1, 2, 3]
}
```

**Response:**
```json

```json
{
  "id": 10,
  "orderDate": "2025-04-22T12:30:00",
  "totalAmount": 2798.00,
  "items": [
    {
      "id": 1,
      "name": "iPhone",
      "price": 1299.00
    },
    {
      "id": 2,
      "name": "AirPods",
      "price": 499.00
    }
  ]
}


---

---

---
------
🔸 2. Get All Orders
**URL:** GET /api/orders
```

**Response:**
```json

```json
[
  {
    "id": 10,
    "orderDate": "2025-04-22T12:30:00",
    "totalAmount": 2798.00,
    "items": [
      {
        "id": 1,
        "name": "iPhone"
      },
      {
        "id": 2,
        "name": "AirPods"
      }
    ]
  },
  ...
]



---

---

---
------
🔸 3. Get Order by ID
**URL:** GET /api/orders/{orderId}
**Example:** GET /api/orders/10
```

**Response:**
```json

```json
{
  "id": 10,
  "orderDate": "2025-04-22T12:30:00",
  "totalAmount": 2798.00,
  "items": [
    {
      "id": 1,
      "name": "iPhone"
    },
    {
      "id": 2,
      "name": "AirPods"
    }
  ]
}


---

---

---
------
🔸 4. Update Order by ID
**URL:** PUT /api/orders/{orderId}
**Example:** PUT /api/orders/10
**Request Body:**

```json
{
  "itemIds": [3, 4]
}
```

**Response:**
```json (Updated order with recalculated total)

```json
{
  "id": 10,
  "orderDate": "2025-04-22T12:30:00",
  "totalAmount": 1399.00,
  "items": [
    {
      "id": 3,
      "name": "MacBook Charger"
    },
    {
      "id": 4,
      "name": "USB Cable"
    }
  ]
}

---

---

---
------
🔸 5. Delete/Cancel Order by ID
**URL:** DELETE /api/orders/{orderId}
**Example:** DELETE /api/orders/10
```

**Response:**
```json 204 No Content (Successful deletion)


---

---
--------------------

Miscellaneous
---
TASK-API Endpoints
---

---
-

---
------------  CART(OPTIONAL) 
---

---
---------
### Create Cart for a User

**Method:** POST

**URL:** /api/carts

Body (JSON):
{
  "userId": 1
}

---

---
----------------------
### Add Item to Cart

**Method:** POST
**URL:** /api/carts/1/items
Body (JSON):
{
  "userId": 1,
  "itemId": 1,
  "quantity": 2
}

---

---
----------------------
### 🛒 Get Cart Items
**Method:** GET
**Description:** Fetch all items in the cart for the given user and cart.

**URL:** /api/carts/{cartId}?userId=1

**Example:**
/api/carts/1?userId=1


---

---

---
-----------
❌ Remove Item from Cart
**Method:** DELETE

**URL:** /api/carts/{cartId}/items/{itemId}?userId=1
**Description:** Removes a specific item from the cart for the given user.

DELETE http://localhost:8080/api/carts/1/items/10?userId=1


---

---

---
------------
Checkout Cart

**Method:** POST
**URL:** /api/carts/{cartId}/checkout?userId=1

**Description:** Completes the purchase of the cart and clears the cart.

**Example:**
POST http://localhost:8080/api/carts/1/checkout?userId=1



---

---

---

---
-------



```