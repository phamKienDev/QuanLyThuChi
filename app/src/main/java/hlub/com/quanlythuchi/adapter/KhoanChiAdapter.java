package hlub.com.quanlythuchi.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hlub.com.quanlythuchi.R;
import hlub.com.quanlythuchi.TabKhoanChiFragment;
import hlub.com.quanlythuchi.TabKhoanThuFragment;
import hlub.com.quanlythuchi.TabLoaiChiFragment;
import hlub.com.quanlythuchi.model.KhoanChi;
import hlub.com.quanlythuchi.model.KhoanThu;

public class KhoanChiAdapter extends RecyclerView.Adapter<KhoanChiAdapter.KhoanChiHolder> {
    private TabKhoanChiFragment mContext;
    private List<KhoanChi> khoanChiList;

    public KhoanChiAdapter(TabKhoanChiFragment mContext, List<KhoanChi> khoanThuList) {
        this.mContext = mContext;
        this.khoanChiList = khoanThuList;
    }

    @NonNull
    @Override
    public KhoanChiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_cardview_khoanchi, parent, false);
        return new KhoanChiHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhoanChiHolder holder, int position) {

        final KhoanChi khoanChi = khoanChiList.get(position);
        holder.tvLoaiKhoanChi.setText(khoanChi.getLoaiChi());
        holder.tvKhoanChi.setText(khoanChi.getKhoanChi());
        holder.imgSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.dialogSuaKhoanChi(khoanChi.getKhoanChi(), khoanChi.getId());
            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.dialogXoaKhoanChi(khoanChi.getId());
            }
        });


    }


    @Override
    public int getItemCount() {
        return khoanChiList.size();
    }

    public class KhoanChiHolder extends RecyclerView.ViewHolder {
        public TextView tvLoaiKhoanChi;
        public TextView tvKhoanChi;
        public ImageView imgSetting;
        public ImageView imgDelete;

        public KhoanChiHolder(View itemView) {
            super(itemView);
            tvLoaiKhoanChi = itemView.findViewById(R.id.item_tv_loaikhoanchi);
            tvKhoanChi = itemView.findViewById(R.id.item_tv_khoanchi);
            imgDelete = itemView.findViewById(R.id.item_img_delete_khoanchi);
            imgSetting = itemView.findViewById(R.id.item_img_set_khoanchi);
        }
    }
}
