insert into app_user (clinic_id, user_name, encrypted_password, full_name)
values (null, 'duc010298', '$2a$10$hjBz774Yg4Fff44DYseK4.w4p27w2enR0W.QxSxlIXA.TcxS2bYV.', 'Đỗ Trung Đức');

insert into app_role (id, role_name)
values (1, 'ROLE_GLOBAL_ADMIN');

insert into app_role (id, role_name)
values (2, 'ROLE_CLINIC_ADMIN');

insert into app_role (id, role_name)
values (3, 'ROLE_USER');

insert into user_role
values (1, 1);

insert into user_role
values (1, 2);

insert into user_role
values (1, 3);