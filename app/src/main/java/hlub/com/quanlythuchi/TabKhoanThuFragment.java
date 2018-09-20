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

import hlub.com.quanlythuchi.adapter.KhoanThuAdapter;
import hlub.com.quanlythuchi.database.DatabaseManager;
import hlub.com.quanlythuchi.model.KhoanThu;

public class TabKhoanThuFragment extends Fragment {
    private FloatingActionButton flTabKhoanThu;
    private RecyclerView recyclerView;
    private DatabaseManager manager;
    private KhoanThuAdapter khoanThuAdapter;
    private List<KhoanThu> khoanThuList;
    private Cursor cursorKhoanThu;
    private Spinner spinnerKhoanThu;
    private EditText edtKhoanThu;
    private EditText edtSuaKhoanThu;

    private Cursor cursorLoaiThu;
    private SimpleCursorAdapter adapterLoaiThu;
    private String tenLoaiThu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab_khoanthu, container, false);
        //khởi tạo
        flTabKhoanThu = v.findViewById(R.id.fl_tab_khoanthu);
        manager = new DatabaseManager(getActivity());
        recyclerView = v.findViewById(R.id.recycleview_khoanthu);
        spinnerKhoanThu = v.findViewById(R.id.spiner_khoanthu);
        khoanThuList = new ArrayList<>();

        getSpinerLoaiThu();
        getKhoanThu();
        flTabKhoanThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addKhoanThuDialog();
            }
        });
        return v;
    }

    public void addKhoanThuDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //thiet lap
        builder.setTitle("Khoản thu");
        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String tenKhoanThu=edtKhoanThu.getText().toString();
                if(edtKhoanThu.equals("")){
                    Toast.makeText(getActivity(), "Không được rỗng", Toast.LENGTH_SHORT).show();
                }else{
                    manager.insertLoaiKhoanThu(tenLoaiThu,tenKhoanThu);
                    getKhoanThu();
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
        View view = inflater.inflate(R.layout.activity_add_khoanthu, null);
        edtKhoanThu=view.findViewById(R.id.edt_add_khoanthu);
        builder.setView(view);

        //
        builder.show();


    }

    public void dialogSuaKhoanThu(String tenMoi, final int id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //thiết lập
        builder.setTitle("Cập nhật loại thu");
        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String tenMoi=edtSuaKhoanThu.getText().toString().trim();

                if (tenMoi.equals("")) {
                    Toast.makeText(getActivity(), "Không được rỗng", Toast.LENGTH_SHORT).show();
                } else {
                    manager.updateLoaiKhoanThu(tenLoaiThu,tenMoi,id);
                    Toast.makeText(getActivity(), "Đã cập nhật ", Toast.LENGTH_SHORT).show();
                    getKhoanThu();
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
        View view = inflater.inflate(R.layout.dialog_edit_khoanthu, null);
        builder.setView(view);
        edtSuaKhoanThu = view.findViewById(R.id.edt_edit_khoanthu);
        edtSuaKhoanThu.setText(tenMoi);

        builder.show();
    }

    public void dialogXoaKhoanThu(final int id){
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        //thiết lập
        builder.setTitle("Xóa khoản thu");
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                cursorKhoanThu=manager.getLoaiKhoanThu();
                manager.deleteLoaiKhoanThu(id);
                Toast.makeText(getActivity(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                getKhoanThu();
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

    public void getSpinerLoaiThu() {
        cursorLoaiThu = manager.getLoaiThu();
        //lấy ->gán dữ liệu lên spiner
        while (cursorLoaiThu.moveToNext()) {
            adapterLoaiThu = new SimpleCursorAdapter(getActivity(),
                    android.R.layout.simple_spinner_item,
                    cursorLoaiThu,
                    new String[]{"LoaiThu"},
                    new int[]{android.R.id.text1});
        }
        spinnerKhoanThu.setAdapter(adapterLoaiThu);

        //lắng nghe
        spinnerKhoanThu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor cursor = (Cursor) adapterView.getItemAtPosition(i);
                tenLoaiThu = cursor.getString(1);
                Toast.makeText(getActivity(), tenLoaiThu, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void getKhoanThu() {

            cursorKhoanThu=manager.getLoaiKhoanThu();
            //khoanThuList.clear();
            while (cursorKhoanThu.moveToNext()){
                int id=cursorKhoanThu.getInt(0);
                String tenLoaiThu=cursorKhoanThu.getString(1);
                String tenKhoanThu=cursorKhoanThu.getString(2);
                khoanThuList.add(new KhoanThu(id,tenLoaiThu,tenKhoanThu));

            }
        Toast.makeText(getActivity(), ""+khoanThuList.size(), Toast.LENGTH_SHORT).show();
            //gán layout
            khoanThuAdapter=new KhoanThuAdapter(this,khoanThuList);
            recyclerView.setAdapter(khoanThuAdapter);
            RecyclerView.LayoutManager manager=new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(manager);



    }
}
