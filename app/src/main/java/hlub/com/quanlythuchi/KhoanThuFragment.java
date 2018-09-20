package hlub.com.quanlythuchi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hlub.com.quanlythuchi.adapter.PaperAdapter;

public class KhoanThuFragment extends Fragment {
    private TabLayout tabLayoutThu;
    private ViewPager viewPagerThu;
    private PaperAdapter adapterThu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_khoanthu,container,false);
        tabLayoutThu=view.findViewById(R.id.tablayout_khoanthu);
        viewPagerThu=view.findViewById(R.id.viewpaper_khoanthu);
        setupViewPaper(viewPagerThu);
        tabLayoutThu.setupWithViewPager(viewPagerThu);
        return view;

    }
    public void setupViewPaper(ViewPager viewPager){
        adapterThu=new PaperAdapter(getFragmentManager());
        adapterThu.addFragment(new TabKhoanThuFragment(),"Khoản thu");
        adapterThu.addFragment(new TabLoaiThuFragment(),"Loại thu");
        viewPager.setAdapter(adapterThu);
    }
}
