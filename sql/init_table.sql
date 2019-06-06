INSERT INTO public.owner (id, name, pass_id, last_name, address, phone) VALUES
    (101, 'Andrei', '5d7825cc-2fdf-4f42-9be2-59f1c1525291', 'Provan', 'Vitebsk', '+375-33-100-14-14'),
    (102, 'Mikhail', '621e67a9-8214-465e-8838-196869893687', 'Zadov', 'Vitebsk', '+375-33-100-14-90'),
    (103, 'Dzmitry', 'd4f4d6d1-edc2-459d-a547-aa87195447a1', 'Klimchanka', 'California', '+375-33-100-14-89'),
    (104, 'Viktar', '3da7685e-91f7-4540-9cab-1d9c18285f1e', 'Lukarev', 'Volozhin', '+375-33-100-14-23'),
    (105, 'Alena', 'e5a60db8-2be2-40fe-9798-a5215645d51b', 'Zaviraiko', 'Vitebsk', '+375-33-100-14-45'),
    (106, 'Valentin', '876e67af-0920-4e0d-a9a7-7e4352251450', 'Pruprunkevich', 'Vitebsk', '+375-33-100-14-67'),
    (107, 'David', '876e23af-0921-5e0d-a9a1-7e4352251450', 'Backham', 'New York', '+8-123-111-13-42'),
    (108, 'Oleksandr', '886a64aa-0933-4e1d-a9a7-6a2211111444', 'Zinchenko', 'Manchester', '+8-022-44-101-14-68'),
    (109, 'Aleksandr', '811e63ah-0922-6e0d-b1a7-5s4324241450', 'Lukashenia', 'Gorki', '+375-35-245-18-43'),
    (110, 'Vitovt', '776a66af-0923-1e1v-c927-4d4352353551', 'Olehnovich', 'Gdansk', '+625-31-106-14-67'),
    (111, 'Vladimir', '970e64av-0940-5d2h-d887-3f4352535352', 'Maleshka', 'Minsk', '+375-33-123-32-43'),
    (112, 'Aleksei', '876a69vf-0925-4p4g-e7k7-2g4380801463', 'Shchupak', 'Gomel', '+375-25-256-117-39'),
    (113, 'Semen', '876h99dv-0966-4i3b-f5y7-1h4352251454', 'Davidov', 'Minsk', '+375-35-336-14-14'),
    (114, 'Andrei', '876e67af-0927-4h2d-g9a7-7j4352251111', 'Stankevich', 'Vitebsk', '+375-33-689-12-42'),
    (115, 'Maria', '567e09y-0787-y72d-g7u9-6t4540781396', 'Lapina', 'Vitebsk', '+375-29-670-27-12');

INSERT INTO public.vehicle (id, owner_id, type, number, body_number, model) VALUES
    (201, 101, 'VAN', '00-01 DF', '91c5896b-1b5f-4669-a88f-790b01148c2e', 'OPEL ZAFIRA'),
    (202, 102, 'OFFRD', '04-56 HG', '1706d480-3bf9-44c8-b3a2-3e6a2bd2b1b3', 'UAZ PATRIOT'),
    (203, 103, 'BUS', '02-03 FG', '2a25cdf1-c38a-4130-9485-84ac6c49f00c', 'CITROEN XSARA PICASSO'),
    (204, 104, 'UNIVERS', '01-14 FU', '2a25cdf1-c36a-5746-9485-84ac6c41f00c', 'LADA VESTA'),
    (205, 105, 'SEDAN', '01-00 FU', '2a123cdf1-c36a-5746-9485-84ac6c41f00c', 'RENAULT LOGAN'),
    (206, 106, 'SEDAN', '90-34 TG', 'e11286be-fdd0-4249-8826-11439a5465317', 'VOLKSWAGEN POLO'),
    (207, 107, 'BUS', '77-34 GT', 'eady87be-f021-4a49-8826-619hpety1456', 'MERCEDES SPRINTER'),
    (208, 108, 'PICKUP', '90-78 LO', 'edae86be-f033-4df9-8826-8980f534617', 'TOYOTA TUNDRA'),
    (209, 109, 'HATCHBACK', '56-98 OL', 'efde86be-a5d0-2f49-8826-798v4969867', 'VOLKSWAGEN POLO '),
    (210, 110, 'OFFRD', '75-06 YU', 'efde86be-f0dq-a542-8vd6-1221f5465317', 'CHEVROLET NIVA'),
    (211, 111, 'VAN', '87-06 UY', 'eagf86be-f0ds-4a49-88vd-3939h5458977', 'RENAULT ESPACE 4'),
    (212, 112, 'HATCHBACK', '89-70 AD', 'eadsde-f0dr-4d56-8826-387n5645317', 'LADA KALINA'),
    (213, 113, 'LIM', '09-34 DA', 'eajf6be-f0dr-4a49-88t7-3456m487380', 'LINCOLN TOWN CAR'),
    (214, 114, 'COUPE', '90-79 RT', 'jfde86be-f0uo-4a49-8j90-3939u467387', 'TOYOTA SUPRA'),
    (215, 115, 'SEDAN', '34-08 TR', 'efj86be-f0iu-4a49-8690-5879k5488323', 'AUDI 90');
	
	

INSERT INTO public.damage (id, vehicle_id, date, zone1, zone2, zone3, zone4, zone5, zone6, zone7, zone8, zone9, zone10, zone11, zone12, zone13) VALUES
    (301, 201, '2019-04-27 19:19:21.630000', true, false, false, true, true, false, true, false, true, true, false, false, true),
    (302, 202, '2019-04-27 19:19:21.630000', true, false, true, true, false, false, true, false, false, true, false, true, true),
    (303, 203, '2019-01-27 19:19:21.630000', false, false, true, true, true, true, true, false, true, true, true, false, true),
    (304, 204, '2019-02-23 19:19:21.630000', true, false, false, false, false, true, true, false, true, false, false, true, false),
    (305, 205, '2019-04-29 19:19:21.630000', false, false, true, true, true, false, true, true, false, true, true, false, true),
    (306, 206, '2019-05-09 21:22:21.630000', false, false, false, true, false, false, false, true, false, true, true, true, false),
    (307, 207, '2019-04-29 19:19:21.630000', true, true, true, true, false, true, true, true, false, true, true, false, false),
    (308, 208, '2019-05-12 19:19:21.630000', false, true, false, true, false, true, true, true, false, false, false, true, true),
    (309, 209, '2019-05-12 19:19:21.630000', true, false, true, false, false, false, false, true, false, true, true, false, true),
    (310, 210, '2019-05-12 19:19:21.630000', true, true, true, true, true, true, true, true, false, true, true, false, true),
    (311, 211, '2019-05-13 19:19:21.630000', false, false, true, false, true, true, false, true, false, true, true, false, true),
    (312, 212, '2019-05-13 19:19:21.630000', true, true, false, true, false, false, true, true, true, true, true, false, true),
    (313, 213, '2019-06-01 19:19:21.630000', false, true, false, true, false, false, false, true, false, true, true, true, false),
    (314, 214, '2019-06-01 19:19:21.630000', true, false, false, false, true, true, true, true, true, true, true, false, true),
    (315, 215, '2019-07-01 19:19:21.630000', true, false, true, true, false, false, false, true, false, true, true, false, true);

INSERT INTO public.user (login, pass, user_role) VALUES
    ('user', 'EE11CBB19052E40B07AAC0CA060C23EE', 2),
    ('admin', '21232F297A57A5A743894A0E4A801FC3', 1),
    ('ivan', '2C42E5CF1CDBAFEA04ED267018EF1511', 2);