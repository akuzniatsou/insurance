CREATE SEQUENCE IF NOT EXISTS public.owner_seq START 130;
CREATE SEQUENCE IF NOT EXISTS public.vehicle_seq START 230;
CREATE SEQUENCE IF NOT EXISTS public.damage_seq START 330;


CREATE TABLE IF NOT EXISTS public.owner
(
  id bigint NOT NULL DEFAULT nextval('owner_seq'::regclass),
  name text NOT NULL,
  pass_id text NOT NULL,
  last_name text,
  address text,
  phone text,
  CONSTRAINT pk_onwer_id PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.vehicle
(
  id bigint NOT NULL DEFAULT nextval('vehicle_seq'::regclass),
  owner_id bigint NOT NULL,
  type text,
  "number" text NOT NULL,
  body_number text NOT NULL,
  model text,
  CONSTRAINT pk_vehicle_id PRIMARY KEY (id),
  CONSTRAINT fk_owner_id_vehicle_id FOREIGN KEY (owner_id)
      REFERENCES public.owner (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);


CREATE TABLE IF NOT EXISTS public.damage
(
  id bigint NOT NULL DEFAULT nextval('damage_seq'::regclass),
  vehicle_id bigint NOT NULL,
  date timestamp with time zone NOT NULL,
  zone1 boolean,
  zone2 boolean,
  zone3 boolean,
  zone4 boolean,
  zone5 boolean,
  zone6 boolean,
  zone7 boolean,
  zone8 boolean,
  zone9 boolean,
  zone10 boolean,
  zone11 boolean,
  zone12 boolean,
  zone13 boolean,
  CONSTRAINT damage_pk PRIMARY KEY (id),
  CONSTRAINT fk_damage_vehicle_id FOREIGN KEY (vehicle_id)
      REFERENCES public.vehicle (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

