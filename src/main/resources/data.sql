INSERT INTO account(id, NUMBER, TYPE ,BALANCE, ISACTIVE) VALUES (1, '01000251215', 'SAVING' ,4210.42, true);
INSERT INTO account(id, number, type ,balance, ISACTIVE) VALUES (2, '01000251216', 'CURRENT' ,25.12, false);


INSERT INTO transaction(id, number , balance, IDACCOUNT) VALUES (1, '12151885120', 42.12 , 1);
INSERT INTO transaction(id, number , balance, IDACCOUNT) VALUES (2, '12151885121', 456.00 , 1);
INSERT INTO transaction(id, number , balance, IDACCOUNT) VALUES (3, '12151885122', -12.12 , 1);
INSERT INTO transaction(id, number , balance, IDACCOUNT) VALUES (4, '12151885123', -12.12 , 1);