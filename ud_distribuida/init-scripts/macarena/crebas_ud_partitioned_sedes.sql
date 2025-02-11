-- Drop triggers
DROP TRIGGER IF EXISTS trigger_profesor_update ON Profesor;
DROP TRIGGER IF EXISTS trigger_pregrado_update ON Pregrado;

-- Drop functions (para triggers)
DROP FUNCTION IF EXISTS prevent_update_profesor();
DROP FUNCTION IF EXISTS prevent_update_pregrado();

-- Drop índices
DROP INDEX IF EXISTS pregrado_asignatura_FK;
DROP INDEX IF EXISTS Asignatura_PK;
DROP INDEX IF EXISTS Clasificacion_PK;
DROP INDEX IF EXISTS Detalle_estudiante_PK;
DROP INDEX IF EXISTS Detalles_profesor_PK;
DROP INDEX IF EXISTS pregrado_estudiante_FK;
DROP INDEX IF EXISTS Estudiante_PK;
DROP INDEX IF EXISTS asignatura_grupo_FK;
DROP INDEX IF EXISTS Grupo_PK;
DROP INDEX IF EXISTS Pregrado_PK;
DROP INDEX IF EXISTS profesor_clasificacion_FK;
DROP INDEX IF EXISTS Profesor_PK;
DROP INDEX IF EXISTS estudiante_grupo2_FK;
DROP INDEX IF EXISTS estudiante_grupo_FK;
DROP INDEX IF EXISTS calificar_PK;
DROP INDEX IF EXISTS grupo_profesor_FK;
DROP INDEX IF EXISTS grupo_profesor2_FK;
DROP INDEX IF EXISTS dictar_PK;

-- Drop tablas en orden de dependencia:
DROP TABLE IF EXISTS dictar;
DROP TABLE IF EXISTS calificar;
DROP TABLE IF EXISTS Grupo;
DROP TABLE IF EXISTS Detalle_estudiante;
DROP TABLE IF EXISTS Detalles_profesor;
DROP TABLE IF EXISTS Estudiante;
DROP TABLE IF EXISTS Profesor;
DROP TABLE IF EXISTS Asignatura;
DROP TABLE IF EXISTS Clasificacion;
DROP TABLE IF EXISTS Pregrado;

create table Asignatura (
                            cod_asignatura       INT8                 not null,
                            cod_pregrado         varchar(36)                 not null,
                            nombre_asignatura    VARCHAR(32)          not null,
                            horas_sem_asignatura INT2                 not null,
                            estud_max_asignatura INT2                 not null,
                            semestre_asignatura  INT2                 not null,
                            constraint PK_ASIGNATURA primary key (cod_asignatura)
);


create unique index Asignatura_PK on Asignatura (
                                                 cod_asignatura
    );


create  index pregrado_asignatura_FK on Asignatura (
                                                    cod_pregrado
    );


create table Clasificacion (
                               nom_clasificacion    VARCHAR(32)          not null,
                               max_horas_clas       INT2                 not null,
                               sueldo_clasificacion NUMERIC(10,2)        not null,
                               constraint PK_CLASIFICACION primary key (nom_clasificacion)
);


create unique index Clasificacion_PK on Clasificacion (
                                                       nom_clasificacion
    );


create table Detalle_estudiante (
                                    cod_estudiante       VARCHAR(10)          not null,
                                    telefono_estudiante  VARCHAR(10)          not null,
                                    correo_personal_estudiante VARCHAR(64)          not null,
                                    num_documento        VARCHAR(10)          not null,
                                    constraint PK_DETALLE_ESTUDIANTE primary key (cod_estudiante)
);


create unique index Detalle_estudiante_PK on Detalle_estudiante (
                                                                 cod_estudiante
    );


create table Detalles_profesor (
                                   doc_profesor         VARCHAR(10)          not null,
                                   direccion_profesor   VARCHAR(32)          not null,
                                   correo_personar      VARCHAR(64)          not null,
                                   telefono_profesor    VARCHAR(10)          not null,
                                   constraint PK_DETALLES_PROFESOR primary key (doc_profesor)
);


