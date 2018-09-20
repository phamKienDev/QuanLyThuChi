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

public class KhoanChiFragment extends Fragment {
    private TabLayout tabLayoutChi;
    private ViewPager viewPagerChi;
    private PaperAdapter adapterChi;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_khoanchi,container,false);
        tabLayoutChi=view.findViewById(R.id.tablayout_khoanchi);
        viewPagerChi=view.findViewById(R.id.viewpaper_khoanchi);
        setupViewPaper(viewPagerChi);
        tabLayoutChi.setupWithViewPager(viewPagerChi);
        return view;
    }
    public void setupViewPaper(ViewPager viewPager){
        adapterChi=new PaperAdapter(getFragmentManager());
        adapterChi.addFragment(new TabKhoanChiFragment(),"Khoản chi");
        adapterChi.addFragment(new TabLoaiChiFragment(),"Loại chi");
        viewPager.setAdapter(adapterChi);
    }
}
