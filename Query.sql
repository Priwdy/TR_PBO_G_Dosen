-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 28, 2025 at 11:01 AM
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
-- Database: `tr-pbo`
--

-- --------------------------------------------------------

--
-- Table structure for table `dosen`
--

CREATE TABLE `dosen` (
  `nid` varchar(20) NOT NULL,
  `nama_dosen` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `dosen`
--

INSERT INTO `dosen` (`nid`, `nama_dosen`) VALUES
('555', 'ocsa'),
('672', 'Wili'),
('muehe', 'Theo Ganteng'),
('xyz123', 'Messi');

-- --------------------------------------------------------

--
-- Table structure for table `input_nilai`
--

CREATE TABLE `input_nilai` (
  `kode_mk` varchar(10) NOT NULL,
  `nilai` varchar(20) DEFAULT NULL,
  `ak` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `input_nilai`
--

INSERT INTO `input_nilai` (`kode_mk`, `nilai`, `ak`) VALUES
('TC007D', 'A', '12.0'),
('TC008E', 'E', '1.0');

-- --------------------------------------------------------

--
-- Table structure for table `jadwal_mengajar`
--

CREATE TABLE `jadwal_mengajar` (
  `id_jadwal` int(11) NOT NULL,
  `kode_mk` varchar(10) NOT NULL,
  `nid_dosen` varchar(20) NOT NULL,
  `hari` varchar(20) NOT NULL,
  `jam` varchar(20) NOT NULL,
  `ruang` varchar(20) NOT NULL,
  `tahun_akademik` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `krs_mhs`
--

CREATE TABLE `krs_mhs` (
  `id_krs` int(11) NOT NULL,
  `nim_mhs` varchar(20) NOT NULL,
  `kode_mk` varchar(10) NOT NULL,
  `id_jadwal` int(11) NOT NULL,
  `tahun_akademik` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `nim` varchar(20) NOT NULL,
  `nama_mhs` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `matakuliah`
--

CREATE TABLE `matakuliah` (
  `kode_mk` varchar(10) NOT NULL,
  `nama_mk` varchar(100) NOT NULL,
  `ruangan` varchar(20) DEFAULT NULL,
  `waktu` varchar(50) DEFAULT NULL,
  `sks` varchar(75) DEFAULT NULL,
  `hari` varchar(53) DEFAULT NULL,
  `id_dosen` varchar(70) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `matakuliah`
--

INSERT INTO `matakuliah` (`kode_mk`, `nama_mk`, `ruangan`, `waktu`, `sks`, `hari`, `id_dosen`) VALUES
('TC007D', 'ASD D', 'FTI500', '9 - 12', '3', 'Senin', '555'),
('TC008E', 'PTI A', 'FTI 501', '12 - 15', '2', 'Selasa', 'xyz123');

-- --------------------------------------------------------

--
-- Table structure for table `nilai_mhs`
--

CREATE TABLE `nilai_mhs` (
  `id_nilai` int(11) NOT NULL,
  `nim_mhs` varchar(20) DEFAULT NULL,
  `kode_mk` varchar(10) DEFAULT NULL,
  `nilai_huruf` varchar(5) DEFAULT NULL,
  `nilai_angka` decimal(4,2) DEFAULT NULL,
  `tahun_akademik` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `pengguna_login`
--

CREATE TABLE `pengguna_login` (
  `id_user` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('mhs','dosen','admin') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pengguna_login`
--

INSERT INTO `pengguna_login` (`id_user`, `password`, `role`) VALUES
('1', '44', 'mhs'),
('44444444', 'password', 'dosen'),
('555', 'password', 'dosen'),
('672', 'dosen_123', 'dosen'),
('672024101', 'jomok', 'mhs'),
('672024123', '123456', 'mhs'),
('672024200', 'hendra123', 'mhs'),
('672024212', '123456', 'mhs'),
('admin', 'ganteng', 'admin'),
('Cuki', 'password', 'dosen'),
('dosen', 'ganteng', 'dosen'),
('muehe', 'dosen_123', 'dosen'),
('xyz123', 'dosen_123', 'dosen');

-- --------------------------------------------------------

--
-- Table structure for table `siasat`
--

CREATE TABLE `siasat` (
  `kode_mk` varchar(10) NOT NULL,
  `nama_mk` varchar(100) NOT NULL,
  `ruangan` varchar(20) DEFAULT NULL,
  `waktu` varchar(50) DEFAULT NULL,
  `sks` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dosen`
--
ALTER TABLE `dosen`
  ADD PRIMARY KEY (`nid`);

--
-- Indexes for table `input_nilai`
--
ALTER TABLE `input_nilai`
  ADD PRIMARY KEY (`kode_mk`);

--
-- Indexes for table `jadwal_mengajar`
--
ALTER TABLE `jadwal_mengajar`
  ADD PRIMARY KEY (`id_jadwal`),
  ADD KEY `fk_jadwal_mk` (`kode_mk`),
  ADD KEY `fk_jadwal_dosen` (`nid_dosen`);

--
-- Indexes for table `krs_mhs`
--
ALTER TABLE `krs_mhs`
  ADD PRIMARY KEY (`id_krs`),
  ADD KEY `fk_krs_mhs` (`nim_mhs`),
  ADD KEY `fk_krs_jadwal` (`id_jadwal`);

--
-- Indexes for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`nim`);

--
-- Indexes for table `matakuliah`
--
ALTER TABLE `matakuliah`
  ADD PRIMARY KEY (`kode_mk`),
  ADD KEY `fk_id_dosen` (`id_dosen`);

--
-- Indexes for table `nilai_mhs`
--
ALTER TABLE `nilai_mhs`
  ADD PRIMARY KEY (`id_nilai`);

--
-- Indexes for table `pengguna_login`
--
ALTER TABLE `pengguna_login`
  ADD PRIMARY KEY (`id_user`);

--
-- Indexes for table `siasat`
--
ALTER TABLE `siasat`
  ADD PRIMARY KEY (`kode_mk`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `jadwal_mengajar`
--
ALTER TABLE `jadwal_mengajar`
  MODIFY `id_jadwal` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `krs_mhs`
--
ALTER TABLE `krs_mhs`
  MODIFY `id_krs` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `dosen`
--
ALTER TABLE `dosen`
  ADD CONSTRAINT `fk_dosen_login` FOREIGN KEY (`nid`) REFERENCES `pengguna_login` (`id_user`);

--
-- Constraints for table `input_nilai`
--
ALTER TABLE `input_nilai`
  ADD CONSTRAINT `input_nilai_ibfk_1` FOREIGN KEY (`kode_mk`) REFERENCES `matakuliah` (`kode_mk`);

--
-- Constraints for table `jadwal_mengajar`
--
ALTER TABLE `jadwal_mengajar`
  ADD CONSTRAINT `fk_jadwal_dosen` FOREIGN KEY (`nid_dosen`) REFERENCES `dosen` (`nid`),
  ADD CONSTRAINT `fk_jadwal_mk` FOREIGN KEY (`kode_mk`) REFERENCES `matakuliah` (`kode_mk`);

--
-- Constraints for table `krs_mhs`
--
ALTER TABLE `krs_mhs`
  ADD CONSTRAINT `fk_krs_jadwal` FOREIGN KEY (`id_jadwal`) REFERENCES `jadwal_mengajar` (`id_jadwal`),
  ADD CONSTRAINT `fk_krs_mhs` FOREIGN KEY (`nim_mhs`) REFERENCES `mahasiswa` (`nim`);

--
-- Constraints for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD CONSTRAINT `fk_mhs_login` FOREIGN KEY (`nim`) REFERENCES `pengguna_login` (`id_user`);

--
-- Constraints for table `matakuliah`
--
ALTER TABLE `matakuliah`
  ADD CONSTRAINT `fk_id_dosen` FOREIGN KEY (`id_dosen`) REFERENCES `dosen` (`nid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
