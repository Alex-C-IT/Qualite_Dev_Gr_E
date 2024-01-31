-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mer. 31 jan. 2024 à 03:31
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
-- Base de données : `coa_banque`
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
('AB7328887341', 'client2', -97, 'AVEC', 123),
('BD4242424242', 'client1', 150, 'SANS', NULL),
('FF5050500202', 'client1', 705, 'SANS', NULL),
('IO1010010001', 'client2', 8468, 'SANS', NULL),
('LA1021931215', 'client1', 150, 'SANS', NULL),
('MD8694030938', 'client1', 90, 'SANS', NULL),
('PP1285735733', 'a', 37, 'SANS', NULL),
('TD0398455576', 'client2', 3534, 'AVEC', 700),
('XD1829451029', 'client2', -93, 'AVEC', 100),
('XX7788778877', 'client2', 290, 'SANS', NULL),
('XX9999999999', 'client2', 0, 'AVEC', 500);

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
  `nbTentativesConnect` tinyint UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `Utilisateur`
--

INSERT INTO `Utilisateur` (`userId`, `nom`, `prenom`, `adresse`, `userPwd`, `male`, `type`, `numClient`, `mail`, `nbTentativesConnect`) VALUES
('a', 'a', 'a', 'a', '$2y$10$hmzyzuzsrPcCX7eXM1wf5uQU4APFom76NRz8/RYY8ZvdGapR1ap2S', b'1', 'MANAGER', NULL, 'calex555@hotmail.fr', 0),
('admin', 'Smith', 'Joe', '123, grande rue, Metz', '$2y$10$PtQbfdRxOH06ndbMZWoHr.ANJpG37c5yw/YOzP36h9DU9KxBjL2I.', b'1', 'MANAGER', '', 'calex555@hotmail.fr', 0),
('client1', 'client1', 'Jane', '45, grand boulevard, Brest', '$2y$10$1ipOO2Oyxi4NSHi5C6JfEe75DDk5XD/uoEsfNBgb37put//qbp3LK', b'1', 'CLIENT', '123456789', 'calex555@hotmail.fr', 0),
('client2', 'client2', 'Jane', '45, grand boulevard, Brest', '$2y$10$JThDAnvOy/uLibaB.hVYVuYoPzGJZlZDibjZ5N/uYg1T3dcrbuX36', b'1', 'CLIENT', '123456788', 'calex555@hotmail.fr', 0);

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