create unique index Detalles_profesor_PK on Detalles_profesor (
                                                               doc_profesor
    );


create table Estudiante (
                            cod_estudiante       VARCHAR(10)          not null,
                            cod_pregrado         varchar(36)                 not null,
                            nombres_estudiante   VARCHAR(32)          not null,
                            apellidos_estudiante VARCHAR(32)          not null,
                            correo_estudianti    VARCHAR(64)          not null,
                            constraint PK_ESTUDIANTE primary key (cod_estudiante)
);


create unique index Estudiante_PK on Estudiante (
                                                 cod_estudiante
    );


create  index pregrado_estudiante_FK on Estudiante (
                                                    cod_pregrado
    );


create table Grupo (
                       cod_grupo            VARCHAR(10)          not null,
                       periodo_grupo        VARCHAR(10)          not null,
                       cod_asignatura       INT8                 not null,
                       constraint PK_GRUPO primary key (cod_grupo, periodo_grupo)
);


create unique index Grupo_PK on Grupo (
                                       cod_grupo,
                                       periodo_grupo
    );


create  index asignatura_grupo_FK on Grupo (
                                            cod_asignatura
    );


create table Pregrado (
                          cod_pregrado         varchar(36)                 not null,
                          nombre_pregrado      VARCHAR(32)          not null,
                          creditos_pregrado    INT2                 not null,
                          nota_minima          double precision        not null,
                          correo_pregrado      VARCHAR(64)          not null,
                          sede                 VARCHAR(32) CHECK ( sede IN ('CHAPINERO','MACARENA','CIUDADBOLIVAR'))         not null,
                          constraint PK_PREGRADO primary key (cod_pregrado)
);


create unique index Pregrado_PK on Pregrado (
                                             cod_pregrado
    );


create table Profesor (
                          doc_profesor         VARCHAR(10)          not null,
                          nom_clasificacion    VARCHAR(32)          not null,
                          nombre_profesor      VARCHAR(32)          not null,
                          apellido_profesor    VARCHAR(32)          not null,
                          correo_profesor      VARCHAR(64)          not null,
                          cod_pregrado        varchar(36)                not null,
                          constraint PK_PROFESOR primary key (doc_profesor)
);


create unique index Profesor_PK on Profesor (
                                             doc_profesor
    );


create  index profesor_clasificacion_FK on Profesor (
                                                     nom_clasificacion
    );


create table calificar (
                           cod_grupo            VARCHAR(10)          not null,
                           periodo_grupo        VARCHAR(10)          not null,
                           cod_estudiante       VARCHAR(10)          not null,
                           constraint PK_CALIFICAR primary key (cod_grupo, periodo_grupo, cod_estudiante)
);


create unique index calificar_PK on calificar (
                                               cod_grupo,
                                               periodo_grupo,
                                               cod_estudiante
    );


create  index estudiante_grupo_FK on calificar (
                                                cod_grupo,
                                                periodo_grupo
    );


create  index estudiante_grupo2_FK on calificar (
                                                 cod_estudiante
    );


create table dictar (
                        doc_profesor         VARCHAR(10)          not null,
                        cod_grupo            VARCHAR(10)          not null,
                        periodo_grupo        VARCHAR(10)          not null,
                        hora_ssem_dictar     INT2                 not null,
                        constraint PK_DICTAR primary key (doc_profesor, cod_grupo, periodo_grupo)
);


create unique index dictar_PK on dictar (
                                         doc_profesor,
                                         cod_grupo,
                                         periodo_grupo
    );


create  index grupo_profesor2_FK on dictar (
                                            cod_grupo,
                                            periodo_grupo
    );


create  index grupo_profesor_FK on dictar (
                                           doc_profesor
    );

alter table Asignatura
    add constraint FK_ASIGNATU_PREGRADO__PREGRADO foreign key (cod_pregrado)
        references Pregrado (cod_pregrado)
        on delete restrict on update restrict;

alter table Detalle_estudiante
    add constraint FK_DETALLE__DETALLE_E_ESTUDIAN foreign key (cod_estudiante)
        references Estudiante (cod_estudiante)
        on delete restrict on update restrict;

