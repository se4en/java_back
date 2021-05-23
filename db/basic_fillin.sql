INSERT INTO workers 
(sername, name, patronymic, birthdate, phone, email,
address, first_day, last_day, post, salary) VALUES
    ('Brazhnikov', 'Anton', 'Olegovich', '1989-03-11', '+79995657112', 'bran@yandex.ru',
     'MOSCOW, Shosse Entuziastov, 22/1, 146', '2020-10-23', NULL, 'director', '150000'),
    ('Serebryakov', 'Serhey', 'Ivanovich', '1985-11-03', '+79991335689', 'sergser@yandex.ru',
     'MOSCOW, Kalashnikova, 2, 13', '2020-10-23', NULL, 'executive director', '130000'),
    ('Ivanenko', 'Elizaveta', 'Sergeevna', '1993-01-29', '+79261671917', 'liza94ivan@gmail.com',
     'MOSCOW, Leningradskiy prospect, 132/2, 10', '2020-10-25', NULL, 'analyst', '110000'),
    ('Ontonenko', 'Leonid', 'Ilich', '1984-02-11', '+79995657112', 'b1390ONT@gmail.com',
     'MOSCOW, Nosovihinnskoe shosse, 146, 1', '2020-10-25', '2021-01-30', 'programmer', '100000'),
    ('Li', 'Ismail', 'Karenovich', '1991-12-08', '+79038990078', 'LiGLi@mail.ru',
     'MOSCOW OBLAST, Balashiha, Lenina, 20, 225', '2020-10-30', NULL, 'product manager', '90000'),
    ('Galajyan', 'Karen', 'Ravatnovich', '1995-08-02', '+79995457552', 'br95@outlook.com',
     'MOSCOW, Shosse Entuziastov, 102, 8', '2021-02-03', NULL, 'programmer', '80000');

INSERT INTO projects 
(title, description, start_date, end_date, status) VALUES
    ('Chatbot', 'Fantasy helper telegram bot', '2020-12-01', '2021-03-01', 'In development'),
    ('WEB-site', NULL, '2020-12-01', '2021-01-01', 'Active'),
    ('WEB-site', 'Fantasy helper web-site', '2020-12-14', '2021-01-31', 'Close'),
    ('Descktop app', 'School portal', '2021-02-01', NULL, 'Support'),
    ('Mobiel app', 'Online auio meeting app', '2021-02-14', '2022-01-01', 'In development');

INSERT INTO payments
(work_id, type, date, amount) VALUES
    (1, 'Salary', '2020-12-15', '100000'),
    (2, 'For successful project', '2020-12-15', '100000'),
    (3, 'For the length of service', '2020-12-15', '5000'),
    (4, 'New Year bonus', '2020-12-15', '6000'),
    (5, 'Birthday bonus', '2020-12-15', '10000'),
    (6, 'Company birthday bonus', '2020-12-15', '15000');

INSERT INTO roles
(work_id, proj_id, role, description, start_date, end_date) VALUES
    (1, 5, 'Director', 'Lead team', '2020-12-01', '2020-12-01'),
    (2, 4, 'Web-designer', 'Create design','2020-12-15', NULL),
    (3, 3, 'Architect', 'Build backend', '2020-12-15', '2021-02-01'),
    (4, 2, 'Analyst', 'Analyze the market', '2020-12-15', NULL),
    (5, 1, 'Product manager', 'Promote the product', '2020-12-28', NULL),
    (6, 5, 'Programmer', 'Code frontend', '2020-12-15', '2021-01-15');