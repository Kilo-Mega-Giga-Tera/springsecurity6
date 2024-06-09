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
    email         VARCHAR(100) NOT NULL UNIQUE,
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

INSERT INTO `customer` (`name`,`email`,`mobile_number`, `pwd`, `role`,`create_dt`)
VALUES ('test','test@test.com','01012341234', '$2y$12$oRRbkNfwuR8ug4MlzH5FOeui.//1mkd.RsOAJMbykTSupVy.x/vb2', 'admin',CURDATE());

INSERT INTO `accounts` (`customer_id`, `account_number`, `account_type`, `branch_address`, `create_dt`)
VALUES (1, 1865764534, 'Savings', '123 Main Street, New York', CURDATE());

INSERT INTO `account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_dt`, `transaction_summary`, `transaction_type`,`transaction_amt`,
                                    `closing_balance`, `create_dt`)  VALUES (UUID(), 1865764534, 1, DATE_SUB(CURDATE(), INTERVAL 7 DAY), 'Coffee Shop', 'Withdrawal', 30,34500,DATE_SUB(CURDATE(), INTERVAL 7 DAY));

INSERT INTO `account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_dt`, `transaction_summary`, `transaction_type`,`transaction_amt`,
                                    `closing_balance`, `create_dt`)  VALUES (UUID(), 1865764534, 1, DATE_SUB(CURDATE(), INTERVAL 6 DAY), 'Uber', 'Withdrawal', 100,34400,DATE_SUB(CURDATE(), INTERVAL 6 DAY));

INSERT INTO `account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_dt`, `transaction_summary`, `transaction_type`,`transaction_amt`,
                                    `closing_balance`, `create_dt`)  VALUES (UUID(), 1865764534, 1, DATE_SUB(CURDATE(), INTERVAL 5 DAY), 'Self Deposit', 'Deposit', 500,34900,DATE_SUB(CURDATE(), INTERVAL 5 DAY));

INSERT INTO `account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_dt`, `transaction_summary`, `transaction_type`,`transaction_amt`,
                                    `closing_balance`, `create_dt`)  VALUES (UUID(), 1865764534, 1, DATE_SUB(CURDATE(), INTERVAL 4 DAY), 'Ebay', 'Withdrawal', 600,34300,DATE_SUB(CURDATE(), INTERVAL 4 DAY));

INSERT INTO `account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_dt`, `transaction_summary`, `transaction_type`,`transaction_amt`,
                                    `closing_balance`, `create_dt`)  VALUES (UUID(), 1865764534, 1, DATE_SUB(CURDATE(), INTERVAL 2 DAY), 'OnlineTransfer', 'Deposit', 700,35000,DATE_SUB(CURDATE(), INTERVAL 2 DAY));

INSERT INTO `account_transactions` (`transaction_id`, `account_number`, `customer_id`, `transaction_dt`, `transaction_summary`, `transaction_type`,`transaction_amt`,
                                    `closing_balance`, `create_dt`)  VALUES (UUID(), 1865764534, 1, DATE_SUB(CURDATE(), INTERVAL 1 DAY), 'Amazon.com', 'Withdrawal', 100,34900,DATE_SUB(CURDATE(), INTERVAL 1 DAY));


INSERT INTO `loans` ( `customer_id`, `start_dt`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `create_dt`)
VALUES ( 1, '2020-10-13', 'Home', 200000, 50000, 150000, '2024-01-13');

INSERT INTO `loans` ( `customer_id`, `start_dt`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `create_dt`)
VALUES ( 1, '2020-06-06', 'Vehicle', 40000, 10000, 30000, '2024-02-06');

INSERT INTO `loans` ( `customer_id`, `start_dt`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `create_dt`)
VALUES ( 1, '2018-02-14', 'Home', 50000, 10000, 40000, '2024-02-14');

INSERT INTO `loans` ( `customer_id`, `start_dt`, `loan_type`, `total_loan`, `amount_paid`, `outstanding_amount`, `create_dt`)
VALUES ( 1, '2018-02-14', 'Personal', 10000, 3500, 6500, '2024-06-01');

INSERT INTO `cards` (`card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `create_dt`)
VALUES ('4565XXXX4656', 1, 'Credit', 10000, 500, 9500, CURDATE());

INSERT INTO `cards` (`card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `create_dt`)
VALUES ('3455XXXX8673', 1, 'Credit', 7500, 600, 6900, CURDATE());

INSERT INTO `cards` (`card_number`, `customer_id`, `card_type`, `total_limit`, `amount_used`, `available_amount`, `create_dt`)
VALUES ('2359XXXX9346', 1, 'Credit', 20000, 4000, 16000, CURDATE());

INSERT INTO `notice_details` ( `notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`)
VALUES ('Home Loan Interest rates reduced', 'Home loan interest rates are reduced as per the goverment guidelines. The updated rates will be effective immediately',
        CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

INSERT INTO `notice_details` ( `notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`)
VALUES ('Net Banking Offers', 'Customers who will opt for Internet banking while opening a saving account will get a $50 amazon voucher',
        CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

INSERT INTO `notice_details` ( `notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`)
VALUES ('Mobile App Downtime', 'The mobile application of the EazyBank will be down from 2AM-5AM on 12/05/2020 due to maintenance activities',
        CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

INSERT INTO `notice_details` ( `notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`)
VALUES ('E Auction notice', 'There will be a e-auction on 12/08/2020 on the Bank website for all the stubborn arrears.Interested parties can participate in the e-auction',
        CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

INSERT INTO `notice_details` ( `notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`)
VALUES ('Launch of Millennia Cards', 'Millennia Credit Cards are launched for the premium customers of EazyBank. With these cards, you will get 5% cashback for each purchase',
        CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);

INSERT INTO `notice_details` ( `notice_summary`, `notice_details`, `notic_beg_dt`, `notic_end_dt`, `create_dt`, `update_dt`)
VALUES ('COVID-19 Insurance', 'EazyBank launched an insurance policy which will cover COVID-19 expenses. Please reach out to the branch for more details',
        CURDATE() - INTERVAL 30 DAY, CURDATE() + INTERVAL 30 DAY, CURDATE(), null);