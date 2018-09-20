package hlub.com.quanlythuchi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hlub.com.quanlythuchi.adapter.KhoanChiAdapter;
import hlub.com.quanlythuchi.adapter.KhoanThuAdapter;
import hlub.com.quanlythuchi.database.DatabaseManager;
import hlub.com.quanlythuchi.model.KhoanChi;
import hlub.com.quanlythuchi.model.KhoanThu;

public class TabKhoanChiFragment extends Fragment {
    private FloatingActionButton flTabKhoanChi;
    private DatabaseManager manager;
    private RecyclerView recyclerView;
    private List<KhoanChi> khoanChiList;
    private KhoanChiAdapter khoanChiAdapter;
    private Cursor cursorKhoanChi;
    private EditText edtTenKhoanChi;
    private EditText edtKhoanChi;

    private Cursor cursorLoaiChi;
    private Spinner spinnerLoaiChi;
    private SimpleCursorAdapter adapterLoaiChi;
    private String tenLoaiChi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab_khoanchi, container, false);

        //ánh xạ
        flTabKhoanChi = v.findViewById(R.id.fl_tab_khoanchi);
        recyclerView=v.findViewById(R.id.recycleview_khoanchi);
        manager=new DatabaseManager(getActivity());
        khoanChiList=new ArrayList<>();
        spinnerLoaiChi=v.findViewById(R.id.spiner_khoanchi);

        getSpinerLoaiChi();
        getKhoanChi();

        flTabKhoanChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addKhoanChiDialog();
            }
        });
        return v;
    }

    public void addKhoanChiDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //thiet lap
        builder.setTitle("Khoản chi");
        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String tenKhoanChi=edtKhoanChi.getText().toString();
                if(edtKhoanChi.equals("")){
                    Toast.makeText(getActivity(), "Không được rỗng", Toast.LENGTH_SHORT).show();
                }else{
                    manager.insertLoaiKhoanChi(tenLoaiChi,tenKhoanChi);
                    getKhoanChi();
                }
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        //tạo view
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_add_khoanchi, null);
        edtKhoanChi=view.findViewById(R.id.edt_add_khoanchi);
        builder.setView(view);

        //
        builder.show();


    }

    public void dialogXoaKhoanChi(final int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //thiết lập
        builder.setTitle("Xóa khoản thu");
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                cursorKhoanChi = manager.getLoaiKhoanThu();
                manager.deleteLoaiKhoanChi(id);
                Toast.makeText(getActivity(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                getKhoanChi();
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        //show
        builder.show();
    }

    public void dialogSuaKhoanChi(String tenMoi, final int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //thiết lập
        builder.setTitle("Cập nhật loại thu");
        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String tenMoi = edtTenKhoanChi.getText().toString().trim();

                if (tenMoi.equals("")) {
                    Toast.makeText(getActivity(), "Không được rỗng", Toast.LENGTH_SHORT).show();
                } else {
                    manager.updateLoaiKhoanChi(tenLoaiChi, tenMoi, id);
                    Toast.makeText(getActivity(), "Đã cập nhật ", Toast.LENGTH_SHORT).show();
                    getKhoanChi();
                }
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        //tạo view
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_edit_khoanchi, null);
        builder.setView(view);
        edtTenKhoanChi = view.findViewById(R.id.edt_edit_khoanchi);
        edtTenKhoanChi.setText(tenMoi);

        builder.show();
    }


    public void getSpinerLoaiChi() {
        cursorLoaiChi = manager.getLoaiChi();
        //lấy ->gán dữ liệu lên spiner
        while (cursorLoaiChi.moveToNext()) {
            adapterLoaiChi = new SimpleCursorAdapter(getActivity(),
                    android.R.layout.simple_spinner_item,
                    cursorLoaiChi,
                    new String[]{"LoaiChi"},
                    new int[]{android.R.id.text1});
        }
        spinnerLoaiChi.setAdapter(adapterLoaiChi);

        //lắng nghe
        spinnerLoaiChi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor cursor = (Cursor) adapterView.getItemAtPosition(i);
                tenLoaiChi = cursor.getString(1);
                Toast.makeText(getActivity(), tenLoaiChi, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void getKhoanChi() {

        cursorKhoanChi = manager.getLoaiKhoanChi();
        khoanChiList.clear();
        while (cursorKhoanChi.moveToNext()) {
            int id = cursorKhoanChi.getInt(0);
            String tenLoaiChi = cursorKhoanChi.getString(1);
            String tenKhoanChi = cursorKhoanChi.getString(2);
            khoanChiList.add(new KhoanChi(id, tenLoaiChi, tenKhoanChi));
        }
        //gán layout
        khoanChiAdapter = new KhoanChiAdapter(this, khoanChiList);
        recyclerView.setAdapter(khoanChiAdapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);


    }

}
