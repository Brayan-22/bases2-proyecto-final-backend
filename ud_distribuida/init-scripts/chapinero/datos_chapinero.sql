-- Active: 1739285099109@@127.0.0.1@5432@chapinero
-- Limpiar tablas.
DELETE FROM calificar;
DELETE FROM dictar;
DELETE FROM Grupo;
DELETE FROM Detalle_estudiante;
DELETE FROM Detalles_profesor;
DELETE FROM Estudiante;
DELETE FROM nomina_profesor;
DELETE FROM nomina;
DELETE FROM Profesor;
DELETE FROM Asignatura;
DELETE FROM Clasificacion;
DELETE FROM Pregrado;

-- Semilla para garantizar mismos valores.
SELECT SETSEED(0.12341234);

-- Poblar tabla Pregrado.
INSERT INTO Pregrado(cod_pregrado, nombre_pregrado, creditos_pregrado, nota_minima, correo_pregrado, sede)
SELECT
    'PRECH' || g,
    'Pregrado Chapinero ' || g,
    (100 + random() * 20)::int,
    3 + random() * 2,
    'pregradoch' || g || '@gmail.com',
    'CHAPINERO'
FROM generate_series(1, 10) g;

-- Poblar tabla Asignatura.
INSERT INTO Asignatura(cod_asignatura, cod_pregrado, nombre_asignatura, horas_sem_asignatura, estud_max_asignatura, semestre_asignatura)
SELECT
    g,
    'PRECH' || (1 + FLOOR(random() * 10)),
    'Asignatura Chapinero ' || g,
    2 + random() * 2,
    20 + random() * 10,
    1 + random() * 10
FROM generate_series(1, 50) g;

-- Insertar en tabla Estudiante.
INSERT INTO Estudiante(cod_estudiante, cod_pregrado, nombres_estudiante, apellidos_estudiante, correo_estudianti)
SELECT
    'ESTCH' || g,
    'PRECH' || (1 + FLOOR(random() * 10)),
    'NombreEstCh' || g,
    'ApellidoEstCh' || g,
    'estudiantech' || g || '@udistrital.edu.co'
FROM generate_series(1, 50) g;

-- Insertar en tabla Detalle_estudiante.
INSERT INTO Detalle_estudiante(cod_estudiante, telefono_estudiante, correo_personal_estudiante, num_documento)
SELECT
    'ESTCH' || g,
    '300' || SUBSTR('' || g || (1 + FLOOR(random() * 1000000)), 1, 7),
    'estudiantech' || g || '@gmail.com',
    '100' || SUBSTR('' || g || (1 + FLOOR(random() * 1000000)), 1, 7)
FROM generate_series(1, 50) g;

-- Insertar en tabla Clasificacion.
INSERT INTO Clasificacion(nom_clasificacion, max_horas_clas, sueldo_clasificacion) VALUES
    ('PLANTA', 40, 3000000),
    ('CONTRATACION_ESPECIAL', 20, 2000000);

-- Insertar en tabla Profesor.
INSERT INTO Profesor(doc_profesor, nom_clasificacion, nombre_profesor, apellido_profesor, correo_profesor, cod_pregrado)
SELECT
    'PROFCH' || g,
    CASE WHEN (random()::int)=0 THEN 'PLANTA' ELSE 'CONTRATACION_ESPECIAL' END,
    'NombreProfCh' || g,
    'ApellidoProfCh' || g,
    'profesorch' || g || '@udistrital.edu.co',
    'PRECH' || (1 + FLOOR(random() * 10))
FROM generate_series(1, 20) g;

-- Insertar en tabla Detalles_profesor.
INSERT INTO Detalles_profesor(doc_profesor, direccion_profesor, correo_personar, telefono_profesor)
SELECT
    'PROFCH' || g,
    (
        CASE WHEN (random()::int)=0 THEN 'Calle ' ELSE 'Carrera ' END ||
        (1 + FLOOR(random() * 100)) || ' # ' ||
        (1 + FLOOR(random() * 100)) || '-' ||
        (1 + FLOOR(random() * 100))
    ),
    'profesorch' || g || '@gmail.com',
    '300' || SUBSTR('' || g || (1 + FLOOR(random() * 1000000)), 1, 7)
FROM generate_series(1, 20) g;

-- Insertar en tabla Grupo.
INSERT INTO Grupo(cod_grupo, periodo_grupo, cod_asignatura)
SELECT
    a.cod_asignatura || '-' || g,
    '2024-01',
    a.cod_asignatura
FROM
    generate_series(1, 3) g,
    Asignatura a;

-- Insertar en tabla Calificar.
INSERT INTO calificar(cod_grupo, periodo_grupo, cod_estudiante)
SELECT DISTINCT
    gr.cod_grupo,
    '2024-01',
    'ESTCH' || (1 + FLOOR(random() * 50))
FROM
    Grupo gr,
    generate_series(1, 20) g;

-- Insertar en tabla Dictar.
WITH pk_dictar AS (
    SELECT DISTINCT
        'PROFCH' || (1 + FLOOR(random() * 20)) doc_profesor,
        gr.cod_grupo cod_grupo,
        '2024-01' periodo_grupo
    FROM
        Grupo gr,
        generate_series(1, 2) g
)
INSERT INTO dictar(doc_profesor, cod_grupo, periodo_grupo, hora_ssem_dictar)
SELECT
    doc_profesor,
    cod_grupo,
    periodo_grupo,
    (2 + random() * 4)::int d
FROM pk_dictar;

-- Insertar en tabla nomina.

WITH valores_generados AS (
    SELECT
        'NOM' || g id_nomina,
        '2024-01-01'::date + (random() * 365)::int fecha_inicio,
        CASE WHEN (random()::int)=0 THEN 'PAGADA' ELSE 'PENDIENTE' END estado
    FROM
        generate_series(1, 2 * (SELECT COUNT(*) FROM profesor)) g
)
INSERT INTO nomina(id_nomina, fecha_inicio, fecha_fin, estado)
SELECT
    id_nomina,
    fecha_inicio,
    fecha_inicio + interval '1 month' fecha_fin,
    estado
FROM valores_generados;

-- Insertar en tabla nomina_profesor.
INSERT INTO nomina_profesor(doc_profesor, id_nomina, pago)
SELECT
    p.doc_profesor,
    n.id_nomina,
    c.sueldo_clasificacion
FROM
    (SELECT doc_profesor, nom_clasificacion, ROW_NUMBER() OVER () as rn FROM Profesor) p
JOIN
    (SELECT id_nomina, ROW_NUMBER() OVER () as rn FROM nomina) n
ON p.rn = n.rn
JOIN
    (SELECT nom_clasificacion, sueldo_clasificacion FROM Clasificacion) c
ON p.nom_clasificacion = c.nom_clasificacion;
