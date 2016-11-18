-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 11, 2016 at 06:34 AM
-- Server version: 10.1.10-MariaDB
-- PHP Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `t2_akun`
--

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `fullname` varchar(50) NOT NULL,
  `username` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `address` varchar(50) NOT NULL,
  `postalcode` varchar(10) NOT NULL,
  `phonenumber` varchar(20) NOT NULL,
  `Token` varchar(255) NOT NULL,
  `createAt` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `fullname`, `username`, `email`, `password`, `address`, `postalcode`, `phonenumber`, `Token`, `createAt`) VALUES
(7, 'Varian Caesar', 'varian97', 'variancaesar@gmail.c', 'admin', 'Jalan Pelesiran no. 19 Taman Sari, Bandung				', '40116', '082379511452', '', '0000-00-00'),
(15, 'admin', 'admin', 'admin@gmail.com', 'admin', 'jalan ganesha no. 7			', '80886', '0811726266', '', '0000-00-00'),
(16, 'majid', 'majid', 'majid@gmail.com', 'y', 'itb				', '80997', '08796788876', '', '0000-00-00'),
(25, 'kei', 'kei', 'kei', 'kei', '123', '1234', '1234', '', '0000-00-00'),
(26, 'tes', 'tes', 'tes@gmail.com', 'tes', 'ITB', '40112', '1234', '', '0000-00-00');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
