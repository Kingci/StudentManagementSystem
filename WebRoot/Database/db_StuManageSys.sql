use db_stumanagesys;

Drop table if exists tb_account;
create table tb_account (
	id int not null ,
	aname nvarchar(30) not null,
	pwd nvarchar(30) not null,
	atype nvarchar(30) not null,
	primary key(id)
) engine = MyISAM default charset = gbk;

insert into tb_account(id, aname, pwd, atype) values 
(1, '1010001', '123', 'Student'),
(2, '1010101', '123', 'Student'),
(3, '100', '123', 'Acdemic Dean'),
(4, '101', '123', 'Acdemic Dean'),
(5, 'admin', 'admin', 'Administrator');


Drop table if exists tb_student;
create table tb_student (
	stu_id bigint not null,
	stu_num nvarchar(50) ,
	name_ch nvarchar(50) not null,
	name_en nvarchar(50),
	birth_time nvarchar(255),
	gender nvarchar(1) not null,
	college_num nvarchar(255) not null,
	major_num nvarchar(100) not null,
	sch_length nvarchar(20),
	id_num nvarchar(50) not null,
	entr_time nvarchar(50) not null,
	stu_status nvarchar(50) not null,
	gradu_sch nvarchar(100),
	email nvarchar(100),
	telephone nvarchar(100),
	home_addr nvarchar(255),
	pos_code nvarchar(20),
	citizenship nvarchar(255) not null,
	nation nvarchar(50)
)engine = MyISAM default charset = gbk;

insert into tb_student(stu_id, stu_num,name_ch,birth_time,gender,college_num,major_num,sch_length,id_num,entr_time,stu_status,citizenship,nation,telephone,home_addr,name_en,gradu_sch,email,pos_code) values 
(1, '1010001', '王刚','1993年2月1日','男', '计算机科学与技术系', '计算机科学与技术','四年','41001','2010', '有','中国','汉族','10086','南京大学','James', '南师大附中','baidu@163.com','210023'),
(2, '1010002', '李刚','1992年5月1日','男', '计算机科学与技术系', '计算机科学与技术','四年','41002','2010', '有','中国','汉族','10086','南京大学','Ang Lee','南京外国语中学','baidu@163.com','210023'),
(3, '1010003', '王五','1992年6月6日','女', '计算机科学与技术系', '计算机科学与技术','四年','41003','2010', '有','中国','汉族','10086','南京大学','Kobe','南大附中','baidu@163.com','210023'),
(4, '1010004', '赵刚','1992年6月6日','女', '计算机科学与技术系', '计算机科学与技术','四年','41003','2011', '有','中国','汉族','10086','南京大学','Kobe','南大附中','baidu@163.com','210023'),
(5, '1010005', '李安','1992年6月6日','女', '计算机科学与技术系', '计算机科学与技术','四年','41003','2011', '有','中国','汉族','10086','南京大学','Kobe','南大附中','baidu@163.com','210023'),
(6, '1010006', '李丹','1992年6月6日','女', '计算机科学与技术系', '计算机科学与技术','四年','41003','2012', '有','中国','汉族','10086','南京大学','Kobe','南大附中','baidu@163.com','210023'),
(7, '1010007', '王强','1992年6月6日','女', '计算机科学与技术系', '计算机科学与技术','四年','41003','2012', '有','中国','汉族','10086','南京大学','Kobe','南大附中','baidu@163.com','210023'),
(8, '1010101', '张红','1993年2月1日','女', '数学系', '统计学','四年','41001','2010', '有','中国','汉族','10086','南京大学','James', '南师大附中','baidu@163.com','210023'),
(9, '1010102', '赵刚','1992年5月1日','男', '数学系', '金融学','四年','41002','2011', '有','中国','汉族','10086','南京大学','Ang Lee','南京外国语中学','baidu@163.com','210023'),
(10, '1010103', '王强','1992年6月6日','男', '数学系', '理论学','五四年','41003','2012', '有','中国','汉族','10086','南京大学','Kobe','南大附中','baidu@163.com','210023'),
(11, '1010104', '李强','1993年2月1日','女', '数学系', '统计学','四年','41001','2010', '有','中国','汉族','10086','南京大学','James', '南师大附中','baidu@163.com','210023'),
(12, '1010105', '赵刚','1992年5月1日','男', '数学系', '金融学','四年','41002','2011', '有','中国','汉族','10086','南京大学','Ang Lee','南京外国语中学','baidu@163.com','210023'),
(13, '1010106', '周进','1992年6月6日','男', '数学系', '理论学','五四年','41003','2012', '有','中国','汉族','10086','南京大学','Kobe','南大附中','baidu@163.com','210023');

drop table if exists tb_course_info;
create table tb_course_info (
	course_id int primary key not null ,
	course_name_chs nvarchar(255) not null,
	course_name_egh nvarchar(255),
	credit float not null,
	week_hour int not null,
	semester nvarchar(50) not null,
	teach_mode nvarchar(50),
	college_id int not null,
	course_year nvarchar(50) not null
) ;

