create database student_management;
use student_management;

create table khoa (
    ma_khoa varchar(10) primary key,
    ten_khoa varchar(100) not null
);

create table sinh_vien (
    ma_sinh_vien varchar(10) primary key,
    ho_ten varchar(100) not null,
    ma_khoa varchar(10) not null,
    foreign key (ma_khoa) references khoa(ma_khoa)
);

insert into khoa(ma_khoa, ten_khoa) VALUES ('KHMT', 'Khoa Học Máy Tính');
insert into khoa(ma_khoa, ten_khoa) VALUES ('KTMT', 'Kỹ Thuật Máy Tính');
insert into khoa(ma_khoa, ten_khoa) VALUES ('KTS&TMDT', 'Kinh Tế Số & Thương Mại Điện Tử');

delimiter //

# Thêm Sinh Viên
# Sửa sinh viên
# Xóa sinh viên
# Tìm kiếm sinh viên theo mã
# Lấy tất cả sinh viên
# lấy tất cả khoa

create procedure GetAllStudents()
begin
    select s.ma_sinh_vien, s.ho_ten, s.ma_khoa, k.ten_khoa
    from sinh_vien s
    join khoa k on k.ma_khoa = s.ma_khoa;
end //

create procedure GetStudentByID(
    in ma_sinh_vien varchar(10)
)
begin
    select s.ma_sinh_vien, s.ho_ten, s.ma_khoa, k.ten_khoa
    from sinh_vien s
    join khoa k on k.ma_khoa = s.ma_khoa
    where s.ma_sinh_vien = ma_sinh_vien;
end //

create procedure AddStudent(
    in p_ma_sinh_vien varchar(10),
    in p_hoten varchar(50),
    in p_ma_khoa varchar(10)
)
begin
    insert into sinh_vien (ma_sinh_vien, ho_ten, ma_khoa)
    values (p_ma_sinh_vien, p_hoten, p_ma_khoa) ;
end //

create procedure UpdateStudent(
    in p_ma_sinh_vien varchar(10),
    in p_hoten varchar(50),
    in p_ma_khoa varchar(10)
)
begin
    update sinh_vien
    set ho_ten = p_hoten, ma_khoa = p_ma_khoa
    where ma_sinh_vien = p_ma_sinh_vien;
end //

create procedure DeleteStudent (
    in p_ma_sinh_vien varchar(10)
)
begin
    delete from sinh_vien
    where ma_sinh_vien = p_ma_sinh_vien;
end //

create procedure SearchStudentsByID(
    in p_ma_sinh_vien varchar(10)
)
begin
    select s.ma_sinh_vien, s.ho_ten, s.ma_khoa, k.ten_khoa
    from sinh_vien s
    join khoa k on k.ma_khoa = s.ma_khoa
    where s.ma_sinh_vien like CONCAT('%', p_ma_sinh_vien, '%');
end //

create procedure GetAllDepartments()
begin
    select * from khoa;
end //

create procedure GetDepartmentById(
    in p_ma_khoa varchar(10)
)
begin
    select * from khoa
    where ma_khoa = p_ma_khoa;
end //

create procedure AddDepartment(
    in p_ma_khoa varchar(10),
    in p_ten_khoa varchar(100)
)
begin
    insert into khoa (ma_khoa, ten_khoa) VALUES (p_ma_khoa, p_ten_khoa);
end //

create procedure UpdateDepartment(
    in p_ma_khoa varchar(10),
    in p_ten_khoa varchar(100)
)
begin
    update khoa
    set ten_khoa = p_ten_khoa
    where ma_khoa = p_ma_khoa;
end //

create procedure DeleteDepartment(
    in p_ma_khoa varchar(10)
)
begin
    delete from khoa
    where ma_khoa = p_ma_khoa;
end //

delimiter ;

call GetAllStudents();