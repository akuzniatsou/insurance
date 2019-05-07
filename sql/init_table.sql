INSERT INTO public.owner (id, name, pass_id, last_name, address, phone) VALUES
    (101, 'Andrei', '5d7825cc-2fdf-4f42-9be2-59f1c1525291', 'Provan', 'Vitebsk', '+375-33-100-14-14'),
    (102, 'Mikhail', '621e67a9-8214-465e-8838-196869893687', 'Zadov', 'Vitebsk', '+375-33-100-14-90'),
    (103, 'Dzmitry', 'd4f4d6d1-edc2-459d-a547-aa87195447a1', 'Klimchanka', 'California', '+375-33-100-14-89'),
    (104, 'Viktar', '3da7685e-91f7-4540-9cab-1d9c18285f1e', 'Lukarev', 'Volozhin', '+375-33-100-14-23'),
    (105, 'Alena', 'e5a60db8-2be2-40fe-9798-a5215645d51b', 'Zaviraiko', 'Vitebsk', '+375-33-100-14-45'),
    (106, 'Valentin', '876e67af-0920-4e0d-a9a7-7e4352251450', 'Pruprunkevich', 'Vitebsk', '+375-33-100-14-67');

INSERT INTO public.vehicle (id, owner_id, type, number, body_number, model) VALUES
    (201, 101, 'VAN', '00-01 DF', '91c5896b-1b5f-4669-a88f-790b01148c2e', 'OPEL ZAFIRA'),
    (202, 102, 'MOTO', '04-56 HG', '1706d480-3bf9-44c8-b3a2-3e6a2bd2b1b3', 'UAZ PATRIOT'),
    (203, 103, 'BUS', '02-03 FG', '2a25cdf1-c38a-4130-9485-84ac6c49f00c', 'CITROEN XSARA PICASSO'),
    (204, 105, 'SUV', '01-14 FU', '2a25cdf1-c36a-5746-9485-84ac6c41f00c', 'LADA VESTA'),
    (205, 104, 'JEEP', '90-34 FG', 'eade86be-f0d0-4a49-8826-3939a5465317', 'TOYOTA RAW 4');

INSERT INTO public.damage (id, vehicle_id, date, zone1, zone2, zone3, zone4, zone5, zone6, zone7, zone8, zone9, zone10, zone11, zone12, zone13) VALUES
    (301, 201, '2019-04-27 19:19:21.630000', true, false, false, true, true, false, true, false, true, true, false, false, true),
    (302, 202, '2019-04-27 19:19:21.630000', true, false, true, true, false, false, true, false, false, true, false, false, true),
    (303, 203, '2019-01-27 19:19:21.630000', true, false, true, true, false, true, true, false, true, true, false, false, true),
    (304, 204, '2019-02-23 19:19:21.630000', true, false, false, false, false, true, true, false, true, false, false, false, false),
    (305, 205, '2019-04-29 19:19:21.630000', true, false, true, true, false, false, true, true, false, true, true, false, true);
