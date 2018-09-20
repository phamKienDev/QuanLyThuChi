package hlub.com.quanlythuchi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hlub.com.quanlythuchi.R;
import hlub.com.quanlythuchi.TabLoaiChiFragment;
import hlub.com.quanlythuchi.model.LoaiChi;
import hlub.com.quanlythuchi.model.LoaiThu;

public class LoaiChiAdapter extends RecyclerView.Adapter<LoaiChiAdapter.LoaiChiHolder> {
    private List<LoaiChi> loaiChiList;
    private TabLoaiChiFragment mContext;

    public LoaiChiAdapter(List<LoaiChi> loaiChiList, TabLoaiChiFragment mContext) {
        this.loaiChiList = loaiChiList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public LoaiChiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_cardview_loaichi, parent, false);
        return new LoaiChiHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiChiHolder holder, int position) {
        final LoaiChi loaiChi = loaiChiList.get(position);
        holder.tvLoaiChi.setText(loaiChi.getTenLoaiChi());
        holder.imgSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.dialogSuaLoaiChi(loaiChi.getTenLoaiChi(),loaiChi.getIdLoaiChi());
            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.diaXoaLoaiChi(loaiChi.getIdLoaiChi());
            }
        });
    }

    @Override
    public int getItemCount() {
        return loaiChiList.size();
    }


    public class LoaiChiHolder extends RecyclerView.ViewHolder {
        public TextView tvLoaiChi;
        public ImageView imgDelete;
        public ImageView imgSetting;

        public LoaiChiHolder(View itemView) {
            super(itemView);
            tvLoaiChi = itemView.findViewById(R.id.item_tv_loaichi);
            imgDelete = itemView.findViewById(R.id.item_img_delete_loaichi);
            imgSetting = itemView.findViewById(R.id.item_img_set_loaichi);
        }
    }
}
