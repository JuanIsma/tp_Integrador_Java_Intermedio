-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3308
-- Tiempo de generación: 30-11-2023 a las 03:09:34
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
-- Estructura de tabla para la tabla `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `id-cliente` int NOT NULL AUTO_INCREMENT,
  `cuit` varchar(13) NOT NULL,
  `razonSocial` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `nom-apel` varchar(60) NOT NULL,
  `direc` varchar(60) NOT NULL,
  `mail` varchar(60) NOT NULL,
  `cel` int NOT NULL,
  `tel-fijo` int NOT NULL,
  `servicio` varchar(60) NOT NULL,
  PRIMARY KEY (`id-cliente`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id-cliente`, `cuit`, `razonSocial`, `nom-apel`, `direc`, `mail`, `cel`, `tel-fijo`, `servicio`) VALUES
(1, '23-24324234-9', 'globalx', 'Benito Peralta', 'Gabino Blanco 324', 'peralta@gmail.com', 4324234, 152438373, 'SAP'),
(2, '23-14324234-3', 'Globalx', 'Jose Peralta', 'Azpeitia 392', 'jose4323@gmail.com', 351827262, 4883322, 'Windows'),
(3, '23-29214473-0', 'Compac', 'Rodrigo Marcos', 'Entre Rios 8322', 'rodri47@gmail.com', 351728373, 4292811, 'Linux'),
(4, '21-24464232-2', 'Electrin', 'Jorge Perez', 'Ayacucho 453', 'jorge452@gmail.com', 4657867, 4882933, 'Windows'),
(5, '23-29872653-0', 'Tango', 'Lucas Romero', 'Allende 213', 'lucas89@gmail.com', 351829282, 4837363, 'Tango');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
