package bigc.uit.quanlytinhnguyen.bigc.hoatdongtinhnguyen.model;
import java.io.Serializable;
/**
 * Created by lehoa on 2018-05-04.
 */

public class DiaDiem implements Serializable {
    private String kinhdo;
    private String vido;




    public String getKinhdo() {
        return kinhdo;
    }

    public String getVido() {
        return vido;
    }

    public void setKinhdo(String kinhdo) {
        this.kinhdo = kinhdo;
    }

    public void setVido(String vido) {
        this.vido = vido;
    }



    public DiaDiem(String kinhdo, String vido) {
        this.kinhdo = kinhdo;
        this.vido = vido;
    }


    public DiaDiem() {
    }
}
