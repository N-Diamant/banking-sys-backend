CREATE TABLE Message (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         customer VARCHAR(255),
                         message TEXT,
                         dateTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DELIMITER $$


CREATE TRIGGER on_transaction
    AFTER INSERT ON transactions
    FOR EACH ROW
BEGIN
    DECLARE customer_email VARCHAR(255);

    -- Retrieve the customer's full name
    SELECT email
    INTO customer_email
    FROM customers
    WHERE id = customers.id;

    -- Insert the message into the Message table
    INSERT INTO Message (customer, message, dateTime)
    VALUES (customer_email, CONCAT('Transaction Successfull -- amount: ', NEW.amount), NOW());
END $$

DELIMITER ;

DROP TRIGGER on_transaction;