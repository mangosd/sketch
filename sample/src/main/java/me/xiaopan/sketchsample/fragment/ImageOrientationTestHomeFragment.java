package me.xiaopan.sketchsample.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import me.xiaopan.androidinjector.InjectContentView;
import me.xiaopan.androidinjector.InjectView;
import me.xiaopan.assemblyadapter.FragmentArrayPagerAdapter;
import me.xiaopan.psts.PagerSlidingTabStrip;
import me.xiaopan.sketchsample.MyFragment;
import me.xiaopan.sketchsample.R;
import me.xiaopan.sketchsample.activity.MainActivity;
import me.xiaopan.sketchsample.util.ImageOrientationCorrectTestFileGenerator;

@InjectContentView(R.layout.fragment_pager_tab)
public class ImageOrientationTestHomeFragment extends MyFragment {
    @InjectView(R.id.tab_pagerTabFragment_tabs)
    private PagerSlidingTabStrip tabStrip;

    @InjectView(R.id.pager_pagerTabFragment_content)
    private ViewPager viewPager;

    private FragmentArrayPagerAdapter fragmentAdapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (fragmentAdapter == null) {
            String[] filePaths = ImageOrientationCorrectTestFileGenerator.getInstance(getContext()).getFilePaths();
            Fragment[] fragments = new Fragment[filePaths.length];
            for (int w = 0; w < filePaths.length; w++) {
                fragments[w] = ImageOrientationTestFragment.build(filePaths[w]);
            }
            fragmentAdapter = new FragmentArrayPagerAdapter(getChildFragmentManager(), fragments);
        }
        viewPager.setAdapter(fragmentAdapter);

        tabStrip.setTabViewFactory(new MainActivity.TitleTabFactory(new String[]{
                "ROTATE_90",
                "ROTATE_180",
                "ROTATE_270",
                "FLIP_HORIZONTAL",
                "TRANSPOSE",
                "FLIP_VERTICAL",
                "TRANSVERSE"}, getActivity()));
        tabStrip.setViewPager(viewPager);
    }
}
