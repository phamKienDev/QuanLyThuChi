package hlub.com.quanlythuchi.model;

public class LoaiChi {
    private int idLoaiChi;
    private String tenLoaiChi;

    public LoaiChi(int idLoaiChi, String tenLoaiChi) {
        this.idLoaiChi = idLoaiChi;
        this.tenLoaiChi = tenLoaiChi;
    }

    public int getIdLoaiChi() {
        return idLoaiChi;
    }

    public void setIdLoaiChi(int idLoaiChi) {
        this.idLoaiChi = idLoaiChi;
    }

    public String getTenLoaiChi() {
        return tenLoaiChi;
    }

    public void setTenLoaiChi(String tenLoaiChi) {
        this.tenLoaiChi = tenLoaiChi;
    }
}
