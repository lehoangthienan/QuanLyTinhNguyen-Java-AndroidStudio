package bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model;

/**
 * Created by Thien An on 2017-12-20.
 */

public class TenTruong {
    // khởi tạo biến để lưu dữ liệu , biến này phải trùng tên với tên trên SQL nếu không sẽ báo lỗi
    private  String TenTruong;

    //khởi tạo contrustor
    public TenTruong(String tenTruong) {
        TenTruong = tenTruong;
    }

    // khởi tạo setter getter

    public void setTenTruong(String tenTruong) {
        TenTruong = tenTruong;
    }

    public String getTenTruong() {
        return TenTruong;
    }
}
