-- DROP SCHEMA public;

CREATE SCHEMA public AUTHORIZATION pg_database_owner;

COMMENT ON SCHEMA public IS 'standard public schema';

-- DROP SEQUENCE public.pf_accord_id_seq;

CREATE SEQUENCE public.pf_accord_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.pf_article_id_seq;

CREATE SEQUENCE public.pf_article_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.pf_article_images_id_seq;

CREATE SEQUENCE public.pf_article_images_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.pf_brand_id_seq;

CREATE SEQUENCE public.pf_brand_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.pf_collection_items_id_seq;

CREATE SEQUENCE public.pf_collection_items_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.pf_fragrance_accords_id_seq;

CREATE SEQUENCE public.pf_fragrance_accords_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.pf_fragrance_id_seq;

CREATE SEQUENCE public.pf_fragrance_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.pf_fragrance_images_id_seq;

CREATE SEQUENCE public.pf_fragrance_images_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.pf_fragrance_notes_id_seq;

CREATE SEQUENCE public.pf_fragrance_notes_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.pf_fragrance_review_id_seq;

CREATE SEQUENCE public.pf_fragrance_review_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.pf_fragrance_type_id_seq;

CREATE SEQUENCE public.pf_fragrance_type_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.pf_media_id_seq;

CREATE SEQUENCE public.pf_media_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.pf_note_id_seq;

CREATE SEQUENCE public.pf_note_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.pf_sample_id_seq;

CREATE SEQUENCE public.pf_sample_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.pf_user_collection_id_seq;

CREATE SEQUENCE public.pf_user_collection_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.pf_user_id_seq;

CREATE SEQUENCE public.pf_user_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.pf_user_wishlist_id_seq;

CREATE SEQUENCE public.pf_user_wishlist_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;
-- DROP SEQUENCE public.pf_wishlist_items_id_seq;

CREATE SEQUENCE public.pf_wishlist_items_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;-- public.pf_accord definition

-- Drop table

-- DROP TABLE public.pf_accord;

CREATE TABLE public.pf_accord (
	id bigserial NOT NULL,
	slug varchar(255) NOT NULL,
	accord_name varchar(255) NOT NULL,
	accord_description varchar(255) NULL,
	created_at timestamp(6) NULL,
	CONSTRAINT pf_accord_name_key UNIQUE (accord_name),
	CONSTRAINT pf_accord_pkey PRIMARY KEY (id),
	CONSTRAINT pf_accord_slug_key UNIQUE (slug)
);


-- public.pf_article definition

-- Drop table

-- DROP TABLE public.pf_article;

CREATE TABLE public.pf_article (
	id bigserial NOT NULL,
	user_id int8 NOT NULL,
	article_title varchar(255) NOT NULL,
	article_description text NULL,
	created_at timestamptz DEFAULT CURRENT_TIMESTAMP NULL,
	created_by varchar(255) NULL,
	modified_at timestamptz DEFAULT CURRENT_TIMESTAMP NULL,
	modified_by varchar(255) NULL,
	article_type varchar(255) NULL,
	article_price numeric(10, 2) NULL,
	article_location varchar(255) NULL,
	article_fragrance_id int4 NULL,
	CONSTRAINT pf_article_pkey PRIMARY KEY (id)
);


-- public.pf_article_images definition

-- Drop table

-- DROP TABLE public.pf_article_images;

CREATE TABLE public.pf_article_images (
	id bigserial NOT NULL,
	article_id int4 NOT NULL,
	media_id int4 NOT NULL,
	is_primary bool DEFAULT false NULL,
	CONSTRAINT pf_article_images_pkey PRIMARY KEY (id)
);


-- public.pf_brand definition

-- Drop table

-- DROP TABLE public.pf_brand;

CREATE TABLE public.pf_brand (
	id bigserial NOT NULL,
	slug varchar(255) NOT NULL,
	brand_name varchar(255) NOT NULL,
	brand_creation_date date NULL,
	brand_description text NULL,
	CONSTRAINT pf_brand_brand_name_key UNIQUE (brand_name),
	CONSTRAINT pf_brand_pkey PRIMARY KEY (id),
	CONSTRAINT pf_brand_slug_key UNIQUE (slug)
);


