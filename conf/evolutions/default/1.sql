# ImaSec schema

# --- !Ups

CREATE TABLE ImaSec (
    id serial NOT NULL,
    id_tienda integer NOT NULL,
    id_mueble integer NOT NULL,
    id_seccion integer NOT NULL,
    id_auditor integer NOT NULL,
    picture varchar(255) NOT NULL,
    fecha timestamp ,
    hora timestamp without time zone ,
    PRIMARY KEY (id)
);

# --- !Downs

#DROP TABLE ImaSec;