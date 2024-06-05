-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 17-05-2024 a las 01:42:04
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `vanguardia`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `bloques`
--

CREATE TABLE `bloques` (
  `ID_BLOQUE` int(11) NOT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `bloques`
--

INSERT INTO `bloques` (`ID_BLOQUE`, `NOMBRE`) VALUES
(1, 'Edificio 1'),
(2, 'Edificio 2'),
(3, 'Ciencias Aplicadas'),
(4, 'BLOQUE 4');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `espacios`
--

CREATE TABLE `espacios` (
  `ID_ESPACIO` int(11) NOT NULL,
  `TIPO` varchar(255) DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  `ID_BLOQUE_PERTENECE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `espacios`
--

INSERT INTO `espacios` (`ID_ESPACIO`, `TIPO`, `NOMBRE`, `ID_BLOQUE_PERTENECE`) VALUES
(1, 'LABORATORIO', 'AUDIOVISUALES (48)', 1),
(2, 'AULA', 'AULA 1 (36)', 3),
(3, 'AULA', 'AULA 2 (36)', 3),
(4, 'AULA', 'AULA 3 (36)', 3),
(5, 'AULA', 'AULA 4 (36)', 3),
(6, 'AULA', 'AULA 5 (36)', 3),
(7, 'AULA', 'AULA 6 (40)', 3),
(8, 'AULA', 'AULA 7 (40)', 3),
(9, 'AULA', 'AULA 8 (40)', 3),
(10, 'AULA', 'AULA 9 (40)', 3),
(11, 'AULA', 'AULA 10 (40)', 3),
(12, 'AULA', 'AULA F01 (39)', 2),
(13, 'AULA', 'AULA F02 (39)', 2),
(14, 'AULA', 'AULA F03 (39)', 2),
(15, 'LABORATORIO', 'LABORATORIO F04 (40)', 2),
(16, 'AULA', 'AULA F08 (40)', 2),
(17, 'AULA', 'AULA G02 (30)', 2),
(18, 'AULA', 'AULA G03 (30)', 2),
(19, 'AULA', 'AULA G04 (40)', 2),
(20, 'AULA', 'AULA H02 (32)', 2),
(21, 'AULA', 'AULA H03 (40)', 2),
(22, 'AULA', 'AULA H04 (40)', 2),
(23, 'AULA', 'AULA H05 (30)', 2),
(24, 'AULA', 'AULA I01 (50)', 2),
(25, 'AULA', 'AULA I02 (40)', 2),
(26, 'AULA', 'AULA I03 (40)', 2),
(27, 'AULA', 'AULA J01 (40)', 2),
(28, 'AULA', 'AULA J05 (30)', 2),
(29, 'LABORATORIO', 'LABORATORIO 1 (40)', 1),
(30, 'LABORATORIO', 'LABORATORIO 2 (40)', 1),
(31, 'LABORATORIO', 'LABORATORIO 3 (24)', 1),
(32, 'LABORATORIO', 'LABORATORIO 4 (24)', 1),
(33, 'LABORATORIO', 'LABORATORIO 5 (24)', 1),
(34, 'LABORATORIO', 'LABORATORIO 6 (32)', 1),
(35, 'LABORATORIO', 'LABORATORIO 7 (40)', 1),
(36, 'LABORATORIO', 'LABORATORIO 8 (40)', 1),
(37, 'LABORATORIO', 'LAB. CTT (56)', 1),
(38, 'LABORATORIO', 'LAB. REDES 1 (38)', 1),
(39, 'LABORATORIO', 'LAB. REDES 2 (40)', 1),
(40, 'LABORATORIO', 'LAB. INDUSTRIAL 1 (32)', 2),
(41, 'LABORATORIO', 'LAB. INDUSTRIAL 2 (32)', 2),
(42, 'LABORATORIO', 'LAB. ROBÓTICA Y REDES INDUSTRIALES (25)', 2),
(43, 'LABORATORIO', 'LAB. AUTOMATIZACIÓN INDUSTRIAL (32)', 2),
(44, 'LABORATORIO', 'LAB. CNC (25)', 4),
(45, 'LABORATORIO', 'LAB. COMUNICACIONES (25)', 2),
(46, 'LABORATORIO', 'LAB. ELECTRÓNICA AVANZADA (32)', 1),
(47, 'LABORATORIO', 'LAB. ELECTRÓNICA BÁSICA (22)', 2),
(48, 'LABORATORIO', 'LAB. INSTRUMENTACIÓN VIRTUAL (24)', 2),
(49, 'LABORATORIO', 'LAB. MÁQUINAS ELÉCTRICAS (25)', 1),
(50, 'LABORATORIO', 'LAB. PLC\'S (25)', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prestamos`
--

CREATE TABLE `prestamos` (
  `ID_PRESTAMO` int(11) NOT NULL,
  `DIA_HORA_INICIO` datetime DEFAULT NULL,
  `HORAS_PRESTAMO` int(11) DEFAULT NULL,
  `CEDULA` int(11) DEFAULT NULL,
  `NOMBRE_USUARIO` varchar(255) DEFAULT NULL,
  `CORREO` varchar(255) DEFAULT NULL,
  `OBSERVACION` varchar(255) DEFAULT NULL,
  `ID_ESPACIO_PERTENECE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `ID_USUARIO` int(11) NOT NULL,
  `CORREO` varchar(255) DEFAULT NULL,
  `CLAVE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`ID_USUARIO`, `CORREO`, `CLAVE`) VALUES
(1, 'admin@gmail.com', 'adminsito1');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `bloques`
--
ALTER TABLE `bloques`
  ADD PRIMARY KEY (`ID_BLOQUE`);

--
-- Indices de la tabla `espacios`
--
ALTER TABLE `espacios`
  ADD PRIMARY KEY (`ID_ESPACIO`),
  ADD KEY `ID_BLOQUE_PERTENECE` (`ID_BLOQUE_PERTENECE`);

--
-- Indices de la tabla `prestamos`
--
ALTER TABLE `prestamos`
  ADD PRIMARY KEY (`ID_PRESTAMO`),
  ADD KEY `ID_ESPACIO_PERTENECE` (`ID_ESPACIO_PERTENECE`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`ID_USUARIO`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `bloques`
--
ALTER TABLE `bloques`
  MODIFY `ID_BLOQUE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `espacios`
--
ALTER TABLE `espacios`
  MODIFY `ID_ESPACIO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT de la tabla `prestamos`
--
ALTER TABLE `prestamos`
  MODIFY `ID_PRESTAMO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `ID_USUARIO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `espacios`
--
ALTER TABLE `espacios`
  ADD CONSTRAINT `espacios_ibfk_1` FOREIGN KEY (`ID_BLOQUE_PERTENECE`) REFERENCES `bloques` (`ID_BLOQUE`);

--
-- Filtros para la tabla `prestamos`
--
ALTER TABLE `prestamos`
  ADD CONSTRAINT `prestamos_ibfk_1` FOREIGN KEY (`ID_ESPACIO_PERTENECE`) REFERENCES `espacios` (`ID_ESPACIO`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
