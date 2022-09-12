DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, date_time, description, calories)
VALUES (100000, '2022-08-23 9:27:00', 'Завтрак', 440),
       (100000, '2022-08-23 13:55:00', 'Обед', 450),
       (100000, '2022-08-23 21:40:00', 'Ужин', 870),
       (100000, '2022-08-24 12:00:00', 'Завтрак', 570),
       (100000, '2022-08-24 21:15:00', 'Ужин', 890),
       (100000, '2022-08-25 12:14:00', 'Завтрак', 600),
       (100000, '2022-08-25 16:00:00', 'Быстрый перекус', 220),
       (100000, '2022-08-25 23:07:00', 'Ужин', 1080),
       (100001, '2022-09-12 7:40:00', 'Завтрак', 200),
       (100001, '2022-09-12 12:25:00', 'Обед', 410);
