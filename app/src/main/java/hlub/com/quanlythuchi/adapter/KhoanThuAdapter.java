package hlub.com.quanlythuchi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import hlub.com.quanlythuchi.R;
import hlub.com.quanlythuchi.TabKhoanThuFragment;
import hlub.com.quanlythuchi.model.KhoanThu;

public class KhoanThuAdapter extends RecyclerView.Adapter<KhoanThuAdapter.KhoanThuHolder> {
    private TabKhoanThuFragment mContext;
    private List<KhoanThu>khoanThuList;

    public KhoanThuAdapter(TabKhoanThuFragment mContext, List<KhoanThu> khoanThuList) {
        this.mContext = mContext;
        this.khoanThuList = khoanThuList;
    }

    @NonNull
    @Override
    public KhoanThuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item_cardview_khoanthu,parent,false);
        return new KhoanThuHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhoanThuHolder holder, int position) {

            final KhoanThu khoanThu=khoanThuList.get(position);
            holder.tvLoaiKhoanThu.setText(khoanThu.getLoaiThu());
            holder.tvKhoanThu.setText(khoanThu.getKhoanThu());
            holder.imgSetting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.dialogSuaKhoanThu(khoanThu.getKhoanThu(),khoanThu.getId());
                }
            });
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.dialogXoaKhoanThu(khoanThu.getId());
                }
            });


    }



    @Override
    public int getItemCount() {
        return khoanThuList.size();
    }

    public class KhoanThuHolder extends RecyclerView.ViewHolder{
        public TextView tvLoaiKhoanThu;
        public TextView tvKhoanThu;
        public ImageView imgSetting;
        public ImageView imgDelete;
        public KhoanThuHolder(View itemView) {
            super(itemView);
            tvLoaiKhoanThu=itemView.findViewById(R.id.item_tv_loaikhoanthu);
            tvKhoanThu=itemView.findViewById(R.id.item_tv_khoanthu);
            imgDelete=itemView.findViewById(R.id.item_img_delete_khoanthu);
            imgSetting=itemView.findViewById(R.id.item_img_set_khoanthu);
        }
    }
}
