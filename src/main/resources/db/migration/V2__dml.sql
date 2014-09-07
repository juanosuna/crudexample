INSERT INTO T_AUTHORITY (name)
VALUES ('ROLE_ADMIN');
INSERT INTO T_AUTHORITY (name)
VALUES ('ROLE_USER');

INSERT INTO T_USER (login, password, first_name, last_name, email, activated, lang_key, created_by)
VALUES ('system', '572d3b834f32347527d749bc1a41042c920682fc430febd380b4b6a0134f314fd381ce11c9a05abe', NULL, 'System', NULL, true, 'en', 'system');

INSERT INTO T_USER (login, password, first_name, last_name, email, activated, lang_key, created_by)
VALUES ('anonymousUser', '4f54479f8290dfd503b72a654faf5d70593eab443993d87a79e14e5f7cda3eb7988423aa99090c9b', 'Anonymous', 'User', NULL, true, 'en', 'system');

INSERT INTO T_USER (login, password, first_name, last_name, email, activated, lang_key, created_by)
VALUES ('admin', 'b8f57d6d6ec0a60dfe2e20182d4615b12e321cad9e2979e0b9f81e0d6eda78ad9b6dcfe53e4e22d1', NULL, 'Administrator', NULL, true, 'en', 'system');

INSERT INTO T_USER (login, password, first_name, last_name, email, activated, lang_key, created_by)
VALUES ('user', '4f54479f8290dfd503b72a654faf5d70593eab443993d87a79e14e5f7cda3eb7988423aa99090c9b', NULL, 'User', NULL, true, 'en', 'system');

INSERT INTO T_USER_AUTHORITY (login, name)
VALUES ('system', 'ROLE_ADMIN');

INSERT INTO T_USER_AUTHORITY (login, name)
VALUES ('system', 'ROLE_USER');

INSERT INTO T_USER_AUTHORITY (login, name)
VALUES ('admin', 'ROLE_ADMIN');

INSERT INTO T_USER_AUTHORITY (login, name)
VALUES ('admin', 'ROLE_USER');

INSERT INTO T_USER_AUTHORITY (login, name)
VALUES ('user', 'ROLE_USER');
