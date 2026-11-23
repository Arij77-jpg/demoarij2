-- Lab 4 Step 7: Seed sample data
INSERT INTO products (id, name, category, price) VALUES (1, 'Wireless Mouse', 'electronics', 19.99);
INSERT INTO products (id, name, category, price) VALUES (2, 'Mechanical Keyboard', 'electronics', 79.99);
INSERT INTO products (id, name, category, price) VALUES (3, 'Desk Lamp', 'home', 24.50);
INSERT INTO products (id, name, category, price) VALUES (4, 'Noise Cancelling Headphones', 'electronics', 149.90);
INSERT INTO products (id, name, category, price) VALUES (5, 'Cotton Hoodie', 'clothing', 45.00);
INSERT INTO products (id, name, category, price) VALUES (6, 'Jakarta EE with JPA Guide', 'books', 39.99);

-- Chapter 4 Activity 1: Seed reviews
INSERT INTO reviews (id, rating, comment, author, product_id) VALUES (1, 5, 'Super smooth and battery lasts forever!', 'Amine', 1);
INSERT INTO reviews (id, rating, comment, author, product_id) VALUES (2, 4, 'Great ergonomic feel for working all day.', 'Arij', 1);
INSERT INTO reviews (id, rating, comment, author, product_id) VALUES (3, 5, 'Best mechanical keyboard for coding!', 'Sami', 2);
