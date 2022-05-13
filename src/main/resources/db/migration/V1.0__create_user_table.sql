create table public.tb_membership_type(
    membership_id serial primary key,
    type varchar (15) NOT NULL
);
create table public.tb_user(
    id_user serial primary key,
    membership_id bigint NOT NULL,
    name_user varchar(255) NOT NULL,
    mail_user varchar(255) NOT NULL,
    CONSTRAINT fk_membership_id FOREIGN KEY (membership_id) REFERENCES tb_membership_type(membership_id)
);