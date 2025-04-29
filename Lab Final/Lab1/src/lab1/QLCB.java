package lab1;

import java.util.ArrayList;
import java.util.List;
import lab1.model.CanBo;

public class QLCB {

    private List<CanBo> danhSachCanBo = new ArrayList<>();
    FileHandler fileHandler = new FileHandler();

    public void themMoiCanBo(CanBo canBo) {
        danhSachCanBo.add(canBo);
        System.out.println("Thêm mới thành công!");
    }

    public CanBo timKiemCanBo(String hoTen) {
        for (CanBo canBo : danhSachCanBo) {
            if (canBo.getHoTen().equalsIgnoreCase(hoTen)) {
                return canBo;
            }
        }
        return null;
    }

    public boolean xoaCanBo(String hoTen) {
        boolean removed = danhSachCanBo.removeIf(canBo -> canBo.getHoTen().equalsIgnoreCase(hoTen));
        return removed;
    }

    public void hienThiDanhSachCanBo() {
        if (danhSachCanBo.isEmpty()) {
            System.out.println("Danh sách trống.");
        } else {
            danhSachCanBo.forEach((t) -> {
                System.out.println(t.toString());
            });
        }
    }

    public void luuDanhSachVaoFile(String filePath) {
        fileHandler.ghiDanhSachCanBo(danhSachCanBo, filePath);
    }

    public void taiDanhSachTuFile(String filePath) {
        danhSachCanBo = fileHandler.docDanhSachCanBo(filePath);
    }

    public List<CanBo> getDanhSachCanBo() {
        return danhSachCanBo;
    }

    public void xoaCanBo(CanBo canBo) {
        if (danhSachCanBo.contains(canBo)) {
            danhSachCanBo.remove(canBo);
            System.out.println("Cán bộ đã được xóa.");
        } else {
            System.out.println("Cán bộ không tồn tại trong danh sách.");
        }
    }

    public void themCanBo(CanBo canBo) {
        if (!danhSachCanBo.contains(canBo)) {
            danhSachCanBo.add(canBo);
            System.out.println("Cán bộ đã được thêm vào danh sách.");
        } else {
            System.out.println("Cán bộ đã tồn tại trong danh sách.");
        }
    }

}
