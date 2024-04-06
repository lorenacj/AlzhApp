-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-04-2024 a las 21:07:18
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `alzhapp`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carer`
--

CREATE TABLE `carer` (
  `deleted` bit(1) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `id` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carer_family_unit`
--

CREATE TABLE `carer_family_unit` (
  `carer_id` int(11) NOT NULL,
  `family_unit_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carer_medicine`
--

CREATE TABLE `carer_medicine` (
  `carer_id` int(11) NOT NULL,
  `medicine_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carer_patient`
--

CREATE TABLE `carer_patient` (
  `carer_id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `carer_seq`
--

CREATE TABLE `carer_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `carer_seq`
--

INSERT INTO `carer_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `event`
--

CREATE TABLE `event` (
  `deleted` bit(1) NOT NULL,
  `finaldate` date NOT NULL,
  `finalhour` time(6) NOT NULL,
  `id` int(11) NOT NULL,
  `initialdate` date NOT NULL,
  `initialhour` time(6) NOT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `event_seq`
--

CREATE TABLE `event_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `event_seq`
--

INSERT INTO `event_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `family_unit`
--

CREATE TABLE `family_unit` (
  `id` int(11) NOT NULL,
  `code` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `family_unit_seq`
--

CREATE TABLE `family_unit_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `family_unit_seq`
--

INSERT INTO `family_unit_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medicine_seq`
--

CREATE TABLE `medicine_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `medicine_seq`
--

INSERT INTO `medicine_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `patient`
--

CREATE TABLE `patient` (
  `birthdate` date NOT NULL,
  `deleted` bit(1) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `family_unit_id` int(11) DEFAULT NULL,
  `height` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  `weight` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `disorder` varchar(255) DEFAULT NULL,
  `passportid` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `patient_medicine`
--

CREATE TABLE `patient_medicine` (
  `medicine_id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `patient_seq`
--

CREATE TABLE `patient_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `patient_seq`
--

INSERT INTO `patient_seq` (`next_val`) VALUES
(1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `symptom`
--

CREATE TABLE `symptom` (
  `date` date NOT NULL,
  `hour` time(6) NOT NULL,
  `id` int(11) NOT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `symptom_seq`
--

CREATE TABLE `symptom_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `symptom_seq`
--

INSERT INTO `symptom_seq` (`next_val`) VALUES
(1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `carer`
--
ALTER TABLE `carer`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_66odh5r1avq9redncxe7f449n` (`username`);

--
-- Indices de la tabla `carer_family_unit`
--
ALTER TABLE `carer_family_unit`
  ADD KEY `FKhmi45sxdt9w7fro68rphdyn02` (`family_unit_id`),
  ADD KEY `FKqwkpwx50cav1n1ccsmlldkeou` (`carer_id`);

--
-- Indices de la tabla `carer_medicine`
--
ALTER TABLE `carer_medicine`
  ADD KEY `FKt3aua7y4krqaxqp7h1al4hltl` (`carer_id`);

--
-- Indices de la tabla `carer_patient`
--
ALTER TABLE `carer_patient`
  ADD KEY `FK91lxt0daowd2mma36lbfg1247` (`patient_id`),
  ADD KEY `FKd2c6jut4af3rjb9rg48mf1sl9` (`carer_id`);

--
-- Indices de la tabla `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3897kfqk5fdqc7sas021r46eo` (`patient_id`);

--
-- Indices de la tabla `family_unit`
--
ALTER TABLE `family_unit`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ievduoib3vpil0q5ohq38wpyn` (`code`);

--
-- Indices de la tabla `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_hs4soipw9djgwibecwp3tim7r` (`family_unit_id`),
  ADD UNIQUE KEY `UK_9qqbr4j28cqad023xa18ggkix` (`passportid`);

--
-- Indices de la tabla `patient_medicine`
--
ALTER TABLE `patient_medicine`
  ADD KEY `FKcdhj3jmqvlrmclxrt4k6wu9t5` (`patient_id`);

--
-- Indices de la tabla `symptom`
--
ALTER TABLE `symptom`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKj500xlsnix5otet7w3mhnkqfy` (`patient_id`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `carer_family_unit`
--
ALTER TABLE `carer_family_unit`
  ADD CONSTRAINT `FKhmi45sxdt9w7fro68rphdyn02` FOREIGN KEY (`family_unit_id`) REFERENCES `family_unit` (`id`),
  ADD CONSTRAINT `FKqwkpwx50cav1n1ccsmlldkeou` FOREIGN KEY (`carer_id`) REFERENCES `carer` (`id`);

--
-- Filtros para la tabla `carer_medicine`
--
ALTER TABLE `carer_medicine`
  ADD CONSTRAINT `FKt3aua7y4krqaxqp7h1al4hltl` FOREIGN KEY (`carer_id`) REFERENCES `carer` (`id`);

--
-- Filtros para la tabla `carer_patient`
--
ALTER TABLE `carer_patient`
  ADD CONSTRAINT `FK91lxt0daowd2mma36lbfg1247` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`),
  ADD CONSTRAINT `FKd2c6jut4af3rjb9rg48mf1sl9` FOREIGN KEY (`carer_id`) REFERENCES `carer` (`id`);

--
-- Filtros para la tabla `event`
--
ALTER TABLE `event`
  ADD CONSTRAINT `FK3897kfqk5fdqc7sas021r46eo` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`);

--
-- Filtros para la tabla `patient`
--
ALTER TABLE `patient`
  ADD CONSTRAINT `FK71eec9wuqjxampb6rnr23yo7b` FOREIGN KEY (`family_unit_id`) REFERENCES `family_unit` (`id`);

--
-- Filtros para la tabla `patient_medicine`
--
ALTER TABLE `patient_medicine`
  ADD CONSTRAINT `FKcdhj3jmqvlrmclxrt4k6wu9t5` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`);

--
-- Filtros para la tabla `symptom`
--
ALTER TABLE `symptom`
  ADD CONSTRAINT `FKj500xlsnix5otet7w3mhnkqfy` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
