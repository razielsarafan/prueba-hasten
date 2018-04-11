package es.santirivera.hastentest.adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import es.santirivera.domain.model.PlayerModel;
import es.santirivera.hastentest.R;

public class PlayerViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageViewPlayer;
    private TextView textViewName;
    private TextView textViewSurname;

    public PlayerViewHolder(View itemView) {
        super(itemView);
        this.imageViewPlayer = itemView.findViewById(R.id.item_player_imageview);
        this.textViewName = itemView.findViewById(R.id.item_player_name_textview);
        this.textViewSurname = itemView.findViewById(R.id.item_player_surname_textview);
    }

    public void bind(PlayerModel player) {
        Picasso.get().load(player.getImageUrl()).placeholder(R.drawable.placeholder).into(imageViewPlayer);
        textViewName.setText(player.getName());
        textViewSurname.setText(player.getSurname());
    }
}