alter table Detalles_profesor
    add constraint FK_DETALLES_DETALLES__PROFESOR foreign key (doc_profesor)
        references Profesor (doc_profesor)
        on delete restrict on update restrict;

alter table Estudiante
    add constraint FK_ESTUDIAN_PREGRADO__PREGRADO foreign key (cod_pregrado)
        references Pregrado (cod_pregrado)
        on delete restrict on update restrict;

alter table Grupo
    add constraint FK_GRUPO_ASIGNATUR_ASIGNATU foreign key (cod_asignatura)
        references Asignatura (cod_asignatura)
        on delete restrict on update restrict;

alter table Profesor
    add constraint FK_PROFESOR_PROFESOR__CLASIFIC foreign key (nom_clasificacion)
        references Clasificacion (nom_clasificacion)
        on delete restrict on update restrict;
alter table Profesor
    add constraint FK_PROFESOR_PROFESOR__PREGRADO foreign key (cod_pregrado)
        references Pregrado (cod_pregrado)
        on delete restrict on update restrict;

alter table calificar
    add constraint FK_CALIFICA_ESTUDIANT_GRUPO foreign key (cod_grupo, periodo_grupo)
        references Grupo (cod_grupo, periodo_grupo)
        on delete restrict on update restrict;

alter table calificar
    add constraint FK_CALIFICA_ESTUDIANT_ESTUDIAN foreign key (cod_estudiante)
        references Estudiante (cod_estudiante)
        on delete restrict on update restrict;

alter table dictar
    add constraint FK_DICTAR_GRUPO_PRO_PROFESOR foreign key (doc_profesor)
        references Profesor (doc_profesor)
        on delete restrict on update restrict;

alter table dictar
    add constraint FK_DICTAR_GRUPO_PRO_GRUPO foreign key (cod_grupo, periodo_grupo)
        references Grupo (cod_grupo, periodo_grupo)
        on delete restrict on update restrict;

-- Trigger para prevenir la inserción de pregrados y profesores fuera de la sede MACARENA
CREATE OR REPLACE FUNCTION prevent_update_profesor()
    RETURNS TRIGGER AS $$
DECLARE
    sede_profesor VARCHAR(36);
BEGIN
    IF TG_OP IN ('INSERT', 'UPDATE') THEN
        -- Get sede for new record
        SELECT sede INTO sede_profesor
        FROM Pregrado
        WHERE cod_pregrado = NEW.cod_pregrado;

        -- Block if not Macarena
        IF sede_profesor <> 'MACARENA' THEN
            RAISE EXCEPTION 'No se puede modificar profesor en sede diferente a MACARENA';
        END IF;
        RETURN NEW;
    ELSIF TG_OP = 'DELETE' THEN
        -- Get sede for existing record
        SELECT sede INTO sede_profesor
        FROM Pregrado
        WHERE cod_pregrado = OLD.cod_pregrado;

        IF sede_profesor <> 'MACARENA' THEN
            RAISE EXCEPTION 'No se puede eliminar profesor en sede diferente a MACARENA';
        END IF;
        RETURN OLD;
    END IF;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_profesor_update
    BEFORE INSERT OR UPDATE OR DELETE ON Profesor
    FOR EACH ROW EXECUTE FUNCTION prevent_update_profesor();

CREATE OR REPLACE FUNCTION prevent_update_pregrado()
    RETURNS TRIGGER AS $$
BEGIN
    IF TG_OP = 'INSERT' THEN
        IF NEW.sede <> 'MACARENA' THEN
            RAISE EXCEPTION 'No se puede crear pregrado en sede diferente a MACARENA';
        END IF;
        RETURN NEW;
    ELSIF TG_OP IN ('UPDATE', 'DELETE') THEN
        IF OLD.sede <> 'MACARENA' THEN
            RAISE EXCEPTION 'No se puede modificar pregrado en sede diferente a MACARENA';
        END IF;
        RETURN (CASE TG_OP WHEN 'DELETE' THEN OLD ELSE NEW END);
    END IF;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_pregrado_update
    BEFORE INSERT OR UPDATE OR DELETE ON Pregrado
    FOR EACH ROW EXECUTE FUNCTION prevent_update_pregrado();
