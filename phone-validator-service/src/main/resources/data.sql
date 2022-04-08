INSERT INTO country VALUES
(1, '+237', 'Cameroon', '\(237\)\ ?[2368]\d{7,8}$');
INSERT INTO country VALUES
(2,  '+251', 'Ethiopia','\(251\)\ ?[1-59]\d{8}$');
INSERT INTO country VALUES
(3, '+212', 'Morocco',  '\(212\)\ ?[5-9]\d{8}$');
INSERT INTO country VALUES
(4, '+258', 'Mozambique',  '\(258\)\ ?[28]\d{7,8}$');
INSERT INTO country VALUES
(5, '+256', 'Uganda', '\(256\)\ ?\d{9}$');

delete from country where name not null;
