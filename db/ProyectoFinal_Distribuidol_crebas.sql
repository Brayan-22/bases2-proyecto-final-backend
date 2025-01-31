/*==============================================================*/
/* DBMS name:      PostgreSQL 9.x                               */
/* Created on:     31/01/2025 6:57:21 p. m.                     */
/*==============================================================*/


drop index pregrado_asignatura_FK;

drop index Asignatura_PK;

drop table Asignatura;

drop index grupo_calificar_FK;

drop index estudiante_calificar_FK;

drop index Calificar_PK;

drop table Calificar;

drop index grupo_dictar_FK;

drop index Dictar_PK;

drop table Dictar;

drop index pregrado_estudiante_FK;

drop index Estudiante_PK;

drop table Estudiante;

drop index asignatura_grupo_FK;

drop index Grupo_PK;

drop table Grupo;

drop index Pregrado_PK;

drop table Pregrado;

/*==============================================================*/
/* Table: Asignatura                                            */
/*==============================================================*/
create table Asignatura (
   cod_asignatura       INT8                 not null,
   pregrado_asignatura  INT4                 not null,
   nom_asignatura       VARCHAR(30)          not null,
   horas_sem_asignatura INT2                 not null,
   estu_max_asignatura  INT2                 not null,
   semestre_asignatura  INT2                 not null,
   constraint PK_ASIGNATURA primary key (cod_asignatura)
);

/*==============================================================*/
/* Index: Asignatura_PK                                         */
/*==============================================================*/
create unique index Asignatura_PK on Asignatura (
cod_asignatura
);

/*==============================================================*/
/* Index: pregrado_asignatura_FK                                */
/*==============================================================*/
create  index pregrado_asignatura_FK on Asignatura (
pregrado_asignatura
);

/*==============================================================*/
/* Table: Calificar                                             */
/*==============================================================*/
create table Calificar (
   cod_grupo_calificar  VARCHAR(10)          not null,
   per_grupo_calificar  VARCHAR(10)          not null,
   estudiante_calificar VARCHAR(10)          not null,
   cod_calificar        VARCHAR(20)          not null,
   porc_calificar       NUMERIC(2,2)         not null,
   val_calificar        NUMERIC(3,2)         not null,
   constraint PK_CALIFICAR primary key (cod_grupo_calificar, per_grupo_calificar, estudiante_calificar, cod_calificar)
);

/*==============================================================*/
/* Index: Calificar_PK                                          */
/*==============================================================*/
create unique index Calificar_PK on Calificar (
cod_grupo_calificar,
per_grupo_calificar,
estudiante_calificar,
cod_calificar
);

/*==============================================================*/
/* Index: estudiante_calificar_FK                               */
/*==============================================================*/
create  index estudiante_calificar_FK on Calificar (
estudiante_calificar
);

/*==============================================================*/
/* Index: grupo_calificar_FK                                    */
/*==============================================================*/
create  index grupo_calificar_FK on Calificar (
cod_grupo_calificar,
per_grupo_calificar
);

/*==============================================================*/
/* Table: Dictar                                                */
/*==============================================================*/
create table Dictar (
   profesor_dictar      VARCHAR(10)          not null,
   cod_grupo_dictar     VARCHAR(10)          not null,
   per_grupo_dictar     VARCHAR(10)          not null,
   hor_sem_dictar       INT2                 not null,
   constraint PK_DICTAR primary key (profesor_dictar, cod_grupo_dictar, per_grupo_dictar)
);

/*==============================================================*/
/* Index: Dictar_PK                                             */
/*==============================================================*/
create unique index Dictar_PK on Dictar (
profesor_dictar,
cod_grupo_dictar,
per_grupo_dictar
);

/*==============================================================*/
/* Index: grupo_dictar_FK                                       */
/*==============================================================*/
create  index grupo_dictar_FK on Dictar (
cod_grupo_dictar,
per_grupo_dictar
);

/*==============================================================*/
/* Table: Estudiante                                            */
/*==============================================================*/
create table Estudiante (
   cod_estudiante       VARCHAR(10)          not null,
   pregrado_estudiante  INT4                 not null,
   documento            VARCHAR(10)          not null,
   nom_estudiante       VARCHAR(30)          not null,
   dir_estudiante       VARCHAR(30)          not null,
   tel_estudiante       VARCHAR(10)          not null,
   correo_estudiante    VARCHAR(70)          not null,
   constraint PK_ESTUDIANTE primary key (cod_estudiante)
);

/*==============================================================*/
/* Index: Estudiante_PK                                         */
/*==============================================================*/
create unique index Estudiante_PK on Estudiante (
cod_estudiante
);

/*==============================================================*/
/* Index: pregrado_estudiante_FK                                */
/*==============================================================*/
create  index pregrado_estudiante_FK on Estudiante (
pregrado_estudiante
);

/*==============================================================*/
/* Table: Grupo                                                 */
/*==============================================================*/
create table Grupo (
   cod_grupo            VARCHAR(10)          not null,
   periodo_grupo        VARCHAR(10)          not null,
   asignatura_grupo     INT8                 not null,
   constraint PK_GRUPO primary key (cod_grupo, periodo_grupo)
);

/*==============================================================*/
/* Index: Grupo_PK                                              */
/*==============================================================*/
create unique index Grupo_PK on Grupo (
cod_grupo,
periodo_grupo
);

/*==============================================================*/
/* Index: asignatura_grupo_FK                                   */
/*==============================================================*/
create  index asignatura_grupo_FK on Grupo (
asignatura_grupo
);

/*==============================================================*/
/* Table: Pregrado                                              */
/*==============================================================*/
create table Pregrado (
   cod_pregrado         INT4                 not null,
   sede_pregrado        VARCHAR(20)          not null,
   nom_pregrado         VARCHAR(30)          not null,
   cred_pregrado        INT2                 not null,
   nota_min_pregrado    NUMERIC(3,2)         not null,
   constraint PK_PREGRADO primary key (cod_pregrado)
);

/*==============================================================*/
/* Index: Pregrado_PK                                           */
/*==============================================================*/
create unique index Pregrado_PK on Pregrado (
cod_pregrado
);

alter table Asignatura
   add constraint FK_ASIGNATU_PREGRADO__PREGRADO foreign key (pregrado_asignatura)
      references Pregrado (cod_pregrado)
      on delete restrict on update restrict;

alter table Calificar
   add constraint FK_CALIFICA_ESTUDIANT_ESTUDIAN foreign key (estudiante_calificar)
      references Estudiante (cod_estudiante)
      on delete restrict on update restrict;

alter table Calificar
   add constraint FK_CALIFICA_GRUPO_CAL_GRUPO foreign key (cod_grupo_calificar, per_grupo_calificar)
      references Grupo (cod_grupo, periodo_grupo)
      on delete restrict on update restrict;

alter table Dictar
   add constraint FK_DICTAR_GRUPO_DIC_GRUPO foreign key (cod_grupo_dictar, per_grupo_dictar)
      references Grupo (cod_grupo, periodo_grupo)
      on delete restrict on update restrict;

alter table Estudiante
   add constraint FK_ESTUDIAN_PREGRADO__PREGRADO foreign key (pregrado_estudiante)
      references Pregrado (cod_pregrado)
      on delete restrict on update restrict;

alter table Grupo
   add constraint FK_GRUPO_ASIGNATUR_ASIGNATU foreign key (asignatura_grupo)
      references Asignatura (cod_asignatura)
      on delete restrict on update restrict;

