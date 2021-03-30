-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Mar 30, 2021 at 10:43 AM
-- Server version: 5.7.24
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `groupe`
--

-- --------------------------------------------------------

--
-- Table structure for table `act`
--

CREATE TABLE `act` (
  `id_act` int(11) NOT NULL,
  `nom_act` varchar(50) NOT NULL,
  `type_act` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `act`
--

INSERT INTO `act` (`id_act`, `nom_act`, `type_act`) VALUES
(1, 'mp', 'yoga');

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id_user` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` text NOT NULL,
  `mail` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id_user`, `username`, `password`, `mail`) VALUES
(1, '', '', ''),
(2, '', '', ''),
(3, 'sffsfs', 'fsfsfsfsfs', 'fsfsfsfs'),
(4, 'sffsfs', 'fsfsfsfsfs', 'fsfsfsfs'),
(5, '', '', ''),
(6, '', '', ''),
(7, '', '', ''),
(8, 'arem', '$2a$13$GQZHj3cTWZ/M6YMbsmsKTuKsJ6IjQ3l5j0xiBNXzm3CLq6sbqhWrq', 'arem@aa.aa'),
(9, 'arem', '$2a$13$Ql0F/kMSWkpKQcvs88HuE.CfYZbvdImAAja7FrJ80iSHtZXoZgRFa', 'arem@aa.aa'),
(10, 'Sana', '$2a$13$zucjGiWa75Ix4rvwEJr8LucgM7grHYoTYMhFotTAVrig90y95cE8a', 'sana@sana.sa'),
(11, 'opop', '$2a$13$mCDxmuwBquRMbhNIjg4NE.lXIU62WxyjfiN83rUS8k541Vv1XUrMK', 'mmm@ytg.ji'),
(12, 'fefefe', '$2a$13$JxtxaUM/LtoeNiDZNNHo9uBrItyGOFhXQiIyNXD6.vLvzYYcO9U26', 'dedede@deded.de'),
(13, 'azer', '$2a$13$WJpBN8dddD12Pl/8ftxoyOR4OO62ENFS3ejv7YZ0HxASNLJTkc.xi', 'azer@azer.az'),
(14, 'Mourad', '$2a$13$dHa.wZRQWNPLQU6O5hXm0utl3nlvQCzpLrlPJO5cQrdJS5ke9G7he', 'mourad@esprit.tn'),
(16, 'mgkadmin', '$2a$13$h84ZE897OC3M1c1TghADruMr6ATIvqKQ8lbV32NqqWTXo.46eqwxK', 'mgkadmin@mgk2.tn');

-- --------------------------------------------------------

--
-- Table structure for table `coach`
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
-- Dumping data for table `coach`
--

INSERT INTO `coach` (`id_user`, `username`, `password`, `mail`, `date_n`, `code`) VALUES
(111, 'fff', 'fff', 'fff', '2021-03-01', 'fff'),
(784, 'mgkcoach', '$2a$13$qBxEx/7EVV03YoUof1xGzOKVcAZyenXXL9SRGvljEpzZr2vXFwOgG', 'mgkcoach@mgk2.tn', '2021-03-11', 'ompo');

-- --------------------------------------------------------

--
-- Table structure for table `commentaire`
--

CREATE TABLE `commentaire` (
  `id_com` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_pub` int(11) NOT NULL,
  `suj_com` varchar(500) DEFAULT NULL,
  `date_com` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `nb_reaction` int(250) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `commentaire`
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
-- Table structure for table `discussion`
--

CREATE TABLE `discussion` (
  `id_disc` int(11) NOT NULL,
  `datetemps_disc` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `nom_source` varchar(50) CHARACTER SET utf8mb4 NOT NULL,
  `nom_destinaire` varchar(20) CHARACTER SET utf8mb4 NOT NULL,
  `vue_disc` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `discussion`
--

INSERT INTO `discussion` (`id_disc`, `datetemps_disc`, `nom_source`, `nom_destinaire`, `vue_disc`) VALUES
(1, '2021-03-30 09:59:53', 'ahmed', 'shidono', 0);

-- --------------------------------------------------------

--
-- Table structure for table `evenement`
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
-- Dumping data for table `evenement`
--

INSERT INTO `evenement` (`id_ev`, `titre_ev`, `type_ev`, `emplacement_ev`, `date_dev`, `date_fev`, `temps_dev`, `temps_fev`, `age_min`, `age_max`, `id_act`) VALUES
(71, 'islem', 'sportif', 'ml', '2021-03-04', '2021-03-20', '15:00:00', '15:00:00', 13, 26, 1),
(73, 'hello', 'sportif', 'from', '2021-03-12', '2021-03-12', '07:00:00', '07:00:00', 12, 26, NULL),
(74, 'ev', 'sportif', 'terrain', '2021-03-04', '2021-03-04', '13:00:00', '13:00:00', 13, 13, NULL),
(76, 'sport', 'sportif', 'tunis', '2021-03-01', '2021-03-02', '15:00:00', '15:00:00', 18, 18, NULL),
(79, 'loi', 'loisir', 'gazela', '2021-03-09', '2021-03-24', '15:02:00', '18:22:00', 13, 12, NULL),
(80, 'event', 'loisir', 'ariana', '2021-03-02', '2021-03-03', '15:00:00', '15:00:00', 16, 16, NULL),
(81, 'event', 'loisir', 'ariana', '2021-03-02', '2021-03-03', '15:00:00', '15:00:00', 16, 16, NULL),
(82, 'ayevTEST', 'loisir', 'eee', '2021-03-26', '2021-03-26', '12:00:00', '12:00:00', 15, 15, NULL),
(90, 'islem', 'sportif', 'ml', '2021-03-11', '2021-03-12', '12:00:00', '12:00:00', 15, 15, NULL),
(91, 'islem', 'educatif', 'esprit', '2021-03-04', '2021-03-04', '12:03:00', '12:03:00', 12, 12, NULL),
(93, 'mm', 'sportif', 'ww', '2021-03-05', '2021-03-27', '21:42:00', '14:42:00', 12, 12, NULL),
(94, 'ui', 'educatif', 'ml', '2021-03-14', '2021-03-21', '15:40:00', '15:40:00', 13, 13, NULL),
(95, 'izlem', 'educatif', 'okkk', '2021-03-21', '2021-03-14', '14:51:00', '14:52:00', 12, 12, NULL),
(96, 'islem', 'sportif', 'lmp', '2021-03-21', '2021-03-21', '15:54:00', '14:54:00', 15, 15, NULL),
(97, 'islem', 'educatif', 'olo', '2021-03-21', '2021-03-20', '13:05:00', '15:05:00', 15, 15, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `invitation`
--

CREATE TABLE `invitation` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_ev` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `invitation`
--

INSERT INTO `invitation` (`id`, `id_user`, `id_ev`) VALUES
(5, -1, 93),
(6, 22, 80),
(7, 31, 74),
(9, -1, -1);

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE `message` (
  `id_msg` int(11) NOT NULL,
  `contenu_msg` varchar(255) NOT NULL,
  `id_disc` int(11) NOT NULL,
  `sender` varchar(50) NOT NULL,
  `datetemps_msg` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `message`
--

INSERT INTO `message` (`id_msg`, `contenu_msg`, `id_disc`, `sender`, `datetemps_msg`) VALUES
(1, 'salut', 1, 'ahmed', '2021-03-30 10:00:13'),
(2, 'cava?', 1, 'ahmed', '2021-03-30 10:02:51');

-- --------------------------------------------------------

--
-- Table structure for table `nutri`
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
-- Dumping data for table `nutri`
--

INSERT INTO `nutri` (`id_user`, `username`, `password`, `mail`, `date_n`, `code`) VALUES
(1, 'sss', 'sss', 'sss', '2021-03-18', 'sss'),
(4, 'aaa', 'aaa', 'aaa', '2021-03-03', 'aaa'),
(5, 'eee', 'eee', 'eee', '2021-03-11', 'eee'),
(6, 'mgknutri', '$2a$13$ymwEH1yMOSd1UpSxv2CtY.6V8xnPLstu1y6QghlT6WXVkQMx4MCbi', 'mgknutri@mgk2.tn', '2021-03-05', 'dododo'),
(7, 'jsjsjs', '$2a$13$FB1otbJc1qJ7ZNSQkgLLN.X0t8aTRRgBPgw3QavN180QmFpe02aZ2', 'jijiji@jxs.cd', '2021-03-10', 'cdijc');

-- --------------------------------------------------------

--
-- Table structure for table `participation`
--

CREATE TABLE `participation` (
  `id_par` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_event` int(11) NOT NULL,
  `nbr_par` int(255) DEFAULT '0',
  `username` varchar(50) DEFAULT NULL,
  `date_par` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `participation`
--

INSERT INTO `participation` (`id_par`, `id_user`, `id_event`, `nbr_par`, `username`, `date_par`) VALUES
(7, 4, 3, 1, 'Anas', '2021-03-03 20:13:56'),
(8, 2, 3, 1, 'Islem', '2021-03-03 20:14:49'),
(10, 5, 3, 1, 'Mourad', '2021-03-03 20:15:33'),
(11, 6, 3, 1, 'Yasmine', '2021-03-03 20:17:07');

-- --------------------------------------------------------

--
-- Table structure for table `psycho`
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
-- Dumping data for table `psycho`
--

INSERT INTO `psycho` (`id_user`, `username`, `password`, `mail`, `date_n`, `code`) VALUES
(1, 'aaa', 'aaa', 'aaa', '2021-03-02', ''),
(1003, 'zer', '$2a$13$SYNeZ/N3Dtf2QXJzukzj/OPRcwKbDmtywihI08gpEAsjs.MXBBefy', 'dgsf@ezf.fe', '2021-03-11', 'efzfd'),
(1004, 'azert', '$2a$13$exqsTF0KrFAh5XP/7FY1gOsH4lua/VpOek7Y8pVA3mMpu3kHR95Z6', 'aeae@aeae.ae', '2021-03-02', 'aeaeaeae'),
(1005, 'rera', '$2a$13$YPGGLPKjerAeGyUam4JlYOofcIPBam15Zom7SRaazbpXUMmdm5ai2', 'rera@rera.tn', '2021-03-02', 'rera'),
(1006, 'qaqa', '$2a$13$xAxUR1PZ45b9Kkfw5ya2UeuVt5sTCZKJy2Ijw8vqPxpPBQlxBe4om', 'qaqa@qaqa.qa', '2021-03-03', 'qaqaqaq'),
(1007, 'tatat', '$2a$13$Z/EKYSQ0BI9rkSgyzuArw.uvQING20xtxsDM7pNQ1FkE/5BqcUZT6', 'tata@tata.ta', '2021-03-02', 'tata'),
(1008, 'aqaq', '$2a$13$mlT7//YR8SKQQDCLvElESOZcqAN8HAN0j/3wxdcgkegrWfIOfAY/W', 'aqaq@aqaq.aq', '2021-03-03', 'aqaq'),
(1009, 'dede', '$2a$13$TZ8mRBd9z.Wnr/ePeMNxg.9Ljf9LcUfTfQHS0nDR0CJr3LxoHHSeC', 'dede@de.de', '2021-03-03', 'dede'),
(1010, 'trtr', '$2a$13$PHopZOHYWIA3aThOesDNJugv4SQwtgt95qgtvy8TgBrsaaL1JZzM2', 'tete@te.te', '2021-03-03', 'tr'),
(1011, 'yoyo', '$2a$13$DkzHMxw0Xk5g75hDezFWAe0pIhcCmmkbWZdE2YVPaRCxobr0OW14i', 'yoyo@yoyo.yy', '2021-03-01', 'yoyo'),
(1012, 'arr', '$2a$13$3rGyz83B3wW7ltDFpPJtgeqR6oFNFe96gtRWznOaHx4A.VrEDWEdq', 'arr@arr.ar', '2021-03-03', 'arar'),
(1013, 'tyty', '$2a$13$QZ/4g2OKb2yUyGBXAQyKUuz2A.ddAI1va3gqv/1TuMA8yF0/RmLIi', 'uiui@ui.ui', '2021-03-10', 'tyty'),
(1014, 'tera', '$2a$13$71skoueMPxb4o5YFFXf0L.uZ51uLf7RNw1unfp6byCKFLROIYnYEC', 'rere@erer.rr', '2021-03-03', '1lj'),
(1015, 'mgkpsy', '$2a$13$XqJEY.kvpYqNGbhlfCNyY.rb1ZlDRfB8FgJZSFpjqYVAsv0tidiS.', 'mgkpsy@mgk2.tn', '2021-03-03', 'fdsilfk'),
(1016, 'ahmed', '$2a$13$oObEEB7eZ8zuTF2VklAKVORecXaEfQG8Xy6yYFmUM0B2EJT0K.SE.', 'mourad.zribi@esprit.tn', '2021-03-02', '6');

-- --------------------------------------------------------

--
-- Table structure for table `reclamation`
--

CREATE TABLE `reclamation` (
  `id_rec` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `obj_rec` varchar(50) DEFAULT NULL,
  `area_rec` varchar(100) DEFAULT NULL,
  `suj_rec` varchar(150) DEFAULT NULL,
  `etat_rec` varchar(20) DEFAULT 'To do',
  `date_rec` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `reclamation`
--

INSERT INTO `reclamation` (`id_rec`, `id_user`, `obj_rec`, `area_rec`, `suj_rec`, `etat_rec`, `date_rec`) VALUES
(35, 2, 'test', 'Events', 'dsdpiskmnsd', 'To do', '2021-03-28 21:23:27'),
(36, 2, 'testtest', 'Pubs', 'pppppp', 'To do', '2021-03-28 22:31:58'),
(37, 2, 'test', 'Chat', 'dflidfn', 'To do', '2021-03-28 23:03:24'),
(38, 2, 'testXXX', 'Events', 'testXXX', 'To do', '2021-03-28 23:11:08');

-- --------------------------------------------------------

--
-- Table structure for table `simple`
--

CREATE TABLE `simple` (
  `id_user` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` text NOT NULL,
  `mail` varchar(50) NOT NULL,
  `date_n` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `simple`
--

INSERT INTO `simple` (`id_user`, `username`, `password`, `mail`, `date_n`) VALUES
(30, 'mourad', '$2a$13$hPY/1pRjOJnRmVHULTSsj.leZn2S8R3VxIl/Sth7wIW31XR0S74bu', 'mouradmourad@esprit.tn', '2021-03-03'),
(31, 'mgk', '$2a$13$L5cAtXVWc559U5FqH3U/EOCoiHMgzqLt7AszGKCG7EWmrpj0kd3aG', 'mgk@mgk2.tn', '2021-03-03'),
(32, 'sccscsc', '$2a$13$SY5cCVGMNXa.w38IN55sTuwJCHtL9NsQiAErQd8HENXUHwxSITZ1u', 'xovij@vucc.vs', '2021-03-04'),
(34, 'shidono', '$2a$13$TKG2ivxvG.MSs/4BSyPu6uxEtjbkdkpUU9eaa9thPCRqCQbQQR7rq', 'shidonosan@gmail.com', '2021-03-30');

-- --------------------------------------------------------

--
-- Table structure for table `suivi`
--

CREATE TABLE `suivi` (
  `id_s` int(11) NOT NULL,
  `username` varchar(50) CHARACTER SET utf8mb4 NOT NULL,
  `client` varchar(20) CHARACTER SET utf8mb4 NOT NULL,
  `titre_s` varchar(50) DEFAULT NULL,
  `date_ds` date NOT NULL,
  `date_fs` date NOT NULL,
  `temps_ds` time NOT NULL,
  `temps_fs` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `suivi`
--

INSERT INTO `suivi` (`id_s`, `username`, `client`, `titre_s`, `date_ds`, `date_fs`, `temps_ds`, `temps_fs`) VALUES
(1, 'ahmed', 'shidono', 'preparation test', '2021-03-10', '2021-03-25', '02:59:00', '04:59:00');

-- --------------------------------------------------------

--
-- Table structure for table `tache`
--

CREATE TABLE `tache` (
  `id_tache` int(11) NOT NULL,
  `username` varchar(30) DEFAULT NULL,
  `etat_tache` tinyint(1) DEFAULT '0',
  `difficulte_tache` varchar(30) NOT NULL,
  `description_tache` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tache`
--

INSERT INTO `tache` (`id_tache`, `username`, `etat_tache`, `difficulte_tache`, `description_tache`) VALUES
(1, 'shidono', 0, 'moyenne', 'bonjour toi');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `mail` varchar(50) NOT NULL,
  `date_n` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `act`
--
ALTER TABLE `act`
  ADD PRIMARY KEY (`id_act`);

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_user`);

--
-- Indexes for table `coach`
--
ALTER TABLE `coach`
  ADD PRIMARY KEY (`id_user`);

--
-- Indexes for table `commentaire`
--
ALTER TABLE `commentaire`
  ADD PRIMARY KEY (`id_com`),
  ADD KEY `fk_com_user` (`id_user`),
  ADD KEY `fk_com_pub` (`id_pub`);

--
-- Indexes for table `discussion`
--
ALTER TABLE `discussion`
  ADD PRIMARY KEY (`id_disc`),
  ADD KEY `fk_simple` (`nom_destinaire`),
  ADD KEY `fk_source` (`nom_source`);

--
-- Indexes for table `evenement`
--
ALTER TABLE `evenement`
  ADD PRIMARY KEY (`id_ev`),
  ADD KEY `fk_art_cat` (`id_act`);

--
-- Indexes for table `invitation`
--
ALTER TABLE `invitation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id` (`id_user`),
  ADD KEY `fk_id_ev` (`id_ev`) USING BTREE;

--
-- Indexes for table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`id_msg`),
  ADD KEY `fk_id_disc` (`id_disc`);

--
-- Indexes for table `nutri`
--
ALTER TABLE `nutri`
  ADD PRIMARY KEY (`id_user`);

--
-- Indexes for table `participation`
--
ALTER TABLE `participation`
  ADD PRIMARY KEY (`id_par`),
  ADD KEY `fk_par_user` (`id_user`),
  ADD KEY `fk_par_event` (`id_event`);

--
-- Indexes for table `psycho`
--
ALTER TABLE `psycho`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `reclamation`
--
ALTER TABLE `reclamation`
  ADD PRIMARY KEY (`id_rec`),
  ADD KEY `bobo` (`id_user`) USING BTREE;

--
-- Indexes for table `simple`
--
ALTER TABLE `simple`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `suivi`
--
ALTER TABLE `suivi`
  ADD PRIMARY KEY (`id_s`),
  ADD KEY `fk_nutri_username` (`username`),
  ADD KEY `fk_simple_client` (`client`);

--
-- Indexes for table `tache`
--
ALTER TABLE `tache`
  ADD PRIMARY KEY (`id_tache`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `coach`
--
ALTER TABLE `coach`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=785;

--
-- AUTO_INCREMENT for table `commentaire`
--
ALTER TABLE `commentaire`
  MODIFY `id_com` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `discussion`
--
ALTER TABLE `discussion`
  MODIFY `id_disc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `evenement`
--
ALTER TABLE `evenement`
  MODIFY `id_ev` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=98;

--
-- AUTO_INCREMENT for table `invitation`
--
ALTER TABLE `invitation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `message`
--
ALTER TABLE `message`
  MODIFY `id_msg` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `nutri`
--
ALTER TABLE `nutri`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `participation`
--
ALTER TABLE `participation`
  MODIFY `id_par` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `psycho`
--
ALTER TABLE `psycho`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1017;

--
-- AUTO_INCREMENT for table `reclamation`
--
ALTER TABLE `reclamation`
  MODIFY `id_rec` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `simple`
--
ALTER TABLE `simple`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `suivi`
--
ALTER TABLE `suivi`
  MODIFY `id_s` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tache`
--
ALTER TABLE `tache`
  MODIFY `id_tache` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `discussion`
--
ALTER TABLE `discussion`
  ADD CONSTRAINT `fk_simple` FOREIGN KEY (`nom_destinaire`) REFERENCES `simple` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_source` FOREIGN KEY (`nom_source`) REFERENCES `psycho` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `evenement`
--
ALTER TABLE `evenement`
  ADD CONSTRAINT `fk_art_cat` FOREIGN KEY (`id_act`) REFERENCES `act` (`id_act`);

--
-- Constraints for table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `fk_id_disc` FOREIGN KEY (`id_disc`) REFERENCES `discussion` (`id_disc`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `suivi`
--
ALTER TABLE `suivi`
  ADD CONSTRAINT `fk_nutri_username` FOREIGN KEY (`username`) REFERENCES `psycho` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_simple_client` FOREIGN KEY (`client`) REFERENCES `simple` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
