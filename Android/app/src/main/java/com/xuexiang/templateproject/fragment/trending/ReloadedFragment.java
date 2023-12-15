/*
 * Copyright (C) 2019 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.xuexiang.templateproject.fragment.trending;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.luck.picture.lib.entity.LocalMedia;
import com.xuexiang.templateproject.R;
import com.xuexiang.templateproject.core.BaseFragment;
import com.xuexiang.templateproject.database.DatabaseTrueFragment;
import com.xuexiang.templateproject.utils.XToastUtils;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.adapter.FragmentAdapter;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xutil.XUtil;
import com.xuexiang.xutil.common.ClickUtils;
import com.xuexiang.xutil.common.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * @author xuexiang
 * @since 2019-10-30 00:19
 */
@Page(anim = CoreAnim.none)
public class ReloadedFragment extends BaseFragment implements View.OnClickListener, ViewPager.OnPageChangeListener, BottomNavigationView.OnNavigationItemSelectedListener, ClickUtils.OnClick2ExitListener, Toolbar.OnMenuItemClickListener{

//    @BindView(R.id.toolbar1)
//    Toolbar toolbar;
    @BindView(R.id.view_pager1)
    ViewPager viewPager;
    protected boolean isVisible;
    /**
     * 底部导航栏
     */
    @BindView(R.id.bottom_navigation1)
    BottomNavigationView bottomNavigation;
    /**
     * 侧边栏
     */
//    @BindView(R.id.nav_view)
//    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;


    private String[] mTitles;
    @Override
    protected TitleBar initTitle() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_upload_new;
    }


    private List<LocalMedia> mSelectList = new ArrayList<>();

    Bundle bundle=new Bundle();
    BaseFragment[] fragments = new BaseFragment[]{
            //new UploadedFragment(),
            //new EchartInnerFragment(),
            new ReuploadPhotoFragment(),
            new WeightSleepFragment(),
            new DatabaseTrueFragment()
    };



    @Override
    protected void initViews() {
        mTitles = ResUtils.getStringArray(R.array.update_titles);
//        bundle.putString("one","1");
//        bundle.putString("open_camera","1");
//        fragments[0].setArguments(bundle);

//        toolbar.setTitle(mTitles[0]);
//        toolbar.inflateMenu(R.menu.menu_main);
//        toolbar.setOnMenuItemClickListener(this);
//
//        initHeader();

        //主页内容填充




        FragmentAdapter<BaseFragment> adapter1 = new FragmentAdapter<>(getChildFragmentManager(), fragments);
        viewPager.setOffscreenPageLimit(mTitles.length - 1);
        //viewPager.setOffscreenPageLimit(1);
        viewPager.setAdapter(adapter1);

    }




//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        // super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            case 0:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(this, "�����������Ȩ��", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(this, "��ܾ������Ȩ��", Toast.LENGTH_SHORT).show();
//                }
//                break;
//            case 1:
//                //TODO multi permissions request result notification
//                boolean result = true;
//                for (int resultCode : grantResults) {
//                    if (resultCode != PackageManager.PERMISSION_GRANTED) {
//                        result = false;
//                    }
//                }
//                break;
//        }
//
//    }






    protected void initListeners() {
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();

        //侧边栏点击事件


        //主页事件监听
        viewPager.addOnPageChangeListener(this);
        bottomNavigation.setOnNavigationItemSelectedListener(this);
    }

//    /**
//     * 处理侧边栏点击事件
//     *
//     * @param menuItem
//     * @return
//     */
//    private boolean handleNavigationItemSelected(@NonNull MenuItem menuItem) {
//        int index = CollectionUtils.arrayIndexOf(mTitles, menuItem.getTitle());
//        if (index != -1) {
//            toolbar.setTitle(menuItem.getTitle());
//            viewPager.setCurrentItem(index, false);
//            return true;
//        }
//        return false;
//    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_privacy:
//                bundle.putString("one","1");
//                bundle.remove("open_camera");
//                bundle.putString("open_camera","2");
//                fragments[0].setArguments(bundle);
//                fragments[1].setArguments(bundle);
//                fragments[2].setArguments(bundle);
//                this.onPageSelected(1);

                break;
            default:
                break;
        }
        return false;
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nav_header:
                //XToastUtils.toast("点击头部！");
                break;
            default:
                break;
        }
    }

    //=============ViewPager===================//

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        //Log.e("-----------","---onPageSelected");
        MenuItem item = bottomNavigation.getMenu().getItem(position);
