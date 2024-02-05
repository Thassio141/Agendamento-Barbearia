CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    user_role SMALLINT CHECK (user_role >= 0 AND user_role <= 3),
    user_status SMALLINT CHECK (user_status >= 0 AND user_status <= 1)
);

CREATE TABLE tasks (
   task_id SERIAL PRIMARY KEY,
   duration TIME(6),
   name VARCHAR(255),
   price FLOAT8,
   status_task SMALLINT CHECK (status_task >= 0 AND status_task <= 1)
);

CREATE TABLE appointments (
  appointment_id SERIAL PRIMARY KEY,
  date_time TIMESTAMP,
  appointment_status VARCHAR(255),
  user_id BIGINT,
  task_id BIGINT,
  FOREIGN KEY (user_id) REFERENCES users(user_id),
  FOREIGN KEY (task_id) REFERENCES tasks(task_id)
);
