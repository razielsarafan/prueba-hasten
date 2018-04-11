package es.santirivera.hastentest.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import es.santirivera.domain.model.CategoryModel;
import es.santirivera.hastentest.R;
import es.santirivera.hastentest.adapter.PlayersAdapter;
import es.santirivera.hastentest.base.activity.BaseActivity;
import es.santirivera.hastentest.base.view.BaseView;

public class PlayerListView extends BaseView<PlayerListListener> {


    private RecyclerView recyclerView;

    public PlayerListView(BaseActivity baseActivity, PlayerListListener presenter) {
        super(baseActivity, presenter);
    }

    @Override
    public int getContentView() {
        return R.layout.fragment_player_list;
    }

    @Override
    protected void prepareView() {
        recyclerView = mainView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(baseActivity));
    }

    public void onCategoryModelsReceived(ArrayList<CategoryModel> categoryModels) {
        recyclerView.setAdapter(new PlayersAdapter(categoryModels));
    }
}
