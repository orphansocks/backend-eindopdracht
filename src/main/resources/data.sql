-- datasql dient om de database te prefillen
-- doe dit voor ALLE entiteiten
--
--
INSERT INTO relatives (id, first_name, last_name, nick_name, dob, social_status, name_of_partner, has_kids, amount_of_kids, names_of_kids, misc, relation)
VALUES
(1001, 'Eva', 'van Dongen', 'Eef', '1976-06-10', 'together', 'Freek', true, 2 , 'Fynn en Luz', 'hou het simpel', 'family' ),
(1002, 'Freek', 'Tromp', '', '1974-07-18', 'together', 'Eva', true, 2 , 'Fynn en Luz', 'geen sla geen vla', 'family' ),
(1003, 'Frouke', 'van Dongen', 'Zussie', '1979-06-09', 'together', 'GeertJan', true, 1 , 'Jule', 'dat bedoel ik', 'family' ),
(1004, 'Nathan', 'Fielder', 'Nathan for You', '1983-05-12', 'divorced', '', false, 0 , '', 'An observer of human behavior. He mines the absurdities of how people relate to one another and how he relates to them, and it all makes you think and laugh', 'neighbour' ),
(1005, 'Kees', 'van Dongen', '', '1877-01-26', 'divorced', '', true, 2 , 'Zoon en dochter', 'gescheiden van Piet', 'collegue' ),
(1006, 'Marie', 'Antoinette', '', '1976-10-16', 'single', '', true, 1 , 'Thérèse', 'Monsieur, je vous demande excuse, je ne l’ai pas fait exprès.', 'family' ),
(1007, 'Immanuel', 'Kant', 'Filosoof', '1724-04-27', 'together', 'Maria', false, 0 , 'unknown', 'Look closely, the beautiful may be small', 'collegue' ),
(1008, 'Jan', 'Janssen', 'JJ', '1976-03-03', 'married', 'Maria', true, 4 , 'Jannie, Kobus, Lieve, Moos', 'En wat schrijven we hier', 'friends' ),
(1009, 'James', 'Gosling', 'Java', '1955-05-19', 'divorced', '', true, 3 , 'Kate, Kelsey, Lorena', 'make something cool', 'study' ),
(1010, 'Brendan', 'Eich', 'JavaScript', '1961-07-04', 'married', 'Eleanor', true, 2 , 'JavaScript and Mozilla', 'Hello, world!', 'collegue' );


INSERT INTO groups (id, group_name, group_place)
VALUES
    (2001, 'De schoonfam', ''),
    (2002, 'Vrienden', ''),
    (2003, 'Huis', 'Utrecht'),
    (2004, 'In Da Hood', 'Haarlem'),
    (2005, 'Kookclub', 'Amsterdam');

INSERT INTO groups_relatives (groups_id, relatives_id)
VALUES (2001, 1001),
        (2002, 1002),
       (2003, 1003),
       (2004, 1004),
       (2005, 1005),
       (2001, 1006),
       (2002, 1007),
       (2003, 1008),
       (2004, 1009),
       (2005, 1010);

INSERT INTO cards (id, card_name, designer, category, amount_of_downloads)
VALUES
    (3001, 'yvi', 'designerA', 'birthday', 21),
    (3002, 'kepler', 'designerB', 'birthday', 22),
    (3003, 'haas', 'designerC', 'birthday', 15),
    (3004, 'degular', 'designerD', 'birthday', 16),
    (3005, 'roboto', 'designerE', 'birthday', 3);


