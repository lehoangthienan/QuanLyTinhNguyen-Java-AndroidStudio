package bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model;

/**
 * Created by Thien An on 2017-12-16.
 */

public class SoHoatDongThamGia {
    // khởi tạo biến để lưu dữ liệu , biến này phải trùng tên với tên trên SQL nếu không sẽ báo lỗi
    private String SOLUONG;

    // khởi tạo setter getter
    public String getSOLUONG() {
        return SOLUONG;
    }

    public void setSOLUONG(String SOLUONG) {
        this.SOLUONG = SOLUONG;
    }

    public SoHoatDongThamGia() {
    }

    //khởi tạo contrustor
    public SoHoatDongThamGia(String SOLUONG) {
        this.SOLUONG = SOLUONG;
    }
}
