-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 26, 2017 lúc 07:46 PM
-- Phiên bản máy phục vụ: 10.1.28-MariaDB
-- Phiên bản PHP: 5.6.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlytinhnguyen`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dangnhap`
--

CREATE TABLE `dangnhap` (
  `TaiKhoan` varchar(30) NOT NULL,
  `MatKhau` varchar(30) NOT NULL,
  `MASV` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `dangnhap`
--

INSERT INTO `dangnhap` (`TaiKhoan`, `MatKhau`, `MASV`) VALUES
('111', '111', '111'),
('121212', '121212', '121212'),
('666', '666', '4556'),
('777', '777', '777'),
('888', '888', '888'),
('admin', 'admin', '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoatdongtinhnguyen`
--

CREATE TABLE `hoatdongtinhnguyen` (
  `MATN` varchar(30) NOT NULL,
  `MASV` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `hoatdongtinhnguyen`
--

INSERT INTO `hoatdongtinhnguyen` (`MATN`, `MASV`) VALUES
('TN2', '4556'),
('TN3', '4556'),
('TN4', '4556');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sinhvien`
--

CREATE TABLE `sinhvien` (
  `MASV` varchar(30) NOT NULL,
  `TenSV` varchar(100) NOT NULL,
  `TenTruong` varchar(255) NOT NULL,
  `NgaySinh` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `sinhvien`
--

INSERT INTO `sinhvien` (`MASV`, `TenSV`, `TenTruong`, `NgaySinh`) VALUES
('111', 'lê hoang thiên ân 11', 'dai hoc an hang o khong 11', '2017-12-15'),
('121212', '121212', '121212', '2017-12-14'),
('12121212', '121212', '121212', '2017-12-14'),
('15520011', 'Lê Ấn', '', '1997-09-15'),
('234', '234', '234', '0000-00-00'),
('4556', 'le an', 'an le', '2017-12-14'),
('777', '777', '777', '2017-12-20'),
('888', '888', '888', '2017-12-13');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tinhnguyen`
--

CREATE TABLE `tinhnguyen` (
  `MATN` varchar(30) NOT NULL,
  `TenTN` varchar(255) NOT NULL,
  `NoiDung` text NOT NULL,
  `SDT` int(11) NOT NULL,
  `NgayGioBatDau` date NOT NULL,
  `NgayGioKetThuc` date NOT NULL,
  `DiaDiem` text NOT NULL,
  `SLMax` int(11) NOT NULL,
  `SLMin` int(11) NOT NULL,
  `SLThamGia` int(11) NOT NULL,
  `MAT` varchar(30) NOT NULL,
  `HinhAnh` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `tinhnguyen`
--

INSERT INTO `tinhnguyen` (`MATN`, `TenTN`, `NoiDung`, `SDT`, `NgayGioBatDau`, `NgayGioKetThuc`, `DiaDiem`, `SLMax`, `SLMin`, `SLThamGia`, `MAT`, `HinhAnh`) VALUES
('TN1', 'Mùa Hè Xanh 6-2017', 'Những lời cảnh tỉnh phải tự biết đảm bảo an toàn cho bản thân ở nơi mình đến, chúng tôi và nhiều bạn sinh viên, thanh niên khác sẽ phải khắc cốt ghi tâm. Sẽ không thể chủ quan, không thể để mình thiếu kiến thức. Nhưng trang bị đủ cho mình những điều đó, để lại được tiếp tục bước chân tình nguyện vững chắc và an toàn hơn, chứ không phải để dừng lại, để chùn bước!', 973043012, '2017-06-12', '2017-07-12', 'Thành Phố Hồ Chí Minh', 200, 20, 106, 'UIT', 'http://ctxh.vn/uploads/news/2012_05/1276442294.nv.jpg'),
('TN2', 'Xuân Tình Nguyện 2018', 'ABCDEFDGHB', 0, '2018-01-01', '2018-01-12', 'nhà hát lớn hồ chí minh', 200, 20, 52, 'UEL', 'http://truonghocketnoi.edu.vn/data/unilever/hinhpv/galery_3_1411061240.jpg'),
('TN3', 'Phân Phát Vàng', 'Việc góp vốn 800 tỷ đồng vào OceanBank là một trong hai vụ án được xác định có liên quan trách nhiệm của ông Đinh La Thăng (nguyên chủ tịch hội đồng thành viên PVN, Phó trưởng Ban Kinh tế Trung ương, nguyên ủy viên Bộ Chính trị). Ngày 8/12, ông Thăng bị bắt, khởi tố về tội Cố ý làm trái quy định nhà nước gây hậu quả nghiêm trọng. Cùng ngày, ông Nguyễn Quốc Khánh (nguyên chủ tịch Hội đồng Thành viên PVN) cũng bị khởi tố về cùng tội danh. \r\n\r\nTrước đó, nhiều lãnh đạo của PVN đã bị bắt như ông Ninh Văn Quỳnh (nguyên kế toán trưởng, phó tổng giám đốc), Nguyễn Xuân Thắng, Nguyễn Thanh Liêm, Vũ Khánh Trường (nguyên thành viên Hội đồng thành viên PVN)...\r\n\r\nTheo nhà chức trách, tháng 9/2008, lãnh đạo PVN ký thỏa thuận với ông Hà Văn Thắm (Chủ tịch HĐQT OceanBank) về tham gia góp 20% vốn điều lệ vào nhà băng này, chia làm ba đợt. Nhà chức trách xác định số tiền góp vốn 800 tỷ đồng có nguồn gốc hình thành từ kết quả sản xuất kinh doanh của PVN.\r\n\r\nOceanBank sau đó có nhiều sai phạm đã bị Ngân hàng Nhà nước mua với giá 0 đồng và hàng loạt lãnh đạo hầu tòa. PVN lúc này phải chấm dứt tư cách cổ đông và toàn bộ các quyền, nghĩa vụ tại OceanBank nên lỗ 800 tỷ đồng.\r\n\r\nCơ quan điều tra cho rằng việc góp vốn đợt ba vào thời điểm Luật Các tổ chức tín dụng 2010 (có hiệu lực từ ngày 1/1/2011) quy định một cổ đông là tổ chức không được sở hữu vượt quá 15% vốn điều lệ của một tổ chức tín dụng. Do vậy, việc này là trái luật.', 999124575, '2017-12-26', '2017-12-28', 'Lề Đường', 1000, 10, 48, 'UIT', 'http://media.doisongphapluat.com/414/2016/7/20/nhat-duoc-vang-555.jpg'),
('TN4', 'Phát Người Yêu Free', 'Mười ngày trước, Công an tỉnh Thanh Hóa nhận được đơn tố cáo của anh Đặng (39 tuổi, ở quận Hoàng Mai, TP Hà Nội) tố cáo Anh và Sáng đã lừa bán bạn gái của mình là chị Phạm (24 tuổi, quê ở huyện Cẩm Thủy, Thanh Hóa) sang Trung Quốc làm gái mại dâm.\r\n\r\nĐược sự hỗ trợ của Cục cảnh sát hình sự (Bộ Công an), công an tỉnh Thanh Hóa phối hợp với Công an tỉnh Quảng Ninh giải cứu thành công chị Phạm đưa về đoàn tụ với gia đình.\r\n\r\nMở rộng điều tra, nhà chức trách bắt giữ Đỗ Ngọc Anh và Hoàng Thị Ánh Sáng. Bước đầu, hai nghi can thừa nhận hành vi môi giới, buôn bán người.', 1675632514, '2017-12-06', '2017-12-31', 'Phố Đi Bộ Nguyện Huệ', 100, 10, 34, 'UIT', 'http://www.marry.vn/wp-content/uploads/2014/03/18/ban-muon-tro-thanh-co-gai-mo-uoc-cua-anh-ay-anh-minh-hoa-internet-tin8-51.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `truong`
--

CREATE TABLE `truong` (
  `MAT` varchar(30) NOT NULL,
  `TenTruong` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `truong`
--

INSERT INTO `truong` (`MAT`, `TenTruong`) VALUES
('UEL', 'Đại học Kinh Tế - Luật'),
('UIT', 'Trường Đại Học Công Nghệ Thông Tin');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `dangnhap`
--
ALTER TABLE `dangnhap`
  ADD PRIMARY KEY (`TaiKhoan`);

--
-- Chỉ mục cho bảng `hoatdongtinhnguyen`
--
ALTER TABLE `hoatdongtinhnguyen`
  ADD PRIMARY KEY (`MATN`,`MASV`),
  ADD KEY `MASV` (`MASV`);

--
-- Chỉ mục cho bảng `sinhvien`
--
ALTER TABLE `sinhvien`
  ADD PRIMARY KEY (`MASV`);

--
-- Chỉ mục cho bảng `tinhnguyen`
--
ALTER TABLE `tinhnguyen`
  ADD PRIMARY KEY (`MATN`),
  ADD KEY `fk_tn_t` (`MAT`);

--
-- Chỉ mục cho bảng `truong`
--
ALTER TABLE `truong`
  ADD PRIMARY KEY (`MAT`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `hoatdongtinhnguyen`
--
ALTER TABLE `hoatdongtinhnguyen`
  ADD CONSTRAINT `hoatdongtinhnguyen_ibfk_1` FOREIGN KEY (`MASV`) REFERENCES `sinhvien` (`MASV`),
  ADD CONSTRAINT `hoatdongtinhnguyen_ibfk_2` FOREIGN KEY (`MATN`) REFERENCES `tinhnguyen` (`MATN`);

--
-- Các ràng buộc cho bảng `tinhnguyen`
--
ALTER TABLE `tinhnguyen`
  ADD CONSTRAINT `fk_tn_t` FOREIGN KEY (`MAT`) REFERENCES `truong` (`MAT`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
