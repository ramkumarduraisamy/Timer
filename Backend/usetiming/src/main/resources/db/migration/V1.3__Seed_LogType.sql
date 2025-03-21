INSERT INTO log_type_lookup (type, description, created_at, updated_at, is_deleted)
VALUES
    ('Working Day', 'A regular working day with logged hours', NOW(), NOW(), FALSE),
    ('Leave', 'A day off taken as leave', NOW(), NOW(), FALSE),
    ('Bank Holiday', 'A public holiday where work is not required', NOW(), NOW(), FALSE);
