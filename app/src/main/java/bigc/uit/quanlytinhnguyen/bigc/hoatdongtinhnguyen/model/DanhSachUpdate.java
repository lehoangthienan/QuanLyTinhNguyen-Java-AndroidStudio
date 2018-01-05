package bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model;

/**
 * Created by Thien An on 2017-12-21.
 */

public class DanhSachUpdate {
    private String MATN ;
    private  String TenTN;

    public DanhSachUpdate(String MATN, String tenTN) {
        this.MATN = MATN;
        TenTN = tenTN;
    }

    public void setMATN(String MATN) {
        this.MATN = MATN;
    }

    public void setTenTN(String tenTN) {
        TenTN = tenTN;
    }

    public String getMATN() {
        return MATN;
    }

    public String getTenTN() {
        return TenTN;
    }
}
