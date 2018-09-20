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

import hlub.com.quanlythuchi.adapter.LoaiChiAdapter;
import hlub.com.quanlythuchi.database.DatabaseManager;
import hlub.com.quanlythuchi.model.LoaiChi;

public class TabLoaiChiFragment extends Fragment {
    private FloatingActionButton flTabLoaiChi;
    private EditText edtTenLoaiChi;
    private EditText edtSuaLoaiChi;
    private DatabaseManager manager;
    private List<LoaiChi> loaiChiList;
    private LoaiChiAdapter loaiChiAdapter;
    private RecyclerView recyclerView;
    private Cursor dataLoaiChi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab_loaichi, container, false);
        //khoi tao
        flTabLoaiChi = v.findViewById(R.id.fl_tab_loaichi);
        recyclerView = v.findViewById(R.id.recycleview_loaichi);
        loaiChiList = new ArrayList<>();
        manager = new DatabaseManager(getActivity());

        //gét dữ liệu
        getLoaiChi();


        //set click FloatingActionButton
        flTabLoaiChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addKhoanThuDialog();
            }
        });
        return v;
    }

    public void dialogSuaLoaiChi(final String tenMoi, final int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //thiết lập
        builder.setTitle("Cập nhật loại thu");
        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String tenMoi = edtSuaLoaiChi.getText().toString().trim();

                if (tenMoi.equals("")) {
                    Toast.makeText(getActivity(), "Không được rỗng", Toast.LENGTH_SHORT).show();
                } else {
                    manager.updateLoaiChi(tenMoi, id);
                    Toast.makeText(getActivity(), "Đã cập nhật ", Toast.LENGTH_SHORT).show();
                    getLoaiChi();
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
        View view = inflater.inflate(R.layout.dialog_edit_loaichi, null);
        builder.setView(view);
        edtSuaLoaiChi = view.findViewById(R.id.edt_edit_loaichi);
        edtTenLoaiChi.setText(tenMoi);

        builder.show();
    }

    public void diaXoaLoaiChi(final int id){
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        //thiết lập
        builder.setTitle("Xóa loại thu");
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dataLoaiChi=manager.getLoaiThu();

                manager.deleteLoaiChi(id);
                Toast.makeText(getActivity(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                getLoaiChi();
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

    public void addKhoanThuDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //thiet lap
        builder.setTitle("Loại chi");
        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String tenLoaiChi = edtTenLoaiChi.getText().toString();
                if (tenLoaiChi.equals("")) {
                    Toast.makeText(getActivity(), "Mời bạn nhập tên loại chi", Toast.LENGTH_SHORT).show();
                } else {
                    manager.insertLoaiChi(tenLoaiChi);
                    Toast.makeText(getActivity(), "Đã thêm", Toast.LENGTH_SHORT).show();
                    getLoaiChi();
                }

            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        //tạo view layout
        LayoutInflater inflater = (LayoutInflater) getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_add_loaichi, null);
        edtTenLoaiChi = view.findViewById(R.id.edt_add_loaichi);

        builder.setView(view);
        //
        builder.show();


    }

    public void getLoaiChi() {
        dataLoaiChi = manager.getLoaiChi();
        // try {
        loaiChiList.clear();
        while (dataLoaiChi.moveToNext()) {
            //lấy dữ liệu
            String tenLoaiChi = dataLoaiChi.getString(1);
            int idLoaiChi = dataLoaiChi.getInt(0);
            loaiChiList.add(new LoaiChi(idLoaiChi, tenLoaiChi));

        }
        //gán layout
        loaiChiAdapter = new LoaiChiAdapter(loaiChiList, this);
        //cập nhật thay đổi
        loaiChiAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(loaiChiAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
//        } catch (Exception e) {
//            Toast.makeText(getActivity(), ""+e, Toast.LENGTH_SHORT).show();
//        }

    }

}
