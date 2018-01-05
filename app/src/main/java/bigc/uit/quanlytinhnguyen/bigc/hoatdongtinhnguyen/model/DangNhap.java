package bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model;

import java.io.Serializable;

/**
 * Created by Thien An on 2017-12-13.
 */

public class DangNhap implements Serializable {
    private  String TaiKhoan ;
    private  String MatKhau;
    private  String MASV;

    public void setTaiKhoan(String taiKhoan) {
        TaiKhoan = taiKhoan;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public void setMASV(String MASV) {
        this.MASV = MASV;
    }

    public String getTaiKhoan() {
        return TaiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public String getMASV() {
        return MASV;
    }

    public DangNhap(String taiKhoan, String matKhau, String MASV) {
        TaiKhoan = taiKhoan;
        MatKhau = matKhau;
        this.MASV = MASV;
    }
}
