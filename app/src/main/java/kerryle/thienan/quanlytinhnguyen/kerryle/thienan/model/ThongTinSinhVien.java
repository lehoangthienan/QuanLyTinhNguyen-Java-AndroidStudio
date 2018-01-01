package kerryle.thienan.quanlytinhnguyen.kerryle.thienan.model;

/**
 * Created by Thien An on 2017-12-29.
 */

public class ThongTinSinhVien {
    private String MASV ;
    private String TenSV;
    private String MAT;

    public void setMASV(String MASV) {
        this.MASV = MASV;
    }

    public void setTenSV(String tenSV) {
        TenSV = tenSV;
    }

    public void setMAT(String MAT) {
        this.MAT = MAT;
    }

    public ThongTinSinhVien(String MASV, String tenSV, String MAT) {
        this.MASV = MASV;
        TenSV = tenSV;
        this.MAT = MAT;
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
}
