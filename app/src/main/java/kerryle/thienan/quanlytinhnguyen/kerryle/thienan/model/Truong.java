package kerryle.thienan.quanlytinhnguyen.kerryle.thienan.model;

/**
 * Created by Thien An on 2017-12-29.
 */

public class Truong {
    private String MAT;
    private String TenTruog;

    public Truong(String MAT, String tenTruog) {
        this.MAT = MAT;
        TenTruog = tenTruog;
    }

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
