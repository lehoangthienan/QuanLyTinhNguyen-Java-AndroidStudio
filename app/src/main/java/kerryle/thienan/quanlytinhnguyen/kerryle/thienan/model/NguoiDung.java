package kerryle.thienan.quanlytinhnguyen.kerryle.thienan.model;

/**
 * Created by Thien An on 2017-12-14.
 */

public class NguoiDung {
    private String MASV;
    private String TenSV;
    private String TenTruong;
    private String NgaySinh;

    public void setMASV(String MASV) {
        this.MASV = MASV;
    }

    public void setTenSV(String tenSV) {
        TenSV = tenSV;
    }

    public void setTenTruong(String tenTruong) {
        TenTruong = tenTruong;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }




    public String getMASV() {
        return MASV;
    }

    public String getTenSV() {
        return TenSV;
    }

    public String getTenTruong() {
        return TenTruong;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public NguoiDung() {
    }

    public NguoiDung(String MASV, String tenSV, String tenTruong, String ngaySinh) {
        this.MASV = MASV;
        TenSV = tenSV;
        TenTruong = tenTruong;
        NgaySinh = ngaySinh;

    }
}
