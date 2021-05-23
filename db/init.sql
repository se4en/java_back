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
    salary BIGINT NOT NULL
);

CREATE TABLE projects (
    proj_id SERIAL PRIMARY KEY,
    title VARCHAR NOT NULL,
    description TEXT,
    start_date DATE NOT NULL,
    end_date DATE,
    status VARCHAR NOT NULL,
    version int
);

CREATE TABLE payments (
    paym_id SERIAL PRIMARY KEY,
    work_id INT REFERENCES workers ON DELETE CASCADE NOT NULL,
    type VARCHAR NOT NULL, 
    date DATE NOT NULL,
    amount BIGINT NOT NULL
);

CREATE TABLE roles (
    role_id SERIAL PRIMARY KEY,
    work_id INT REFERENCES workers ON DELETE CASCADE NOT NULL,
    proj_id INT REFERENCES projects ON DELETE CASCADE NOT NULL,
    role VARCHAR NOT NULL,
    description TEXT, 
    start_date DATE NOT NULL,
    end_date DATE
);
