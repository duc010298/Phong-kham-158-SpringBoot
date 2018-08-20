DROP DATABASE Clinic;
CREATE DATABASE Clinic;
USE Clinic;
CREATE TABLE Customer (
	Id INT PRIMARY KEY AUTO_INCREMENT,
	Name VARCHAR(250),
	YOB INT,
	AddressCus VARCHAR(250),
	DayVisit DATE,
	ExpectedDOB DATE,
	Result VARCHAR(250),
	Note VARCHAR(250),
	Report MEDIUMTEXT
);
CREATE TABLE Customer_Hidden (
  Id INT PRIMARY KEY AUTO_INCREMENT,
  Name VARCHAR(250),
	YOB INT,
	AddressCus VARCHAR(250),
	DayVisit DATE,
	Result VARCHAR(250),
	Report MEDIUMTEXT
);
CREATE TABLE UltraSoundResult
(
	ID INT PRIMARY KEY NOT NULL,
	Name VARCHAR(100) NOT NULL,
	OrderNumber INT NOT NULL,
	FormID INT NOT NULL
);
INSERT INTO UltraSoundResult
VALUES
	(1, 'Thai dưới 12T', 1, 1);
INSERT INTO UltraSoundResult
VALUES
	(2, 'Thai dưới 12T - Dọa sảy', 2, 1);
INSERT INTO UltraSoundResult
VALUES
	(3, 'Thai 12T - 14T', 3, 2);
INSERT INTO UltraSoundResult
VALUES
	(4, 'Thai 12T - 14T - Dọa sảy', 4, 2);
INSERT INTO UltraSoundResult
VALUES
	(5, 'Thai 15T - 34T', 5, 3);
INSERT INTO UltraSoundResult
VALUES
	(6, 'Thai 34T - 40T', 6, 3);
INSERT INTO UltraSoundResult
VALUES
	(7, 'Túi ối', 7, 1);
INSERT INTO UltraSoundResult
VALUES
	(8, 'Túi ối - Dọa sảy', 8, 1);
INSERT INTO UltraSoundResult
VALUES
	(9, 'Siêu âm ổ bụng 4D', 9, 4);
INSERT INTO UltraSoundResult
VALUES
	(10, 'Ổ bụng vú', 10, 4);
INSERT INTO UltraSoundResult
VALUES
	(11, 'Vú', 11, 5);
