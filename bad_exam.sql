-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 12, 2024 at 01:39 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bad_exam`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_produk`
--

CREATE TABLE `tb_produk` (
  `id_produk` tinyint(4) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `kategori` varchar(100) NOT NULL,
  `stok` tinyint(10) NOT NULL,
  `harga` int(10) NOT NULL,
  `deskripsi` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_produk`
--

INSERT INTO `tb_produk` (`id_produk`, `nama`, `kategori`, `stok`, `harga`, `deskripsi`) VALUES
(1, 'Samsung A33', 'Phone', 4, 5000000, 'Support 5G, 128/8'),
(3, 'Xiaomi Redmi Note 12', 'Phone', 15, 3500000, 'Snapdragon 732G, 6GB RAM, 128GB Storage'),
(4, 'Oppo Reno8', 'Phone', 5, 7000000, 'Dimensity 810, 8GB RAM, 256GB Storage'),
(10, 'Logitech 1220', 'Mouse', 6, 35000, 'Wireless'),
(11, 'Asus A570', 'Mouse', 6, 150000, 'Tipe ABC, Wireless');

-- --------------------------------------------------------

--
-- Table structure for table `tb_transaksi`
--

CREATE TABLE `tb_transaksi` (
  `id_transaksi` mediumint(5) NOT NULL,
  `tanggal` date NOT NULL,
  `nama_pembeli` varchar(100) NOT NULL,
  `id_produk` tinyint(4) NOT NULL,
  `jumlah` tinyint(10) NOT NULL,
  `harga` int(10) NOT NULL,
  `total_harga` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_transaksi`
--

INSERT INTO `tb_transaksi` (`id_transaksi`, `tanggal`, `nama_pembeli`, `id_produk`, `jumlah`, `harga`, `total_harga`) VALUES
(1, '2022-02-03', 'Happy', 1, 1, 5000000, 5000000),
(2, '2024-08-02', 'Jeslin', 3, 2, 3500000, 7000000),
(8, '2022-02-03', 'Tes', 1, 2, 5000000, 10000000),
(11, '2024-02-02', 'Vita', 3, 8, 3500000, 28000000),
(13, '2024-08-12', 'Aliffiana', 11, 2, 150000, 300000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_produk`
--
ALTER TABLE `tb_produk`
  ADD PRIMARY KEY (`id_produk`);

--
-- Indexes for table `tb_transaksi`
--
ALTER TABLE `tb_transaksi`
  ADD PRIMARY KEY (`id_transaksi`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_produk`
--
ALTER TABLE `tb_produk`
  MODIFY `id_produk` tinyint(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `tb_transaksi`
--
ALTER TABLE `tb_transaksi`
  MODIFY `id_transaksi` mediumint(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
