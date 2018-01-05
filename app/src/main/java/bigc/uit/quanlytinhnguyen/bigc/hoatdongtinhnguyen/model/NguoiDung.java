package bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model;

/**
 * Created by Thien An on 2017-12-14.
 */

public class NguoiDung {
    // khởi tạo biến để lưu dữ liệu , biến này phải trùng tên với tên trên SQL nếu không sẽ báo lỗi
    private String MASV;
    private String TenSV;
    private String MAT;
    private String NgaySinh;

    // khởi tạo setter getter
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

    public String getNgaySinh() {
        return NgaySinh;
    }

    //khởi tạo contrustor không đối số
    public NguoiDung() {
    }

    //khởi tạo contrustor
    public NguoiDung(String MASV, String tenSV, String MAT, String ngaySinh) {
        this.MASV = MASV;
        TenSV = tenSV;
        this.MAT = MAT;
        NgaySinh = ngaySinh;

    }
}