-- public.pf_collection_items definition

-- Drop table

-- DROP TABLE public.pf_collection_items;

CREATE TABLE public.pf_collection_items (
	id bigserial NOT NULL,
	user_id int8 NOT NULL,
	fragrance_slug varchar(255) NOT NULL,
	created_at timestamptz DEFAULT CURRENT_TIMESTAMP NULL,
	created_by varchar(255) NULL,
	CONSTRAINT pf_collection_items_pkey PRIMARY KEY (id)
);


-- public.pf_fragrance definition

-- Drop table

-- DROP TABLE public.pf_fragrance;

CREATE TABLE public.pf_fragrance (
	id bigserial NOT NULL,
	slug varchar(255) NOT NULL,
	official_name varchar(255) NOT NULL,
	brand_id int4 NOT NULL,
	type_id int4 NOT NULL,
	winter_rating int4 NULL,
	fall_rating int4 NULL,
	summer_rating int4 NULL,
	spring_rating int4 NULL,
	sillage_rating int4 NULL,
	longevity_rating int4 NULL,
	price_range varchar(255) NULL,
	created_by varchar(255) NULL,
	created_at timestamptz DEFAULT CURRENT_TIMESTAMP NULL,
	modified_by varchar(255) NULL,
	modified_at timestamptz DEFAULT CURRENT_TIMESTAMP NULL,
	gender varchar(255) NULL,
	CONSTRAINT pf_fragrance_fall_rating_check CHECK (((fall_rating >= 1) AND (fall_rating <= 10))),
	CONSTRAINT pf_fragrance_longevity_rating_check CHECK (((longevity_rating >= 1) AND (longevity_rating <= 10))),
	CONSTRAINT pf_fragrance_pkey PRIMARY KEY (id),
	CONSTRAINT pf_fragrance_sillage_rating_check CHECK (((sillage_rating >= 1) AND (sillage_rating <= 10))),
	CONSTRAINT pf_fragrance_slug_key UNIQUE (slug),
	CONSTRAINT pf_fragrance_spring_rating_check CHECK (((spring_rating >= 1) AND (spring_rating <= 10))),
	CONSTRAINT pf_fragrance_summer_rating_check CHECK (((summer_rating >= 1) AND (summer_rating <= 10))),
	CONSTRAINT pf_fragrance_winter_rating_check CHECK (((winter_rating >= 1) AND (winter_rating <= 10)))
);


-- public.pf_fragrance_accords definition

-- Drop table

-- DROP TABLE public.pf_fragrance_accords;

CREATE TABLE public.pf_fragrance_accords (
	id bigserial NOT NULL,
	fragrance_id int4 NOT NULL,
	accord_id int4 NOT NULL,
	accord_presence varchar(50) NULL,
	CONSTRAINT pf_fragrance_accords_pkey PRIMARY KEY (id)
);


-- public.pf_fragrance_images definition

-- Drop table

-- DROP TABLE public.pf_fragrance_images;

CREATE TABLE public.pf_fragrance_images (
	id bigserial NOT NULL,
	fragrance_id int4 NOT NULL,
	media_id int4 NOT NULL,
	CONSTRAINT pf_fragrance_images_pkey PRIMARY KEY (id)
);


-- public.pf_fragrance_notes definition

-- Drop table

-- DROP TABLE public.pf_fragrance_notes;

CREATE TABLE public.pf_fragrance_notes (
	id bigserial NOT NULL,
	fragrance_id int4 NOT NULL,
	note_id int4 NOT NULL,
	CONSTRAINT pf_fragrance_notes_pkey PRIMARY KEY (id)
);


-- public.pf_fragrance_type definition

-- Drop table

-- DROP TABLE public.pf_fragrance_type;

CREATE TABLE public.pf_fragrance_type (
	id bigserial NOT NULL,
	"name" varchar(255) NOT NULL,
	description text NULL,
	concentration varchar(50) NULL,
	CONSTRAINT pf_fragrance_type_name_key UNIQUE (name),
	CONSTRAINT pf_fragrance_type_pkey PRIMARY KEY (id)
);


-- public.pf_media definition

-- Drop table

-- DROP TABLE public.pf_media;

