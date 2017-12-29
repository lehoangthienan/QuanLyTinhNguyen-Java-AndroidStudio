package kerryle.thienan.quanlytinhnguyen.kerryle.thienan.model;

/**
 * Created by Thien An on 2017-12-29.
 */

public class ThongTinSinhVien {
    private String MASV ;
    private String TenSV;
    private String TenTruong;

    public ThongTinSinhVien(String MASV, String tenSV, String tenTruong) {
        this.MASV = MASV;
        TenSV = tenSV;
        TenTruong = tenTruong;
    }

    public void setMASV(String MASV) {
        this.MASV = MASV;
    }

    public void setTenSV(String tenSV) {
        TenSV = tenSV;
    }

    public void setTenTruong(String tenTruong) {
        TenTruong = tenTruong;
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
}
