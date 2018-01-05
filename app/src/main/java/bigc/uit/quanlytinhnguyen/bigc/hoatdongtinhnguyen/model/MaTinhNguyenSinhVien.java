package bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model;

/**
 * Created by Thien An on 2017-12-24.
 */

public class MaTinhNguyenSinhVien {

    // khởi tạo biến để lưu dữ liệu , biến này phải trùng tên với tên trên SQL nếu không sẽ báo lỗi
    private String MATN;

    //khởi tạo contrustor
    public MaTinhNguyenSinhVien(String MATN) {
        this.MATN = MATN;
    }

    // khởi tạo setter getter
    public void setMATN(String MATN) {
        this.MATN = MATN;
    }

    public String getMATN() {
        return MATN;
    }
}
