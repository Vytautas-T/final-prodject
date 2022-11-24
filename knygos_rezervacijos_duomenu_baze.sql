-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Nov 24, 2022 at 10:29 AM
-- Server version: 5.7.36
-- PHP Version: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `knygos_rezervacijos_duomenu_baze`
--

-- --------------------------------------------------------

--
-- Table structure for table `autorius`
--

DROP TABLE IF EXISTS `autorius`;
CREATE TABLE IF NOT EXISTS `autorius` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aprasymas` varchar(255) DEFAULT NULL,
  `pavarde` varchar(255) DEFAULT NULL,
  `vardas` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `autorius`
--

INSERT INTO `autorius` (`id`, `aprasymas`, `pavarde`, `vardas`) VALUES
(1, 'lk\\sndfnasldfnaslkdnf', 'pavardenis', 'jonas'),
(2, 'vava', 'pavarde', 'Vardas'),
(3, 'ss', 'dsa', 'as');

-- --------------------------------------------------------

--
-- Table structure for table `autorius_knygos`
--

DROP TABLE IF EXISTS `autorius_knygos`;
CREATE TABLE IF NOT EXISTS `autorius_knygos` (
  `knygos_id` int(11) NOT NULL,
  `autoriaus_id` int(11) NOT NULL,
  PRIMARY KEY (`autoriaus_id`,`knygos_id`),
  KEY `FKpyrgcuj355tcksameyrtoghw` (`knygos_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `kategorijos`
--

DROP TABLE IF EXISTS `kategorijos`;
CREATE TABLE IF NOT EXISTS `kategorijos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pavadinimas` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kategorijos`
--

INSERT INTO `kategorijos` (`id`, `pavadinimas`) VALUES
(2, 'nuotikiu'),
(4, '3145');

-- --------------------------------------------------------

--
-- Table structure for table `kategotrijos`
--

DROP TABLE IF EXISTS `kategotrijos`;
CREATE TABLE IF NOT EXISTS `kategotrijos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aprasymas` varchar(255) DEFAULT NULL,
  `pavadinimas` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `kategotrijos`
--

INSERT INTO `kategotrijos` (`id`, `aprasymas`, `pavadinimas`) VALUES
(1, 'kasjdbnakjsbdnkabjsnd', 'sifi');

-- --------------------------------------------------------

--
-- Table structure for table `knygos`
--

DROP TABLE IF EXISTS `knygos`;
CREATE TABLE IF NOT EXISTS `knygos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aprasymas` varchar(255) DEFAULT NULL,
  `pavadinimas` varchar(255) DEFAULT NULL,
  `puslapiu_skait` int(11) NOT NULL,
  `turinys` varchar(255) DEFAULT NULL,
  `kiekis` int(11) NOT NULL,
  `rezervoutas_kiekis` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `knygos`
--

INSERT INTO `knygos` (`id`, `aprasymas`, `pavadinimas`, `puslapiu_skait`, `turinys`, `kiekis`, `rezervoutas_kiekis`) VALUES
(1, 'medziu karas uzteritorija ir saule', 'Misko karas', 311, '1pristatymas 2 blabla', 3, 0);

-- --------------------------------------------------------

--
-- Table structure for table `knygos_kategorijos`
--

DROP TABLE IF EXISTS `knygos_kategorijos`;
CREATE TABLE IF NOT EXISTS `knygos_kategorijos` (
  `knygos_id` int(11) NOT NULL,
  `kategorijos_id` int(11) NOT NULL,
  PRIMARY KEY (`kategorijos_id`,`knygos_id`),
  KEY `FKq2mjk3xmb43mgycy74b205m5s` (`knygos_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `privilegijos`
--

DROP TABLE IF EXISTS `privilegijos`;
CREATE TABLE IF NOT EXISTS `privilegijos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pavadinimas` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `privilegijos`
--

INSERT INTO `privilegijos` (`id`, `pavadinimas`) VALUES
(1, 'READ_PRIVILEGE'),
(2, 'WHRITE_PRIVILEGE');

-- --------------------------------------------------------

--
-- Table structure for table `rezervacijos`
--

DROP TABLE IF EXISTS `rezervacijos`;
CREATE TABLE IF NOT EXISTS `rezervacijos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kiekis` int(11) NOT NULL,
  `pavadinimas` varchar(255) DEFAULT NULL,
  `vartotojas_id` int(11) DEFAULT NULL,
  `kuri_knyga_uzrezervuota` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6faa1yesk3ko7cr0q5feb23lt` (`vartotojas_id`),
  KEY `FK9avke92ds95vkdjbd12e8116t` (`kuri_knyga_uzrezervuota`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pavadinimas` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`id`, `pavadinimas`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Table structure for table `roles_privilegijos`
--

