package hlub.com.quanlythuchi.model;

public class KhoanChi {
    private int id;
    private String loaiChi;
    private String khoanChi;

    public KhoanChi(int id, String loaiChi, String khoanChi) {
        this.id = id;
        this.loaiChi = loaiChi;
        this.khoanChi = khoanChi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoaiChi() {
        return loaiChi;
    }

    public void setLoaiChi(String loaiChi) {
        this.loaiChi = loaiChi;
    }

    public String getKhoanChi() {
        return khoanChi;
    }

    public void setKhoanChi(String khoanChi) {
        this.khoanChi = khoanChi;
    }
}
