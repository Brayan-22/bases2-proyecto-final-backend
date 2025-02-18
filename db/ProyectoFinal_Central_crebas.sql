/*==============================================================*/
/* DBMS name:      PostgreSQL 9.x                               */
/* Created on:     31/01/2025 6:53:28 p. m.                     */
/*==============================================================*/


drop index pregrado_asignatura_FK;

drop index Asignatura_PK;

drop table Asignatura;

drop index grupo_calificar_FK;

drop index estudiante_calificar_FK;

drop index Calificar_PK;

drop table Calificar;

drop index Clasificacion_PK;

drop table Clasificacion;

drop index profesor_dictar_FK;

drop index grupo_dictar_FK;

drop index Dictar_PK;

drop table Dictar;

drop index pregrado_estudiante_FK;

drop index Estudiante_PK;

drop table Estudiante;

drop index asignatura_grupo_FK;

drop index Grupo_PK;

drop table Grupo;

drop index clasificacion_pagar_FK;

drop index profesor_pagar_FK;

drop index Pagar_PK;

drop table Pagar;

drop index sede_pregrado_FK;

drop index Pregrado_PK;

drop table Pregrado;

drop index clasificacion_profesor_FK;

drop index Profesor_PK;

drop table Profesor;

drop index Sede_PK;

drop table Sede;

drop index sede_usuario_FK;

drop index Usuario_PK;

drop table Usuario;

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
/* Table: Clasificacion                                         */
/*==============================================================*/
create table Clasificacion (
   nom_clasificacion    VARCHAR(30)          not null,
   max_hor_clasificacion INT2                 not null,
   sal_clasificacion    NUMERIC(10,2)        not null,
   constraint PK_CLASIFICACION primary key (nom_clasificacion)
);

/*==============================================================*/
/* Index: Clasificacion_PK                                      */
/*==============================================================*/
create unique index Clasificacion_PK on Clasificacion (
nom_clasificacion
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
/* Index: profesor_dictar_FK                                    */
/*==============================================================*/
create  index profesor_dictar_FK on Dictar (
profesor_dictar
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
/* Table: Pagar                                                 */
/*==============================================================*/
create table Pagar (
   profesor_pagar       VARCHAR(10)          not null,
   fecha_pagar          DATE                 not null,
   clasificacion_pagar  VARCHAR(30)          not null,
   constraint PK_PAGAR primary key (profesor_pagar, fecha_pagar)
);

/*==============================================================*/
/* Index: Pagar_PK                                              */
/*==============================================================*/
create unique index Pagar_PK on Pagar (
profesor_pagar,
fecha_pagar
);

/*==============================================================*/
/* Index: profesor_pagar_FK                                     */
/*==============================================================*/
create  index profesor_pagar_FK on Pagar (
profesor_pagar
);

/*==============================================================*/
/* Index: clasificacion_pagar_FK                                */
/*==============================================================*/
create  index clasificacion_pagar_FK on Pagar (
clasificacion_pagar
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

/*==============================================================*/
/* Index: sede_pregrado_FK                                      */
/*==============================================================*/
create  index sede_pregrado_FK on Pregrado (
sede_pregrado
);

/*==============================================================*/
/* Table: Profesor                                              */
/*==============================================================*/
create table Profesor (
   doc_profesor         VARCHAR(10)          not null,
   clasificacion_profesor VARCHAR(30)          not null,
   nom_profesor         VARCHAR(30)          not null,
   dir_profesor         VARCHAR(30)          not null,
   tel_profesor         VARCHAR(10)          not null,
   correo_profesor      VARCHAR(70)          not null,
   constraint PK_PROFESOR primary key (doc_profesor)
);

/*==============================================================*/
/* Index: Profesor_PK                                           */
/*==============================================================*/
create unique index Profesor_PK on Profesor (
doc_profesor
);

/*==============================================================*/
/* Index: clasificacion_profesor_FK                             */
/*==============================================================*/
create  index clasificacion_profesor_FK on Profesor (
clasificacion_profesor
);

/*==============================================================*/
/* Table: Sede                                                  */
/*==============================================================*/
create table Sede (
   nom_sede             VARCHAR(20)          not null,
   dir_sede             VARCHAR(30)          not null,
   correo_sede          VARCHAR(70)          not null,
   constraint PK_SEDE primary key (nom_sede)
);

/*==============================================================*/
/* Index: Sede_PK                                               */
/*==============================================================*/
create unique index Sede_PK on Sede (
nom_sede
);

/*==============================================================*/
/* Table: Usuario                                               */
/*==============================================================*/
create table Usuario (
   username_usuario     VARCHAR(30)          not null,
   password_usuario     VARCHAR(30)          not null,
   sede_usuario         VARCHAR(20)          not null,
   constraint PK_USUARIO primary key (username_usuario)
);

/*==============================================================*/
/* Index: Usuario_PK                                            */
/*==============================================================*/
create unique index Usuario_PK on Usuario (
username_usuario
);

/*==============================================================*/
/* Index: sede_usuario_FK                                       */
/*==============================================================*/
create  index sede_usuario_FK on Usuario (
sede_usuario
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

alter table Dictar
   add constraint FK_DICTAR_PROFESOR__PROFESOR foreign key (profesor_dictar)
      references Profesor (doc_profesor)
      on delete restrict on update restrict;

alter table Estudiante
   add constraint FK_ESTUDIAN_PREGRADO__PREGRADO foreign key (pregrado_estudiante)
      references Pregrado (cod_pregrado)
      on delete restrict on update restrict;

alter table Grupo
   add constraint FK_GRUPO_ASIGNATUR_ASIGNATU foreign key (asignatura_grupo)
      references Asignatura (cod_asignatura)
      on delete restrict on update restrict;

alter table Pagar
   add constraint FK_PAGAR_CLASIFICA_CLASIFIC foreign key (clasificacion_pagar)
      references Clasificacion (nom_clasificacion)
      on delete restrict on update restrict;

alter table Pagar
   add constraint FK_PAGAR_PROFESOR__PROFESOR foreign key (profesor_pagar)
      references Profesor (doc_profesor)
      on delete restrict on update restrict;

alter table Pregrado
   add constraint FK_PREGRADO_SEDE_PREG_SEDE foreign key (sede_pregrado)
      references Sede (nom_sede)
      on delete restrict on update restrict;

alter table Profesor
   add constraint FK_PROFESOR_CLASIFICA_CLASIFIC foreign key (clasificacion_profesor)
      references Clasificacion (nom_clasificacion)
      on delete restrict on update restrict;

alter table Usuario
   add constraint FK_USUARIO_SEDE_USUA_SEDE foreign key (sede_usuario)
      references Sede (nom_sede)
      on delete restrict on update restrict;

