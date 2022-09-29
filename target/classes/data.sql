INSERT INTO user_addresses(city, country, street, zipcode)
VALUES
('City1', 'Country1', 'Street1', 'Zipcode1'),
('City2', 'Country2', 'Street2', 'Zipcode2'),
('City3', 'Country3', 'Street3', 'Zipcode3'),
('City4', 'Country4', 'Street4', 'Zipcode4');

INSERT INTO users(name, username, password, address_address_id)
VALUES
('User1', 'Username1', '12345', 1),
('User2', 'Username2', '12345', 2),
( 'User3', 'Username3', '12345', 3),
('User4', 'Username4', '12345', 4);

INSERT INTO todos(completed, title, user_id)
VALUES
(true, 'Title1', 1),
(false, 'Title2', 1),
(true, 'Title3', 2),
(false, 'Title4', 2),
(true, 'Title5', 3),
(false, 'Title6', 3),
(true, 'Title7', 4),
(false, 'Title8', 4);