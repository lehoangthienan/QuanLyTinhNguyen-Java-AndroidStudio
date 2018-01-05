package bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model;

/**
 * Created by Thien An on 2017-12-29.
 */

public class ThongTinSinhVien {
    // khởi tạo biến để lưu dữ liệu , biến này phải trùng tên với tên trên SQL nếu không sẽ báo lỗi
    private String MASV ;
    private String TenSV;
    private String MAT;

    // khởi tạo setter getter
    public void setMASV(String MASV) {
        this.MASV = MASV;
    }

    public void setTenSV(String tenSV) {
        TenSV = tenSV;
    }

    public void setMAT(String MAT) {
        this.MAT = MAT;
    }

    //khởi tạo contrustor
    public ThongTinSinhVien(String MASV, String tenSV, String MAT) {
        this.MASV = MASV;
        TenSV = tenSV;
        this.MAT = MAT;
    }

    // khởi tạo setter getter
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
