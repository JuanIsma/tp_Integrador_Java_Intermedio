-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3308
-- Tiempo de generación: 30-11-2023 a las 03:09:15
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
-- Estructura de tabla para la tabla `tecnico`
--

DROP TABLE IF EXISTS `tecnico`;
CREATE TABLE IF NOT EXISTS `tecnico` (
  `id-tecnico` int NOT NULL AUTO_INCREMENT,
  `cuit-tec` varchar(13) NOT NULL,
  `nom-ape` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `especialidad` varchar(40) NOT NULL,
  `medio-comu` varchar(40) NOT NULL,
  `estado` varchar(10) NOT NULL,
  PRIMARY KEY (`id-tecnico`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `tecnico`
--

INSERT INTO `tecnico` (`id-tecnico`, `cuit-tec`, `nom-ape`, `especialidad`, `medio-comu`, `estado`) VALUES
(1, '21-42483147-8', 'Pepe Peralta', 'Linux', 'mail', 'Activo'),
(2, '21-46483047-9', 'Lucas Gomez', 'Windows', 'mail', 'Activo'),
(3, '21-47483474-3', 'Mario Gomez', 'SAP', 'whatsapp', 'Activo'),
(4, '23-48483647-9', 'Juan Lopez', 'SAP', 'mail', 'Inactivo'),
(5, '27-29292479-6', 'Pablo Oyola', 'Windows,Linux,Sap,Tango', 'mail', 'Activo');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
