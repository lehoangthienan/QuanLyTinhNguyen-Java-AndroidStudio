package kerryle.thienan.quanlytinhnguyen.kerryle.thienan.model;

/**
 * Created by Thien An on 2017-12-14.
 */

public class NguoiDung {
    private String MASV;
    private String TenSV;
    private String MAT;
    private String NgaySinh;

    public void setMASV(String MASV) {
        this.MASV = MASV;
    }

    public void setTenSV(String tenSV) {
        TenSV = tenSV;
    }

    public void setTenTruong(String tenTruong) {
        MAT = MAT;
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

    public String getMAT() {
        return MAT;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public NguoiDung() {
    }

    public NguoiDung(String MASV, String tenSV, String MAT, String ngaySinh) {
        this.MASV = MASV;
        TenSV = tenSV;
        this.MAT = MAT;
        NgaySinh = ngaySinh;

    }
}
