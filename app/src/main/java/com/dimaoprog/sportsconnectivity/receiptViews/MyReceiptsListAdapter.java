package com.dimaoprog.sportsconnectivity.receiptViews;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dimaoprog.sportsconnectivity.R;
import com.dimaoprog.sportsconnectivity.databinding.ItemMealdbBinding;
import com.dimaoprog.sportsconnectivity.dbEntities.MealDBFavorite;

public class MyReceiptsListAdapter extends ListAdapter<MealDBFavorite, MyReceiptsListViewHolder> {

    private IFavoriteReceiptPickedListener iFavoriteReceiptPickedListener;

    public interface IFavoriteReceiptPickedListener {
        void openMyReceiptDetailFragment(long id);
    }

    protected MyReceiptsListAdapter(IFavoriteReceiptPickedListener iFavoriteReceiptPickedListener) {
        super(DIFF_CALLBACK);
        this.iFavoriteReceiptPickedListener = iFavoriteReceiptPickedListener;
    }

    private static final DiffUtil.ItemCallback<MealDBFavorite> DIFF_CALLBACK = new DiffUtil.ItemCallback<MealDBFavorite>() {
        @Override
        public boolean areItemsTheSame(@NonNull MealDBFavorite mealDBFavorite, @NonNull MealDBFavorite t1) {
            return mealDBFavorite.getIdMeal() == t1.getIdMeal();
        }

        @Override
        public boolean areContentsTheSame(@NonNull MealDBFavorite mealDBFavorite, @NonNull MealDBFavorite t1) {
            return false;
        }
    };

    @NonNull
    @Override
    public MyReceiptsListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemMealdbBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_mealdb,
                viewGroup, false);
        return new MyReceiptsListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyReceiptsListViewHolder myReceiptsListViewHolder, int i) {
        myReceiptsListViewHolder.bind(getItem(i), iFavoriteReceiptPickedListener);
    }

    public MealDBFavorite getMealAtPos(int position) {
        return getItem(position);
    }
}