CREATE TABLE public.pf_media (
	id bigserial NOT NULL,
	media_category varchar(255) NOT NULL,
	object_id int8 NOT NULL,
	image_url varchar(255) NOT NULL,
	is_thumbnail bool NULL,
	CONSTRAINT pf_media_pkey PRIMARY KEY (id)
);


-- public.pf_note definition

-- Drop table

-- DROP TABLE public.pf_note;

CREATE TABLE public.pf_note (
	id bigserial NOT NULL,
	slug varchar(255) NOT NULL,
	"name" varchar(255) NOT NULL,
	description text NULL,
	CONSTRAINT pf_note_name_key UNIQUE (name),
	CONSTRAINT pf_note_pkey PRIMARY KEY (id),
	CONSTRAINT pf_note_slug_key UNIQUE (slug)
);


-- public.pf_sample definition

-- Drop table

-- DROP TABLE public.pf_sample;

CREATE TABLE public.pf_sample (
	id int8 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	sample_name varchar(255) NULL,
	CONSTRAINT pf_sample_pkey PRIMARY KEY (id)
);


-- public.pf_user definition

-- Drop table

-- DROP TABLE public.pf_user;

CREATE TABLE public.pf_user (
	id bigserial NOT NULL,
	username varchar(255) NOT NULL,
	"name" varchar(255) NULL,
	surname varchar(255) NULL,
	email varchar(255) NOT NULL,
	"password" varchar(255) NOT NULL,
	phone_number varchar(255) NULL,
	"location" varchar(255) NULL,
	isadmin bool DEFAULT false NULL,
	joined_at timestamptz DEFAULT CURRENT_TIMESTAMP NULL,
	CONSTRAINT pf_user_email_key UNIQUE (email),
	CONSTRAINT pf_user_pkey PRIMARY KEY (id),
	CONSTRAINT pf_user_username_key UNIQUE (username)
);


-- public.pf_user_collection definition

-- Drop table

-- DROP TABLE public.pf_user_collection;

CREATE TABLE public.pf_user_collection (
	id int8 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	collection_name varchar(255) NULL,
	created_at timestamp(6) NULL,
	modified_at timestamp(6) NULL,
	user_id int8 NULL,
	CONSTRAINT pf_user_collection_pkey PRIMARY KEY (id)
);


-- public.pf_user_wishlist definition

-- Drop table

-- DROP TABLE public.pf_user_wishlist;

CREATE TABLE public.pf_user_wishlist (
	id int8 GENERATED BY DEFAULT AS IDENTITY( INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807 START 1 CACHE 1 NO CYCLE) NOT NULL,
	created_at timestamp(6) NULL,
	list_name varchar(255) NULL,
	modified_at timestamp(6) NULL,
	user_id int8 NULL,
	CONSTRAINT pf_user_wishlist_pkey PRIMARY KEY (id)
);


-- public.pf_wishlist_items definition

-- Drop table

-- DROP TABLE public.pf_wishlist_items;

CREATE TABLE public.pf_wishlist_items (
	id bigserial NOT NULL,
	user_id int8 NOT NULL,
	fragrance_slug varchar(255) NOT NULL,
	created_at timestamptz DEFAULT CURRENT_TIMESTAMP NULL,
	created_by varchar(255) NULL,
	CONSTRAINT pf_wishlist_items_pkey PRIMARY KEY (id)
);


-- public.pf_fragrance_review definition

-- Drop table

-- DROP TABLE public.pf_fragrance_review;

CREATE TABLE public.pf_fragrance_review (
	id bigserial NOT NULL,
	head varchar(255) NOT NULL,
	body varchar(255) NOT NULL,
	rating int2 NULL,
	created_by varchar(255) NOT NULL,
	created_at timestamptz DEFAULT CURRENT_TIMESTAMP NULL,
	fragrance_id int8 NOT NULL,
	CONSTRAINT pf_fragrance_review_pkey PRIMARY KEY (id),
	CONSTRAINT pf_fragrance_review_rating_check CHECK (((rating >= 1) AND (rating <= 10))),
	CONSTRAINT pf_fragrance_review_fragrance_id_fkey FOREIGN KEY (fragrance_id) REFERENCES public.pf_fragrance(id) ON DELETE CASCADE
);