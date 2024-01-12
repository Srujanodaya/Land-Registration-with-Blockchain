-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 22, 2023 at 12:18 PM
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
  `is_product_ordered` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `cart_details`
--

INSERT INTO `cart_details` (`id`, `username`, `product_id`, `added_on`, `is_product_ordered`) VALUES
(8, 'test', 'PROP100', '13/03/2017 12:59', 0),
(41, 'ramesh', 'PROP101', '22/12/2023 16:47', 1);

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
(1, 'PROP100', 'test', 4, '15/03/2017 12:57', 0, '', 0, NULL),
(15, 'PROP101', 'ramesh', 1, '22/12/2023 16:47', 1, '22/12/2023 16:47', 1, '22/12/2023 16:47');

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
(1, 'PROP100', 2, 700.00, '14/03/2017 13:20', 'test', 'Pending', '14/03/2017 13:20', 0, '', 0, ''),
(10, 'PROP101', 1, 7500000.00, '22/12/2023 16:47', 'ramesh', 'Pending', '22/12/2023 16:47', 0, '', 0, '');

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
  `pin_code` varchar(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `product_details`
--

INSERT INTO `product_details` (`product_id`, `product_name`, `product_category`, `product_unit_price`, `product_description`, `product_registered_on`, `pin_code`) VALUES
('PROP100', '30x40 Site', 'site', 5500000.00, 'Refreshing chewing gum', '13/03/2017 21:51', '560017'),
('PROP101', '2 BHK with Pooja Room', 'house', 7500000.00, '2 BHK with Pooja Room and attched bathroom', '20/12/2023 20:56', '560045');

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
(1, 'test', 'food', 5, '16/03/2017 14:17', NULL, 0, NULL, ''),
(5, 'test', 'house', 1, '20/12/2023 12:41', NULL, 0, NULL, ''),
(6, 'test', 'site', 2, '20/12/2023 21:41', NULL, 0, NULL, ''),
(7, 'test', 'land', 1, '20/12/2023 12:42', NULL, 0, NULL, ''),
(8, 'test', NULL, 0, NULL, '30x40 site', 3, '20/12/2023 21:53', ''),
(9, 'test', NULL, 0, NULL, '', 1, '20/12/2023 22:31', '560017'),
(10, 'test', NULL, 0, NULL, '', 1, '20/12/2023 22:31', '560045'),
(11, 'test', NULL, 0, NULL, '2 bhk with pooja room', 5, '20/12/2023 23:11', ''),
(12, 'test', NULL, 0, NULL, '', 1, '20/12/2023 22:52', '560045'),
(13, 'ramesh', 'land', 2, '21/12/2023 22:55', NULL, 0, NULL, ''),
(14, 'ramesh', 'site', 11, '21/12/2023 22:56', NULL, 0, NULL, ''),
(15, 'ramesh', 'house', 3, '21/12/2023 22:56', NULL, 0, NULL, ''),
(16, 'ramesh', NULL, 0, NULL, '', 1, '21/12/2023 22:57', '560017');

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
('ramesh', 'kfrSc0NQLTke6JfQbro7KA==', 'Ramesh', 'Karuti', '1988-03-03', 'Male', '8MxjqlkjjMWtNIOGIRfb8wI/t9mRtctKg/vmekhGf/U=', '8lXwVBue84WBEH6SnopPkQsNTNCb58IK9ic+bnoxE3TqPGCne9FaAvQGZlGleQ++', 'Vijaynagar', 'Bangalore', 'Karnataka', 'India', '560045', '22/12/2023 16:19', 'buyer'),
('rose', 'kfrSc0NQLTke6JfQbro7KA==', 'Rose', 'Marray', '1989-03-03', 'Female', '1NE9vFjvu21KiL6Y93+g0GkKY/Bawj+60xAXAtFkX7E=', 'iN2sUXq8UF1eSnBDZSkqSqwb3wZodR/hJxVfPJMsRXA=', 'Vijaynagar', 'Bangalore', 'Karnataka', 'India', '560079', '22/12/2023 16:19', 'seller'),
('test', 'test', 'Update', 'Test', '1990-05-04', 'Male', '9857454715', 'test@test.com', 'Vijayanagar', 'Bangalore', 'Karnataka', 'India', '560085', '22/12/2023 16:19', 'buyer');

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT for table `count_details`
--
ALTER TABLE `count_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `order_details`
--
ALTER TABLE `order_details`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `search_count_details`
--
ALTER TABLE `search_count_details`
  MODIFY `search_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
