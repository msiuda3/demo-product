CREATE KEYSPACE IF NOT EXISTS my_keyspace
WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

USE my_keyspace;

CREATE TABLE IF NOT EXISTS product (
    id UUID PRIMARY KEY,
    name text,
    description text,
    price double,
    category text
);

CREATE INDEX IF NOT EXISTS idx_username ON user (category);

CREATE TABLE IF NOT EXISTS user (
    id UUID PRIMARY KEY,
    username text,
    password text
);

CREATE INDEX IF NOT EXISTS idx_username ON user (username);
CREATE INDEX IF NOT EXISTS idx_password ON user (password);

INSERT INTO product (id, name, description, price, category)
VALUES (uuid(), 'Sample Product', 'This is a sample product', 10.0, 'Sample Category');

INSERT INTO user (id, username, password)
VALUES (uuid(), 'admin', '$2a$10$vE44T2XxSgrJIqq39eZCB.swtxKZB9hJu3xheYc6wYQ.WB8xlJYuW');
