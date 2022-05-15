-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 15, 2022 at 03:32 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electro_payment`
--

-- --------------------------------------------------------

--
-- Table structure for table `electro_payment`
--

CREATE TABLE `electro_payment` (
  `id` int(11) NOT NULL,
  `b_id` varchar(45) DEFAULT NULL,
  `account_number` varchar(45) DEFAULT NULL,
  `c_id` varchar(45) DEFAULT NULL,
  `c_name` varchar(45) DEFAULT NULL,
  `amount` varchar(45) DEFAULT NULL,
  `card_number` varchar(45) DEFAULT NULL,
  `bank_name` varchar(45) DEFAULT NULL,
  `card_exp_date` varchar(45) DEFAULT NULL,
  `cvv` varchar(45) DEFAULT NULL,
  `date` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `electro_payment`
--

INSERT INTO `electro_payment` (`id`, `b_id`, `account_number`, `c_id`, `c_name`, `amount`, `card_number`, `bank_name`, `card_exp_date`, `cvv`, `date`) VALUES
(1, 'B001', 'EG12345678', 'C001', 'Ravindu.Kaushan', '2500', '123456789123', 'HNB', '12.12', '159', '15.05.2022'),
(3, 'B002', 'EG12345689', 'C002', 'Kasun', '2000', '789456321452', 'NSB', '23.11', '458', '05.03.2022'),
(6, 'B003', 'EG6328569', 'C003', 'Janith', '5000', '789652569874', 'HNB', '12.02', '569', '18.05.2022');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `electro_payment`
--
ALTER TABLE `electro_payment`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `electro_payment`
--
ALTER TABLE `electro_payment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
