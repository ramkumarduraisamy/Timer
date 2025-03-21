use usetiming;
INSERT INTO roles_lookup (title, description) VALUES
                                                  ('Root user', 'Has all permissions and access to the system'),
                                                  ('Super user', 'Has elevated privileges but not full root access'),
                                                  ('Simple user', 'Has limited access based on assigned roles');
