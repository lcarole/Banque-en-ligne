-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 17 sep. 2021 à 16:22
-- Version du serveur :  10.4.17-MariaDB
-- Version de PHP : 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `banque`
DROP DATABASE if exists `banque`;
CREATE DATABASE `banque`;
--

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `idClient` int(11) NOT NULL,
  `Nom` varchar(50) NOT NULL,
  `Prenom` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `Adresse` varchar(100) NOT NULL,
  `Sexe` varchar(10) NOT NULL,
  `Date_de_naissance` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`idClient`, `Nom`, `Prenom`, `Email`, `Password`, `Adresse`, `Sexe`, `Date_de_naissance`) VALUES
(1, 'Carole', 'Luigi', 'luigi.carole@gmail.com', 'e7cf3ef4f17c3999a94f2c6f612e8a888e5b1026878e4e19398b23bd38ec221a', '3 Place Andre Malraux', 'Homme', '1998-07-09'),
(4, 'Dupont', 'Nathan', 'ndupont@outlook.fr', '30c3b0286bdf3a556d4df366715bcf756320274bf8e376616c5eab32d1c505b0', '16 Rue Maryse Bastié, 95190 Goussainville', 'Homme', '2000-12-30');

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE `compte` (
  `idCompte` int(11) NOT NULL,
  `Solde` float NOT NULL,
  `libelle` varchar(60) NOT NULL,
  `idClient` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `compte`
--

INSERT INTO `compte` (`idCompte`, `Solde`, `libelle`, `idClient`) VALUES
(1, 7000.9, 'Compte Courant', 1),
(3, 3150, 'Compte courant', 4);

-- --------------------------------------------------------

--
-- Structure de la table `operation`
--

CREATE TABLE `operation` (
  `idOperation` int(11) NOT NULL,
  `Montant` float NOT NULL,
  `libelle` varchar(50) NOT NULL,
  `idCompte` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `operation`
--

INSERT INTO `operation` (`idOperation`, `Montant`, `libelle`, `idCompte`) VALUES
(1, 10500.9, 'Ouverture du compte', 1),
(2, -500, 'Achat d\'un ordianteur Portable HP', 1),
(6, -3000, 'Virement débité', 1),
(7, 3000, 'Somme créditée', 3);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`idClient`);

--
-- Index pour la table `compte`
--
ALTER TABLE `compte`
  ADD PRIMARY KEY (`idCompte`),
  ADD KEY `Compte_Client_FK` (`idClient`);

--
-- Index pour la table `operation`
--
ALTER TABLE `operation`
  ADD PRIMARY KEY (`idOperation`),
  ADD KEY `Operation_Compte_FK` (`idCompte`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `idClient` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `compte`
--
ALTER TABLE `compte`
  MODIFY `idCompte` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `operation`
--
ALTER TABLE `operation`
  MODIFY `idOperation` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `compte`
--
ALTER TABLE `compte`
  ADD CONSTRAINT `Compte_Client_FK` FOREIGN KEY (`idClient`) REFERENCES `client` (`idClient`);

--
-- Contraintes pour la table `operation`
--
ALTER TABLE `operation`
  ADD CONSTRAINT `Operation_Compte_FK` FOREIGN KEY (`idCompte`) REFERENCES `compte` (`idCompte`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
