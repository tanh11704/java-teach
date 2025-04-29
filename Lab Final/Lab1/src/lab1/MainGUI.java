package lab1;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import lab1.model.CanBo;
import lab1.model.CongNhan;
import lab1.model.KySu;
import lab1.model.NhanVien;

public class MainGUI extends javax.swing.JFrame {

    private QLCB qlcb = new QLCB();
    private String filePath = "danhSachCanBo.txt";

    public MainGUI() {
        initComponents();
        init();
    }

    public void init() {
        loadDataToTable();
    }

    private void loadDataToTable() {
        qlcb.taiDanhSachTuFile(filePath);
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        for (CanBo canBo : qlcb.getDanhSachCanBo()) {
            String[] rowData = {
                canBo.getHoTen(),
                String.valueOf(canBo.getTuoi()),
                canBo.getGioiTinh(),
                canBo.getDiaChi(),
                canBo instanceof CongNhan ? "Bậc: " + ((CongNhan) canBo).getBac()
                : canBo instanceof KySu ? "Ngành: " + ((KySu) canBo).getNganhDaoTao()
                : canBo instanceof NhanVien ? "Công việc: " + ((NhanVien) canBo).getCongViec()
                : "Khác"
            };
            model.addRow(rowData);
        }
    }

    private void updateTable(List<CanBo> searchResults) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        for (CanBo canBo : searchResults) {
            Object[] row = new Object[]{
                canBo.getHoTen(),
                canBo.getTuoi(),
                canBo.getGioiTinh(),
                canBo.getDiaChi(),
                canBo instanceof CongNhan ? "Bậc: " + ((CongNhan) canBo).getBac()
                : canBo instanceof KySu ? "Ngành: " + ((KySu) canBo).getNganhDaoTao()
                : canBo instanceof NhanVien ? "Công việc: " + ((NhanVien) canBo).getCongViec()
                : "Khác"
            };
            model.addRow(row);
        }
    }

    private void clearInputFields() {
        txtHoTen.setText("");
        txtTuoi1.setText("");
        txtDiaChi.setText("");
        cbbGioiTinh.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTuoi1 = new javax.swing.JTextField();
        cbbGioiTinh = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnShowAll = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ CÁN BỘ");

        jTable1.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Họ Tên", "Tuổi", "Giới Tính", "Địa Chỉ", "Khác"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel2.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel2.setText("Họ Tên");

        txtHoTen.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel3.setText("Tuổi");

        jLabel4.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel4.setText("Giới Tính");

        jLabel5.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel5.setText("Địa Chỉ");

        txtTuoi1.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N

        cbbGioiTinh.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        cbbGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ", "Khác" }));

        jLabel6.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jLabel6.setText("Loại Cán Bộ");

        txtDiaChi.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N

        jComboBox2.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Công nhân", "Kỹ sư", "Nhân viên" }));

        btnThem.setText("THÊM");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setText("XÓA");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setText("CHỈNH SỬA");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnHuy.setText("HỦY BỎ");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabel7.setText("Tìm kiếm");

        txtSearch.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8-search.png"))); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnShowAll.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        btnShowAll.setText("Hiển Thị Tất Cả");
        btnShowAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtTuoi1, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                        .addComponent(cbbGioiTinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch)
                        .addGap(18, 18, 18)
                        .addComponent(btnShowAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(6, 6, 6))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSearch)
                    .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnShowAll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTuoi1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            String hoTen = txtHoTen.getText();
            int tuoi = Integer.parseInt(txtTuoi1.getText());
            String gioiTinh = (String) cbbGioiTinh.getSelectedItem();
            String diaChi = txtDiaChi.getText();
            String loaiCanBo = (String) jComboBox2.getSelectedItem();

            String oldHoTen = (String) jTable1.getValueAt(selectedRow, 0);
            CanBo canBo = qlcb.timKiemCanBo(oldHoTen);

            if (canBo != null) {
                String newHoTen = JOptionPane.showInputDialog(this, "Họ tên:", canBo.getHoTen());
                if (newHoTen != null && !newHoTen.trim().isEmpty()) {
                    canBo.setHoTen(newHoTen);
                }

                String newTuoiStr = JOptionPane.showInputDialog(this, "Tuổi:", String.valueOf(canBo.getTuoi()));
                if (newTuoiStr != null && !newTuoiStr.trim().isEmpty()) {
                    int newTuoi = Integer.parseInt(newTuoiStr);
                    canBo.setTuoi(newTuoi);
                }

                String[] options = {"Nam", "Nữ", "Khác"};
                String newGioiTinh = (String) JOptionPane.showInputDialog(
                        this,
                        "Chọn giới tính:",
                        "Sửa giới tính",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        canBo.getGioiTinh()
                );
                if (newGioiTinh != null) {
                    canBo.setGioiTinh(newGioiTinh);
                }

                String newDiaChi = JOptionPane.showInputDialog(this, "Địa chỉ:", canBo.getDiaChi());
                if (newDiaChi != null && !newDiaChi.trim().isEmpty()) {
                    canBo.setDiaChi(newDiaChi);
                }

                String newLoaiCanBo = (String) JOptionPane.showInputDialog(
                        this,
                        "Chọn loại cán bộ:",
                        "Sửa loại cán bộ",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new String[]{"Công nhân", "Kỹ sư", "Nhân viên"},
                        loaiCanBo
                );

                if (newLoaiCanBo != null && !newLoaiCanBo.equals(loaiCanBo)) {
                    // Khởi tạo đối tượng mới dựa trên loại cán bộ mới
                    CanBo newCanBo = null;

                    if (newLoaiCanBo.equals("Công nhân")) {
                        newCanBo = new CongNhan(hoTen, tuoi, gioiTinh, diaChi, 0);
                        String bacStr = JOptionPane.showInputDialog(this, "Nhập bậc công nhân (1-10):");
                        if (bacStr != null && !bacStr.trim().isEmpty()) {
                            int bac = Integer.parseInt(bacStr);
                            ((CongNhan) newCanBo).setBac(bac);
                        }
                    } else if (newLoaiCanBo.equals("Kỹ sư")) {
                        newCanBo = new KySu(hoTen, tuoi, gioiTinh, diaChi, "");
                        String nganhDaoTao = JOptionPane.showInputDialog(this, "Nhập ngành đào tạo:");
                        if (nganhDaoTao != null && !nganhDaoTao.trim().isEmpty()) {
                            ((KySu) newCanBo).setNganhDaoTao(nganhDaoTao);
                        }
                    } else if (newLoaiCanBo.equals("Nhân viên")) {
                        newCanBo = new NhanVien(hoTen, tuoi, gioiTinh, diaChi, "");
                        String congViec = JOptionPane.showInputDialog(this, "Nhập công việc:");
                        if (congViec != null && !congViec.trim().isEmpty()) {
                            ((NhanVien) newCanBo).setCongViec(congViec);
                        }
                    }

                    if (newCanBo != null) {
                        qlcb.xoaCanBo(canBo);
                        qlcb.themCanBo(newCanBo);

                        qlcb.luuDanhSachVaoFile(filePath);
                        loadDataToTable();
                        clearInputFields();
                        JOptionPane.showMessageDialog(this, "Sửa thông tin và thay đổi loại cán bộ thành công!");
                    }
                }

                qlcb.luuDanhSachVaoFile(filePath);
                loadDataToTable();
                clearInputFields();
                JOptionPane.showMessageDialog(this, "Sửa thông tin thành công!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để chỉnh sửa!");
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Bạn có chắc chắn muốn xóa cán bộ này không?",
                    "Xác nhận xóa",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                String hoTen = (String) jTable1.getValueAt(selectedRow, 0);

                boolean removed = qlcb.xoaCanBo(hoTen);

                if (removed) {
                    qlcb.luuDanhSachVaoFile(filePath);

                    loadDataToTable();

                    JOptionPane.showMessageDialog(this, "Xóa cán bộ thành công!");
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy cán bộ cần xóa!");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để xóa!");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        String hoTen = txtHoTen.getText();
        int tuoi = Integer.parseInt(txtTuoi1.getText());
        String gioiTinh = (String) cbbGioiTinh.getSelectedItem();
        String diaChi = txtDiaChi.getText();
        String loaiCanBo = (String) jComboBox2.getSelectedItem();

        CanBo canBo = null;
        if (loaiCanBo.equals("Công nhân")) {
            int bac = Integer.parseInt(JOptionPane.showInputDialog("Nhập bậc (1-10):"));
            canBo = new CongNhan(hoTen, tuoi, gioiTinh, diaChi, bac);
        } else if (loaiCanBo.equals("Kỹ sư")) {
            String nganhDaoTao = JOptionPane.showInputDialog("Nhập ngành đào tạo:");
            canBo = new KySu(hoTen, tuoi, gioiTinh, diaChi, nganhDaoTao);
        } else if (loaiCanBo.equals("Nhân viên")) {
            String congViec = JOptionPane.showInputDialog("Nhập công việc:");
            canBo = new NhanVien(hoTen, tuoi, gioiTinh, diaChi, congViec);
        }

        if (canBo != null) {
            qlcb.themMoiCanBo(canBo);
            qlcb.luuDanhSachVaoFile(filePath);
            loadDataToTable();
            JOptionPane.showMessageDialog(this, "Thêm cán bộ thành công!");
            clearInputFields();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow != -1) {
            txtHoTen.setText((String) jTable1.getValueAt(selectedRow, 0));
            txtTuoi1.setText(String.valueOf(jTable1.getValueAt(selectedRow, 1)));
            cbbGioiTinh.setSelectedItem((String) jTable1.getValueAt(selectedRow, 2));
            txtDiaChi.setText((String) jTable1.getValueAt(selectedRow, 3));

            String other = (String) jTable1.getValueAt(selectedRow, 4);
            if (other.startsWith("Bậc:")) {
                jComboBox2.setSelectedItem("Công nhân");
            } else if (other.startsWith("Ngành:")) {
                jComboBox2.setSelectedItem("Kỹ sư");
            } else if (other.startsWith("Công việc:")) {
                jComboBox2.setSelectedItem("Nhân viên");
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnHuyActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
    }//GEN-LAST:event_txtSearchActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        String searchTerm = txtSearch.getText().toLowerCase().trim();

        if (!searchTerm.isEmpty()) {
            List<CanBo> searchResults = new ArrayList<>();

            for (CanBo canBo : qlcb.getDanhSachCanBo()) {
                if (canBo.getHoTen().toLowerCase().contains(searchTerm)) {
                    searchResults.add(canBo);
                }
            }

            if (!searchResults.isEmpty()) {
                updateTable(searchResults);
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy cán bộ nào!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            loadDataToTable();
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnShowAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowAllActionPerformed
        loadDataToTable();
    }//GEN-LAST:event_btnShowAllActionPerformed

    public static void main(String args[]) throws Exception {
        UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName()
        );

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnShowAll;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbbGioiTinh;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTuoi1;
    // End of variables declaration//GEN-END:variables

}
