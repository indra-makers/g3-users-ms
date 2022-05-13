create table public.tbl_membership_types(
    membership_id serial primary key,
    type varchar (15) NOT NULL
);
create table public.tbl_users(
    id_user serial primary key,
    id_membership bigint NOT NULL,
    name varchar(255) NOT NULL,
    mail varchar(255) NOT NULL,
    CONSTRAINT fk_membership_id FOREIGN KEY (id_membership) REFERENCES tbl_membership_types(membership_id)
);