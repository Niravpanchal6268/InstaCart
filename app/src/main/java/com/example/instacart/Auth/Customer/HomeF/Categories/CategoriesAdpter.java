package com.example.instacart.Auth.Customer.HomeF.Categories;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.instacart.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoriesAdpter extends FirebaseRecyclerAdapter<CategoriesModel, CategoriesAdpter.CategoriesViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CategoriesAdpter(@NonNull FirebaseRecyclerOptions<CategoriesModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position, @NonNull CategoriesModel model) {
        holder.categoiesName.setText(model.getCategoriesName());
        Glide.with(holder.categoriesImage).load(model.getCategoriesImage()).into(holder.categoriesImage);
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_single_card, parent, false);
        return new CategoriesViewHolder(view);
    }


    public class CategoriesViewHolder extends RecyclerView.ViewHolder {
        CircleImageView categoriesImage;
        TextView categoiesName;

        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            categoiesName = itemView.findViewById(R.id.categories_name_C_id);
            categoriesImage = itemView.findViewById(R.id.categories_image_C_id);

        }
    }
}
