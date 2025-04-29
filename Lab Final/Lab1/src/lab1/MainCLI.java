package lab1;

import java.util.Scanner;
import lab1.model.CanBo;
import lab1.model.CongNhan;
import lab1.model.KySu;
import lab1.model.NhanVien;

public class MainCLI {
    public static void main(String[] args) {
        QLCB qlcb = new QLCB();
        Scanner scanner = new Scanner(System.in);
        String filePath = "danhSachCanBo.txt";

        while (true) {
            System.out.println("\n===== Quản Lý Cán Bộ =====");
            System.out.println("1. Thêm mới cán bộ");
            System.out.println("2. Tìm kiếm cán bộ theo tên");
            System.out.println("3. Xoá cán bộ theo tên");
            System.out.println("4. Hiển thị danh sách cán bộ");
            System.out.println("5. Lưu danh sách vào file");
            System.out.println("6. Tải danh sách từ file");
            System.out.println("7. Thoát");
            System.out.print("Chọn chức năng: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    themMoiCanBo(qlcb, scanner);
                    break;
                case 2:
                    timKiemCanBo(qlcb, scanner);
                    break;
                case 3:
                    xoaCanBo(qlcb, scanner);
                    break;
                case 4:
                    qlcb.hienThiDanhSachCanBo();
                    break;
                case 5:
                    qlcb.luuDanhSachVaoFile(filePath);
                    break;
                case 6:
                    qlcb.taiDanhSachTuFile(filePath);
                    break;
                case 7:
                    System.out.println("Thoát chương trình. Tạm biệt!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại!");
            }
        }
    }

    private static void themMoiCanBo(QLCB qlcb, Scanner scanner) {
        System.out.println("\nChọn loại cán bộ muốn thêm:");
        System.out.println("1. Công nhân");
        System.out.println("2. Kỹ sư");
        System.out.println("3. Nhân viên");
        System.out.print("Lựa chọn: ");
        int loai = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        System.out.print("Nhập họ tên: ");
        String hoTen = scanner.nextLine();
        System.out.print("Nhập tuổi: ");
        int tuoi = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        System.out.print("Nhập giới tính (Nam/Nữ/Khác): ");
        String gioiTinh = scanner.nextLine();
        System.out.print("Nhập địa chỉ: ");
        String diaChi = scanner.nextLine();

        switch (loai) {
            case 1:
                System.out.print("Nhập bậc (1-10): ");
                int bac = scanner.nextInt();
                qlcb.themMoiCanBo(new CongNhan(hoTen, tuoi, gioiTinh, diaChi, bac));
                break;
            case 2:
                System.out.print("Nhập ngành đào tạo: ");
                String nganhDaoTao = scanner.nextLine();
                qlcb.themMoiCanBo(new KySu(hoTen, tuoi, gioiTinh, diaChi, nganhDaoTao));
                break;
            case 3:
                System.out.print("Nhập công việc: ");
                String congViec = scanner.nextLine();
                qlcb.themMoiCanBo(new NhanVien(hoTen, tuoi, gioiTinh, diaChi, congViec));
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ. Không thêm cán bộ.");
        }
    }

    private static void timKiemCanBo(QLCB qlcb, Scanner scanner) {
        System.out.print("\nNhập tên cán bộ cần tìm: ");
        String hoTen = scanner.nextLine();
        CanBo canBo = qlcb.timKiemCanBo(hoTen);
        if (canBo == null) {
            System.out.println("Không tìm thấy cán bộ");
        } else {
            System.out.println(canBo.toString());
        }
    }

    private static void xoaCanBo(QLCB qlcb, Scanner scanner) {
        System.out.print("\nNhập tên cán bộ cần xoá: ");
        String hoTen = scanner.nextLine();
        if (qlcb.xoaCanBo(hoTen)) {
            System.out.println("Xóa Cán Bộ Thành Công");
        } else {
            System.out.println("Xóa Cán Bộ Thất Bại");
        }
    }
}
