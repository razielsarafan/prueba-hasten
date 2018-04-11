package es.santirivera.hastentest.adapter.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import es.santirivera.hastentest.R;

public class TitleViewHolder extends RecyclerView.ViewHolder {

    TextView textViewTitle;

    public TitleViewHolder(View itemView) {
        super(itemView);
        textViewTitle = itemView.findViewById(R.id.item_title_text_view);
    }

    public void bind(String str) {
        textViewTitle.setText(str);
    }
}
