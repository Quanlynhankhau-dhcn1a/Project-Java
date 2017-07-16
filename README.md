<h1>
<a href="https://github.com/Quanlynhankhau-dhcn1a/QLNK-DHCN1A"><img alt="" src="https://www.vectorlogo.zone/logos/java/java-card.png" title="Quản lý nhân khẩu bằng Java"/></a>
</h1>

## Các phần mềm cần thiết &nbsp;
- [Xampp](https://www.apachefriends.org/index.html)
- [NetBeans IDE](https://netbeans.org/)
- [Java JDK](http://www.oracle.com/technetwork/java/javase/downloads/index-jsp-138363.html)
- [Giới thiệu về Ubuntu](https://github.com/Quanlynhankhau-dhcn1a/QLNK-DHCN1A/blob/master/Gioi%20thieu%20ubuntu.md)

## Cách upload từ ubuntu lên Github

**Cài đặt Git trên ubuntu**
```sh
$ Sudo apt-get install git
```

**Nhập Username và Email khi yêu cầu:**
```sh
$ Git config --global user.name "Username"
$ git config --global user.email "EmailUser"
```

**Clone kho dự án về Ubuntu:**
```sh
$ Git clone https://github.com/Quanlynhankhau-dhcn1a/QLNK-DHCN1A
# Sau khi thực hiện lệnh trên sẽ xuất hiện 1 thư mục có tên QLNK-DHCN1A trong home
# Tiến hành copy cái file thành viên thực hiện vào cây thư mục ./build/classes/gui
```

**Tiến hành upload:**
```sh
# Mở thư mục:
$ cd ~/QLNK-DHCN1A

# Tạo branch riêng để phát triển:
$ Git checkout -b Tên ( Tên theo tên thành viên )

# Tạo remote:
$ Git remote add origin master https://github.com/Quanlynhankhau-dhcn1a/QLNK-DHCN1A

# Check remote:
$ Git remote -v
# Nếu có 2 dòng Fetch và Push thì tiếp tục bước tiếp theo

# Thêm và Kiểm tra tệp:
$ Git add . ( Lưu ý phải có dấu chấm )
$ git status
# Check kỹ các file cần upload.

# Thêm Commits:
$ Git commit -m "tin nhắn " ( Phần tin nhắn là Decription của file khi upload lên Github )

# Gộp file vào nhánh Source chính:
$ Git checkout master && git merge Tên ( Lưu ý tên đã đặt ở phần  tạo branch )

# Upload file lên Github:
$ Git push origin master hoặc $ Git push -f origin master
```

## Tài liệu tham khảo online
- [NetBeans IDE Java Quick Start Tutorial](https://netbeans.org/kb/docs/java/quickstart.html) Khởi đầu với NetBeans IDE.
- [XAMPP Tutorial](https://blog.udemy.com/xampp-tutorial/) Cách cài đặt và sử dụng Xampp.
- [Java How To: Connect to XAMPP's MySQL in Eclipse](https://nodehead.com/java-how-to-connect-to-xampps-mysql-in-eclipse/) Cách kết nối CSDL với Java.


## Phân chia thực hiện
Nhóm phân công thực hiện từng phần khác nhau:
- Trần Lục Long Tính thực hiện kết nối toàn bộ [Chương trình chính](https://github.com/Quanlynhankhau-dhcn1a/QLNK-DHCN1A), [Form main](https://github.com/Quanlynhankhau-dhcn1a/QLNK-DHCN1A/tree/master/build/classes/gui) và [Form đăng nhập](https://github.com/Quanlynhankhau-dhcn1a/QLNK-DHCN1A/tree/master/build/classes/gui).
- Huỳnh Vân Nhật thực hiện các [Form quản lý](https://github.com/Quanlynhankhau-dhcn1a/QLNK-DHCN1A/tree/master/build/classes/gui) và [Quản lý cán bộ](https://github.com/Quanlynhankhau-dhcn1a/QLNK-DHCN1A/tree/master/build/classes/gui).
- Hoàng Công Kiên thực hiện các [Form thay đổi](https://github.com/Quanlynhankhau-dhcn1a/QLNK-DHCN1A/tree/master/build/classes/gui), [Form thống kê dân số](https://github.com/Quanlynhankhau-dhcn1a/QLNK-DHCN1A/tree/master/build/classes/gui) và [Form thống kê thời gian](https://github.com/Quanlynhankhau-dhcn1a/QLNK-DHCN1A/tree/master/build/classes/gui).
- Lê Hùng Phú thực hiệc các [Form tạm trú](https://github.com/Quanlynhankhau-dhcn1a/QLNK-DHCN1A/tree/master/build/classes/gui) và [Form tạm vắng](https://github.com/Quanlynhankhau-dhcn1a/QLNK-DHCN1A/tree/master/build/classes/gui).

Do trong quá trình upload các file cuối cùng có xảy ra lỗi nên đã ghi đè toàn bộ dữ liệu từ trước, nhóm phải thực hiện upload lại từng phần nên không còn số lượng commits.
## Video Demo
[![DeMo](https://scontent.fsgn2-2.fna.fbcdn.net/v/t35.0-12/20182501_1776692762347715_146635551_o.png?oh=c2f8e6d04c88be4839eaeb3d73f0fc44&oe=596DAC9A)](https://www.youtube.com/watch?v=EiGLOiibU3U)
## Tài Liệu Thiết Kế 
[Tài Liệu Cho Thiết Kế](https://github.com/Quanlynhankhau-dhcn1a/QLNK-DHCN1A/blob/master/TaiLieuThietKe.md)
## Nhóm
Danh sách nhóm sinh viên thực hiện:

[![Trần Lục Long Tính](https://scontent.fsgn2-2.fna.fbcdn.net/v/t34.0-12/20134438_1307982839301077_214408582_n.png?oh=1cc59a8747489e0e8064554f82c290fe&oe=596D4EC8)](https://www.facebook.com/tinh.dk) |  [![Hoàng Công Kiên](https://scontent.fsgn2-2.fna.fbcdn.net/v/t34.0-12/20134489_1307983665967661_1170340642_n.png?oh=a3b4add24ab2b13014fe5a38a40f2b2e&oe=596D4C0A)](https://www.facebook.com/hck1996)| [![Huỳnh Vân Nhật](https://scontent.fsgn2-2.fna.fbcdn.net/v/t34.0-12/20158127_1307988119300549_1205138497_n.png?oh=05242942415bac4262f4c753c859310e&oe=596D3807)](https://www.facebook.com/hvn96) | [![Lê Hùng Phú](https://scontent.fsgn2-2.fna.fbcdn.net/v/t34.0-12/20067846_1307985349300826_588960186_n.png?oh=3044000b758ba118cf5be36feb5ee44a&oe=596C431A)](https://www.facebook.com/lynklee.le)
:---:|:---:|:---:|:---:
[Trần Lục Long Tính](https://github.com/tinhdk1) | [Hoàng Công Kiên](https://github.com/deepink2) | [Huỳnh Vân Nhật](https://github.com/huynhvannhat) | [Lê Hùng Phú](https://github.com/lehungphu)

## Kênh Truyền Thông 
Facebook: [Quản Lý Nhân Khẩu](https://www.facebook.com/quanlynhankhau/)
## Giấy phép

[MIT License](https://vi.wikipedia.org/wiki/Gi%E1%BA%A5y_ph%C3%A9p_MIT)  Copyright © 2012-2017 Mike McNeil, Balderdash Design Co., & The Sails Company
