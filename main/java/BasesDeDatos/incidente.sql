-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3308
-- Tiempo de generación: 30-11-2023 a las 03:10:31
-- Versión del servidor: 8.0.31
-- Versión de PHP: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `soporte`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `incidente`
--

DROP TABLE IF EXISTS `incidente`;
CREATE TABLE IF NOT EXISTS `incidente` (
  `idIncidente` int NOT NULL AUTO_INCREMENT,
  `servicio` varchar(20) NOT NULL,
  `descripcionProblema` varchar(40) NOT NULL,
  `tipoProblema` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `idCliente` int DEFAULT NULL,
  `idTecnico` int DEFAULT NULL,
  `fechaInicial` date DEFAULT NULL,
  `fechaResolucion` date DEFAULT NULL,
  `estadoInc` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`idIncidente`),
  KEY `fk_idTecnico` (`idTecnico`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `incidente`
--

INSERT INTO `incidente` (`idIncidente`, `servicio`, `descripcionProblema`, `tipoProblema`, `idCliente`, `idTecnico`, `fechaInicial`, `fechaResolucion`, `estadoInc`) VALUES
(1, 'Windows', 'Falla de Inicio', 'software', 2, 1, '2023-10-11', '2023-10-29', 'RESUELTO'),
(2, 'Windows', 'problema en inicio de Windows', 'de software', 4, 5, '2023-09-02', '2023-10-02', 'RESUELTO'),
(3, 'Linux', 'falla en instalacion', 'instalar programa ', 3, 5, '2023-09-05', '2023-12-02', 'REPORTADO'),
(4, 'Windows', 'fala en office', 'se renicicia office', 2, 2, '2023-11-03', '2023-12-02', 'RESUELTO'),
(5, 'Windows', 'falla en registro', 'resgistro', 2, 2, '2023-11-29', '2023-12-03', 'RESUELTO');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
