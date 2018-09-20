package hlub.com.quanlythuchi.model;

public class KhoanThu {
    private int id;
    private String loaiThu;
    private String khoanThu;

    public KhoanThu(int id, String loaiThu, String khoanThu) {
        this.id = id;
        this.loaiThu = loaiThu;
        this.khoanThu = khoanThu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoaiThu() {
        return loaiThu;
    }

    public void setLoaiThu(String loaiThu) {
        this.loaiThu = loaiThu;
    }

    public String getKhoanThu() {
        return khoanThu;
    }

    public void setKhoanThu(String khoanThu) {
        this.khoanThu = khoanThu;
    }
}
