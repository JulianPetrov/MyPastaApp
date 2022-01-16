package com.f101469.mypastaapp.ui;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.f101469.mypastaapp.R;
import com.f101469.mypastaapp.model.Pasta;

import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.Locale;

public class PastaListAdapter extends ListAdapter<Pasta, PastaListAdapter.PastaListViewHolder> {

  static class PastaListViewHolder extends RecyclerView.ViewHolder {
    TextView pastaNameTextView;
    TextView pastaIngredientsTextView;
    TextView pastaPriceTextView;
    ImageView pastaImageView;

    public PastaListViewHolder(@NonNull View itemView) {
      super(itemView);
      pastaNameTextView = itemView.findViewById(R.id.pasta_name_text_view);
      pastaPriceTextView = itemView.findViewById(R.id.pasta_price_text_view);
      pastaIngredientsTextView = itemView.findViewById(R.id.pasta_ingredients_text_view);
      pastaImageView = itemView.findViewById(R.id.pasta_image_view);
    }

    public void bind(
        String pastaName, String pastaPrice, String pastaIngredients, String pastaImage) {
      pastaNameTextView.setText(pastaName);
      pastaPriceTextView.setText(pastaPrice);
      pastaIngredientsTextView.setText(pastaIngredients);
      Thread thread =
          new Thread(
              () -> {
                try {
                  pastaImageView.setImageDrawable(loadImageFromUrl(pastaImage));

                } catch (Exception e) {
                  Log.d("ImageLoader", "Problem loading image.");
                }
              });
      thread.start();
    }

    static PastaListViewHolder create(ViewGroup parent) {
      View view =
          LayoutInflater.from(parent.getContext())
              .inflate(R.layout.recycler_view_item, parent, false);
      return new PastaListViewHolder(view);
    }
  }

  public PastaListAdapter(@NonNull DiffUtil.ItemCallback<Pasta> diffCallback) {
    super(diffCallback);
  }

  @NonNull
  @Override
  public PastaListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return PastaListViewHolder.create(parent);
  }

  @Override
  public void onBindViewHolder(@NonNull PastaListViewHolder holder, int position) {
    Pasta current = getItem(position);
    String pastaPrice = String.format(Locale.getDefault(), "%.2f BGN", current.getBasePrice());
    String pastaIngredients =
        String.format(
            "%s, %s, %s, %s",
            current.getStrIngredient1(),
            current.getStrIngredient2(),
            current.getStrIngredient3(),
            current.getStrIngredient4());
    String pastaImage = current.getStrMealThumb();
    holder.bind(current.getStrMeal(), pastaPrice, pastaIngredients, pastaImage);
  }

  public static class PastaDiff extends DiffUtil.ItemCallback<Pasta> {

    @Override
    public boolean areItemsTheSame(@NonNull Pasta oldItem, @NonNull Pasta newItem) {
      return oldItem == newItem;
    }

    @Override
    public boolean areContentsTheSame(@NonNull Pasta oldItem, @NonNull Pasta newItem) {
      return oldItem.getFavourite().equals(newItem.getFavourite())
          && oldItem.getBasePrice().equals(newItem.getBasePrice())
          && oldItem.getIdMeal().equals(newItem.getIdMeal())
          && oldItem.getStrMeal().equals(newItem.getStrMeal());
    }
  }

  public static Drawable loadImageFromUrl(String url) {
    try {
      InputStream is = (InputStream) new URL(url).getContent();
      return Drawable.createFromStream(is, "src name");
    } catch (Exception e) {
      return null;
    }
  }

  public static BigDecimal generateRandomBigDecimalFromInRange(BigDecimal min, BigDecimal max) {
    BigDecimal randomBigDecimal =
        min.add(BigDecimal.valueOf(Math.random()).multiply(max.subtract(min)));
    return randomBigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
  }
}
