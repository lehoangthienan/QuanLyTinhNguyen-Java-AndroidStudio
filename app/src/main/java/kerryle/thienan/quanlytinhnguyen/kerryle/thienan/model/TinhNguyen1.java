package kerryle.thienan.quanlytinhnguyen.kerryle.thienan.model;

/**
 * Created by Thien An on 2017-12-11.
 */

public class TinhNguyen1 {

    private String MATN;
    private String TenTN;
    private String NoiDung;
    private int SDT;
    private String NgayGioBatDau;
    private String NgayGioKetThuc;
    private String DiaDiem;
    private int SLMax;
    private int SLMin;
    private int SLThamGia;
    private String MAT;
    private String HinhAnh;

    public TinhNguyen1() {
    }

    public TinhNguyen1(String MATN, String tenTN, String noiDung, int SDT, String ngayGioBatDau, String ngayGioKetThuc, String diaDiem, int SLMax, int SLMin, int SLThamGia, String MAT, String hinhAnh) {
        this.MATN = MATN;
        TenTN = tenTN;
        NoiDung = noiDung;
        this.SDT = SDT;
        NgayGioBatDau = ngayGioBatDau;
        NgayGioKetThuc = ngayGioKetThuc;
        DiaDiem = diaDiem;
        this.SLMax = SLMax;
        this.SLMin = SLMin;
        this.SLThamGia = SLThamGia;
        this.MAT = MAT;
        HinhAnh = hinhAnh;
    }

    public void setMATN(String MATN) {
        this.MATN = MATN;
    }

    public void setTenTN(String tenTN) {
        TenTN = tenTN;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public void setSDT(int SDT) {
        this.SDT = SDT;
    }

    public void setNgayGioBatDau(String ngayGioBatDau) {
        NgayGioBatDau = ngayGioBatDau;
    }

    public void setNgayGioKetThuc(String ngayGioKetThuc) {
        NgayGioKetThuc = ngayGioKetThuc;
    }

    public void setDiaDiem(String diaDiem) {
        DiaDiem = diaDiem;
    }

    public void setSLMax(int SLMax) {
        this.SLMax = SLMax;
    }

    public void setSLMin(int SLMin) {
        this.SLMin = SLMin;
    }

    public void setSLThamGia(int SLThamGia) {
        this.SLThamGia = SLThamGia;
    }

    public void setMAT(String MAT) {
        this.MAT = MAT;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public String getMATN() {
        return MATN;
    }

    public String getTenTN() {
        return TenTN;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public int getSDT() {
        return SDT;
    }

    public String getNgayGioBatDau() {
        return NgayGioBatDau;
    }

    public String getNgayGioKetThuc() {
        return NgayGioKetThuc;
    }

    public String getDiaDiem() {
        return DiaDiem;
    }

    public int getSLMax() {
        return SLMax;
    }

    public int getSLMin() {
        return SLMin;
    }

    public int getSLThamGia() {
        return SLThamGia;
    }

    public String getMAT() {
        return MAT;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }
}
