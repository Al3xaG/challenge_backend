-- public.clients definition

-- Drop table

-- DROP TABLE public.clients;

CREATE TABLE public.clients (
	client_id int8 NOT NULL,
	address varchar(255) NULL,
	age int4 NULL,
	dni varchar(255) NULL,
	gender varchar(255) NULL,
	"name" varchar(255) NULL,
	phone_number varchar(255) NULL,
	"password" varchar(255) NULL,
	status bool NULL,
	CONSTRAINT clients_pkey PRIMARY KEY (client_id)
);

-- public.accounts definition

-- Drop table

-- DROP TABLE public.accounts;

CREATE TABLE public.accounts (
	id int8 NOT NULL,
	account_number varchar(255) NULL,
	account_type varchar(255) NULL,
	initial_balance float8 NULL,
	status bool NULL,
	client_id int8 NULL,
	CONSTRAINT accounts_pkey PRIMARY KEY (id)
);


-- public.accounts foreign keys

ALTER TABLE public.accounts ADD CONSTRAINT fkgymog7firrf8bnoiig61666ob FOREIGN KEY (client_id) REFERENCES public.clients(client_id);


-- public.transactions definition

-- Drop table

-- DROP TABLE public.transactions;

CREATE TABLE public.transactions (
	id int8 NOT NULL,
	amount float8 NULL,
	balance float8 NULL,
	"date" timestamp(6) NULL,
	transaction_type varchar(255) NULL,
	account_id int8 NULL,
	CONSTRAINT transactions_pkey PRIMARY KEY (id)
);


-- public.transactions foreign keys

ALTER TABLE public.transactions ADD CONSTRAINT fk20w7wsg13u9srbq3bd7chfxdh FOREIGN KEY (account_id) REFERENCES public.accounts(id);


-- public.clients_accounts definition

-- Drop table

-- DROP TABLE public.clients_accounts;

CREATE TABLE public.clients_accounts (
	client_client_id int8 NOT NULL,
	accounts_id int8 NOT NULL,
	CONSTRAINT uk_6lf2twq8o1jhum9ghi1mm7lxi UNIQUE (accounts_id)
);


-- public.clients_accounts foreign keys

ALTER TABLE public.clients_accounts ADD CONSTRAINT fk9cif30a38beujgkphvm7v9t9f FOREIGN KEY (accounts_id) REFERENCES public.accounts(id);
ALTER TABLE public.clients_accounts ADD CONSTRAINT fkv78puqvgrcpi25k5xs8j76a1 FOREIGN KEY (client_client_id) REFERENCES public.clients(client_id);



-- public.accounts_transactions definition

-- Drop table

-- DROP TABLE public.accounts_transactions;

CREATE TABLE public.accounts_transactions (
	account_id int8 NOT NULL,
	transactions_id int8 NOT NULL,
	CONSTRAINT uk_qa4occ0lq1r2wrxaimcb9or9s UNIQUE (transactions_id)
);


-- public.accounts_transactions foreign keys

ALTER TABLE public.accounts_transactions ADD CONSTRAINT fk4f5mhickmjv0s875m8cft9j6b FOREIGN KEY (account_id) REFERENCES public.accounts(id);
ALTER TABLE public.accounts_transactions ADD CONSTRAINT fk7qukpadrni76yppcb4cbq43nt FOREIGN KEY (transactions_id) REFERENCES public.transactions(id);