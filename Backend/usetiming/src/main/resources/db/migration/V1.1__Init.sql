CREATE TABLE log_type_lookup
(
    id            INT AUTO_INCREMENT NOT NULL,
    type          VARCHAR(255)       NULL,
    description   VARCHAR(255)       NULL,
    created_at    TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted    BOOLEAN DEFAULT FALSE NOT NULL,
    CONSTRAINT pk_log_type_lookup PRIMARY KEY (id)
);

CREATE TABLE project
(
    id            INT AUTO_INCREMENT NOT NULL,
    title         VARCHAR(255)       NULL,
    description   VARCHAR(255)       NULL,
    created_at    TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted    BOOLEAN DEFAULT FALSE NOT NULL,
    CONSTRAINT pk_project PRIMARY KEY (id)
);

CREATE TABLE project_user
(
    id         INT AUTO_INCREMENT NOT NULL,
    user_id    INT                NOT NULL,
    project_id INT                NOT NULL,
    role_id    INT                NULL,
    created_at TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted BOOLEAN DEFAULT FALSE NOT NULL,
    CONSTRAINT pk_project_user PRIMARY KEY (id)
);

CREATE TABLE roles_lookup
(
    id            INT AUTO_INCREMENT NOT NULL,
    title         VARCHAR(255)       NOT NULL,
    description   VARCHAR(255)       NULL,
    created_at    TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted    BOOLEAN DEFAULT FALSE NOT NULL,
    CONSTRAINT pk_roles_lookup PRIMARY KEY (id)
);

CREATE TABLE timesheets
(
    id               INT AUTO_INCREMENT NOT NULL,
    title            VARCHAR(255)       NULL,
    logged_hours     DOUBLE             NULL,
    estimated_hours  DOUBLE             NULL,
    work_description TEXT               NULL,
    challenges       TEXT               NULL,
    log_type_id      INT                NULL,
    project_id       INT                NULL,
    created_at       TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at       TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted       BOOLEAN DEFAULT FALSE NOT NULL,
    CONSTRAINT pk_timesheets PRIMARY KEY (id)
);

CREATE TABLE users
(
    id          INT AUTO_INCREMENT NOT NULL,
    name        VARCHAR(255)       NOT NULL,
    email       VARCHAR(255)       NOT NULL,
    photo_url   VARCHAR(255)       NULL,
    schedule_id INT                NULL,
    created_at  TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted  BOOLEAN DEFAULT FALSE NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE work_item
(
    id         INT AUTO_INCREMENT NOT NULL,
    issue_id   VARCHAR(10)        NULL,
    title      VARCHAR(255)       NULL,
    user_id    INT                NULL,
    created_at TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted BOOLEAN DEFAULT FALSE NOT NULL,
    CONSTRAINT pk_work_item PRIMARY KEY (id)
);

CREATE TABLE work_item_timesheet
(
    id           INT AUTO_INCREMENT NOT NULL,
    work_item_id INT                NULL,
    timesheet_id INT                NULL,
    created_at   TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at   TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted   BOOLEAN DEFAULT FALSE NOT NULL,
    CONSTRAINT pk_work_item_timesheet PRIMARY KEY (id)
);

CREATE TABLE work_schedules_lookup
(
    id            INT AUTO_INCREMENT NOT NULL,
    title         VARCHAR(255)       NULL,
    description   VARCHAR(255)       NOT NULL,
    created_at    TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP          NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted    BOOLEAN DEFAULT FALSE NOT NULL,
    CONSTRAINT pk_work_schedules_lookup PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE project_user
    ADD CONSTRAINT FK_PROJECT_USER_ON_PROJECT FOREIGN KEY (project_id) REFERENCES project (id);

ALTER TABLE project_user
    ADD CONSTRAINT FK_PROJECT_USER_ON_ROLE FOREIGN KEY (role_id) REFERENCES roles_lookup (id);

ALTER TABLE project_user
    ADD CONSTRAINT FK_PROJECT_USER_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE timesheets
    ADD CONSTRAINT FK_TIMESHEETS_ON_LOG_TYPE FOREIGN KEY (log_type_id) REFERENCES log_type_lookup (id);

ALTER TABLE timesheets
    ADD CONSTRAINT FK_TIMESHEETS_ON_PROJECT FOREIGN KEY (project_id) REFERENCES project (id);

ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_SCHEDULE FOREIGN KEY (schedule_id) REFERENCES work_schedules_lookup (id);

ALTER TABLE work_item
    ADD CONSTRAINT FK_WORK_ITEM_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE work_item_timesheet
    ADD CONSTRAINT FK_WORK_ITEM_TIMESHEET_ON_TIMESHEET FOREIGN KEY (timesheet_id) REFERENCES timesheets (id);

ALTER TABLE work_item_timesheet
    ADD CONSTRAINT FK_WORK_ITEM_TIMESHEET_ON_WORK_ITEM FOREIGN KEY (work_item_id) REFERENCES work_item (id);
