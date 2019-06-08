-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 08, 2019 at 01:25 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sbpd`
--

-- --------------------------------------------------------

--
-- Table structure for table `battery`
--

CREATE TABLE `battery` (
  `PSS_id` varchar(50) DEFAULT NULL,
  `Water_level` tinyint(1) DEFAULT NULL,
  `Terminal` tinyint(1) DEFAULT NULL,
  `Voltage` int(5) DEFAULT NULL,
  `Gravity` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `batterycharger`
--

CREATE TABLE `batterycharger` (
  `PSS_id` varchar(50) DEFAULT NULL,
  `SupplyVoltageAC` int(5) DEFAULT NULL,
  `SupplyVoltageDC` int(5) DEFAULT NULL,
  `ChargingCurrent` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `generalreport`
--

CREATE TABLE `generalreport` (
  `PSS_id` varchar(50) DEFAULT NULL,
  `A` tinyint(1) DEFAULT NULL,
  `B` tinyint(1) DEFAULT NULL,
  `C1` tinyint(1) DEFAULT NULL,
  `C2` tinyint(1) DEFAULT NULL,
  `D1` tinyint(1) DEFAULT NULL,
  `D2` tinyint(1) DEFAULT NULL,
  `E` tinyint(1) DEFAULT NULL,
  `F1` tinyint(1) DEFAULT NULL,
  `F2` tinyint(1) DEFAULT NULL,
  `G` tinyint(1) DEFAULT NULL,
  `H` tinyint(1) DEFAULT NULL,
  `I` tinyint(1) DEFAULT NULL,
  `J` tinyint(1) DEFAULT NULL,
  `K` tinyint(1) DEFAULT NULL,
  `L` tinyint(1) DEFAULT NULL,
  `M` tinyint(1) DEFAULT NULL,
  `N` tinyint(1) DEFAULT NULL,
  `O` tinyint(1) DEFAULT NULL,
  `P` tinyint(1) DEFAULT NULL,
  `Q` tinyint(1) DEFAULT NULL,
  `R` tinyint(1) DEFAULT NULL,
  `S` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `isolator`
--

CREATE TABLE `isolator` (
  `PSS_id` varchar(50) DEFAULT NULL,
  `Total` int(5) DEFAULT NULL,
  `Working` int(5) DEFAULT NULL,
  `Defective` int(5) DEFAULT NULL,
  `Type` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `la`
--

CREATE TABLE `la` (
  `PSS_id` varchar(50) DEFAULT NULL,
  `Total` int(5) DEFAULT NULL,
  `Working` int(5) DEFAULT NULL,
  `Defective` int(5) DEFAULT NULL,
  `Required` int(5) DEFAULT NULL,
  `Type` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `materials`
--

CREATE TABLE `materials` (
  `PSS_id` varchar(50) DEFAULT NULL,
  `Name` varchar(50) DEFAULT NULL,
  `for33KV` int(5) DEFAULT NULL,
  `for11KV` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `meter`
--

CREATE TABLE `meter` (
  `PSS_id` varchar(50) DEFAULT NULL,
  `Total` int(5) DEFAULT NULL,
  `Working` int(5) DEFAULT NULL,
  `Defective` int(5) DEFAULT NULL,
  `Type` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `meteringunit`
--

CREATE TABLE `meteringunit` (
  `PSS_id` varchar(50) DEFAULT NULL,
  `Total` int(5) DEFAULT NULL,
  `Working` int(5) DEFAULT NULL,
  `Defective` int(5) DEFAULT NULL,
  `Type` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `powertransformer`
--

CREATE TABLE `powertransformer` (
  `PSS_id` varchar(50) NOT NULL,
  `PT_id` varchar(50) NOT NULL,
  `SL_no` varchar(50) DEFAULT NULL,
  `MfgYear` date DEFAULT NULL,
  `OilLevel` varchar(50) DEFAULT NULL,
  `BDVTop` varchar(50) DEFAULT NULL,
  `BDVBottom` varchar(50) DEFAULT NULL,
  `BuchholzRelay` tinyint(1) DEFAULT NULL,
  `OilLeakage` tinytext,
  `TempMeterWinding` int(5) DEFAULT NULL,
  `TempMeterOil` int(5) DEFAULT NULL,
  `SilicaGel` tinyint(1) DEFAULT NULL,
  `EarthResistNeutral` int(5) DEFAULT NULL,
  `EarthResistBody` int(5) DEFAULT NULL,
  `HTBrush` tinyint(1) DEFAULT NULL,
  `LTBrush` tinyint(1) DEFAULT NULL,
  `BrushConnectors` tinyint(1) DEFAULT NULL,
  `Plinth` varchar(50) DEFAULT NULL,
  `EarthPit&connector` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pss`
--

CREATE TABLE `pss` (
  `PSS_id` varchar(50) NOT NULL,
  `PSS_name` varchar(50) DEFAULT NULL,
  `section_name` varchar(50) DEFAULT NULL,
  `sub_division_name` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `vcb`
--

CREATE TABLE `vcb` (
  `PSS_id` varchar(50) DEFAULT NULL,
  `Type` tinyint(1) DEFAULT NULL,
  `Name` varchar(50) DEFAULT NULL,
  `CT` int(5) DEFAULT NULL,
  `Interrupter` tinyint(1) DEFAULT NULL,
  `Mechanism` tinyint(1) DEFAULT NULL,
  `Motor` tinyint(1) DEFAULT NULL,
  `TrippingCoil` tinyint(1) DEFAULT NULL,
  `closingCoil` tinyint(1) DEFAULT NULL,
  `EarthingPanel` tinyint(1) DEFAULT NULL,
  `RelayOC` tinyint(1) DEFAULT NULL,
  `MasterTripRelay` tinyint(1) DEFAULT NULL,
  `SL_no` varchar(50) DEFAULT NULL,
  `MfgYear` date DEFAULT NULL,
  `Make` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `battery`
--
ALTER TABLE `battery`
  ADD KEY `FK` (`PSS_id`);

--
-- Indexes for table `batterycharger`
--
ALTER TABLE `batterycharger`
  ADD KEY `FK` (`PSS_id`);

--
-- Indexes for table `generalreport`
--
ALTER TABLE `generalreport`
  ADD KEY `FK` (`PSS_id`);

--
-- Indexes for table `isolator`
--
ALTER TABLE `isolator`
  ADD KEY `FK` (`PSS_id`);

--
-- Indexes for table `la`
--
ALTER TABLE `la`
  ADD KEY `FK` (`PSS_id`);

--
-- Indexes for table `materials`
--
ALTER TABLE `materials`
  ADD KEY `FK` (`PSS_id`);

--
-- Indexes for table `meter`
--
ALTER TABLE `meter`
  ADD KEY `FK` (`PSS_id`);

--
-- Indexes for table `meteringunit`
--
ALTER TABLE `meteringunit`
  ADD KEY `FK` (`PSS_id`);

--
-- Indexes for table `powertransformer`
--
ALTER TABLE `powertransformer`
  ADD PRIMARY KEY (`PSS_id`,`PT_id`);

--
-- Indexes for table `pss`
--
ALTER TABLE `pss`
  ADD PRIMARY KEY (`PSS_id`);

--
-- Indexes for table `vcb`
--
ALTER TABLE `vcb`
  ADD KEY `FK` (`PSS_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
