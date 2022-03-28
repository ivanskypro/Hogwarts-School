-- liquibase formatted sql

-- changeset ivan:1
CREATE INDEX student_name_index ON student (name)

-- changeset ivan:2
CREATE INDEX color_and_name_index ON faculty (name, color)