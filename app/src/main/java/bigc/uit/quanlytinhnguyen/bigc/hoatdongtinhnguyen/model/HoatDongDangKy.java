package bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model;

/**
 * Created by Thien An on 2017-12-15.
 */

public class HoatDongDangKy  {
    private String MATN;
    private String TenTN;
    private String DiaDiem;
    private String NgayGioBatDau;
    private String NgayGioKetThuc;

    public HoatDongDangKy(String MATN, String tenTN, String diaDiem, String ngayGioBatDau, String ngayGioKetThuc) {
        this.MATN = MATN;
        TenTN = tenTN;
        DiaDiem = diaDiem;
        NgayGioBatDau = ngayGioBatDau;
        NgayGioKetThuc = ngayGioKetThuc;
    }

    public void setMATN(String MATN) {
        this.MATN = MATN;
    }

    public void setTenTN(String tenTN) {
        TenTN = tenTN;
    }

    public void setDiaDiem(String diaDiem) {
        DiaDiem = diaDiem;
    }

    public void setNgayGioBatDau(String ngayGioBatDau) {
        NgayGioBatDau = ngayGioBatDau;
    }

    public void setNgayGioKetThuc(String ngayGioKetThuc) {
        NgayGioKetThuc = ngayGioKetThuc;
    }

    public String getMATN() {
        return MATN;
    }

    public String getTenTN() {
        return TenTN;
    }

    public String getDiaDiem() {
        return DiaDiem;
    }

    public String getNgayGioBatDau() {
        return NgayGioBatDau;
    }

    public String getNgayGioKetThuc() {
        return NgayGioKetThuc;
    }
}
