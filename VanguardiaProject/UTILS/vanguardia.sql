-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: vanguardia
-- ------------------------------------------------------
-- Server version	8.0.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bloques`
--

DROP TABLE IF EXISTS `bloques`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bloques` (
  `ID_BLOQUE` int NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`ID_BLOQUE`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bloques`
--

LOCK TABLES `bloques` WRITE;
/*!40000 ALTER TABLE `bloques` DISABLE KEYS */;
INSERT INTO `bloques` VALUES (1,'Edificio 1'),(2,'Edificio 2'),(3,'Ciencias Aplicadas'),(4,'BLOQUE 4');
/*!40000 ALTER TABLE `bloques` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carreras`
--

DROP TABLE IF EXISTS `carreras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carreras` (
  `carrera_id` int NOT NULL AUTO_INCREMENT,
  `nombre_carrera` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`carrera_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carreras`
--

LOCK TABLES `carreras` WRITE;
/*!40000 ALTER TABLE `carreras` DISABLE KEYS */;
INSERT INTO `carreras` VALUES (1,'Software'),(2,'Tecnologías de la Información'),(3,'Ingeniería Industrial'),(4,'Telecomunicaciones'),(5,'Automatización y Robótica');
/*!40000 ALTER TABLE `carreras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `espacios`
--

DROP TABLE IF EXISTS `espacios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `espacios` (
  `ID_ESPACIO` int NOT NULL AUTO_INCREMENT,
  `TIPO` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `NOMBRE` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `ID_BLOQUE_PERTENECE` int DEFAULT NULL,
  PRIMARY KEY (`ID_ESPACIO`),
  KEY `ID_BLOQUE_PERTENECE` (`ID_BLOQUE_PERTENECE`),
  CONSTRAINT `espacios_ibfk_1` FOREIGN KEY (`ID_BLOQUE_PERTENECE`) REFERENCES `bloques` (`ID_BLOQUE`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `espacios`
--

LOCK TABLES `espacios` WRITE;
/*!40000 ALTER TABLE `espacios` DISABLE KEYS */;
INSERT INTO `espacios` VALUES (1,'LABORATORIO','AUDIOVISUALES (48)',1),(2,'AULA','AULA 1 (36)',3),(3,'AULA','AULA 2 (36)',3),(4,'AULA','AULA 3 (36)',3),(5,'AULA','AULA 4 (36)',3),(6,'AULA','AULA 5 (36)',3),(7,'AULA','AULA 6 (40)',3),(8,'AULA','AULA 7 (40)',3),(9,'AULA','AULA 8 (40)',3),(10,'AULA','AULA 9 (40)',3),(11,'AULA','AULA 10 (40)',3),(12,'AULA','AULA F01 (39)',2),(13,'AULA','AULA F02 (39)',2),(14,'AULA','AULA F03 (39)',2),(15,'LABORATORIO','LABORATORIO F04 (40)',2),(16,'AULA','AULA F08 (40)',2),(17,'AULA','AULA G02 (30)',2),(18,'AULA','AULA G03 (30)',2),(19,'AULA','AULA G04 (40)',2),(20,'AULA','AULA H02 (32)',2),(21,'AULA','AULA H03 (40)',2),(22,'AULA','AULA H04 (40)',2),(23,'AULA','AULA H05 (30)',2),(24,'AULA','AULA I01 (50)',2),(25,'AULA','AULA I02 (40)',2),(26,'AULA','AULA I03 (40)',2),(27,'AULA','AULA J01 (40)',2),(28,'AULA','AULA J05 (30)',2),(29,'LABORATORIO','LABORATORIO 1 (40)',1),(30,'LABORATORIO','LABORATORIO 2 (40)',1),(31,'LABORATORIO','LABORATORIO 3 (24)',1),(32,'LABORATORIO','LABORATORIO 4 (24)',1),(33,'LABORATORIO','LABORATORIO 5 (24)',1),(34,'LABORATORIO','LABORATORIO 6 (32)',1),(35,'LABORATORIO','LABORATORIO 7 (40)',1),(36,'LABORATORIO','LABORATORIO 8 (40)',1),(37,'LABORATORIO','LAB. CTT (56)',1),(38,'LABORATORIO','LAB. REDES 1 (38)',1),(39,'LABORATORIO','LAB. REDES 2 (40)',1),(40,'LABORATORIO','LAB. INDUSTRIAL 1 (32)',2),(41,'LABORATORIO','LAB. INDUSTRIAL 2 (32)',2),(42,'LABORATORIO','LAB. ROBÓTICA Y REDES INDUSTRIALES (25)',2),(43,'LABORATORIO','LAB. AUTOMATIZACIÓN INDUSTRIAL (32)',2),(44,'LABORATORIO','LAB. CNC (25)',4),(45,'LABORATORIO','LAB. COMUNICACIONES (25)',2),(46,'LABORATORIO','LAB. ELECTRÓNICA AVANZADA (32)',1),(47,'LABORATORIO','LAB. ELECTRÓNICA BÁSICA (22)',2),(48,'LABORATORIO','LAB. INSTRUMENTACIÓN VIRTUAL (24)',2),(49,'LABORATORIO','LAB. MÁQUINAS ELÉCTRICAS (25)',1),(50,'LABORATORIO','LAB. PLC\'S (25)',2);
/*!40000 ALTER TABLE `espacios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `horarios`
--

DROP TABLE IF EXISTS `horarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `horarios` (
  `id_horario` int NOT NULL,
  `fk_materia` int DEFAULT NULL,
  `hora` varchar(11) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `lunes` tinyint(1) DEFAULT NULL,
  `martes` tinyint(1) DEFAULT NULL,
  `miercoles` tinyint(1) DEFAULT NULL,
  `jueves` tinyint(1) DEFAULT NULL,
  `viernes` tinyint(1) DEFAULT NULL,
  `fk_espacio` int DEFAULT NULL,
  PRIMARY KEY (`id_horario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horarios`
--

LOCK TABLES `horarios` WRITE;
/*!40000 ALTER TABLE `horarios` DISABLE KEYS */;
INSERT INTO `horarios` VALUES (1,1,'07:00-09:00',1,NULL,NULL,NULL,NULL,27),(2,2,'09:00-11:00',1,NULL,NULL,NULL,NULL,27),(3,3,'11:00-13:00',1,NULL,NULL,NULL,NULL,29),(4,2,'07:00-09:00',NULL,1,NULL,NULL,NULL,27),(5,1,'09:00-10:00',NULL,1,NULL,NULL,NULL,27),(6,4,'10:00-12:00',NULL,1,NULL,NULL,NULL,27),(7,1,'07:00-09:00',NULL,NULL,1,NULL,NULL,27),(8,3,'07:00-10:00',NULL,NULL,NULL,1,NULL,29),(9,2,'10:00-12:00',NULL,NULL,NULL,1,NULL,27),(10,2,'07:00-09:00',NULL,NULL,NULL,NULL,1,27),(11,1,'14:00-16:00',1,NULL,NULL,NULL,NULL,27),(12,2,'16:00-18:00',1,NULL,NULL,NULL,NULL,27),(13,2,'14:00-16:00',NULL,1,NULL,NULL,NULL,27),(14,1,'16:00-18:00',NULL,1,NULL,NULL,NULL,27),(15,3,'14:00-16:00',NULL,NULL,1,NULL,NULL,29),(16,2,'16:00-18:00',NULL,NULL,1,NULL,NULL,27),(17,4,'18:00-20:00',NULL,NULL,1,NULL,NULL,27),(18,1,'14:00-15:00',NULL,NULL,NULL,1,NULL,27),(19,2,'14:00-16:00',NULL,NULL,NULL,1,NULL,27),(20,3,'14:00-17:00',NULL,NULL,NULL,NULL,1,29),(21,5,'07:00-09:00',1,NULL,NULL,NULL,NULL,24),(22,6,'09:00-10:00',1,NULL,NULL,NULL,NULL,24),(23,7,'11:00-13:00',1,NULL,NULL,NULL,NULL,24),(24,8,'07:00-10:00',NULL,1,NULL,NULL,NULL,29),(25,7,'10:00-11:00',NULL,1,NULL,NULL,NULL,24),(26,9,'11:00-13:00',NULL,1,NULL,NULL,NULL,24),(27,7,'07:00-09:00',NULL,NULL,1,NULL,NULL,24),(28,9,'09:00-11:00',NULL,NULL,1,NULL,NULL,24),(29,6,'11:00-13:00',NULL,NULL,1,NULL,NULL,24),(30,5,'07:00-10:00',NULL,NULL,NULL,1,NULL,24),(31,8,'10:00-13:00',NULL,NULL,NULL,1,NULL,29),(32,9,'08:00-09:00',NULL,NULL,NULL,NULL,1,24),(33,8,'07:00-10:00',1,NULL,NULL,NULL,NULL,36),(34,9,'10:00-13:00',1,NULL,NULL,NULL,NULL,25),(35,8,'07:00-10:00',NULL,1,NULL,NULL,NULL,36),(36,7,'11:00-13:00',NULL,1,NULL,NULL,NULL,25),(37,5,'07:00-10:00',NULL,NULL,1,NULL,NULL,25),(38,7,'09:00-11:00',NULL,NULL,1,NULL,NULL,25),(39,6,'11:00-13:00',NULL,NULL,1,NULL,NULL,25),(40,5,'10:00-13:00',NULL,NULL,NULL,1,NULL,25),(41,6,'07:00-08:00',NULL,NULL,NULL,NULL,1,25),(42,7,'08:00-09:00',NULL,NULL,NULL,NULL,1,25),(43,9,'09:00-11:00',NULL,NULL,NULL,NULL,1,29),(55,10,'14:00-16:00',1,NULL,NULL,NULL,NULL,36),(56,11,'16:00-18:00',1,NULL,NULL,NULL,NULL,25),(57,12,'18:00-20:00',1,NULL,NULL,NULL,NULL,36),(58,13,'14:00-17:00',NULL,1,NULL,NULL,NULL,36),(59,12,'17:00-19:00',NULL,1,NULL,NULL,NULL,36),(60,13,'14:00-16:00',NULL,NULL,1,NULL,NULL,36),(61,12,'16:00-19:00',NULL,NULL,1,NULL,NULL,36),(62,10,'14:00-16:00',NULL,NULL,NULL,1,NULL,36),(63,14,'16:00-18:00',NULL,NULL,NULL,1,NULL,25),(64,11,'18:00-20:00',NULL,NULL,NULL,1,NULL,25),(65,10,'14:00-17:00',NULL,NULL,NULL,NULL,1,36),(66,15,'09:00-11:00',1,NULL,NULL,NULL,NULL,22),(67,16,'11:00-13:00',1,NULL,NULL,NULL,NULL,12),(68,17,'07:00-10:00',NULL,1,NULL,NULL,NULL,39),(69,18,'10:00-13:00',NULL,1,NULL,NULL,NULL,29),(70,15,'07:00-09:00',NULL,NULL,1,NULL,NULL,22),(71,18,'09:00-12:00',NULL,NULL,1,NULL,NULL,29),(72,17,'07:00-10:00',NULL,NULL,NULL,1,NULL,39),(73,19,'10:00-13:00',NULL,NULL,NULL,1,NULL,36),(74,16,'07:00-09:00',NULL,NULL,NULL,NULL,1,12),(75,19,'09:00-11:00',NULL,NULL,NULL,NULL,1,36),(76,20,'14:00-16:00',1,NULL,NULL,NULL,NULL,38),(77,21,'16:00-18:00',1,NULL,NULL,NULL,NULL,19),(78,22,'18:00-20:00',1,NULL,NULL,NULL,NULL,30),(79,23,'14:00-16:00',NULL,1,NULL,NULL,NULL,30),(80,20,'16:00-18:00',NULL,1,NULL,NULL,NULL,39),(81,22,'18:00-20:00',NULL,1,NULL,NULL,NULL,30),(82,21,'14:00-17:00',NULL,NULL,1,NULL,NULL,32),(83,24,'17:00-20:00',NULL,NULL,1,NULL,NULL,37),(84,23,'14:00-17:00',NULL,NULL,NULL,1,NULL,30),(85,24,'17:00-20:00',NULL,NULL,NULL,1,NULL,37),(86,20,'15:00-17:00',NULL,NULL,NULL,NULL,1,39),(87,22,'17:00-18:00',NULL,NULL,NULL,NULL,1,30),(88,25,'07:00-10:00',1,NULL,NULL,NULL,NULL,31),(89,26,'10:00-13:00',1,NULL,NULL,NULL,NULL,30),(90,27,'07:00-09:00',NULL,1,NULL,NULL,NULL,33),(91,26,'09:00-12:00',NULL,1,NULL,NULL,NULL,30),(92,28,'07:00-10:00',NULL,NULL,1,NULL,NULL,37),(93,29,'10:00-13:00',NULL,NULL,1,NULL,NULL,30),(94,25,'07:00-09:00',NULL,NULL,NULL,1,NULL,31),(95,27,'09:00-11:00',NULL,NULL,NULL,1,NULL,38),(96,29,'11:00-13:00',NULL,NULL,NULL,1,NULL,34),(97,28,'07:00-10:00',NULL,NULL,NULL,NULL,1,37),(98,30,'14:00-17:00',1,NULL,NULL,NULL,NULL,33),(99,31,'17:00-19:00',1,NULL,NULL,NULL,NULL,31),(100,32,'14:00-17:00',NULL,1,NULL,NULL,NULL,35),(101,33,'17:00-20:00',NULL,1,NULL,NULL,NULL,33),(102,32,'14:00-17:00',NULL,NULL,1,NULL,NULL,35),(103,34,'17:00-20:00',NULL,NULL,1,NULL,NULL,33),(104,33,'14:00-17:00',NULL,NULL,NULL,1,NULL,33),(105,30,'17:00-19:00',NULL,NULL,NULL,1,NULL,33),(106,31,'14:00-16:00',NULL,NULL,NULL,NULL,1,30),(107,34,'16:00-19:00',NULL,NULL,NULL,NULL,1,33),(108,35,'07:00-09:00',1,NULL,NULL,NULL,NULL,35),(109,36,'09:00-12:00',1,NULL,NULL,NULL,NULL,35),(110,37,'08:00-10:00',NULL,1,NULL,NULL,NULL,34),(111,38,'10:00-13:00',NULL,1,NULL,NULL,NULL,35),(112,39,'07:00-09:00',NULL,NULL,1,NULL,NULL,34),(113,38,'09:00-11:00',NULL,NULL,1,NULL,NULL,35),(114,36,'11:00-13:00',NULL,NULL,1,NULL,NULL,35),(115,37,'08:00-11:00',NULL,NULL,NULL,1,NULL,34),(116,35,'11:00-13:00',NULL,NULL,NULL,1,NULL,35),(117,39,'08:00-11:00',NULL,NULL,NULL,NULL,1,31),(118,40,'15:00-17:00',1,NULL,NULL,NULL,NULL,17),(119,41,'17:00-20:00',1,NULL,NULL,NULL,NULL,34),(120,42,'15:00-17:00',NULL,1,NULL,NULL,NULL,32),(121,43,'17:00-20:00',NULL,1,NULL,NULL,NULL,35),(122,41,'14:00-16:00',NULL,NULL,1,NULL,NULL,34),(123,43,'14:00-17:00',NULL,NULL,NULL,1,NULL,35),(124,42,'17:00-20:00',NULL,NULL,NULL,1,NULL,32),(125,40,'15:00-18:00',NULL,NULL,NULL,NULL,1,17),(126,44,'08:00-10:00',NULL,1,NULL,NULL,NULL,32),(127,44,'11:00-13:00',NULL,NULL,1,NULL,NULL,32),(128,44,'08:00-11:00',NULL,NULL,NULL,1,NULL,32);
/*!40000 ALTER TABLE `horarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materias`
--

DROP TABLE IF EXISTS `materias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materias` (
  `materia_id` int NOT NULL AUTO_INCREMENT,
  `nombre_materia` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `semestre_id` int DEFAULT NULL,
  PRIMARY KEY (`materia_id`),
  KEY `semestre_id` (`semestre_id`),
  CONSTRAINT `materias_ibfk_1` FOREIGN KEY (`semestre_id`) REFERENCES `semestres` (`semestre_id`)
) ENGINE=InnoDB AUTO_INCREMENT=256 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materias`
--

LOCK TABLES `materias` WRITE;
/*!40000 ALTER TABLE `materias` DISABLE KEYS */;
INSERT INTO `materias` VALUES (1,'Física',1),(2,'Matemática',1),(3,'Lógica de Programación',1),(4,'Universidad y Sociedad',1),(5,'Física',2),(6,'Lógica Matemática',2),(7,'Álgebra Lineal',2),(8,'Algoritmos y Lógica de Programación',2),(9,'Cálculo Diferencial',2),(10,'Fundamentos de la Ingeniería de Software',3),(11,'Cálculo Integral',3),(12,'Sistemas Operativos',3),(13,'Programación Orientada a Objetos',3),(14,'Metodologías de la Investigación',3),(15,'Métodos Numéricos',4),(16,'Probabilidad y Estadística',4),(17,'Introducción a Redes',4),(18,'Modelamiento y Diseño de Software',4),(19,'Estructura de Datos',4),(20,'Redes',5),(21,'Manejo y Configuración de Software',5),(22,'Metodologías Ágiles',5),(23,'Computación Visual',5),(24,'Base de Datos',5),(25,'Interacción Humano Computador',6),(26,'Aplicaciones Orientadas a Servicios',6),(27,'Investigación Operativa',6),(28,'Sistema de Soporte de Decisiones',6),(29,'Patrones de Software',6),(30,'Gestión de Pruebas e Implantación de Software',7),(31,'Realidad Nacional',7),(32,'Inteligencia de Negocios',7),(33,'Aplicaciones Web y Móviles',7),(34,'Aplicaciones Distribuidas',7),(35,'Gestión de Calidad del Software',8),(36,'Inteligencia Artificial',8),(37,'Desarrollo Asistido por Software',8),(38,'Auditoría de Sistemas de Información',8),(39,'Gestión de Proyectos de Software',8),(40,'Emprendimiento y Legislación Laboral',9),(41,'Seguridad en el Desarrollo del Software',9),(42,'Ingeniería Económica para Software',9),(43,'Diseño de Proyectos',9),(44,'Desarrollo de Proyectos',10),(45,'Física',11),(46,'Matemática',11),(47,'Lógica de Programación',11),(48,'Universidad y Sociedad',11),(49,'Álgebra Lineal',12),(50,'Fundamentos de Programación',12),(51,'Cálculo Diferencial',12),(52,'Física',12),(53,'Metodología de la Investigación',12),(54,'Cálculo Integral',13),(55,'Programación Orientada a Objetos',13),(56,'Estadística y Probabilidades',13),(57,'Electricidad y Magnetismo',13),(58,'Estructuras Discretas',13),(59,'Estructura de Datos',14),(60,'Matemáticas Aplicadas a la Computación',14),(61,'Sistemas Operativos',14),(62,'Circuitos Eléctricos y Electrónicos',14),(63,'Administración de Bases de Datos',14),(64,'Diseño y Análisis de Algoritmos',15),(65,'Arquitectura de Computadoras',15),(66,'Redes de Computadoras',15),(67,'Ingeniería de Software',15),(68,'Lenguajes de Programación',15),(69,'Sistemas Distribuidos',16),(70,'Seguridad Informática',16),(71,'Desarrollo de Aplicaciones Web',16),(72,'Inteligencia Artificial',16),(73,'Bases de Datos Avanzadas',16),(74,'Desarrollo de Aplicaciones Móviles',17),(75,'Minería de Datos',17),(76,'Gestión de Proyectos de TI',17),(77,'Auditoría de Sistemas',17),(78,'Ética Profesional en TI',17),(79,'Computación en la Nube',18),(80,'Big Data',18),(81,'Administración de Redes',18),(82,'Gobiernos de TI',18),(83,'Innovación y Emprendimiento',18),(84,'Proyecto Integrador de Carrera I',19),(85,'Aplicaciones de Realidad Virtual y Aumentada',19),(86,'Seguridad en Redes de Datos',19),(87,'Electiva Profesional I',19),(88,'Electiva Profesional II',19),(89,'Desarrollo de Proyectos',20),(90,'Matemática',21),(91,'Química',21),(92,'Física',21),(93,'Universidad y Sociedad',21),(94,'Álgebra',22),(95,'Lógica Matemática',22),(96,'Introducción a la Ingeniería Industrial',22),(97,'Química',22),(98,'Física Básica',22),(99,'Tecnologías de la Información y de la Comunicación',22),(100,'Programación',23),(101,'Física Aplicada',23),(102,'Realidad Nacional',23),(103,'Cálculo Diferencial',23),(104,'Álgebra Lineal',23),(105,'Metodología de la Investigación',23),(106,'Cálculo Integral',24),(107,'Termodinámica',24),(108,'Electrónica y Electricidad',24),(109,'Investigación de Operaciones',24),(110,'Estadística y Probabilidad',24),(111,'Seguridad Industrial',25),(112,'Ingeniería de Métodos',25),(113,'Operaciones Unitarias',25),(114,'Dibujo Asistido por Computador',25),(115,'Seguridad Industrial',25),(116,'Máquinas Eléctricas',25),(117,'Contabilidad y Costos Industriales',25),(118,'Procesos Industriales',26),(119,'Administración de la Producción',26),(120,'Gestión de Operaciones',26),(121,'Máquinas Herramientas',26),(122,'Instrumentación Industrial',26),(123,'Ergonomía',26),(124,'Higiene Industrial',27),(125,'Sistemas CAD/CAM',27),(126,'Control Neumático y Oleohidráulica',27),(127,'Gestión Ambiental y Energías Alternativas',27),(128,'Diseño y Organización de Plantas',27),(129,'Gestión de Procesos',27),(130,'Gestión del Mantenimiento',28),(131,'Control de Calidad',28),(132,'Emprendimiento e Innovación',28),(133,'Control de Calidad',28),(134,'Instrumentación Virtual',28),(135,'Gerencia Empresarial',28),(136,'Automatización Industrial y Robótica',29),(137,'Diseño de Proyectos',29),(138,'Gestión de Calidad',29),(139,'Simulación y Laboratorio',29),(140,'Logística y Cadena de Abastecimiento',29),(142,'Desarrollo de Proyectos',30),(143,'Física',31),(144,'Universidad y Sociedad',31),(145,'Matemática',31),(146,'Química',31),(147,'Cálculo de Una Variable',32),(148,'Física Básica',32),(149,'Metodología de la Investigación',32),(150,'Química',32),(151,'Álgebra Lineal',32),(152,'Tecnologías de la Información y de la Comunicación',32),(153,'Cálculo de Varias Variables',33),(154,'Evolución de las Telecomunicaciones',33),(155,'Base de Datos',33),(156,'Evolución de las Telecomunicaciones',33),(157,'Física Aplicada',33),(158,'Fundamentos de Programación',33),(159,'Gestión de Calidad',33),(160,'Probabilidad y Estadística',34),(161,'Dispositivos y Medidas',34),(162,'Programación Avanzada',34),(163,'Métodos Numéricos',34),(164,'Ecuaciones Diferenciales',34),(165,'Física para la Electricidad',34),(166,'Electromagnetismo',35),(167,'Análisis de Circuitos',35),(168,'Sistemas Digitales',35),(169,'Sistemas Lineales',35),(170,'Software de Simulación',35),(171,'Sistemas Embebidos',36),(172,'Circuitos Electrónicos',36),(173,'Procesos Estocásticos',36),(174,'Realidad Nacional',36),(175,'Legislación Laboral',36),(176,'Comunicación Analógica',37),(177,'Líneas de Transmisión',37),(178,'Procesamiento Digital de Señales',37),(179,'Sistemas de Telefonía',37),(180,'Proyectos de Telecomunicaciones',37),(181,'Redes de Datos',37),(182,'Comunicación y Enrutamiento de Redes',38),(183,'Propagación y Antenas',38),(184,'Comunicación Digital',38),(185,'Circuitos RF',38),(186,'Comunicaciones Avanzadas',39),(187,'Diseño de Proyectos',39),(188,'Comunicaciones Ópticas',39),(189,'Sistemas Satelitales y GPS',39),(190,'Sistemas Inalámbricos',39),(191,'Comunicaciones Móviles',39),(192,'Televisión Digital',39),(193,'Desarrollo de Proyectos',40),(194,'Física',31),(195,'Universidad y Sociedad',31),(196,'Matemática',31),(197,'Química',31),(198,'Cálculo de Una Variable',32),(199,'Física Básica',32),(200,'Metodología de la Investigación',32),(201,'Química',32),(202,'Álgebra Lineal',32),(203,'Tecnologías de la Información y de la Comunicación',32),(204,'Cálculo de Varias Variables',33),(205,'Evolución de las Telecomunicaciones',33),(206,'Base de Datos',33),(207,'Evolución de las Telecomunicaciones',33),(208,'Física Aplicada',33),(209,'Fundamentos de Programación',33),(210,'Gestión de Calidad',33),(211,'Probabilidad y Estadística',34),(212,'Dispositivos y Medidas',34),(213,'Programación Avanzada',34),(214,'Métodos Numéricos',34),(215,'Ecuaciones Diferenciales',34),(216,'Física para la Electricidad',34),(217,'Electromagnetismo',35),(218,'Análisis de Circuitos',35),(219,'Sistemas Digitales',35),(220,'Sistemas Lineales',35),(221,'Software de Simulación',35),(222,'Sistemas Embebidos',36),(223,'Circuitos Electrónicos',36),(224,'Procesos Estocásticos',36),(225,'Realidad Nacional',36),(226,'Legislación Laboral',36),(227,'Comunicación Analógica',37),(228,'Líneas de Transmisión',37),(229,'Procesamiento Digital de Señales',37),(230,'Sistemas de Telefonía',37),(231,'Proyectos de Telecomunicaciones',37),(232,'Redes de Datos',37),(233,'Comunicación y Enrutamiento de Redes',38),(234,'Propagación y Antenas',38),(235,'Comunicación Digital',38),(236,'Circuitos RF',38),(237,'Comunicaciones Avanzadas',39),(238,'Diseño de Proyectos',39),(239,'Comunicaciones Ópticas',39),(240,'Sistemas Satelitales y GPS',39),(241,'Sistemas Inalámbricos',39),(242,'Comunicaciones Móviles',39),(243,'Televisión Digital',39),(244,'Desarrollo de Proyectos',40),(245,'Física',41),(246,'Matemática',41),(247,'Universidad y Sociedad',41),(248,'Química',41),(249,'Álgebra Lineal',42),(250,'Tecnología del Aprendizaje',42),(251,'Cálculo 1',42),(252,'Mecánica Básica',42),(253,'Fundamentos de Programación',42),(254,'Introducción a la Automatización',42),(255,'Metodologías de la Investigación',42);
/*!40000 ALTER TABLE `materias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamos`
--

DROP TABLE IF EXISTS `prestamos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestamos` (
  `ID_PRESTAMO` int NOT NULL AUTO_INCREMENT,
  `DIA_HORA_INICIO` datetime DEFAULT NULL,
  `HORAS_PRESTAMO` int DEFAULT NULL,
  `CEDULA` int DEFAULT NULL,
  `NOMBRE_USUARIO` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `CORREO` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `OBSERVACION` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `ID_ESPACIO_PERTENECE` int DEFAULT NULL,
  PRIMARY KEY (`ID_PRESTAMO`),
  KEY `ID_ESPACIO_PERTENECE` (`ID_ESPACIO_PERTENECE`),
  CONSTRAINT `prestamos_ibfk_1` FOREIGN KEY (`ID_ESPACIO_PERTENECE`) REFERENCES `espacios` (`ID_ESPACIO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamos`
--

LOCK TABLES `prestamos` WRITE;
/*!40000 ALTER TABLE `prestamos` DISABLE KEYS */;
/*!40000 ALTER TABLE `prestamos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semestres`
--

DROP TABLE IF EXISTS `semestres`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `semestres` (
  `semestre_id` int NOT NULL AUTO_INCREMENT,
  `numero_semestre` int NOT NULL,
  `carrera_id` int DEFAULT NULL,
  PRIMARY KEY (`semestre_id`),
  KEY `carrera_id` (`carrera_id`),
  CONSTRAINT `semestres_ibfk_1` FOREIGN KEY (`carrera_id`) REFERENCES `carreras` (`carrera_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semestres`
--

LOCK TABLES `semestres` WRITE;
/*!40000 ALTER TABLE `semestres` DISABLE KEYS */;
INSERT INTO `semestres` VALUES (1,0,1),(2,1,1),(3,2,1),(4,3,1),(5,4,1),(6,5,1),(7,6,1),(8,7,1),(9,8,1),(10,9,1),(11,0,2),(12,1,2),(13,2,2),(14,3,2),(15,4,2),(16,5,2),(17,6,2),(18,7,2),(19,8,2),(20,9,2),(21,0,3),(22,1,3),(23,2,3),(24,3,3),(25,4,3),(26,5,3),(27,6,3),(28,7,3),(29,8,3),(30,9,3),(31,0,4),(32,1,4),(33,2,4),(34,3,4),(35,4,4),(36,5,4),(37,6,4),(38,7,4),(39,8,4),(40,9,4),(41,0,5),(42,1,5);
/*!40000 ALTER TABLE `semestres` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `ID_USUARIO` int NOT NULL AUTO_INCREMENT,
  `CORREO` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `CLAVE` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`ID_USUARIO`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'admin@gmail.com','C/vQhACyrp6Sr4PI8gHZrg==');
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

-- Dump completed on 2024-06-03 23:38:34
