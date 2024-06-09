CREATE TABLE contact_messages
(
    contact_id    VARCHAR(50)   NOT NULL PRIMARY KEY,
    contact_name  VARCHAR(50)   NOT NULL,
    contact_email VARCHAR(100)  NOT NULL,
    subject       VARCHAR(500)  NOT NULL,
    message       VARCHAR(2000) NOT NULL,
    create_dt     DATE          NULL
);

CREATE TABLE customer
(
    customer_id   INT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(100) NOT NULL,
    email         VARCHAR(100) NOT NULL,
    mobile_number VARCHAR(20)  NOT NULL,
    pwd           VARCHAR(500) NOT NULL,
    role          VARCHAR(100) NOT NULL,
    create_dt     DATE         NULL
);

CREATE TABLE accounts
(
    customer_id    INT          NOT NULL,
    account_number INT          NOT NULL PRIMARY KEY,
    account_type   VARCHAR(100) NOT NULL,
    branch_address VARCHAR(200) NOT NULL,
    create_dt      DATE         NULL,
    CONSTRAINT customer_ibfk_1
        FOREIGN KEY (customer_id) REFERENCES customer (customer_id)
            ON DELETE CASCADE
);

CREATE TABLE account_transactions
(
    transaction_id      VARCHAR(200) NOT NULL
        PRIMARY KEY,
    account_number      INT          NOT NULL,
    customer_id         INT          NOT NULL,
    transaction_dt      DATE         NOT NULL,
    transaction_summary VARCHAR(200) NOT NULL,
    transaction_type    VARCHAR(100) NOT NULL,
    transaction_amt     INT          NOT NULL,
    closing_balance     INT          NOT NULL,
    create_dt           DATE         NULL,
    CONSTRAINT accounts_ibfk_2
        FOREIGN KEY (account_number) REFERENCES accounts (account_number)
            ON DELETE CASCADE,
    CONSTRAINT acct_user_ibfk_1
        FOREIGN KEY (customer_id) REFERENCES customer (customer_id)
            ON DELETE CASCADE
);

CREATE INDEX account_number
    ON account_transactions (account_number);

CREATE INDEX customer_id
    ON account_transactions (customer_id);

CREATE INDEX customer_id
    ON accounts (customer_id);

CREATE TABLE cards
(
    card_id          INT AUTO_INCREMENT PRIMARY KEY,
    card_number      VARCHAR(100) NOT NULL,
    customer_id      INT          NOT NULL,
    card_type        VARCHAR(100) NOT NULL,
    total_limit      INT          NOT NULL,
    amount_used      INT          NOT NULL,
    available_amount INT          NOT NULL,
    create_dt        DATE         NULL,
    CONSTRAINT card_customer_ibfk_1
        FOREIGN KEY (customer_id) REFERENCES customer (customer_id)
            ON DELETE CASCADE
);

CREATE INDEX customer_id
    ON cards (customer_id);

CREATE TABLE loans
(
    loan_number        INT AUTO_INCREMENT PRIMARY KEY,
    customer_id        INT          NOT NULL,
    start_dt           DATE         NOT NULL,
    loan_type          VARCHAR(100) NOT NULL,
    total_loan         INT          NOT NULL,
    amount_paid        INT          NOT NULL,
    outstanding_amount INT          NOT NULL,
    create_dt          DATE         NULL,
    CONSTRAINT loan_customer_ibfk_1
        FOREIGN KEY (customer_id) REFERENCES customer (customer_id)
            ON DELETE CASCADE
);

CREATE INDEX customer_id
    ON loans (customer_id);

CREATE TABLE notice_details
(
    notice_id      INT AUTO_INCREMENT PRIMARY KEY,
    notice_summary VARCHAR(200) NOT NULL,
    notice_details VARCHAR(500) NOT NULL,
    notic_beg_dt   DATE         NOT NULL,
    notic_end_dt   DATE         NULL,
    create_dt      DATE         NULL,
    update_dt      DATE         NULL
);

