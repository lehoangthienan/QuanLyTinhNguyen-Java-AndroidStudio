package bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model;

/**
 * Created by Thien An on 2017-12-26.
 */

public class SoLuongThamGia {
    // khởi tạo biến để lưu dữ liệu , biến này phải trùng tên với tên trên SQL nếu không sẽ báo lỗi
    private int SLThamGia ;


    // khởi tạo setter getter
    public void setSLThamGia(int SLThamGia) {
        this.SLThamGia = SLThamGia;
    }

    public int getSLThamGia() {
        return SLThamGia;
    }

    //khởi tạo contrustor
    public SoLuongThamGia(int SLThamGia) {
        this.SLThamGia = SLThamGia;
    }
}
