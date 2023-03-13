CREATE TABLE exchange_log (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(10),
    method VARCHAR(10),
    endpoint VARCHAR(256),
    status VARCHAR(3),
    header VARCHAR(1024),
    body VARCHAR(1024),
    timestamp TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
