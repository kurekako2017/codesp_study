-- Initial data for H2 database
-- This file will be automatically executed by Spring Boot on startup

-- Insert default categories
INSERT INTO CATEGORY(category_id, name) VALUES 
(1, 'Fruits'),
(2, 'Vegetables'),
(3, 'Meat'),
(4, 'Fish'),
(5, 'Dairy'),
(6, 'Bakery'),
(7, 'Drinks'),
(8, 'Sweets'),
(9, 'Other');

-- Insert default customers
INSERT INTO CUSTOMER(id, address, email, password, role, username) VALUES
(1, '123, Albany Street', 'admin@nyan.cat', '123', 'ROLE_ADMIN', 'admin'),
(2, '765, 5th Avenue', 'lisa@gmail.com', '765', 'ROLE_NORMAL', 'lisa');

-- Insert default products
INSERT INTO PRODUCT(product_id, description, image, name, price, quantity, weight, category_id) VALUES
(1, 'Fresh and juicy', 'https://freepngimg.com/save/9557-apple-fruit-transparent/744x744', 'Apple', 3, 40, 76, 1),
(2, 'Woops! There goes the eggs...', 'https://www.nicepng.com/png/full/813-8132637_poiata-bunicii-cracked-egg.png', 'Cracked Eggs', 1, 90, 43, 9);
