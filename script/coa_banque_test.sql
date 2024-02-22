-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mer. 31 jan. 2024 à 03:43
-- Version du serveur : 8.3.0
-- Version de PHP : 8.1.2-1ubuntu2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `coa_banque_test`
--

-- --------------------------------------------------------

--
-- Structure de la table `Compte`
--

CREATE TABLE `Compte` (
  `numeroCompte` varchar(50) NOT NULL,
  `userId` varchar(50) NOT NULL,
  `solde` double NOT NULL,
  `avecDecouvert` varchar(5) NOT NULL,
  `decouvertAutorise` decimal(10,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `Compte`
--

INSERT INTO `Compte` (`numeroCompte`, `userId`, `solde`, `avecDecouvert`, `decouvertAutorise`) VALUES
('AB7328887341', 'j.doe2', 4242, 'AVEC', 123),
('AV1011011011', 'g.descomptes', 5, 'AVEC', 100),
('BD4242424242', 'j.doe1', 100, 'SANS', NULL),
('CADNV00000', 'j.doe1', 42, 'AVEC', 42),
('CADV000000', 'j.doe1', 0, 'AVEC', 42),
('CSDNV00000', 'j.doe1', 42, 'SANS', NULL),
('CSDV000000', 'j.doe1', 0, 'SANS', NULL),
('IO1010010001', 'j.doe2', 6868, 'SANS', NULL),
('KL4589219196', 'g.descomptesvides', 0, 'AVEC', 150),
('KO7845154956', 'g.descomptesvides', 0, 'SANS', NULL),
('LA1021931215', 'j.doe1', 100, 'SANS', NULL),
('MD8694030938', 'j.doe1', 500, 'SANS', NULL),
('PP1285735733', 'a.lidell1', 37, 'SANS', NULL),
('SA1011011011', 'g.descomptes', 10, 'SANS', 0),
('TD0398455576', 'j.doe1', 23, 'AVEC', 500),
('XD1829451029', 'j.doe1', -48, 'AVEC', 100);

-- --------------------------------------------------------

--
-- Structure de la table `Utilisateur`
--

CREATE TABLE `Utilisateur` (
  `userId` varchar(50) NOT NULL,
  `nom` varchar(45) NOT NULL,
  `prenom` varchar(45) NOT NULL,
  `adresse` varchar(100) NOT NULL,
  `userPwd` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `male` bit(1) NOT NULL,
  `type` varchar(10) NOT NULL,
  `numClient` varchar(45) DEFAULT NULL,
  `mail` varchar(100) DEFAULT NULL,
  `nbTentativesConnect` tinyint UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `Utilisateur`
--

INSERT INTO `Utilisateur` (`userId`, `nom`, `prenom`, `adresse`, `userPwd`, `male`, `type`, `numClient`, `mail`, `nbTentativesConnect`) VALUES
('a.lidell1', 'Lidell', 'Alice', '789, grande rue, Metz', '$2y$10$DFOlEvEBh86eQDPmbjvtX.nVfnij5QTLi6a7djBweDovBiYcmfkUy', b'1', 'CLIENT', '9865432100', 'calex555@hotmail.fr', 0),
('admin', 'Smith', 'Joe', '123, grande rue, Metz', '$2y$10$kRMzptKWsg5JeEi5hSUaUOzhilBxWcI3HBANZWIgeJW43dPmGWFDu', b'1', 'MANAGER', '', 'calex555@hotmail.fr', 0),
('c.exist', 'TEST NOM', 'TEST PRENOM', 'TEST ADRESSE', '$2y$10$vackxI3h6iTbiV2cb3.xWuyGznk0MbvhGTz0c1pchq4CU3iabr84.', b'1', 'CLIENT', '0101010101', 'calex555@hotmail.fr', 0),
('g.descomptes', 'TEST NOM', 'TEST PRENOM', 'TEST ADRESSE', '$2y$10$PgDiZo7TBssmn/BrYglNou2dhEj6O.hDafQJ5jrMtbwXUXUqCgxwu', b'1', 'CLIENT', '1000000001', 'calex555@hotmail.fr', 0),
('g.descomptesvides', 'TEST NOM', 'TEST PRENOM', 'TEST ADRESSE', '$2y$10$CnzSqdD7jrHxKSAqKx3eKePpiRi3esT4wFFdWsHuHjYxUV4yXRiwi', b'1', 'CLIENT', '0000000002', 'calex555@hotmail.fr', 0),
('g.exist', 'TEST NOM', 'TEST PRENOM', 'TEST ADRESSE', '$2y$10$vGFtNyJzFqDUWQ/TjdkzC.D9K5Go9Qxo3EA4wlEfNNMxttMno5N/m', b'1', 'CLIENT', '1010101010', 'calex555@hotmail.fr', 0),
('g.pasdecompte', 'TEST NOM', 'TEST PRENOM', 'TEST ADRESSE', '$2y$10$ELBhTZG.0to0m6ZgkdU5w.dAalDgHz7wUJLalUJhMl3tNiJc8Pwni', b'1', 'CLIENT', '5544554455', 'calex555@hotmail.fr', 0),
('j.doe1', 'Doe', 'Jane', '456, grand boulevard, Brest', '$2y$10$YLB8BnyaDSMZTNpUaBqqGubiQp2RPHTVnOCUHLNm58hEbjsDriEGu', b'1', 'CLIENT', '1234567890', 'calex555@hotmail.fr', 0),
('j.doe2', 'Doe', 'John', '457, grand boulevard, Perpignan', '$2y$10$Rv1KqCiKzADHzioX4N.E8e3H/QQ/PrzTGvYlJCHAQ94pIxE1kt96y', b'1', 'CLIENT', '0000000001', 'calex555@hotmail.fr', 0);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Compte`
--
ALTER TABLE `Compte`
  ADD PRIMARY KEY (`numeroCompte`),
  ADD KEY `index_userClient` (`userId`);

--
-- Index pour la table `Utilisateur`
--
ALTER TABLE `Utilisateur`
  ADD PRIMARY KEY (`userId`),
  ADD UNIQUE KEY `numClient_UNIQUE` (`numClient`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `Compte`
--
ALTER TABLE `Compte`
  ADD CONSTRAINT `fk_Compte_userId` FOREIGN KEY (`userId`) REFERENCES `Utilisateur` (`userId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
