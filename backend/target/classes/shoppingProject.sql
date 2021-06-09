drop database if exists ShoppingProject;

create database ShoppingProject;

use ShoppingProject;

create table Product (
	product_id varchar(100) primary key,
    product_url varchar(250) null,
    product_name varchar(250) null,
    product_category_tree varchar(500) null,
    pid varchar(100) null,
    retail_price int null,
    discounted_price int null,
    image text null,
    `description` text null,
    product_rating varchar(250) null,
	overall_rating varchar(250) null,
    brand varchar(100) null
);

create table `User` (
	user_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name varchar(250) null,
	last_name varchar(250) null,
    `password` varchar(250) null
);
create table Cart (
	user_id INT NOT NULL,
    product_id varchar(100) NOT NULL,
    count INT NOT NULL default 1,
    PRIMARY KEY pk_Cart (user_id, product_id),
	FOREIGN KEY fk_Cart_User (user_id)
		REFERENCES `User`(user_id),
	FOREIGN KEY fk_Cart_Product (product_id)
		REFERENCES `Product`(product_id));
SET GLOBAL local_infile = true;
LOAD DATA LOCAL INFILE '/Users/tanse/Downloads/flipkart_com-ecommerce_sample/product_100limit.csv' 
INTO TABLE Product FIELDS TERMINATED BY ',' 
OPTIONALLY ENCLOSED BY '"'
LINES TERMINATED BY '\n' 
IGNORE 1 ROWS (product_id, product_url, product_name, product_category_tree, pid, retail_price, discounted_price, image, `description`, product_rating, overall_rating, brand);

INSERT INTO `User` (`first_name`, last_name, `password`) VALUES
    ('1test', '2test', 'root'),
    ('Shirou', 'Emiya', 'admin');
    
INSERT INTO cart (`user_id`, product_id, `count`) VALUES
    (2, '05324708277dbf2f3e8b55a660e91988', 2),
    (1, '08cb8617f5aacbe98024867f509f6bbc', 3);
select * from product;
select * from `User`;
select * from cart;