//        toolbar.setTitle(item.getTitle());
        item.setChecked(true);
        viewPager.setCurrentItem(position, true);
        //updateSideNavStatus(item);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }


    //================Navigation================//

    /**
     * 底部导航栏点击事件
     *
     * @param menuItem
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int index = CollectionUtils.arrayIndexOf(mTitles, menuItem.getTitle());
        if (index != -1) {
//            toolbar.setTitle(menuItem.getTitle());
            viewPager.setCurrentItem(index, false);

            //updateSideNavStatus(menuItem);
            return true;
        }
        return false;
    }

    /**
     * 更新侧边栏菜单选中状态
     *
     * @param menuItem
     */
//    private void updateSideNavStatus(MenuItem menuItem) {
//        MenuItem side = navView.getMenu().findItem(menuItem.getItemId());
//        if (side != null) {
//            side.setChecked(true);
//        }
//    }

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ClickUtils.exitBy2Click(2000, this);
        }
        return true;
    }

    @Override
    public void onRetry() {
        XToastUtils.toast("再按一次退出程序");
    }

    @Override
    public void onExit() {
        XUtil.get().exitApp();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {


        //Log.e("-----------","---reuploadedrun");
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }


    }

    protected void onVisible() {

        lazyLoad();
    }

    protected void lazyLoad() {

//        if(firstStartFlag == 0){
//            firstStartFlag = 1;
//        }
//        else{
        this.onPageSelected(0);

        //}





    }

    protected void onInvisible() {


    }

}

