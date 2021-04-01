-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 01 avr. 2021 à 03:07
-- Version du serveur :  10.4.11-MariaDB
-- Version de PHP : 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `spirity`
--

-- --------------------------------------------------------

--
-- Structure de la table `act`
--

CREATE TABLE `act` (
  `id_act` int(11) NOT NULL,
  `nom_act` varchar(50) NOT NULL,
  `type_act` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `act`
--

INSERT INTO `act` (`id_act`, `nom_act`, `type_act`) VALUES
(1, 'mp', 'yoga');

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `id_user` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` text NOT NULL,
  `mail` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `admin`
--

INSERT INTO `admin` (`id_user`, `username`, `password`, `mail`) VALUES
(16, 'mgkadmin', '$2a$13$h84ZE897OC3M1c1TghADruMr6ATIvqKQ8lbV32NqqWTXo.46eqwxK', 'mgkadmin@mgk2.tn'),
(18, 'finaltestadmin', '$2a$13$cCGTxqoWPE4ToeyGXBNsgOdISMKnTJYHkeFaQXbqt1ypEE5fxhPQ6', 'finaltestadmin@aaaa.aa');

-- --------------------------------------------------------

--
-- Structure de la table `articles`
--

CREATE TABLE `articles` (
  `id_art` int(11) NOT NULL,
  `titre_art` varchar(255) NOT NULL,
  `auteur_art` varchar(255) DEFAULT NULL,
  `description_art` varchar(1500) DEFAULT NULL,
  `date_art` datetime DEFAULT current_timestamp(),
  `likes` int(11) DEFAULT 0,
  `id_cat` int(11) NOT NULL,
  `photo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `articles`
--

INSERT INTO `articles` (`id_art`, `titre_art`, `auteur_art`, `description_art`, `date_art`, `likes`, `id_cat`, `photo`) VALUES
(22, 'régime guide complet pour perdre du poids', 'mgknutri', 'Pour perdre du poids durablement il est essentiel de combiner des changements alimentaires ET une activité sportive\nface à ce double défi certains régimes proposent de véritables plans d\'exercices\nnos experts ont élu les meilleurs régimes qui associent harmonieusement rééquilibrage alimentaire et activité physique', '2021-03-27 02:11:23', 6, 6, 'file:/C:/xampp/htdocs/img/palmares-des-regimes.jpg'),
(23, 'pourquoi aimons nous dessiner', 'test1Coach', 'Dessiner nous pousse à expérimenter des méthodes\ndes techniques afin de se rapprocher le plus possible de la réalité dans le but de la figer dans le temps \nà tout jamais ou au contraire à s’en éloigner tout en l’exprimant à travers notre prisme émotionnel', '2021-03-27 02:22:15', 2, 61, 'file:/C:/xampp/htdocs/img/téléchargement.jpg'),
(24, 'les bienfaits du sport sur la santé', 'mgkcoach', 'L\'activité physique est également un élément de prévention essentiel pour garder des os solides \net prévenir ainsi l\'ostéoporose Pratiquer un sport permet de prévenir les lombalgies et la récurrence des symptômes\nLe renforcement musculaire occasionné lors des exercices physiques est aussi bénéfique pour les rhumatismes inflammatoires chroniques', '2021-03-27 02:26:17', 5, 23, 'file:/C:/xampp/htdocs/img/sp.jpg'),
(45, 'iuiuiu', 'mgkcoach', 'ityghtjty', '2021-04-01 01:40:34', 0, 27, 'http://www..'),
(46, 'kkkk', 'mgkcoach', 'jjjjjjj', '2021-04-01 01:41:56', 0, 61, 'https://i.pinimg.com/736x/5b/b4/8b/5bb48b07fa6e3840bb3afa2bc821b882.jpg'),
(47, 'nbnbnnbnn', 'mgkcoach', 'hfhfhfhfhfhfhf', '2021-04-01 01:42:59', 0, 23, 'http://www..'),
(48, 'uuuuuuu', 'mgkcoach', 'uuuuuuuuuuuuuuuuuuuuuu', '2021-04-01 01:43:36', 0, 6, 'file:/C:/Users/HP/Desktop/Nouveau%20dossier/imgcache0.350705.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE `categorie` (
  `id_cat` int(11) NOT NULL,
  `titre_cat` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `categorie`
--

INSERT INTO `categorie` (`id_cat`, `titre_cat`) VALUES
(61, 'arts'),
(27, 'Méditation'),
(6, 'Nourriture'),
(23, 'santé'),
(5, 'sport');

-- --------------------------------------------------------

--
-- Structure de la table `coach`
--

CREATE TABLE `coach` (
  `id_user` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` text NOT NULL,
  `mail` varchar(50) NOT NULL,
  `date_n` date NOT NULL,
  `code` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `coach`
--

INSERT INTO `coach` (`id_user`, `username`, `password`, `mail`, `date_n`, `code`) VALUES
(1, 'mgkcoach', '$2a$13$qBxEx/7EVV03YoUof1xGzOKVcAZyenXXL9SRGvljEpzZr2vXFwOgG', 'mgkcoach@mgk2.tn', '2021-03-11', 'ompo'),
(785, 'finaltestcoach', '$2a$13$s25jOXth07SFlC0PQuMpWOGy2DBRbl9W7bHPI1Lq0/73r7ve0xJ1G', 'finaltestcoach@fff.ff', '2002-03-21', 'fdzddz'),
(786, 'mourad', '$2a$13$UpkLwSCwnOXFPXUEMXmDuuQdTTUysX9yaLCM7vDLPq7eGgBCGS/Ke', 'mourad@esprit.tn', '1997-04-11', 'tgtgtg');

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

CREATE TABLE `commentaire` (
  `id_com` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_pub` int(11) NOT NULL,
  `suj_com` varchar(500) DEFAULT NULL,
  `date_com` timestamp NOT NULL DEFAULT current_timestamp(),
  `nb_reaction` int(250) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `commentaire`
--

INSERT INTO `commentaire` (`id_com`, `id_user`, `id_pub`, `suj_com`, `date_com`, `nb_reaction`) VALUES
(13, 5, 4, 'aaslema', '2021-03-10 21:51:07', 2),
(15, 5, 4, 'aaslema beslema', '2021-03-10 23:30:22', 1),
(17, 5, 4, 'aaaaa', '2021-03-11 00:19:45', 0),
(18, 5, 4, 'kkk', '2021-03-11 08:36:03', 0),
(19, 5, 4, 'Yes together', '2021-03-18 22:12:01', 0),
(21, 4, 4, 'no no', '2021-03-19 23:22:29', 0);

-- --------------------------------------------------------

--
-- Structure de la table `evenement`
--

CREATE TABLE `evenement` (
  `id_ev` int(11) NOT NULL,
  `titre_ev` varchar(80) NOT NULL,
  `type_ev` varchar(50) NOT NULL,
  `emplacement_ev` varchar(30) NOT NULL,
  `date_dev` date NOT NULL,
  `date_fev` date NOT NULL,
  `temps_dev` time NOT NULL,
  `temps_fev` time NOT NULL,
  `age_min` int(11) DEFAULT NULL,
  `age_max` int(11) DEFAULT NULL,
  `id_act` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `evenement`
--

INSERT INTO `evenement` (`id_ev`, `titre_ev`, `type_ev`, `emplacement_ev`, `date_dev`, `date_fev`, `temps_dev`, `temps_fev`, `age_min`, `age_max`, `id_act`) VALUES
(3, 'islem', 'sportif', 'ml', '2021-03-04', '2021-03-20', '15:00:00', '15:00:00', 13, 26, 1),
(73, 'hello', 'sportif', 'from', '2021-03-12', '2021-03-12', '07:00:00', '07:00:00', 12, 26, NULL),
(74, 'ev', 'sportif', 'terrain', '2021-03-04', '2021-03-04', '13:00:00', '13:00:00', 13, 13, NULL),
(76, 'sport', 'sportif', 'tunis', '2021-03-01', '2021-03-02', '15:00:00', '15:00:00', 18, 18, NULL),
(80, 'event', 'loisir', 'ariana', '2021-03-02', '2021-03-03', '15:00:00', '15:00:00', 16, 16, NULL),
(81, 'event', 'loisir', 'ariana', '2021-03-02', '2021-03-03', '15:00:00', '15:00:00', 16, 16, NULL),
(82, 'ayevTEST', 'loisir', 'eee', '2021-03-26', '2021-03-26', '12:00:00', '12:00:00', 15, 15, NULL),
(90, 'islem', 'sportif', 'ml', '2021-03-11', '2021-03-12', '12:00:00', '12:00:00', 15, 15, NULL),
(91, 'islem', 'educatif', 'esprit', '2021-03-04', '2021-03-04', '12:03:00', '12:03:00', 12, 12, NULL),
(93, 'mm', 'sportif', 'ww', '2021-03-05', '2021-03-27', '21:42:00', '14:42:00', 12, 12, NULL),
(94, 'ui', 'educatif', 'ml', '2021-03-14', '2021-03-21', '15:40:00', '15:40:00', 13, 13, NULL),
(95, 'izlem', 'educatif', 'okkk', '2021-03-21', '2021-03-14', '14:51:00', '14:52:00', 12, 12, NULL),
(96, 'islem', 'sportif', 'lmp', '2021-03-21', '2021-03-21', '15:54:00', '14:54:00', 15, 15, NULL),
(97, 'islem', 'educatif', 'olo', '2021-03-21', '2021-03-20', '13:05:00', '15:05:00', 15, 15, NULL),
(100, 'islem', 'educatif', 'qsdqsd', '2021-03-04', '2021-03-31', '13:28:00', '16:28:00', 5, 5, NULL),
(101, 'hj', 'educatif', 'xthg', '2021-04-01', '2021-04-28', '10:23:00', '03:23:00', 22, 22, NULL),
(102, 'hj', 'educatif', 'xthg', '2021-04-01', '2021-04-28', '10:23:00', '03:23:00', 22, 22, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `invitation`
--

CREATE TABLE `invitation` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_ev` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `invitation`
--

INSERT INTO `invitation` (`id`, `id_user`, `id_ev`) VALUES
(5, -1, 93),
(6, 22, 80),
(7, 31, 74),
(9, -1, -1);

-- --------------------------------------------------------

--
-- Structure de la table `nutri`
--

CREATE TABLE `nutri` (
  `id_user` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` text NOT NULL,
  `mail` varchar(50) NOT NULL,
  `date_n` date NOT NULL,
  `code` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `nutri`
--

INSERT INTO `nutri` (`id_user`, `username`, `password`, `mail`, `date_n`, `code`) VALUES
(6, 'mgknutri', '$2a$13$ymwEH1yMOSd1UpSxv2CtY.6V8xnPLstu1y6QghlT6WXVkQMx4MCbi', 'mgknutri@mgk2.tn', '2021-03-05', 'dododo');

-- --------------------------------------------------------

--
-- Structure de la table `participation`
--

CREATE TABLE `participation` (
  `id_par` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_event` int(11) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `date_par` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `participation`
--

INSERT INTO `participation` (`id_par`, `id_user`, `id_event`, `username`, `date_par`) VALUES
(7, 0, 3, 'Anas', '2021-03-30 21:01:13'),
(10, 0, 3, 'Mourad', '2021-03-30 21:01:20'),
(23, 0, 73, 'mgk', '2021-03-31 23:28:07');

-- --------------------------------------------------------

--
-- Structure de la table `photo_publications`
--

CREATE TABLE `photo_publications` (
  `id_ph` int(255) NOT NULL,
  `id_pub` int(255) NOT NULL,
  `lien` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `photo_publications`
--

INSERT INTO `photo_publications` (`id_ph`, `id_pub`, `lien`) VALUES
(163, 250, 'file:/C:/Users/HP/Pictures/Capture.PNG'),
(165, 250, 'file:/C:/Users/HP/Pictures/Sketchpad.png'),
(166, 261, 'https://static.wikia.nocookie.net/lemondededisney/images/e/e0/Hakunamatata.jpg/revision/latest?cb=20151215190528&path-prefix=fr');

-- --------------------------------------------------------

--
-- Structure de la table `psycho`
--

CREATE TABLE `psycho` (
  `id_user` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` text NOT NULL,
  `mail` varchar(50) NOT NULL,
  `date_n` date NOT NULL,
  `code` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `psycho`
--

INSERT INTO `psycho` (`id_user`, `username`, `password`, `mail`, `date_n`, `code`) VALUES
(1015, 'mgkpsy', '$2a$13$XqJEY.kvpYqNGbhlfCNyY.rb1ZlDRfB8FgJZSFpjqYVAsv0tidiS.', 'mgkpsy@mgk2.tn', '2021-03-03', 'fdsilfk'),
(1016, 'finaltestpsy', '$2a$13$9m34hhoZLMMPcSPcE9zc/.bK/18/9FZXAC8Z60ee.f9cz1RUMX0Cu', 'finaltestpsy@ddd.dd', '2000-03-10', 'dsdzdzdz');

-- --------------------------------------------------------

--
-- Structure de la table `publication`
--

CREATE TABLE `publication` (
  `id_pub` int(100) NOT NULL,
  `id_user` int(100) NOT NULL,
  `nb_reaction` int(255) NOT NULL,
  `texte` varchar(1000) NOT NULL,
  `date_pub` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `publication`
--

INSERT INTO `publication` (`id_pub`, `id_user`, `nb_reaction`, `texte`, `date_pub`) VALUES
(247, 1, 0, 'dddddsq', '2021-03-31 17:56:50'),
(248, 1, 5, '#1', '2021-03-31 18:05:51'),
(250, 1015, 0, '#145', '2021-03-31 19:33:49'),
(256, 1015, 0, 'ffff', '2021-03-31 20:04:55'),
(257, 1, 0, 'zzzea', '2021-03-31 20:06:50'),
(259, 6, 0, 'ddd', '2021-03-31 20:23:36'),
(260, 16, 2, 'ssss', '2021-03-31 20:25:29'),
(261, 786, 3, 'hakuna matata\n#Kel_phrase_manyfike', '2021-03-31 22:46:04');

-- --------------------------------------------------------

--
-- Structure de la table `pub_like_tracks`
--

CREATE TABLE `pub_like_tracks` (
  `id_track` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_pub` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `reclamation`
--

CREATE TABLE `reclamation` (
  `id_rec` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `obj_rec` varchar(50) DEFAULT NULL,
  `area_rec` varchar(100) DEFAULT NULL,
  `suj_rec` varchar(150) DEFAULT NULL,
  `etat_rec` varchar(20) DEFAULT 'To do',
  `date_rec` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `reclamation`
--

INSERT INTO `reclamation` (`id_rec`, `id_user`, `username`, `obj_rec`, `area_rec`, `suj_rec`, `etat_rec`, `date_rec`) VALUES
(35, 2, '', 'test', 'Events', 'dsdpiskmnsd', 'To do', '2021-03-28 21:23:27'),
(36, 2, '', 'testtest', 'Pubs', 'pppppp', 'To do', '2021-03-28 22:31:58'),
(37, 2, '', 'test', 'Chat', 'dflidfn', 'To do', '2021-03-28 23:03:24'),
(38, 2, '', 'testXXX', 'Events', 'testXXX', 'To do', '2021-03-28 23:11:08'),
(39, 2, '', 'qsqsqqqsqsq', 'Chat', 'qsqsqsq', 'To do', '2021-03-29 09:25:29'),
(41, 0, 'mgk', 'salut', 'Events', 'test test makrouna', 'To do', '2021-03-30 22:05:37'),
(43, 51, 'shidono', 'birrass', 'Events', 'sqdqsdqsdqsdqs', 'To do', '2021-03-30 22:38:29'),
(48, 784, 'mgkcoach', 'cool life', 'Events', 'qsdqsdqsdqsd', 'To do', '2021-03-31 01:36:29'),
(49, 1015, 'mgkpsy', 'dcdcdcdc', 'Select the area of the problem', 'mlkjhgfd', 'To do', '2021-03-31 01:37:32'),
(50, 1015, 'mgkpsy', 'dcdcdcdc', 'Events', 'mlkjhgfd', 'To do', '2021-03-31 01:37:40'),
(51, 0, 'mgk', 'teteteteteteetetetetet', 'Events', 'ytrfgbhgfdfghg', 'To do', '2021-04-01 02:16:02');

-- --------------------------------------------------------

--
-- Structure de la table `simple`
--

CREATE TABLE `simple` (
  `id_user` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` text NOT NULL,
  `mail` varchar(50) NOT NULL,
  `date_n` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `simple`
--

INSERT INTO `simple` (`id_user`, `username`, `password`, `mail`, `date_n`) VALUES
(0, 'mgk', '$2a$13$L5cAtXVWc559U5FqH3U/EOCoiHMgzqLt7AszGKCG7EWmrpj0kd3aG', 'mgk@mgk2.tn', '2021-03-03'),
(52, 'finaltestsimple', '$2a$13$7xaKYKJ4eRDUB5J.BWH3G.bIQy8bg5Yvri9N6iSPBE7Bre5GpmfRO', 'finaltestsimple@test.com', '2001-03-08');

-- --------------------------------------------------------

--
-- Structure de la table `tag`
--

CREATE TABLE `tag` (
  `id_tag` int(11) NOT NULL,
  `tag` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tag`
--

INSERT INTO `tag` (`id_tag`, `tag`) VALUES
(64, '1'),
(65, '145'),
(66, 'Kel_phrase_manyfike');

-- --------------------------------------------------------

--
-- Structure de la table `tag_publication`
--

CREATE TABLE `tag_publication` (
  `id_rel` int(11) NOT NULL,
  `id_pub` int(11) NOT NULL,
  `id_tag` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `tag_publication`
--

INSERT INTO `tag_publication` (`id_rel`, `id_pub`, `id_tag`) VALUES
(129, 248, 64),
(130, 250, 65),
(131, 261, 66);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `mail` varchar(50) NOT NULL,
  `date_n` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `act`
--
ALTER TABLE `act`
  ADD PRIMARY KEY (`id_act`);

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_user`);

--
-- Index pour la table `articles`
--
ALTER TABLE `articles`
  ADD PRIMARY KEY (`id_art`),
  ADD KEY `FK_cat_art` (`id_cat`);

--
-- Index pour la table `categorie`
--
ALTER TABLE `categorie`
  ADD PRIMARY KEY (`id_cat`),
  ADD UNIQUE KEY `titre_cat` (`titre_cat`);

--
-- Index pour la table `coach`
--
ALTER TABLE `coach`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Index pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD PRIMARY KEY (`id_com`),
  ADD KEY `fk_com_user` (`id_user`),
  ADD KEY `fk_com_pub` (`id_pub`);

--
-- Index pour la table `evenement`
--
ALTER TABLE `evenement`
  ADD PRIMARY KEY (`id_ev`),
  ADD KEY `fk_art_cat` (`id_act`);

--
-- Index pour la table `invitation`
--
ALTER TABLE `invitation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id` (`id_user`),
  ADD KEY `fk_id_ev` (`id_ev`) USING BTREE;

--
-- Index pour la table `nutri`
--
ALTER TABLE `nutri`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Index pour la table `participation`
--
ALTER TABLE `participation`
  ADD PRIMARY KEY (`id_par`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `fk_event` (`id_event`),
  ADD KEY `fk_user_part` (`id_user`);

--
-- Index pour la table `photo_publications`
--
ALTER TABLE `photo_publications`
  ADD PRIMARY KEY (`id_ph`),
  ADD KEY `fk_pub` (`id_pub`);

--
-- Index pour la table `psycho`
--
ALTER TABLE `psycho`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `username_2` (`username`);

--
-- Index pour la table `publication`
--
ALTER TABLE `publication`
  ADD PRIMARY KEY (`id_pub`);

--
-- Index pour la table `pub_like_tracks`
--
ALTER TABLE `pub_like_tracks`
  ADD PRIMARY KEY (`id_track`),
  ADD KEY `FK_pub_likes` (`id_pub`),
  ADD KEY `fk_simple` (`id_user`);

--
-- Index pour la table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`id_rec`);

--
-- Index pour la table `simple`
--
ALTER TABLE `simple`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Index pour la table `tag`
--
ALTER TABLE `tag`
  ADD PRIMARY KEY (`id_tag`),
  ADD UNIQUE KEY `U_tag` (`tag`);

--
-- Index pour la table `tag_publication`
--
ALTER TABLE `tag_publication`
  ADD PRIMARY KEY (`id_rel`),
  ADD KEY `FK_pub_rel` (`id_pub`),
  ADD KEY `FK_tag_rel` (`id_tag`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `admin`
--
ALTER TABLE `admin`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT pour la table `articles`
--
ALTER TABLE `articles`
  MODIFY `id_art` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT pour la table `categorie`
--
ALTER TABLE `categorie`
  MODIFY `id_cat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- AUTO_INCREMENT pour la table `coach`
--
ALTER TABLE `coach`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=787;

--
-- AUTO_INCREMENT pour la table `commentaire`
--
ALTER TABLE `commentaire`
  MODIFY `id_com` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT pour la table `evenement`
--
ALTER TABLE `evenement`
  MODIFY `id_ev` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=103;

--
-- AUTO_INCREMENT pour la table `invitation`
--
ALTER TABLE `invitation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `nutri`
--
ALTER TABLE `nutri`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `participation`
--
ALTER TABLE `participation`
  MODIFY `id_par` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT pour la table `photo_publications`
--
ALTER TABLE `photo_publications`
  MODIFY `id_ph` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=167;

--
-- AUTO_INCREMENT pour la table `psycho`
--
ALTER TABLE `psycho`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1017;

--
-- AUTO_INCREMENT pour la table `publication`
--
ALTER TABLE `publication`
  MODIFY `id_pub` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=262;

--
-- AUTO_INCREMENT pour la table `pub_like_tracks`
--
ALTER TABLE `pub_like_tracks`
  MODIFY `id_track` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=396;

--
-- AUTO_INCREMENT pour la table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `id_rec` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT pour la table `simple`
--
ALTER TABLE `simple`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT pour la table `tag`
--
ALTER TABLE `tag`
  MODIFY `id_tag` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=67;

--
-- AUTO_INCREMENT pour la table `tag_publication`
--
ALTER TABLE `tag_publication`
  MODIFY `id_rel` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=132;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `evenement`
--
ALTER TABLE `evenement`
  ADD CONSTRAINT `fk_art_cat` FOREIGN KEY (`id_act`) REFERENCES `act` (`id_act`);

--
-- Contraintes pour la table `participation`
--
ALTER TABLE `participation`
  ADD CONSTRAINT `fk_event` FOREIGN KEY (`id_event`) REFERENCES `evenement` (`id_ev`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_user_part` FOREIGN KEY (`id_user`) REFERENCES `simple` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `photo_publications`
--
ALTER TABLE `photo_publications`
  ADD CONSTRAINT `fk_pub` FOREIGN KEY (`id_pub`) REFERENCES `publication` (`id_pub`);

--
-- Contraintes pour la table `pub_like_tracks`
--
ALTER TABLE `pub_like_tracks`
  ADD CONSTRAINT `FK_pub_likes` FOREIGN KEY (`id_pub`) REFERENCES `publication` (`id_pub`),
  ADD CONSTRAINT `fk_simple` FOREIGN KEY (`id_user`) REFERENCES `simple` (`id_user`);

--
-- Contraintes pour la table `tag_publication`
--
ALTER TABLE `tag_publication`
  ADD CONSTRAINT `FK_pub_rel` FOREIGN KEY (`id_pub`) REFERENCES `publication` (`id_pub`),
  ADD CONSTRAINT `FK_tag_rel` FOREIGN KEY (`id_tag`) REFERENCES `tag` (`id_tag`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
