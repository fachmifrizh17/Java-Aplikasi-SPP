-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 18, 2017 at 05:00 AM
-- Server version: 5.7.17-log
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pembayaranspp`
--

-- --------------------------------------------------------

--
-- Table structure for table `data_pembayaran`
--

CREATE TABLE `data_pembayaran` (
  `data_pembayaran` char(10) NOT NULL,
  `bulan_pembayaran` enum('Januari','Februari','Maret','April','Mei','Juni','Juli','Agustus','September','Oktober','Novembert','Desember') NOT NULL,
  `biaya_spp` int(20) NOT NULL,
  `no_transaksi` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `siswa`
--

CREATE TABLE `siswa` (
  `nisn` char(20) NOT NULL,
  `namasiswa` varchar(100) NOT NULL,
  `jeniskelamin` enum('L','P') NOT NULL,
  `kelas` char(10) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `kode_angkatan` char(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `siswa`
--

INSERT INTO `siswa` (`nisn`, `namasiswa`, `jeniskelamin`, `kelas`, `alamat`, `kode_angkatan`) VALUES
('NIS001', 'Arga Pratama', 'L', '5', 'Yogyakarta', '2016'),
('NIS002', 'Agung', 'L', '5', 'Bantul', '2016'),
('NIS004', 'Angelina', 'P', '2', 'Bandung', '2017'),
('NIS008', 'Adinda', 'P', '4', 'Ponorogo', '2016'),
('NIS009', 'Noviana', 'P', '5', 'null', '2015');

-- --------------------------------------------------------

--
-- Table structure for table `spp`
--

CREATE TABLE `spp` (
  `kode_angkatan` char(10) NOT NULL,
  `biaya_spp` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `spp`
--

INSERT INTO `spp` (`kode_angkatan`, `biaya_spp`) VALUES
('2015', 40000),
('2016', 45000),
('2017', 52000),
('2018', 55000),
('2019', 63000);

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `idstaff` char(10) NOT NULL,
  `namastaff` char(50) NOT NULL,
  `password` char(20) NOT NULL,
  `jeniskelamin` char(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`idstaff`, `namastaff`, `password`, `jeniskelamin`) VALUES
('STA-001', 'WULAN', 'wulan', 'P'),
('STA-002', 'YULI', 'yuli', 'P'),
('STA-004', 'SITI', 'siti', 'P'),
('STA-005', 'Gusdur', 'gusdur', 'L'),
('STA-006', 'Juna', 'juna', 'L'),
('STA003', 'Agustin', 'agustin', 'P');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_pembayaran`
--

CREATE TABLE `transaksi_pembayaran` (
  `no_transaksi` char(10) NOT NULL,
  `nisn` char(20) NOT NULL,
  `idstaff` char(20) NOT NULL,
  `tgl_bayar` date NOT NULL,
  `jumlah_bayar` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `data_pembayaran`
--
ALTER TABLE `data_pembayaran`
  ADD PRIMARY KEY (`data_pembayaran`),
  ADD KEY `no_transaksi` (`no_transaksi`);

--
-- Indexes for table `siswa`
--
ALTER TABLE `siswa`
  ADD PRIMARY KEY (`nisn`),
  ADD KEY `kode_angkatan` (`kode_angkatan`);

--
-- Indexes for table `spp`
--
ALTER TABLE `spp`
  ADD PRIMARY KEY (`kode_angkatan`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`idstaff`);

--
-- Indexes for table `transaksi_pembayaran`
--
ALTER TABLE `transaksi_pembayaran`
  ADD PRIMARY KEY (`no_transaksi`),
  ADD KEY `nisn` (`nisn`),
  ADD KEY `idstaff` (`idstaff`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `data_pembayaran`
--
ALTER TABLE `data_pembayaran`
  ADD CONSTRAINT `data_pembayaran_ibfk_1` FOREIGN KEY (`no_transaksi`) REFERENCES `transaksi_pembayaran` (`no_transaksi`);

--
-- Constraints for table `siswa`
--
ALTER TABLE `siswa`
  ADD CONSTRAINT `siswa_ibfk_1` FOREIGN KEY (`kode_angkatan`) REFERENCES `spp` (`kode_angkatan`);

--
-- Constraints for table `transaksi_pembayaran`
--
ALTER TABLE `transaksi_pembayaran`
  ADD CONSTRAINT `transaksi_pembayaran_ibfk_1` FOREIGN KEY (`nisn`) REFERENCES `siswa` (`nisn`),
  ADD CONSTRAINT `transaksi_pembayaran_ibfk_2` FOREIGN KEY (`idstaff`) REFERENCES `staff` (`idstaff`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