//public class ReloadedFragment extends BaseFragment implements View.OnClickListener, ViewPager.OnPageChangeListener, BottomNavigationView.OnNavigationItemSelectedListener, ClickUtils.OnClick2ExitListener, Toolbar.OnMenuItemClickListener {
////    @BindView(R.id.recycler_view)
////    RecyclerView recyclerView;
////    @BindView(R.id.Button2)
////    Button button2;
//    private ImageSelectGridAdapter mAdapter;
//    String current_time = null;
//    protected boolean isVisible;
//    FileOutputStream out;
//    String bitmapName = "zipped.jpg";
//    File file;
//    String QQFilePath;
//    ProgressDialog dialog;
//    private static final int PICK_FILE_REQUEST = 1;
//    private static final String TAG = TrendingFragment.class.getSimpleName();
//    public String selectedFilePath;
//    static public String flag1;
//    private final static String SERVER_URL = "http://39.100.73.118/deeplearning_photo/insert_data.php";
//    String total_path = Environment.getExternalStorageDirectory() + "/zip/zipped.jpg";
//    private String mImagePath;
//    private String mFileName; //ͼƬ����
//    private Uri mImageUri; //ͼƬ·��Uri
//    public static final int TAKE_PHOTO = 2;
//    public static final int CROP_PHOTO = 3;
//    private final int PICK = 4;
//    private String imgString = "";
//    private static final int MSG_SUCCESS = 0;//获取图片成功的标识
//    private static final int MSG_FAILURE = 1;//获取图片失败的标识
//
//    private List<LocalMedia> mSelectList = new ArrayList<>();
//    private String[] mTitles;
//    @BindView(R.id.view_pager1)
//    ViewPager viewPager;
//    @BindView(R.id.toolbar1)
//    Toolbar toolbar;
//    @BindView(R.id.bottom_navigation1)
//    BottomNavigationView bottomNavigation;
//    @BindView(R.id.drawer_layout)
//    DrawerLayout drawerLayout;
//
//    private Handler mHandler = new Handler() {
//        public void handleMessage (Message msg) {//此方法在ui线程运行
//            switch(msg.what) {
//                case MSG_SUCCESS:
//                    final MainActivity mainActivity = (MainActivity) getActivity();
//                    mainActivity.onPageSelected(0);
//
//                    break;
//
//                case MSG_FAILURE:
//
//                    break;
//            }
//        }
//    };
//
////    @BindView(R.id.refreshLayout)
////    SmartRefreshLayout refreshLayout;
//    /**
//     * @return 返回为 null意为不需要导航栏
//     */
////    @Override
////    protected TitleBar initTitle() {
////        return null;
////    }
//    @Override
//    protected TitleBar initTitle() {
//        return null;
//    }
//
//    /**
//     * 布局的资源id
//     *
//     * @return
//     */
//    @Override
//    protected int getLayoutId() {
//        return R.layout.fragment_upload_new;
//    }
//
//    /**
//     * 初始化控件
//     */
//    BaseFragment[] fragments = new BaseFragment[]{
//            //new UploadedFragment(),
//            //new EchartInnerFragment(),
//
//            new HowtouseFragment(),
//            new SettingsFragment()
//
//    };
//
//
//    @Override
//    protected void initViews() {
//        mTitles = ResUtils.getStringArray(R.array.update_titles);
//        toolbar.setTitle(mTitles[0]);
//        toolbar.inflateMenu(R.menu.menu_main);
//        toolbar.setOnMenuItemClickListener(this);
////
////        initHeader();
//
//        //主页内容填充
//
//
//
//
//        FragmentAdapter<BaseFragment> adapter = new FragmentAdapter<>(getActivity().getSupportFragmentManager(), fragments);
//        viewPager.setOffscreenPageLimit(mTitles.length - 1);
//        //viewPager.setOffscreenPageLimit(1);
//        viewPager.setAdapter(adapter);
//
//    }
//
//
//
//    @Override
//    public void onPageScrolled(int i, float v, int i1) {
//
//    }
//
//    @Override
//    public void onPageSelected(int position) {
//        //Log.e("-----------","---onPageSelected");
//        MenuItem item = bottomNavigation.getMenu().getItem(position);
//        toolbar.setTitle(item.getTitle());
//        item.setChecked(true);
//        viewPager.setCurrentItem(position, true);
//        //updateSideNavStatus(item);
//    }
//
//    @Override
//    public void onPageScrollStateChanged(int i) {
//
//    }
//
//
//    protected void initListeners() {
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//
////        //侧边栏点击事件
////        navView.setNavigationItemSelectedListener(menuItem -> {
////            if (menuItem.isCheckable()) {
////                drawerLayout.closeDrawers();
////                return handleNavigationItemSelected(menuItem);
////            } else {
////                switch (menuItem.getItemId()) {
////                    case R.id.nav_settings:
////                        openNewPage(SettingsFragment.class);
////                        break;
////                    case R.id.nav_about:
////                        openNewPage(AboutFragment.class);
////                        break;
////                    case R.id.nav_data:
////                        openNewPage(HowtouseFragment.class);
////                        break;
////                    case R.id.nav_notifications:
////                        openNewPage(QuestionFragment.class);
////                        break;
////                    default:
////                        XToastUtils.toast("点击了:" + menuItem.getTitle());
////                        break;
////                }
////            }
////            return true;
////        });
//
//        //主页事件监听
//        viewPager.addOnPageChangeListener(this);
//        bottomNavigation.setOnNavigationItemSelectedListener(this);
//    }
//
//    private static long lastClickTime;
//    public static boolean isFastDoubleClick() {
//        long time = SystemClock.uptimeMillis(); // �˷���������Android
//        if (time - lastClickTime < 2000) {
//            return true;
//        }
//        lastClickTime = time;
//        return false;
//    }
//
//
//
//
//
//
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == Activity.RESULT_OK) {
//            switch (requestCode) {
//                case PictureConfig.CHOOSE_REQUEST:
//                    // 图片选择
//                    mSelectList = PictureSelector.obtainMultipleResult(data);
//                    mAdapter.setSelectList(mSelectList);
//                    mAdapter.notifyDataSetChanged();
////                    Uri selectedFileUri = data.getData();
////                    selectedFilePath = getRealPathFromUri(getActivity(), selectedFileUri);
//                    selectedFilePath = mSelectList.get(0).getCompressPath();
//                    break;
//
////                case PICK_FILE_REQUEST:
////                    if (data == null) {
////                        //no data present
////                        return;
////                    }
////
////
////
////
////
////
////                    //selectedFilePath = FilePath.getPath(this, selectedFileUri);
////
////
////                    Log.i(TAG, "Selected File Path:" + selectedFilePath);
////                    break;
//
//
////                    if (selectedFilePath != null && !selectedFilePath.equals("")) {
////                        tvFileName.setText(selectedFilePath);
////                    } else {
////                        Toast.makeText(this, "Cannot upload file to server", Toast.LENGTH_SHORT).show();
////                    }
//
//
////                    if (selectedFilePath != null)
////                    {
////                        Bitmap bm = BitmapFactory.decodeFile(selectedFilePath);
////                        img_show.setImageBitmap(bm);
////                    }
//                default:
//                    break;
//            }
//        }
//    }
//
//
//
////    @Override
////    public void onAddPicClick() {
////        Utils.getPictureSelector(this)
////                .selectionMedia(mSelectList)
////                .forResult(PictureConfig.CHOOSE_REQUEST);
////        button2.setVisibility(View.VISIBLE);
////    }
//
//
//
//
//
//
//    @Override
//    public boolean onMenuItemClick(MenuItem item) {
//        return false;
//    }
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        return false;
//    }
//
//    @Override
//    public void onClick(View v) {
//
//    }
//
//    @Override
//    public void onRetry() {
//
//    }
//
//    @Override
//    public void onExit() {
//
//    }
//}
