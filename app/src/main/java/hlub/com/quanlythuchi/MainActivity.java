package hlub.com.quanlythuchi;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private KhoanChiFragment khoanChiFragment;
    private KhoanThuFragment khoanThuFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //khoi tao
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        khoanChiFragment = new KhoanChiFragment();
        khoanThuFragment=new KhoanThuFragment();

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        //click
        navigationView.setNavigationItemSelectedListener(this);

        //thiet lap man hinh ban dau
        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content,new ThongKeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_thongke);
        }



    }

    //su kien tren toolbar
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_khoanthu:
                hienThiKhoanThu();
                break;
            case R.id.nav_khoanchi:
                hienThiKhoanChi();
                break;
            case R.id.nav_thongke:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, new ThongKeFragment()).commit();
                break;
            case R.id.nav_gioithieu:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, new GioiThieuFragment()).commit();
                break;
            case R.id.nav_thoat:
                Toast.makeText(this, "Thoat", Toast.LENGTH_SHORT).show();
                break;


        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    private void hienThiKhoanThu(){
        //định nghĩa thành phần quản lý fragment
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        //ẩn hết các fragment trên khung hiện tại nếu có->chỉ hiển thị 1 màn hình fragment
        if(khoanChiFragment.isAdded()){
            fragmentTransaction.hide(khoanChiFragment);
        }
        if(khoanThuFragment.isAdded()){
            fragmentTransaction.show(khoanThuFragment);
        }else {
            fragmentTransaction.add(R.id.fragment_content,khoanThuFragment);

        }
        //xác thực lại thay đổi
        fragmentTransaction.commit();

    }

    private void hienThiKhoanChi(){
        //định nghĩa thành phần quản lý fragment
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        //ẩn hết các fragment trên khung hiện tại nếu có->chỉ hiển thị 1 màn hình fragment
        if(khoanThuFragment.isAdded()){
            fragmentTransaction.hide(khoanThuFragment);
        }
        if(khoanChiFragment.isAdded()){
            fragmentTransaction.show(khoanChiFragment);
        }else {
            fragmentTransaction.add(R.id.fragment_content,khoanChiFragment);

        }
        //xác thực lại thay đổi
        fragmentTransaction.commit();

    }
}