insert into tb_course_info (course_id, course_name_chs, course_name_egh,
							credit, week_hour, semester, teach_mode, college_id,
							course_year) values
(1,'程序设计基础','',4,4,'上学期','理论课',100,'2010-2011'),
(2,'计算机系统概论','',3,3,'下学期','平台课',100,'2010-2011'),
(3,'数字逻辑电路','',2,4,'上学期','平台课',100,'2011-2012'),
(4,'计算机组成原理','',3,4,'下学期','核心课',100,'2011-2012'),
(5,'中国近代史','',2,3,'上学期','通识课',100,'2012-2013'),

(6,'微积分I','',5,6,'上学期','理论课',101,'2010-2011'),
(7,'微积分II','',5,6,'下学期','平台课',101,'2010-2011'),
(8,'线性代数','',4,4,'上学期','平台课',101,'2011-2012'),
(9,'计算方法','',3,4,'下学期','核心课',101,'2011-2012'),
(10,'概率论与数理统计','',3,3,'上学期','通识课',101,'2012-2013');

drop table if exists tb_student_course_info;
create table tb_student_course_info (
	student_course_id int primary key not null auto_increment,
	student_id int not null,
	course_id int not null,
	score float
);

drop table if exists tb_course_eval_info;
create table tb_course_eval_info (
	student_id int not null,
	course_id int ,
	course_name_chs nvarchar(255) not null,
	course_name_egh nvarchar(255),
	semester nvarchar(50) not null,
	course_year nvarchar(50) not null,
    attitude nvarchar(50) not null,
	primary key(student_id,course_name_chs)
) ;

insert into tb_student_course_info (student_course_id, student_id, course_id, score) values
(1, 10100, 1, 90.0),
(2, 10101, 1, 92.0),
(3, 10100, 2, 80.0),
(4, 10101, 2, 60.0),
(5, 10100, 3, 59.0),
(6, 10101, 3, 90.0),
(7, 10100, 6, 80.0),
(8, 10105, 10, 80.0);


drop table if exists tb_college_info;
create table tb_college_info (
	college_id int primary key not null,
	college_name nvarchar(255) not null,
	sci_arts nvarchar(10),
	college_en nvarchar(255)
);

insert into tb_college_info (college_id, college_name) values
(100,'计算机科学与技术系'),
(101,'数学系');


Drop table if exists decForm;
create table decForm (
	proj_id bigint not null,
	proj_name nvarchar(50) not null,
	outlay nvarchar(255) not null,
	starting_time nvarchar(255) not null,
	terminal_time nvarchar(255) not null,
	stu_name1 nvarchar(255) not null,
	stu_grade1 nvarchar(255) not null,
	stu_major1 nvarchar(255) not null,
	stu_id1 bigint not null,
	stu_phonenum1 nvarchar(255) not null,
	stu_email1 nvarchar(255) not null,
	stu_name2 nvarchar(255),
	stu_grade2 nvarchar(255),
	stu_major2 nvarchar(255),
	stu_id2 bigint,
	stu_phonenum2 nvarchar(255),
	stu_email2 nvarchar(255),
	stu_name3 nvarchar(255),
	stu_grade3 nvarchar(255),
	stu_major3 nvarchar(255),
	stu_id3 bigint,
	stu_phonenum3 nvarchar(255),
	stu_email3 nvarchar(255),
	stu_name4 nvarchar(255),
	stu_grade4 nvarchar(255),
	stu_major4 nvarchar(255),
	stu_id4 bigint,
	stu_phonenum4 nvarchar(255),
	stu_email4 nvarchar(255),
	teacher_name nvarchar(255) not null,
	teacher_age nvarchar(255) not null,
	teacher_duties nvarchar(255) not null,
	teacher_phonenum nvarchar(255) not null,
	teacher_email nvarchar(255) not null,
	research_area nvarchar(255) not null,
	brief_intro nvarchar(255) not null,
	proj_alternatives nvarchar(255) not null
)engine = MyISAM default charset = gbk;

Drop table if exists ProjForm;
create table ProjForm (
	Selected bool not null,
	proj_name nvarchar(50) not null,
	teacher_name nvarchar(255) not null,
	brief_intro nvarchar(255) not null
)engine = MyISAM default charset = gbk;

insert into ProjForm (Selected, proj_name, teacher_name, brief_intro) values
(true,'proj1','teacher1','intro1'),
(false,'proj2','teacher2','intro2');

Drop table if exists tb_choose_course_time;
create table tb_choose_course_time (
	college_id int primary key not null,
	begin_time nvarchar(255) not null,
	end_time nvarchar(255) not null,
	state bool not null
);

Drop table if exists tb_eval_course_time;
create table tb_eval_course_time (
	college_id int primary key not null,
	begin_time nvarchar(255) not null,
	end_time nvarchar(255) not null,
	state bool not null
);

drop table if exists tb_notification;
create table tb_notification(
	notification_id int primary key not null auto_increment,
	author_id nvarchar(50) not null,
	college_id int not null,
	title nvarchar(50) not null,
	content nvarchar(300) not null
);
