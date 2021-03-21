CREATE TABLE workers (
    work_id SERIAL PRIMARY KEY,
    sername VARCHAR(40) NOT NULL,
    name VARCHAR(40) NOT NULL,
    patronymic VARCHAR(40),
    birthdate DATE NOT NULL,
    phone VARCHAR(12) NOT NULL,
    email VARCHAR(60) NOT NULL,
    address VARCHAR NOT NULL,
    first_day DATE NOT NULL,
    last_day DATE,
    post VARCHAR NOT NULL,
    salary MONEY NOT NULL
);

CREATE TABLE projects (
    proj_id SERIAL PRIMARY KEY,
    title VARCHAR NOT NULL,
    description TEXT,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP,
    status VARCHAR NOT NULL
);

CREATE TABLE payments (
    paym_id SERIAL PRIMARY KEY,
    work_id INT REFERENCES workers NOT NULL,
    type VARCHAR NOT NULL, 
    date_time TIMESTAMP NOT NULL,
    amount MONEY NOT NULL 
);

CREATE TABLE roles (
    role_id SERIAL PRIMARY KEY,
    work_id INT REFERENCES workers NOT NULL,
    proj_id INT REFERENCES projects NOT NULL,
    role VARCHAR NOT NULL,
    description TEXT, 
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP
);
