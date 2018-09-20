package hlub.com.quanlythuchi.model;

public class LoaiThu{
    private int idLoaiThu;
    private String tenLoaiThu;

    public LoaiThu(int idLoaiThu, String tenLoaiThu) {
        this.idLoaiThu = idLoaiThu;
        this.tenLoaiThu = tenLoaiThu;
    }

    public int getIdLoaiThu() {
        return idLoaiThu;
    }

    public void setIdLoaiThu(int idLoaiThu) {
        this.idLoaiThu = idLoaiThu;
    }

    public String getTenLoaiThu() {
        return tenLoaiThu;
    }

    public void setTenLoaiThu(String tenLoaiThu) {
        this.tenLoaiThu = tenLoaiThu;
    }
}
