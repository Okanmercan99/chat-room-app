-- Create user table
CREATE TABLE user_table (
                              user_id SERIAL PRIMARY KEY,
                              name VARCHAR(255),
                              surname VARCHAR(255)
);

-- Insert dummy user
INSERT INTO user_table (name, surname) VALUES
    ('Okan', 'Mercan');