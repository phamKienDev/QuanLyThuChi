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
import hlub.com.quanlythuchi.TabLoaiThuFragment;
import hlub.com.quanlythuchi.model.LoaiThu;

public class LoaiThuAdapter extends RecyclerView.Adapter<LoaiThuAdapter.LoaiThuHolder> {
    private List<LoaiThu> loaiThuList;
    private TabLoaiThuFragment mContext;

    public LoaiThuAdapter(List<LoaiThu> loaiThuList, TabLoaiThuFragment mContext) {
        this.loaiThuList = loaiThuList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public LoaiThuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_cardview_loaithu, parent, false);
        return new LoaiThuHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiThuHolder holder, int position) {
        final LoaiThu loaiThu = loaiThuList.get(position);
        holder.tvLoaiThu.setText(loaiThu.getTenLoaiThu());

        //bắt sự kiện edit & delete
        holder.imgSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.dialogSuaLoaiThu(loaiThu.getTenLoaiThu(), loaiThu.getIdLoaiThu());
            }
        });
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.diaXoaLoaiThu(loaiThu.getIdLoaiThu());
            }
        });
    }


    @Override
    public int getItemCount() {
        return loaiThuList.size();
    }

    public class LoaiThuHolder extends RecyclerView.ViewHolder {
        public TextView tvLoaiThu;
        public ImageView imgDelete;
        public ImageView imgSetting;

        public LoaiThuHolder(View itemView) {
            super(itemView);
            tvLoaiThu = itemView.findViewById(R.id.item_tv_loaithu);
            imgDelete = itemView.findViewById(R.id.item_img_delete_loaithu);
            imgSetting = itemView.findViewById(R.id.item_img_set_loaithu);
        }
    }
}
