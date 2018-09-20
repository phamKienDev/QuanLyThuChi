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
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hlub.com.quanlythuchi.adapter.LoaiThuAdapter;
import hlub.com.quanlythuchi.database.DatabaseManager;
import hlub.com.quanlythuchi.model.LoaiThu;

public class TabLoaiThuFragment extends Fragment {
    private EditText edtTabLoaiThu;
    private EditText edtSuaLoaiThu;
    private String tenLoaiThu;
    private FloatingActionButton flTabLoaiThu;
    private LoaiThuAdapter loaiThuAdapter;
    private List<LoaiThu> loaiThuList;
    private RecyclerView recyclerView;
    private DatabaseManager manager;
    private Cursor dataLoaiThu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab_loaithu, container, false);
        //khoi tao
        flTabLoaiThu = v.findViewById(R.id.fl_tab_loaithu);
        recyclerView = v.findViewById(R.id.recycleview_loaithu);
        loaiThuList = new ArrayList<>();
        manager = new DatabaseManager(getActivity());
        //get du lieu
        getLoaiThu();

        //set click FloatingActionButton
        flTabLoaiThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addLoaiThuDialog();
            }
        });
        //Toast.makeText(getActivity(), ""+v.getContext(), Toast.LENGTH_SHORT).show();
        return v;
    }

    public void dialogSuaLoaiThu( final String tenMoi, final int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //thiết lập
        builder.setTitle("Cập nhật loại thu");
        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String tenMoi=edtSuaLoaiThu.getText().toString().trim();

                if (tenMoi.equals("")) {
                    Toast.makeText(getActivity(), "Không được rỗng", Toast.LENGTH_SHORT).show();
                } else {
                    manager.updateLoaiThu(tenMoi, id);
                    Toast.makeText(getActivity(), "Đã cập nhật ", Toast.LENGTH_SHORT).show();
                    getLoaiThu();
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
        View view = inflater.inflate(R.layout.dialog_edit_loaithu, null);
        builder.setView(view);
        edtSuaLoaiThu = view.findViewById(R.id.edt_edit_loaithu);
        edtSuaLoaiThu.setText(tenMoi);

        builder.show();
    }

    public void diaXoaLoaiThu(final int id){
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        //thiết lập
        builder.setTitle("Xóa loại thu");
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dataLoaiThu=manager.getLoaiThu();
                manager.deleteLoaiThu(id);
                Toast.makeText(getActivity(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                getLoaiThu();
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

    public void addLoaiThuDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        //thiet lap
        builder.setTitle("Loại thu");
        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String tenLoaiThu = edtTabLoaiThu.getText().toString();
                if (tenLoaiThu.equals("")) {
                    Toast.makeText(getActivity(), "Mời bạn nhập loại thu", Toast.LENGTH_SHORT).show();
                } else {
                    manager.insertLoaiThu(edtTabLoaiThu.getText().toString());
                    Toast.makeText(getActivity(), "Đã thêm", Toast.LENGTH_SHORT).show();
                    getLoaiThu();
                }

            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        //tao view
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_add_loaithu, null);
        builder.setView(view);
        edtTabLoaiThu = view.findViewById(R.id.edt_add_loaithu);


        //hien thi
        builder.show();


    }

    public void getLoaiThu() {
        //try {

        //loaiThuList.clear();
        dataLoaiThu = manager.getLoaiThu();

        while (dataLoaiThu.moveToNext()) {
            //lay du liệu
            tenLoaiThu = dataLoaiThu.getString(1);
            int idLoaithu = dataLoaiThu.getInt(0);
            loaiThuList.add(new LoaiThu(idLoaithu, tenLoaiThu));

        }
        Toast.makeText(getActivity(), ""+loaiThuList.size(), Toast.LENGTH_SHORT).show();
        //gán layout
        loaiThuAdapter = new LoaiThuAdapter(loaiThuList, this);
        loaiThuAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(loaiThuAdapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);

//        } catch (Exception e) {
//            Toast.makeText(getActivity(), "" +e, Toast.LENGTH_LONG).show();
//        }

    }
}
