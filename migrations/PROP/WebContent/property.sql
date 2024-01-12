-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 10, 2024 at 07:02 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `property`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart_details`
--

CREATE TABLE `cart_details` (
  `id` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `product_id` varchar(30) NOT NULL,
  `added_on` varchar(30) NOT NULL,
  `is_product_ordered` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `cart_details`
--

INSERT INTO `cart_details` (`id`, `username`, `product_id`, `added_on`, `is_product_ordered`) VALUES
(49, 'ramesh', '100222', '10/01/2024 22:17', '1'),
(50, 'ramesh', '10000', '10/01/2024 22:40', '1'),
(52, 'ramesh', '10001', '10/01/2024 23:30', '1');

-- --------------------------------------------------------

--
-- Table structure for table `count_details`
--

CREATE TABLE `count_details` (
  `id` int(11) NOT NULL,
  `product_id` varchar(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  `view_count` int(11) DEFAULT NULL,
  `view_date` varchar(30) DEFAULT NULL,
  `add_to_cart_count` int(11) DEFAULT 0,
  `add_to_cart_date` varchar(30) DEFAULT NULL,
  `order_count` int(11) DEFAULT 0,
  `order_date` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `count_details`
--

INSERT INTO `count_details` (`id`, `product_id`, `username`, `view_count`, `view_date`, `add_to_cart_count`, `add_to_cart_date`, `order_count`, `order_date`) VALUES
(23, '1001', 'ramesh', 2, '10/01/2024 22:31', 0, NULL, 0, NULL),
(24, '10003', 'ramesh', 1, '10/01/2024 22:40', 0, NULL, 0, NULL),
(25, '10000', 'ramesh', 1, '10/01/2024 22:40', 1, '10/01/2024 22:40', 1, '10/01/2024 22:40'),
(26, '10001', 'ramesh', 1, '10/01/2024 23:30', 1, '10/01/2024 23:30', 1, '10/01/2024 23:31');

-- --------------------------------------------------------

--
-- Table structure for table `order_details`
--

CREATE TABLE `order_details` (
  `order_id` int(11) NOT NULL,
  `ordered_product_id` varchar(30) NOT NULL,
  `ordered_product_units` int(11) NOT NULL,
  `total_order_amount` decimal(10,2) NOT NULL,
  `ordered_on` varchar(30) NOT NULL,
  `ordered_by` varchar(30) NOT NULL,
  `order_status` varchar(30) NOT NULL,
  `order_status_updated_on` varchar(30) NOT NULL,
  `is_order_delivered` tinyint(1) NOT NULL,
  `order_delivered_on` varchar(30) NOT NULL,
  `is_order_cancelled` tinyint(1) NOT NULL,
  `order_cancelled_on` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `order_details`
--

INSERT INTO `order_details` (`order_id`, `ordered_product_id`, `ordered_product_units`, `total_order_amount`, `ordered_on`, `ordered_by`, `order_status`, `order_status_updated_on`, `is_order_delivered`, `order_delivered_on`, `is_order_cancelled`, `order_cancelled_on`) VALUES
(18, '100222', 1, 7600000.00, '10/01/2024 22:17', 'ramesh', 'Pending', '10/01/2024 22:17', 0, '', 0, ''),
(19, '10000', 1, 8990009.00, '10/01/2024 22:40', 'ramesh', 'Pending', '10/01/2024 22:40', 0, '', 0, ''),
(20, '10001', 1, 4000000.00, '10/01/2024 23:31', 'ramesh', 'Pending', '10/01/2024 23:31', 0, '', 0, '');

-- --------------------------------------------------------

--
-- Table structure for table `product_details`
--

CREATE TABLE `product_details` (
  `product_id` varchar(30) NOT NULL,
  `product_name` varchar(100) NOT NULL,
  `product_category` varchar(50) NOT NULL,
  `product_unit_price` decimal(10,2) NOT NULL,
  `product_description` varchar(200) NOT NULL,
  `product_registered_on` varchar(30) NOT NULL,
  `pin_code` varchar(6) NOT NULL,
  `is_product_ordered` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `product_details`
--

INSERT INTO `product_details` (`product_id`, `product_name`, `product_category`, `product_unit_price`, `product_description`, `product_registered_on`, `pin_code`, `is_product_ordered`) VALUES
('10000', '3 BHK House', 'house', 8990009.00, '3 BHK HOuse for sale', '10/01/2024 22:36', '560040', '0'),
('10001', 'Site', 'site', 4000000.00, 'Site for sale in Vijaynagar', '10/01/2024 22:34', '560040', '0'),
('10002', '40*60 Site for Sale..', 'site', 15000000.00, '40*60 Site for Sale in prime location..', '10/01/2024 22:35', '', '0'),
('10003', '20*30 Site for sale', 'site', 5000000.00, '20*30 Site for sale', '10/01/2024 22:37', '560045', '0');

-- --------------------------------------------------------

--
-- Table structure for table `search_count_details`
--

CREATE TABLE `search_count_details` (
  `search_id` int(10) UNSIGNED NOT NULL,
  `username` varchar(30) NOT NULL,
  `category` varchar(30) DEFAULT NULL,
  `category_search_count` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `recent_category_search_date` varchar(30) DEFAULT NULL,
  `product` varchar(100) DEFAULT NULL,
  `product_search_count` int(10) UNSIGNED NOT NULL DEFAULT 0,
  `recent_product_search_date` varchar(30) DEFAULT NULL,
  `pin_code` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `search_count_details`
--

INSERT INTO `search_count_details` (`search_id`, `username`, `category`, `category_search_count`, `recent_category_search_date`, `product`, `product_search_count`, `recent_product_search_date`, `pin_code`) VALUES
(21, 'kiran', 'land', 1, '28/12/2023 20:53', NULL, 0, NULL, ''),
(22, 'ramesh', 'site', 3, '10/01/2024 23:23', NULL, 0, NULL, ''),
(23, 'ramesh', NULL, 0, NULL, '', 1, '10/01/2024 23:05', '560040'),
(24, 'ramesh', 'land', 1, '10/01/2024 23:08', NULL, 0, NULL, ''),
(25, 'ramesh', 'house', 2, '10/01/2024 23:23', NULL, 0, NULL, '');

-- --------------------------------------------------------

--
-- Table structure for table `user_details`
--

CREATE TABLE `user_details` (
  `username` varchar(20) NOT NULL,
  `password` varchar(500) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `dob` varchar(30) NOT NULL,
  `gender` varchar(30) NOT NULL,
  `phone` varchar(250) NOT NULL,
  `email` varchar(250) NOT NULL,
  `address` varchar(50) NOT NULL,
  `city` varchar(30) NOT NULL,
  `state` varchar(30) NOT NULL,
  `country` varchar(30) NOT NULL,
  `zipcode` varchar(10) NOT NULL,
  `last_login` varchar(30) NOT NULL,
  `user_type` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `user_details`
--

INSERT INTO `user_details` (`username`, `password`, `firstname`, `lastname`, `dob`, `gender`, `phone`, `email`, `address`, `city`, `state`, `country`, `zipcode`, `last_login`, `user_type`) VALUES
('kiran', 'kfrSc0NQLTke6JfQbro7KA==', 'Kiran', 'K', '2005-02-28', 'Male', 'FNJnDZM7FMpKcu0r34zsrbK4X5mKr46ZQZZQ4gLrO5Y=', '6L0pMV18YhzwUJ8xfOf/V1zNQDRs+M9rfICt9CcfAt8=', 'Bangalore', 'Bangalore', 'Karnataka', 'India', '560079', '10/01/2024 22:37', 'buyer'),
('ramesh', 'kfrSc0NQLTke6JfQbro7KA==', 'Ramesh', 'Karuti', '1988-03-03', 'Male', '8MxjqlkjjMWtNIOGIRfb8wI/t9mRtctKg/vmekhGf/U=', '8lXwVBue84WBEH6SnopPkQsNTNCb58IK9ic+bnoxE3TqPGCne9FaAvQGZlGleQ++', 'Vijaynagar', 'Bangalore', 'Karnataka', 'India', '560045', '10/01/2024 22:37', 'buyer'),
('rose', 'kfrSc0NQLTke6JfQbro7KA==', 'Rose', 'Marray', '1989-03-03', 'Female', '1NE9vFjvu21KiL6Y93+g0GkKY/Bawj+60xAXAtFkX7E=', 'iN2sUXq8UF1eSnBDZSkqSqwb3wZodR/hJxVfPJMsRXA=', 'Vijaynagar', 'Bangalore', 'Karnataka', 'India', '560079', '10/01/2024 22:37', 'seller'),
('srujan', 'kfrSc0NQLTke6JfQbro7KA==', 'Srujan', 'S', '2007-03-02', 'Male', 'vZkh2rNU+gzOSOQN2f0caTO5Zc6mm8YlzoWMrpGCIWU=', 'kbBlLFcvWj0BqWmCbmEL5RVQg3MXXvYgkP+kYR1Y2pP+sYlWMpGfsL5Uk8UJwqGF', 'Vijaynagar', 'Bangalore', 'Karnataka', 'India', '560040', '10/01/2024 22:37', 'seller');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart_details`
--
ALTER TABLE `cart_details`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `count_details`
--
ALTER TABLE `count_details`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`order_id`);

--
-- Indexes for table `product_details`
--
ALTER TABLE `product_details`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `search_count_details`
--
ALTER TABLE `search_count_details`
  ADD PRIMARY KEY (`search_id`);

--
-- Indexes for table `user_details`
--
ALTER TABLE `user_details`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cart_details`
--
ALTER TABLE `cart_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT for table `count_details`
--
ALTER TABLE `count_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `order_details`
--
ALTER TABLE `order_details`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `search_count_details`
--
ALTER TABLE `search_count_details`
  MODIFY `search_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
