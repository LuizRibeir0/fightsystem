CREATE TABLE students(
     id BIGSERIAL PRIMARY KEY,
     name VARCHAR(150) NOT NULL,
     birth_date DATE,
     gender VARCHAR(1) CHECK (gender IN ('M', 'F')),
     phone VARCHAR(30),
     mobile_phone VARCHAR(30),
     email VARCHAR(150),
     notes TEXT,
     address VARCHAR(150),
     number VARCHAR(20),
     complement VARCHAR(100),
     neighborhood VARCHAR(100),
     city VARCHAR(100),
     state VARCHAR(2),
     zip_code VARCHAR(20),
     created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
     updated_at TIMESTAMP
);

CREATE TABLE modalities(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE graduation(
    id BIGSERIAL PRIMARY KEY,
    modalitie_id BIGINT NOT NULL REFERENCES modalities(id),
    name VARCHAR(100) NOT NULL,
    UNIQUE (modalitie_id, name)
);

CREATE TABLE subscription(
    id BIGSERIAL PRIMARY KEY,
    modalitie_id BIGINT NOT NULL REFERENCES modalities(id),
    name VARCHAR(100) NOT NULL,
    monthly_value NUMERIC(10,2) NOT NULL CHECK ( monthly_value >= 0 ),
    active BOOLEAN NOT NULL DEFAULT TRUE,
    UNIQUE (modalitie_id, name)
);

CREATE TABLE registrations(
    id BIGSERIAL PRIMARY KEY,
    studant_id BIGINT NOT NULL REFERENCES students(id),
    date_subscription DATE NOT NULL DEFAULT CURRENT_DATE,
    due_date INTEGER NOT NULL CHECK ( due_date BETWEEN 1 AND 31 ),
    closing_date DATE,
    status VARCHAR(20) NOT NULL DEFAULT 'ATIVA',
    CHECK ( status IN ('ATIVA', 'ENCERRADA', 'CANCELADA') )
);

CREATE TABLE registrations_modalities(
    id BIGSERIAL PRIMARY KEY,
    registration_id BIGINT NOT NULL REFERENCES registrations(id),
    modality_id BIGINT NOT NULL REFERENCES modalities(id),
    graduation_id BIGINT NOT NULL REFERENCES graduation(id),
    subscription_id BIGINT NOT NULL REFERENCES subscription(id),
    start_date DATE NOT NULL DEFAULT CURRENT_DATE,
    end_date DATE,
    UNIQUE (registration_id, modality_id)
);

CREATE TABLE invoices_registrations(
    id BIGSERIAL PRIMARY KEY,
    registration_id BIGINT NOT NULL REFERENCES registrations(id),
    due_date DATE NOT NULL,
    price NUMERIC (10,2) NOT NULL CHECK ( price >=0 ),
    pay_day TIMESTAMP,
    cancellation_date DATE,
    status VARCHAR(20) NOT NULL DEFAULT 'ABERTA',
    CHECK ( status IN ('ABERTA', 'PAGA', 'CANCELADA', 'VENCIDA') ),
    UNIQUE (registration_id, due_date)
);

CREATE TABLE attendance(
    id BIGSERIAL PRIMARY KEY,
    registration_id BIGINT NOT NULL REFERENCES registrations(id),
    entry_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    leave_date TIMESTAMP
);