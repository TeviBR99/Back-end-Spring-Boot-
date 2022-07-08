
INSERT INTO CATEGORY(id, name) VALUES (1, 'Eurogames');
INSERT INTO CATEGORY(id, name) VALUES (2, 'Ameritrash');
INSERT INTO CATEGORY(id, name) VALUES (3, 'Familiar');

INSERT INTO AUTHOR(id, name, nationality) VALUES (1, 'Alan R. Moon', 'US');
INSERT INTO AUTHOR(id, name, nationality) VALUES (2, 'Vital Lacerda', 'PT');
INSERT INTO AUTHOR(id, name, nationality) VALUES (3, 'Simone Luciani', 'IT');
INSERT INTO AUTHOR(id, name, nationality) VALUES (4, 'Perepau Llistosella', 'ES');
INSERT INTO AUTHOR(id, name, nationality) VALUES (5, 'Michael Kiesling', 'DE');
INSERT INTO AUTHOR(id, name, nationality) VALUES (6, 'Phil Walker-Harding', 'US');

INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (1, 'On Mars', '14', 1, 2);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (2, 'Aventureros al tren', '8', 3, 1);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (3, '1920: Wall Street', '12', 1, 4);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (4, 'Barrage', '14', 1, 3);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (5, 'Los viajes de Marco Polo', '12', 1, 3);
INSERT INTO GAME(id, title, age, category_id, author_id) VALUES (6, 'Azul', '8', 3, 5);


INSERT INTO CLIENT(id,name) VALUES (1,'TeviAle');
INSERT INTO CLIENT(id,name) VALUES (2,'OscarLele');
INSERT INTO CLIENT(id,name) VALUES (3,'Tevi2');
INSERT INTO CLIENT(id,name) VALUES (4,'Oscar2');



INSERT INTO PRESTAMOS(id,game_id,client_id,day_in,day_out) VALUES(1,1,2,to_date('2022/12/05','YYYY/MM/DD'),to_date('2022/12/16','YYYY/MM/DD'));
INSERT INTO PRESTAMOS(id,game_id,client_id,day_in,day_out) VALUES(2,2,3,to_date('2022/12/05', 'YYYY/MM/DD'),to_date('2022/12/12','YYYY/MM/DD'));
INSERT INTO PRESTAMOS(id,game_id,client_id,day_in,day_out) VALUES(3,3,4,to_date('2022/12/14','YYYY/MM/DD'),to_date('2022/12/18','YYYY/MM/DD'));
INSERT INTO PRESTAMOS(id,game_id,client_id,day_in,day_out) VALUES(4,4,1,to_date('2022/12/18', 'YYYY/MM/DD'),to_date('2022/12/20','YYYY/MM/DD'));
INSERT INTO PRESTAMOS(id,game_id,client_id,day_in,day_out) VALUES(5,5,2,to_date('2022/12/10', 'YYYY/MM/DD'),to_date('2022/12/12','YYYY/MM/DD'));
INSERT INTO PRESTAMOS(id,game_id,client_id,day_in,day_out) VALUES(6,6,3,to_date('2022/12/14','YYYY/MM/DD'),to_date('2022/12/16','YYYY/MM/DD'));
INSERT INTO PRESTAMOS(id,game_id,client_id,day_in,day_out) VALUES(7,1,4,to_date('2022/12/26', 'YYYY/MM/DD'),to_date('2022/12/31','YYYY/MM/DD'));
