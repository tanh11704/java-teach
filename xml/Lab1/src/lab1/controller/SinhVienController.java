package lab1.controller;

import java.util.ArrayList;
import lab1.model.SinhVien;
import lab1.model.SinhVienModel;
import lab1.view.SinhVienView;

public class SinhVienController {
    private SinhVienModel model;
    
    public SinhVienController(SinhVienView view) {
        this.model = new SinhVienModel();
    }
    
    public ArrayList<SinhVien> getAllSinhVien() {
        return model.getDanhSachSinhVien();
    }
    
    public boolean addSinhVien(SinhVien sv) {
        return model.addSinhVien(sv);
    }
    
    public boolean updateSinhVien(SinhVien sv) {
        return model.updateSinhVien(sv);
    }
    
    public boolean deleteSinhVien(String stuID) {
        return model.deleteSinhVien(stuID);
    }
    
    public SinhVien findSinhVien(String stuID) {
        return model.findSinhVien(stuID);
    }
}