DROP TABLE IF EXISTS `roles_privilegijos`;
CREATE TABLE IF NOT EXISTS `roles_privilegijos` (
  `roles_id` int(11) NOT NULL,
  `privilegijos_id` int(11) NOT NULL,
  KEY `FKcstidwkko1i18oq8n5iaufaqw` (`privilegijos_id`),
  KEY `FKq5e7yfko37wouu08uykpi53h` (`roles_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `roles_privilegijos`
--

INSERT INTO `roles_privilegijos` (`roles_id`, `privilegijos_id`) VALUES
(1, 1),
(1, 2),
(2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `vartotojas`
--

DROP TABLE IF EXISTS `vartotojas`;
CREATE TABLE IF NOT EXISTS `vartotojas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `ijungta` bit(1) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `token_expired` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `vartotojas`
--

INSERT INTO `vartotojas` (`id`, `password`, `username`, `email`, `first_name`, `ijungta`, `last_name`, `token_expired`) VALUES
(2, '$2a$10$pQGMfK8q7uO2ynjuXIWF1uexs8A8YDWX34djka9EtBNkQcwV0BzPe', NULL, 'asd', 'vardas', b'1', 'pavarde', b'0'),
(3, '$2a$10$uX1wBOK5qM5egyhmJVp1yOzPoiOw33PUcmwt3hI9p38HEYIu5cZva', NULL, 'qwe', 'vv', b'1', 'pp', b'0');

-- --------------------------------------------------------

--
-- Table structure for table `vartotojas_knygos`
--

DROP TABLE IF EXISTS `vartotojas_knygos`;
CREATE TABLE IF NOT EXISTS `vartotojas_knygos` (
  `varotojas_id` int(11) NOT NULL,
  `knygos_id` int(11) NOT NULL,
  `vartotojas_id` int(11) NOT NULL,
  PRIMARY KEY (`knygos_id`,`vartotojas_id`),
  KEY `FKeojv0oojes0h1usv4y84v3j6c` (`varotojas_id`),
  KEY `FKdwk7lyt4f983712n553q5jbfa` (`vartotojas_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `vartotojas_roles`
--

DROP TABLE IF EXISTS `vartotojas_roles`;
CREATE TABLE IF NOT EXISTS `vartotojas_roles` (
  `vartotojo_id` int(11) NOT NULL,
  `roles_id` int(11) NOT NULL,
  KEY `FKm9yd1gmtmbm811u1dyutwbcb7` (`roles_id`),
  KEY `FK5rr05uq47t6eor2k8mfvenpls` (`vartotojo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `vartotojas_roles`
--

INSERT INTO `vartotojas_roles` (`vartotojo_id`, `roles_id`) VALUES
(2, 1),
(3, 2);

-- --------------------------------------------------------

--
-- Table structure for table `vartotoju_megstamos_knygos`
--

DROP TABLE IF EXISTS `vartotoju_megstamos_knygos`;
CREATE TABLE IF NOT EXISTS `vartotoju_megstamos_knygos` (
  `vartotojo_id` int(11) NOT NULL,
  `knygos_id` int(11) NOT NULL,
  PRIMARY KEY (`knygos_id`,`vartotojo_id`),
  KEY `FKjad2kggry3yp9jwxricmb4qn` (`vartotojo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `autorius_knygos`
--
ALTER TABLE `autorius_knygos`
  ADD CONSTRAINT `FK42eesfrluxjqbv0tfv7wit56p` FOREIGN KEY (`autoriaus_id`) REFERENCES `autorius` (`id`),
  ADD CONSTRAINT `FKpyrgcuj355tcksameyrtoghw` FOREIGN KEY (`knygos_id`) REFERENCES `knygos` (`id`);

--
-- Constraints for table `knygos_kategorijos`
--
ALTER TABLE `knygos_kategorijos`
  ADD CONSTRAINT `FKdf0bd87y0dl1lqgca4nyc732e` FOREIGN KEY (`kategorijos_id`) REFERENCES `kategorijos` (`id`),
  ADD CONSTRAINT `FKdqqxcdc50xd5qsfc523xelm2j` FOREIGN KEY (`kategorijos_id`) REFERENCES `kategotrijos` (`id`),
  ADD CONSTRAINT `FKq2mjk3xmb43mgycy74b205m5s` FOREIGN KEY (`knygos_id`) REFERENCES `knygos` (`id`);

--
-- Constraints for table `rezervacijos`
--
ALTER TABLE `rezervacijos`
  ADD CONSTRAINT `FK6faa1yesk3ko7cr0q5feb23lt` FOREIGN KEY (`vartotojas_id`) REFERENCES `vartotojas` (`id`),
  ADD CONSTRAINT `FK9avke92ds95vkdjbd12e8116t` FOREIGN KEY (`kuri_knyga_uzrezervuota`) REFERENCES `knygos` (`id`);

--
-- Constraints for table `roles_privilegijos`
--
ALTER TABLE `roles_privilegijos`
  ADD CONSTRAINT `FKcstidwkko1i18oq8n5iaufaqw` FOREIGN KEY (`privilegijos_id`) REFERENCES `privilegijos` (`id`),
  ADD CONSTRAINT `FKq5e7yfko37wouu08uykpi53h` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`);

--
-- Constraints for table `vartotojas_knygos`
--
ALTER TABLE `vartotojas_knygos`
  ADD CONSTRAINT `FKdwk7lyt4f983712n553q5jbfa` FOREIGN KEY (`vartotojas_id`) REFERENCES `vartotojas` (`id`),
  ADD CONSTRAINT `FKeojv0oojes0h1usv4y84v3j6c` FOREIGN KEY (`varotojas_id`) REFERENCES `vartotojas` (`id`),
  ADD CONSTRAINT `FKfk0hddvpqpc9wduusns06hrdq` FOREIGN KEY (`knygos_id`) REFERENCES `knygos` (`id`);

--
-- Constraints for table `vartotojas_roles`
--
ALTER TABLE `vartotojas_roles`
  ADD CONSTRAINT `FK5rr05uq47t6eor2k8mfvenpls` FOREIGN KEY (`vartotojo_id`) REFERENCES `vartotojas` (`id`),
  ADD CONSTRAINT `FKm9yd1gmtmbm811u1dyutwbcb7` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`);

--
-- Constraints for table `vartotoju_megstamos_knygos`
--
ALTER TABLE `vartotoju_megstamos_knygos`
  ADD CONSTRAINT `FK6n1qy6n5fqufrm5efhmbs9rfs` FOREIGN KEY (`knygos_id`) REFERENCES `knygos` (`id`),
  ADD CONSTRAINT `FKjad2kggry3yp9jwxricmb4qn` FOREIGN KEY (`vartotojo_id`) REFERENCES `vartotojas` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
