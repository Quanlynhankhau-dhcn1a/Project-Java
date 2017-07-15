-- phpMyAdmin SQL Dump
-- version 2.9.2
-- http://www.phpmyadmin.net
-- 
-- Host: localhost
-- Generation Time: May 14, 2012 at 12:04 PM
-- Server version: 5.0.27
-- PHP Version: 5.2.1
-- 
-- Database: `data`
-- 

-- --------------------------------------------------------

-- 
-- Table structure for table `canbo`
-- 

CREATE TABLE `canbo` (
  `MaCB` text NOT NULL,
  `TenCB` text NOT NULL,
  `NgaySinh` text,
  `QueQuan` text,
  `ChucVu` text,
  `Socmnd` int(9) NOT NULL,
  `MK` text,
  `stt` int(11) NOT NULL auto_increment,
  PRIMARY KEY  (`stt`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=65 ;

-- 
-- Dumping data for table `canbo`
-- 

INSERT INTO `canbo` VALUES ('admin', 'Ban quản trị', '2012-04-16', 'qqqqqqq', 'Quản Lý', 685758562, '123456', 4);

-- --------------------------------------------------------

-- 
-- Table structure for table `hokhau`
-- 

CREATE TABLE `hokhau` (
  `MaHD` text NOT NULL,
  `TenChuho` text NOT NULL,
  `Socmnd` int(9) NOT NULL,
  `diachi` text,
  `ngaycap` date default NULL,
  `noicap` text,
  `stt` int(11) NOT NULL default '0',
  PRIMARY KEY  (`stt`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `hokhau`
-- 


-- --------------------------------------------------------

-- 
-- Table structure for table `log`
-- 

CREATE TABLE `log` (
  `Quantri` text,
  `CongViec` text,
  `ThoiGan` text,
  `h` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `log`
-- 


-- --------------------------------------------------------

-- 
-- Table structure for table `nguoidan`
-- 

CREATE TABLE `nguoidan` (
  `MaHd` text NOT NULL,
  `hoten` text NOT NULL,
  `bidanh` text,
  `NamSinh` text NOT NULL,
  `socmnd` int(9) NOT NULL default '0',
  `Noicap` text,
  `ngaycap` date default NULL,
  `QueQuan` text NOT NULL,
  `nghenghiep` text NOT NULL,
  `hocvan` text,
  `tongiao` text,
  `quanhe` text,
  `Ghichu` text,
  `anh` text,
  `Gioitinh` text NOT NULL,
  `stt` text NOT NULL,
  PRIMARY KEY  (`socmnd`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `nguoidan`
-- 


-- --------------------------------------------------------

-- 
-- Table structure for table `tamtru`
-- 

CREATE TABLE `tamtru` (
  `Socmnd` int(9) NOT NULL,
  `ten` text NOT NULL,
  `GioiTinh` text NOT NULL,
  `Namsinh` text NOT NULL,
  `quequan` text,
  `Diachio` text,
  `Lydo` text,
  `ghichu` text,
  `ngaychuyen` date default NULL,
  `thoigan` text,
  `stt` text NOT NULL,
  PRIMARY KEY  (`Socmnd`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `tamtru`
-- 


-- --------------------------------------------------------

-- 
-- Table structure for table `tamvang`
-- 

CREATE TABLE `tamvang` (
  `Socmnd` int(9) NOT NULL,
  `ten` text NOT NULL,
  `GioiTinh` text NOT NULL,
  `Namsinh` text NOT NULL,
  `quequan` text,
  `Diachio` text,
  `Lydo` text,
  `ghichu` text,
  `ngaychuyen` text,
  `thoigan` text,
  `stt` text NOT NULL,
  PRIMARY KEY  (`Socmnd`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `tamvang`
-- 