CREATE TABLE UltraSoundResult_Content
(
	OrderNumber INT,
	Class1 VARCHAR(250),
	Class2 VARCHAR(250),
	UltraSoundResultId INT,
	PRIMARY KEY(OrderNumber, UltraSoundResultId),
	FOREIGN KEY (UltraSoundResultId) REFERENCES UltraSoundResult(ID)
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		1,
		'tình trạng thai',
		NULL,
		1
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		2,
		'- Tử cung:',
		'Buồng tử cung có 01 thai',
		1
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		3,
		'- Cử động thai:',
		'(+)',
		1
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		4,
		'- Tim thai:',
		' lần/phút',
		1
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		5,
		'- Rau thai:',
		'Bình thường',
		1
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		6,
		'- Túi ối:',
		'Bình thường',
		1
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		7,
		'- Chiều dài đầu mông:',
		' mm',
		1
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		8,
		'- Đường kính lưỡng đỉnh:',
		NULL,
		1
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		9,
		'- Tuổi thai:',
		' tuần',
		1
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		10,
		'Kết luận:',
		'Thai  tuần ',
		1
);
--
INSERT INTO UltraSoundResult_Content
VALUES
	(
		1,
		'tình trạng thai',
		'01',
		2
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		2,
		'- Số lượng thai:',
		'01',
		2
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		3,
		'- Cử động thai:',
		'(+)',
		2
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		4,
		'- Tim thai:',
		'  lần/phút',
		2
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		5,
		'- Rau thai:',
		'bình thường',
		2
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		6,
		'- Túi ối:',
		'bờ túi ối không đều, có hiện tượng bóc tách',
		2
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		7,
		'- Chiều dài đầu mông:',
		' mm',
		2
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		8,
		'- Đường kính lưỡng đỉnh:',
		NULL,
		2
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		9,
		'- Tuổi thai:',
		' tuần',
		2
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		10,
		'- Phần phụ phải:',
		'bình thường',
		2
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		11,
		'- Phần phụ trái:',
		'bình thường',
		2
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		12,
		'Kết luận:',
		'Thai  tuần - Doạ sảy',
		2
);
--
INSERT INTO UltraSoundResult_Content
VALUES
	(
		1,
		NULL,
		'01',
		7
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		2,
		'- Tử cung:',
		'có 01 túi ối',
		7
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		3,
		'- Đường kính túi ối:',
		' mm',
		7
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		4,
		'- Bờ túi ối:',
		'bình thường',
		7
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		5,
		'- Nước ối:',
		'bình thường',
		7
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		6,
		'- Phôi thai:',
		NULL,
		7
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		7,
		'- Tim thai:',
		NULL,
		7
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		8,
		'- Tương đương thai:',
		' tuần',
		7
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		9,
		'- Phần phụ phải:',
		'bình thường',
		7
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		10,
		'- Phần phụ trái:',
		'bình thường',
		7
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		11,
		'Kết luận:',
		'Thai  tuần',
		7
);
--
INSERT INTO UltraSoundResult_Content
VALUES
	(
		1,
		NULL,
		'có 01 túi ối',
		8
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		2,
		'- Tử cung:',
		'có 01 túi ối',
		8
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		3,
		'- Đường kính túi ối:',
		' mm',
		8
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		4,
		'- Bờ túi ối:',
		'không đều có hiện tượng bóc tách',
		8
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		5,
		'- Nước ối:',
		'bình thường',
		8
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		6,
		'- Phôi thai:',
		NULL,
		8
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		7,
		'- Tim thai ',
		NULL,
		8
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		8,
		'- Tương đương thai:',
		' tuần',
		8
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		9,
		'- Phần phụ phải:',
		'bình thường',
		8
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		10,
		'- Phần phụ trái:',
		'bình thường',
		8
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		11,
		'Kết luận:',
		'Thai  tuần - Doạ sảy',
		8
);
--
INSERT INTO UltraSoundResult_Content
VALUES
	(
		1,
		'Kiểm tra tình trạng thai',
		NULL,
		3
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		2,
		'- Số lượng thai:',
		'01',
		3
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		3,
		'- ĐK lưỡng đỉnh:',
		' mm',
		3
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		4,
		'- Chiều dài đầu mông:',
		' mm',
		3
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		5,
		'- Vị trí rau:',
		'rộng',
		3
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		6,
		'- Cử động thai:',
		'Bình thường',
		3
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		7,
		'Bờ hộp sọ:',
		NULL,
		3
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		8,
		'- Cấu trúc đường giữa(vách trong suốt):',
		'Bình thường',
		3
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		9,
		'- Hình ảnh siêu âm tiểu não:',
		'Bình thường',
		3
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		10,
		'- Não thất 2 bên:',
		'Bình thường',
		3
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		11,
		'- Kích thước hố sau:',
		'Bình thường',
		3
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		12,
		'- Cấu trúc tim:',
		'4 buồng',
		3
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		13,
		'- Dây rốn(3 mạch máu)',
		NULL,
		3
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		14,
		'- Nhịp tim thai:',
		' lần/phút',
		3
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		15,
		'- Hình ảnh dạ dày:',
		'có',
		3
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		16,
		'- Dày da gáy:',
		'1.8mm',
		3
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		17,
		'- Bàng quang:',
		'Bình thường',
		3
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		18,
		'- Tình trạng ối:',
		'Bình thường',
		3
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		19,
		'- Cột sống:',
		'Bình thường',
		3
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		20,
		'- Dự kiến ngày sinh:',
		' (+,- 7 ngày)',
		3
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		21,
		'- Kết luận:',
		'01 thai, Thai   tuần, cử động thai bình thường',
		3
);
--
INSERT INTO UltraSoundResult_Content
VALUES
	(
		1,
		'Kiểm tra tình trạng thai',
		NULL,
		4
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		2,
		'- Số lượng thai:',
		'01',
		4
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		4,
		'- ĐK lưỡng đỉnh:',
		' mm',
		4
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		5,
		'- Chiều dài đầu mông:',
		' mm',
		4
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		6,
		'- Vị trí rau:',
		'Rau bám rộng, bờ ối có hiện tượng bóc tách',
		4
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		7,
		'- Cử động thai:',
		'Tốt',
		4
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		8,
		'Bờ hộp sọ:',
		NULL,
		4
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		9,
		'- Cấu trúc đường giữa(vách trong suốt):',
		'Bình thường',
		4
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		10,
		'- Hình ảnh siêu âm tiểu não:',
		'Bình thường',
		4
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		11,
		'- Não thất 2 bên:',
		'Bình thường',
		4
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		12,
		'- Kích thước hố sau:',
		'Bình thường',
		4
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		13,
		'- Nhịp tim thai:',
		' lần/phút',
		4
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		14,
		'- Cột sống:',
		'Bình thường',
		4
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		15,
		'- Dầy da gáy:',
		'2.0mm',
		4
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		16,
		'- Tình trạng ối:',
		'có',
		4
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		17,
		'- Dự kiến ngày sinh:',
		' (+,- 7 ngày)',
		4
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		18,
		'- Kết luận:',
		'01 thai, Thai  tuần  ngày - Doạ sảy',
		4
);
--
INSERT INTO UltraSoundResult_Content
VALUES
	(
		1,
		'Kiểm tra tình trạng thai',
		NULL,
		5
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		2,
		'- Số lượng thai:',
		'01',
		5
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		3,
		'- ĐK lưỡng đỉnh:',
		' mm',
		5
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		4,
		'- Chiều dài xương đùi:',
		' mm',
		5
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		5,
		'- Đường kình ngang bụng:',
		' mm',
		5
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		6,
		'- Đường kính trước sau:',
		' mm',
		5
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		7,
		'- Trọng lượng thai:',
		' g',
		5
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		8,
		'- Vị trí rau:',
		'Bình thường',
		5
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		9,
		'- Cử động thai:',
		'Bình thường',
		5
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		10,
		'Bờ hộp sọ:',
		NULL,
		5
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		11,
		'- Cấu trúc đường giữa(vách trong suốt):',
		'Bình thường',
		5
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		12,
		'- Hình ảnh siêu âm tiểu não:',
		'Bình thường',
		5
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		13,
		'- Não thất 2 bên:',
		'Bình thường',
		5
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		14,
		'- Kích thước hố sau:',
		'Bình thường',
		5
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		15,
		'- Hai thận:',
		'Bình thường',
		5
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		16,
		'- Cấu trúc tim:',
		'4 buồng',
		5
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		17,
		'- Dây rốn(3 mạch máu)',
		NULL,
		5
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		18,
		'- Nhịp tim thai:',
		' lần/phút',
		5
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		19,
		'- Hình ảnh dạ dày:',
		'có',
		5
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		20,
		'- Bàng quang:',
		'Bình thường',
		5
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		21,
		'- Tình trạng ối:',
		'Bình thường',
		5
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		22,
		'- Cột sống:',
		'Bình thường',
		5
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		23,
		'- Dự kiến ngày sinh:',
		' (+;- 7 ngày)',
		5
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		24,
		'- Kết luận:',
		'01 thai, Thai  tuần  ngày. Cử động thai bình thường.',
		5
);
--
INSERT INTO UltraSoundResult_Content
VALUES
	(
		1,
		'Kiểm tra tình trạng thai',
		NULL,
		6
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		2,
		'- Số lượng thai:',
		'01',
		6
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		3,
		'- ĐK lưỡng đỉnh:',
		' mm',
		6
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		4,
		'- Chiều dài xương đùi:',
		' mm',
		6
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		5,
		'- Đường kình ngang bụng:',
		' mm',
		6
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		6,
		'- Đường kính trước sau:',
		' mm',
		6
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		7,
		'- Trọng lượng thai:',
		' g (+_200g)',
		6
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		8,
		'- Vị trí rau:',
		'Bình thường',
		6
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		9,
		'- Cử động thai:',
		'Bình thường',
		6
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		10,
		'Bờ hộp sọ:',
		NULL,
		6
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		11,
		'- Cấu trúc đường giữa(vách trong suốt):',
		'Bình thường',
		6
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		12,
		'- Hình ảnh siêu âm tiểu não:',
		'Bình thường',
		6
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		13,
		'- Não thất 2 bên:',
		'Bình thường',
		6
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		14,
		'- Kích thước hố sau:',
		'Bình thường',
		6
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		15,
		'- Hai thận:',
		'Bình thường',
		6
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		16,
		'- Cấu trúc tim:',
		'4 buồng',
		6
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		17,
		'- Dây rốn(3 mạch máu)',
		NULL,
		6
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		18,
		'- Nhịp tim thai:',
		' lần/phút',
		6
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		19,
		'- Hình ảnh dạ dày:',
		'có',
		6
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		20,
		'- Ngôi thai:',
		'Đầu',
		6
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		21,
		'- Bàng quang:',
		'Bình thường',
		6
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		22,
		'- Tình trạng ối:',
		'Bình thường',
		6
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		23,
		'- Cột sống:',
		'Bình thường',
		6
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		24,
		'- Dự kiến ngày sinh:',
		' (+;- 7 ngày )',
		6
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		25,
		'- Kết luận:',
		'01 thai, Thai  tuần  ngày. Cử động thai bình thường.',
		6
);
--
INSERT INTO UltraSoundResult_Content
VALUES
	(
		1,
		NULL,
		NULL,
		9
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		2,
		'Gan',
		'-  Không to, nhu mô gan đều, không có hình khối khu trú bất thường.',
		9
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		3,
		NULL,
		'-  Tĩnh mạch cửa không giãn',
		9
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		4,
		'Mật',
		'- Túi mật không căng, thành mỏng, không có sỏi',
		9
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		5,
		NULL,
		'-  Đường mật trong gan và ống mật chủ không giãn, không có sỏi',
		9
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		6,
		'Thận',
		'- Thận phải: Kích thước bình thường, nhu mô đều, đài bể thận không giãn, không có sỏi',
		9
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		7,
		NULL,
		'- Thận trái: Kích thước bình thường, nhu mô đều, đài bể thận không giãn, không có sỏi',
		9
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		8,
		NULL,
		'-  Niệu quản hai bên bình thường',
		9
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		9,
		'Tử cung phần phụ',
		'- Tử cung kích bình thường, đường lòng tử cung rõ niêm mạc mỏng, chưa thấy khối bất thường',
		9
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		10,
		NULL,
		'- Phần phụ trái: bình thường',
		9
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		11,
		NULL,
		'- Phần phụ phải: bình thường',
		9
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		12,
		'Kết luận',
		'Hiện tại gan, mật, thận, tử cung, phần phụ, bình thường',
		9
);
--
INSERT INTO UltraSoundResult_Content
VALUES
	(
		1,
		NULL,
		NULL,
		10
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		2,
		'Gan',
		'-  Không to, nhu mô gan đều, không có hình khối khu trú bất thường.',
		10
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		3,
		NULL,
		'-  Tĩnh mạch cửa không giãn',
		10
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		4,
		'Mật',
		'- Túi mật không căng, thành mỏng, không có sỏi',
		10
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		5,
		NULL,
		'-  Đường mật trong gan và ống mât chủ không giãn, không có sỏi',
		10
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		6,
		'Thận',
		'- Thận phải: Kích thước bình thường, nhu mô đều, đài bể thận không giãn, không có sỏi',
		10
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		7,
		NULL,
		'- Thận trái: Kích thước bình thường, nhu mô đều, đài bể thận không giãn, không có sỏi',
		10
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		8,
		NULL,
		'-  Niệu quản hai bên bình thường',
		10
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		9,
		'Tử cung phần phụ',
		'- Tử cung kích thước bình thường, đường lòng tử cung rõ niêm mạc mỏng, chưa thấy khối bất thường',
		10
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		10,
		NULL,
		'- Phần phụ trái: bình thường',
		10
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		11,
		NULL,
		'- Phần phụ phải: bình thường',
		10
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		12,
		'Vú',
		'- Vú trái: không thấy khối u khu trú',
		10
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		13,
		NULL,
		'- Vú phải: không thấy khối u khu trú',
		10
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		14,
		'Kết luận',
		'Hiện tại gan, mật, thận, tử cung, phần phụ, vú bình thường',
		10
);
--
INSERT INTO UltraSoundResult_Content
VALUES
	(
		1,
		NULL,
		NULL,
		11
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		2,
		'1. Vú phải',
		'Nhu mô tuyến vú bình thường',
		11
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		3,
		NULL,
		'Mật độ tuyến vú bình thường',
		11
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		4,
		NULL,
		'Không có hình ảnh nhân xơ và nang tuyến vú',
		11
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		5,
		NULL,
		'Núm vú bình thường',
		11
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		6,
		NULL,
		NULL,
		11
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		7,
		'2. Vú trái',
		'Nhu mô tuyến vú bình thường',
		11
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		8,
		NULL,
		'Mật độ tuyến vú bình thường',
		11
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		9,
		NULL,
		'Không có hình ảnh nhân xơ và nang tuyến vú',
		11
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		10,
		NULL,
		'Núm vú bình thường',
		11
);
INSERT INTO UltraSoundResult_Content
VALUES
	(
		11,
		'Kết luận',
		'Hiện tại 2 vú chưa thấy hình ảnh bất thường',
		11
);
--