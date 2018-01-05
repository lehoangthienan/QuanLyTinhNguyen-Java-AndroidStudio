package bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model;

/**
 * Created by Thien An on 2017-12-26.
 */

public class SoLuongSinhVienToiDa {
    // khởi tạo biến để lưu dữ liệu , biến này phải trùng tên với tên trên SQL nếu không sẽ báo lỗi
    int SLMax;

    //khởi tạo contrustor
    public SoLuongSinhVienToiDa(int SLMax) {
        this.SLMax = SLMax;
    }

    // khởi tạo setter getter

    public void setSLMax(int SLMax) {
        this.SLMax = SLMax;
    }

    public int getSLMax() {
        return SLMax;
    }
}
