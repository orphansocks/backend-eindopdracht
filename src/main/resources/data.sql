-- datasql dient om de database te prefillen
-- doe dit voor ALLE entiteiten
--
--
INSERT INTO relatives (id, first_name, last_name, nick_name, dob, social_status, has_kids, amount_of_kids, names_of_kids, misc, relation)
VALUES
(1001, 'Eva', 'van Dongen', 'Eef', '1976-06-10', 'together', true, 2 , 'Fynn en Luz', 'geen sla geen vla', 'family' ),
(1002, 'Marie', 'Machielsen', 'Marietje', '1980-01-02', 'divorced', true, 3 , 'Klaasje, Pietje, Elsje', 'gescheiden van Piet', 'neighbour' ),
(1003, 'Kees', 'van Dongen', '', '1877-01-26', 'single', true, 2 , 'Zoon en dochter', 'gescheiden van Piet', 'collegue' );


-- INSERT INTO groups (id, group_name, group_place) VALUES (2001, 'Group 1', 'Place 1'),
-- INSERT INTO groups (id, group_name, group_place) VALUES (2002, 'Group 2', 'Place 2');
--
-- INSERT INTO groups_relatives ( groups_id, relatves_id) VALUES
--                                                            (1001, 2001),
--                                                            (1001, 2002),
--                                                            (1002, 2001),
--                                                            (1002, 2002);



