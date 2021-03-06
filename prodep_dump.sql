-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: prodep
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comprobaciones`
--

DROP TABLE IF EXISTS `comprobaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comprobaciones` (
  `id_comp` int NOT NULL AUTO_INCREMENT,
  `id_usuario` int DEFAULT NULL,
  `id_fc` int DEFAULT NULL,
  `doc_url` varchar(120) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `estado` int DEFAULT '1',
  `ultima_revision` datetime DEFAULT NULL,
  `comentario` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id_comp`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_fc` (`id_fc`),
  CONSTRAINT `comprobaciones_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `comprobaciones_ibfk_2` FOREIGN KEY (`id_fc`) REFERENCES `fc` (`id_fc`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comprobaciones`
--

LOCK TABLES `comprobaciones` WRITE;
/*!40000 ALTER TABLE `comprobaciones` DISABLE KEYS */;
INSERT INTO `comprobaciones` VALUES (15,2,5,'\\uploads\\Builder.png','2021-11-21 01:31:47',2,'2021-11-21 01:32:52','Est?? muy feo.'),(16,2,3,'\\uploads\\Practica Final M.pkt','2021-11-21 01:37:16',3,'2021-11-21 01:37:27',NULL),(17,2,19,'\\uploads\\juegoN.png','2021-11-22 15:08:56',3,'2021-11-23 21:24:56',NULL),(19,2,1,'\\uploads\\Ciclo del DevOps 2.0.pdf','2021-11-22 18:59:36',2,'2021-11-22 19:20:45','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi et sodales est, iaculis commodo tortor. Duis fringilla faucibus scelerisque. Morbi commodo elit in ex malesuada commodo. Fusce gravida erat augue, eu interdum odio iaculis vitae.'),(20,2,2,'\\uploads\\CuadroComparativo.Equipo6 (1).pdf','2021-11-22 19:35:10',2,'2021-11-22 19:35:47','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi et sodales est, iaculis commodo tortor. Duis fringilla faucibus scelerisque. Morbi commodo elit in ex malesuada commodo. asas asasdasd asdasda sdasd asd as da sd asd  ASD SAFC DSF SD F AD'),(21,2,4,'\\uploads\\Practica de laboratorio DPS1.pdf','2021-11-22 19:36:12',2,'2021-11-24 18:15:24','Por qu?? rechaz?? el documento...'),(22,2,9,'\\uploads\\actividad2.png','2021-11-24 17:59:37',1,'2021-11-24 17:59:37',NULL);
/*!40000 ALTER TABLE `comprobaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fc`
--

DROP TABLE IF EXISTS `fc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fc` (
  `id_fc` int NOT NULL AUTO_INCREMENT,
  `id_rubro` int DEFAULT NULL,
  `nombre` varchar(120) DEFAULT NULL,
  `descripcion` text,
  PRIMARY KEY (`id_fc`),
  KEY `id_rubro` (`id_rubro`),
  CONSTRAINT `fc_ibfk_1` FOREIGN KEY (`id_rubro`) REFERENCES `rubro` (`id_rubro`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fc`
--

LOCK TABLES `fc` WRITE;
/*!40000 ALTER TABLE `fc` DISABLE KEYS */;
INSERT INTO `fc` VALUES (1,1,'Docencia','<h6>Documento:</h6>\n<p>* -Horarios o carga acad??mica semestral, de los per??odos a evaluar</p>\n\n<h6>Condiciones:</h6>\n<p>-Haber impartido al menos un curso frente al grupo, por a??o en el nivel de Licenciatura o de Posgrado. Y actualmente estar frente al grupo, por ejemplo:</p>\n<p>-Per??odo 1 (2018)</p>\n<p>-Per??odo 2 (2019)</p>\n<p>-Per??odo 3 (2020)</p>\n<p>-Per??odo actual (2021-1)</p>\n\n<h6>Documento:</h6>\n<p>* Constancia del (la) Jefe (a) de Departamento de Servicios Escolares que indique por semestre el nivel, El nombre de la clave de la materia que imparte, asi como la cantidad de los estudiantes atendidos en cada grupo durante el per??odo a evaluar</p>\n<h6>Condiciones:</h6>\n<p>-Haber impartido al menos un curso frente al grupo, por a??o en el nivel de Licenciatura o de Posgrado. Y actualmente estar frente al grupo, por ejemplo:</p>\n<p>-Per??odo 1 (2018)</p>\n<p>-Per??odo 2 (2019)</p>\n<p>-Per??odo 3 (2020)</p>\n<p>-Per??odo actual (2021-1)</p>'),(2,2,'Tutor asignado por el PIT','<h6>Documento:</h6>\n<p>* Reporte de resultados de tutor??as y constancia de cumplimiento firmada por el (la jefe (a) del depto. de desarrollo acad??mico.</p>\n<h6>Condiciones:</h6>\n<p>Cumplir con 2 individuales o 1 colectiva en un per??odo anterior.</p>\n<p>Contar con tutor??as en proceso.</p>'),(3,2,'Director de t??sis (concluidas)','<h6>Documento:<h6>\n<p>* Copia del documento del libro de actas de examen Profesional o de grado en la que aparezca como presidente del jurado.</p>\n<h6>Condiciones:<h6>\n<p>Haber dirigido al menos 1 tesis.</p>\n<p>Contar con una direcci??n individualizada en proceso.</p>'),(4,2,'Otro tipo de asesor??as','<h4>Tipos de asesor??as</h4>\n<p>Assesor??as a alumnos de sus materias (especiales).</p>\n<p>Asesor??as a alumnos de materias de otras ??reas.</p>\n<p>Asesor??as a estudiantes en Servicio Social.</p>\n<p>Asesor??as a estudiantes en Proyectos de Formaci??n Dual.</p>\n<h6>Documento:</h6>\n<p>* Constancia de cumplimento por parte del Jefe (a) del Departamento Acad??mico correspondiente.</p>\n<h6>Condiciones:</h6>\n<p>Orientaci??n y/o asesor??as a estudiantes o grupos.</p>\n<p>Contar con asesor??a en proceso.</p>\n<h4>Otras asesor??as</h4>\n<p>Asesor??a de estudiantes del TecNM Concurso o Eventos Acad??micos (ENEIT, CONACYT, etc.).</p>\n<h6>Documento:</h6>\n<p>* Constancia de la Instituci??n Organizadora, donde indique el Evento o Concurso donde particip??.</p>\n<h6>Condiciones:</h6>\n<p>Cumplir con 2 individuales o 1 colectiva en un per??odo anterior.</p>\n<h6>Documento:</h6>\n<p>* Constancia emitida por el Departamento Acad??mico.</p>\n<h6>Condiciones:</h6>\n<p>Cumplir con 2 individuales o 1 colectiva en un per??odo anterior.</p>'),(5,3,'Nombramientos de cargos','<h4>Tipos de Nombramientos</h4>\n<p>Director</p>\n<p>Subdirector</p>\n<p>Presidente de Academia</p>\n<p>Secretario de Academia</p>\n<p>Miembros de Consejo Acad??mico</p>\n<p>Coordinador de Posgrado</p>\n<p>Coordinador de Carrera</p>\n<p>Jefe de Proyectos de Vinculaci??n</p>\n<p>Coordinador de Residencias</p>\n<p>Coordinadora Institucional de Tutor??as</p>\n<p>Coordinador Institucional de Idiomas</p>\n<p>Representante Institucional PRODEP</p>\n<p>Enlace CONACyT</p>\n<p>Coordinador de CIIE</p>\n<p>Jefe de Departamento Acad??mico</p>\n<p>Jefes de Oficina</p>\n<p>Jefe de Laboratorio o Taller</p>\n<p>Auxiliar de Laboratorio o Taller</p>\n<p>Jefe de Proyectos de Docencia</p>\n<p>Jefe de Proyectos de Investigaci??n</p>\n<p>Coordinador de Proceso de Acreditaci??n de Carreras</p>\n<p>Coordinador de Certificaciones</p>\n<h6>Documento:</h6>\n<p>* Constancia de vigencia del nombramiento expedida por el Director.</p>\n<h6>Condiciones:</h6>\n<p>Cumplir con el nombramiento y comprobar 40 horas de labores.</p>\n<p>Contar con una gesti??n en proceso.</p>'),(6,3,'Nombramientos de Comisiones','<h4>Tipos de Comisiones</h4>\n<p>Comisiones especiales del TecNM (EDD, o cualquier secretar??a o direcci??n del TecNM).</p>\n<p>Comisiones especiales locales (EDD, dictaminadora y mixta de seguridad e higiene).</p>\n<h6>Documento:</h6>\n<p>* Oficio de Comisi??n firmada por el (la) Director (a) del plantel y constancia del TECNM.</p>\n<h6>Condiciones:</h6>\n<p>Cumplir con el nombramiento y comprobar 40 horas de labores.</p>\n<p>Contar con una gesti??n en proceso.</p>'),(7,4,'Art??culo arbitrado','<h6>Documento:</h6>\n<p>* Copia de la car??tula del art??culo publicado en la cual se mencione la adscripci??n del autor en el TecNM.</p>\n<h6>Condiciones:</h6>\n<p>Contar con un m??nimo de tres productos publicados dentro del per??odo de evaluaci??n (3 a??os).</p>'),(8,4,'Art??culo en revistas indexadas','<h6>Documento:</h6>\n<p>* Impresi??n de la pantalla de la p??gina del clarivate, donde aparece el nombre de la revista.</p>\n<h6>Condiciones:</h6>\n<h6>Documento:</h6>\n<p>* Copia de la car??tula del art??culo publicado en la cual se mencione la adscripci??n del autor en el TecNM.</p>\n<h6>Condiciones:</h6>'),(9,4,'Libro','<h6>Documento:</h6>\n<p>* Portada e ??ndice del libro con Vo.Bo, por el Director del Plantel.</p>\n<h6>Condiciones:</h6>\n<p>Se puede combinar, por ejemplo: 2018, 2019, 2020 y hasta febrero 2021.</p>\n<h6>Documento:</h6>\n<p>* Constancia del libro o cap??tulo publicado donde se especifique los autores del libro, ISBN, nombre de la publicaci??n, editorial y fecha.</p>\n<h6>Condiciones:</h6>\n<p>Se puede combinar, por ejemplo: 2018, 2019, 2020 y hasta febrero 2021.</p>'),(10,4,'Cap??tulo de libro','<h6>Documento:</h6>\n<p>* T??tulo emitido por el IMPI.</p>\n<h6>Condiciones:</h6>'),(11,4,'Patente','<h6>Documento:</h6>\n<p>* Registro ante indautor.</p>\n<h6>Condiciones:</h6>'),(12,4,'Prototipos','<h6>Documento:</h6>\n<p>* Documento que considere compruebe el rubro.</p>\n<h6>Condiciones:</h6>'),(13,4,'Informe T??cnico','<h6>Documento:</h6>\n<p>* Documento que considere compruebe el rubro.</p>\n<h6>Condiciones:</h6>'),(14,4,'Dise??o de herramientas','<h6>Documento:</h6>\n<p>* Documento que considere compruebe el rubro.</p>\n<h6>Condiciones:</h6>'),(15,4,'Transferencias de tecnolog??as','<h6>Documento:</h6>\n<p>* Documento que considere compruebe el rubro.</p>\n<h6>Condiciones:</h6>'),(16,4,'Modelo de utilidad','<h6>Documento:</h6>\n<p>* Documento que considere compruebe el rubro.</p>\n<h6>Condiciones:</h6>'),(17,4,'Desarrollo de infraestructura','<h6>Documento:</h6>\n<p>* Documento que considere compruebe el rubro.</p>\n<h6>Condiciones:</h6>'),(18,5,'Participaci??n en la actualizaci??n de Programas Educat??vos (PE)','<h6>Documento:</h6>\n<p>* Constancia por el (la) Director (a) del Plantel.</p>\n<h6>Condiciones:</h6>\n<p>Debe contener:</p>\n<p>Nombre del docente</p>\n<p>Departamento</p>\n<p>Per??odo</p>\n<p>Nombre de la Licenciatura</p>'),(19,6,'Premios o Distinciones','<h6>Documento:</h6>\n<p>* Comprobaci??n de premio o Distinci??n.</p>\n<h6>Condiciones:</h6>\n<p>Debe contener:</p>\n<p>Nombre del docente</p>\n<p>Departamento</p>\n<p>Per??odo</p>\n<p>Nombre de la Distinci??n</p>');
/*!40000 ALTER TABLE `fc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pruebas`
--

DROP TABLE IF EXISTS `pruebas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pruebas` (
  `id_prueba` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_prueba`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pruebas`
--

LOCK TABLES `pruebas` WRITE;
/*!40000 ALTER TABLE `pruebas` DISABLE KEYS */;
INSERT INTO `pruebas` VALUES (1,'ejemplo'),(2,'ejemplo'),(3,'ejemplo');
/*!40000 ALTER TABLE `pruebas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rubro`
--

DROP TABLE IF EXISTS `rubro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rubro` (
  `id_rubro` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(120) DEFAULT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `img_url` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`id_rubro`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rubro`
--

LOCK TABLES `rubro` WRITE;
/*!40000 ALTER TABLE `rubro` DISABLE KEYS */;
INSERT INTO `rubro` VALUES (1,'Docencia','Aqu?? se sube los documentos para comprobar que imparti?? clases en los per??odos necesarios, y el grado de la clase (Licenciatura o Posgrado).','http://localhost/img/docencia.png'),(2,'Tutor??as y/o Direcciones Individuales','Aqu?? se sube los documentos para comprobar que imparti?? tutorias en los per??odos necesarios, el per??odo y la cantidad de alumnos atendidos.','http://localhost/img/tutoria.png'),(3,'Gesti??n Acad??mica','Aqu?? se sube los documentos para comprobar que cuenta con nombramientos de cargos o comisiones.','http://localhost/img/tesis.png'),(4,'Producci??n acad??mica','Aqu?? se sube los documentos para comprobar que public?? algun libro o un cap??tulo del mismo, art??culos, patentes, entre otras.','http://localhost/img/asesorss.png'),(5,'Programas Educativos (PE)','Aqu?? se sube los documentos para comprobar que tuvo participaci??n en la actualizaci??n de Programas Educativos (PE) Licenciatura.','http://localhost/img/PE.png'),(6,'Premios o Distinciones','Aqu?? se sube los documentos para comprobar que cuenta con alguna Distinci??n o Premio de alg??n Evento','http://localhost/img/Premios.png');
/*!40000 ALTER TABLE `rubro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `a_paterno` varchar(50) DEFAULT NULL,
  `a_materno` varchar(50) DEFAULT NULL,
  `permiso` int DEFAULT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Sebastian Arath','Ca??edo','Nu??ez',1),(2,'Arturo Jerem??as','Castillo','Nu??ez',2),(3,'Said','Valencia','Miranda',2);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-13 19:46:20
