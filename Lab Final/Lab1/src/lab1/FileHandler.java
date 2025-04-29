package lab1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lab1.model.CanBo;
import lab1.model.CongNhan;
import lab1.model.KySu;
import lab1.model.NhanVien;

public class FileHandler {
    public void ghiDanhSachCanBo(List<CanBo> danhSach, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (CanBo canBo : danhSach) {
                String line;
                if (canBo instanceof CongNhan) {
                    CongNhan congNhan = (CongNhan) canBo;
                    line = "CongNhan|" + congNhan.getHoTen() + "|" + congNhan.getTuoi() + "|" 
                           + congNhan.getGioiTinh() + "|" + congNhan.getDiaChi() + "|" 
                           + congNhan.getBac();
                } else if (canBo instanceof KySu) {
                    KySu kySu = (KySu) canBo;
                    line = "KySu|" + kySu.getHoTen() + "|" + kySu.getTuoi() + "|" 
                           + kySu.getGioiTinh() + "|" + kySu.getDiaChi() + "|" 
                           + kySu.getNganhDaoTao();
                } else if (canBo instanceof NhanVien) {
                    NhanVien nhanVien = (NhanVien) canBo;
                    line = "NhanVien|" + nhanVien.getHoTen() + "|" + nhanVien.getTuoi() + "|" 
                           + nhanVien.getGioiTinh() + "|" + nhanVien.getDiaChi() + "|" 
                           + nhanVien.getCongViec();
                } else {
                    continue;
                }
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Ghi dữ liệu thành công vào file: " + filePath);
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }
    
    public List<CanBo> docDanhSachCanBo(String filePath) {
        List<CanBo> danhSach = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length < 5) continue; // Bỏ qua dòng không hợp lệ

                String loaiCanBo = parts[0];
                String hoTen = parts[1];
                int tuoi = Integer.parseInt(parts[2]);
                String gioiTinh = parts[3];
                String diaChi = parts[4];

                switch (loaiCanBo) {
                    case "CongNhan":
                        int bac = Integer.parseInt(parts[5]);
                        danhSach.add(new CongNhan(hoTen, tuoi, gioiTinh, diaChi, bac));
                        break;
                    case "KySu":
                        String nganhDaoTao = parts[5];
                        danhSach.add(new KySu(hoTen, tuoi, gioiTinh, diaChi, nganhDaoTao));
                        break;
                    case "NhanVien":
                        String congViec = parts[5];
                        danhSach.add(new NhanVien(hoTen, tuoi, gioiTinh, diaChi, congViec));
                        break;
                }
            }
            System.out.println("Đọc dữ liệu thành công từ file: " + filePath);
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file: " + e.getMessage());
        }

        return danhSach;
    }
}
