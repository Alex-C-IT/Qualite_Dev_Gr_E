SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `allard19u_coa_banque_essais`
--

-- --------------------------------------------------------

--
-- Table structure for table `Compte`
--

CREATE TABLE IF NOT EXISTS `Compte` (
  `numeroCompte` varchar(50) NOT NULL,
  `userId` varchar(50) NOT NULL,
  `solde` double NOT NULL,
  `avecDecouvert` varchar(5) NOT NULL,
  `decouvertAutorise` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`numeroCompte`),
  KEY `index_userClient` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Compte`
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
-- Table structure for table `Utilisateur`
--

CREATE TABLE IF NOT EXISTS `Utilisateur` (
  `userId` varchar(50) NOT NULL,
  `nom` varchar(45) NOT NULL,
  `prenom` varchar(45) NOT NULL,
  `adresse` varchar(100) NOT NULL,
  `userPwd` varchar(45) DEFAULT NULL,
  `male` bit(1) NOT NULL,
  `type` varchar(10) NOT NULL,
  `numClient` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `numClient_UNIQUE` (`numClient`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Utilisateur`
--

INSERT INTO `Utilisateur` (`userId`, `nom`, `prenom`, `adresse`, `userPwd`, `male`, `type`, `numClient`) VALUES
('a.lidell1', 'Lidell', 'Alice', '789, grande rue, Metz', 'toto', b'1', 'CLIENT', '9865432100'),
('admin', 'Smith', 'Joe', '123, grande rue, Metz', 'adminpass', b'1', 'MANAGER', ''),
('c.exist', 'TEST NOM', 'TEST PRENOM', 'TEST ADRESSE', 'TEST PASS', b'1', 'CLIENT', '0101010101'),
('g.descomptes', 'TEST NOM', 'TEST PRENOM', 'TEST ADRESSE', 'TEST PASS', b'1', 'CLIENT', '1000000001'),
('g.descomptesvides', 'TEST NOM', 'TEST PRENOM', 'TEST ADRESSE', 'TEST PASS', b'1', 'CLIENT', '0000000002'),
('g.exist', 'TEST NOM', 'TEST PRENOM', 'TEST ADRESSE', 'TEST PASS', b'1', 'CLIENT', '1010101010'),
('g.pasdecompte', 'TEST NOM', 'TEST PRENOM', 'TEST ADRESSE', 'TEST PASS', b'1', 'CLIENT', '5544554455'),
('j.doe1', 'Doe', 'Jane', '456, grand boulevard, Brest', 'toto', b'1', 'CLIENT', '1234567890'),
('j.doe2', 'Doe', 'John', '457, grand boulevard, Perpignan', 'toto', b'1', 'CLIENT', '0000000001');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Compte`
--
ALTER TABLE `Compte`
  ADD CONSTRAINT `fk_Compte_userId` FOREIGN KEY (`userId`) REFERENCES `Utilisateur` (`userId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
