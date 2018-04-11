package es.santirivera.hastentest.activity;

import es.santirivera.hastentest.R;
import es.santirivera.hastentest.base.activity.BaseActivity;
import es.santirivera.hastentest.fragment.PlayerListFragment;
import es.santirivera.hastentest.fragment.PlayerListListener;


public class MainActivity extends BaseActivity implements PlayerListListener {

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void prepareInterface() {
        PlayerListFragment fragment = new PlayerListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }
}
