package bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model;

/**
 * Created by Thien An on 2017-12-29.
 */

public class Truong {
    // khởi tạo biến để lưu dữ liệu , biến này phải trùng tên với tên trên SQL nếu không sẽ báo lỗi
    private String MAT;
    private String TenTruog;


    //khởi tạo contrustor
    public Truong(String MAT, String tenTruog) {
        this.MAT = MAT;
        TenTruog = tenTruog;
    }

    // khởi tạo setter getter
    public void setMAT(String MAT) {
        this.MAT = MAT;
    }

    public void setTenTruog(String tenTruog) {
        TenTruog = tenTruog;
    }

    public String getMAT() {
        return MAT;
    }

    public String getTenTruog() {
        return TenTruog;
    }
}
