package es.santirivera.hastentest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import es.santirivera.domain.model.CategoryModel;
import es.santirivera.domain.model.PlayerModel;
import es.santirivera.hastentest.R;
import es.santirivera.hastentest.adapter.ViewHolder.PlayerViewHolder;
import es.santirivera.hastentest.adapter.ViewHolder.TitleViewHolder;


public class PlayersAdapter extends RecyclerView.Adapter {

    private final static int TYPE_CATEGORY = 0;
    private final static int TYPE_PLAYER = 1;

    private ArrayList<Object> objects = new ArrayList<>();

    public PlayersAdapter(ArrayList<CategoryModel> models) {
        for (CategoryModel model : models) {
            objects.add(model.getTitle());
            for (PlayerModel player : model.getPlayerList()) {
                objects.add(player);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        Object obj = objects.get(position);
        if (obj instanceof String) {
            return TYPE_CATEGORY;
        } else {
            return TYPE_PLAYER;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_CATEGORY: {
                View view = View.inflate(parent.getContext(), R.layout.item_title, null);
                TitleViewHolder holder = new TitleViewHolder(view);
                return holder;
            }
            case TYPE_PLAYER: {
                View view = View.inflate(parent.getContext(), R.layout.item_player, null);
                PlayerViewHolder holder = new PlayerViewHolder(view);
                return holder;
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TitleViewHolder) {
            ((TitleViewHolder) holder).bind((String) objects.get(position));
        } else {
            ((PlayerViewHolder) holder).bind((PlayerModel) objects.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }
}
