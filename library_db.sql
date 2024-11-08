-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 08, 2024 at 07:51 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `publicationYear` int(11) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `author` varchar(255) NOT NULL,
  `isbn` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`publicationYear`, `id`, `author`, `isbn`, `title`) VALUES
(2023, 2, 'Elias', '1234567890', 'Test 2');

-- --------------------------------------------------------

--
-- Table structure for table `borrowingrecord`
--

CREATE TABLE `borrowingrecord` (
  `book_id` bigint(20) NOT NULL,
  `borrowedDate` datetime(6) NOT NULL,
  `patron_id` bigint(20) NOT NULL,
  `returnedDate` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `borrowingrecord`
--

INSERT INTO `borrowingrecord` (`book_id`, `borrowedDate`, `patron_id`, `returnedDate`) VALUES
(2, '2024-11-08 03:33:15.000000', 1, '2024-11-08 03:42:04.000000'),
(2, '2024-11-08 17:31:33.000000', 1, '2024-11-08 17:31:58.000000');

-- --------------------------------------------------------

--
-- Table structure for table `patron`
--

CREATE TABLE `patron` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `patron`
--

INSERT INTO `patron` (`id`, `email`, `name`) VALUES
(1, 'elias@gmail.com', 'Elias'),
(2, 'elias2@gmail.com', 'Elias 2'),
(3, 'mr.j@gmail.com', 'Mr.Java');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_bi5lx9jtv1f52idrmc0ck8ysx` (`isbn`);

--
-- Indexes for table `borrowingrecord`
--
ALTER TABLE `borrowingrecord`
  ADD PRIMARY KEY (`book_id`,`borrowedDate`,`patron_id`),
  ADD KEY `FKr0qfrca1791c2xlx0pij7v8pv` (`patron_id`);

--
-- Indexes for table `patron`
--
ALTER TABLE `patron`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `book`
--
ALTER TABLE `book`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `patron`
--
ALTER TABLE `patron`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `borrowingrecord`
--
ALTER TABLE `borrowingrecord`
  ADD CONSTRAINT `FK78v4w90sksp64jjo2byfxd6dj` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
  ADD CONSTRAINT `FKr0qfrca1791c2xlx0pij7v8pv` FOREIGN KEY (`patron_id`) REFERENCES `patron` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
