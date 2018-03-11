DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, id, datetime, description, calories) VALUES
  (100000, 100003, '1999-12-01 12:12:12','breakfast', 100),
  (100000, 100004, '1999-12-01 12:12:13','lunch', 500),
  (100001, 100005, '1999-12-01 12:12:14','dinner',800);
