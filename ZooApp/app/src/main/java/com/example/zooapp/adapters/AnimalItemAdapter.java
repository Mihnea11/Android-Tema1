package com.example.zooapp.adapters;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.zooapp.R;
import com.example.zooapp.models.Animal;
import com.example.zooapp.models.Continent;
import java.util.ArrayList;
import java.util.List;

public class AnimalItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private List<Animal> animals;
    private OnAnimalClickListener onAnimalClickListenerVar;

    public AnimalItemAdapter(OnAnimalClickListener onAnimalClickListener)
    {
        this.animals = new ArrayList<>();
        this.onAnimalClickListenerVar = onAnimalClickListener;
    }

    @Override
    public int getItemViewType(int position)
    {
        return animals.get(position).getContinent().getKey();
    }

    public void setAnimals(List<Animal> animals)
    {
        this.animals = animals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {

        if (viewType == Continent.AFRICA.getKey())
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.africa, parent, false);

            AnimalViewHolder viewHolder = new AnimalViewHolder(view);

            Log.e("Adapter", "onCreateViewHolder africa");

            return viewHolder;
        }
        else if (viewType == Continent.AMERICA.getKey())
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.america, parent, false);

            AnimalViewHolder viewHolder = new AnimalViewHolder(view);

            Log.e("Adapter", "onCreateViewHolder america");

            return viewHolder;
        }
        else if (viewType == Continent.ASIA.getKey()) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.asia, parent, false);

            AnimalViewHolder viewHolder = new AnimalViewHolder(view);

            Log.e("Adapter", "onCreateViewHolder asia");

            return viewHolder;
        }
        else if (viewType == Continent.AUSTRALIA.getKey())
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.australia, parent, false);

            AnimalViewHolder viewHolder = new AnimalViewHolder(view);

            Log.e("Adapter", "onCreateViewHolder australia");

            return viewHolder;
        }
        else if (viewType == Continent.EUROPE.getKey())
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.europa, parent, false);

            AnimalViewHolder viewHolder = new AnimalViewHolder(view);

            Log.e("Adapter", "onCreateViewHolder europe");

            return viewHolder;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof AnimalViewHolder) {
            Animal animal = animals.get(position);

            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    int backgroundColor = getBackgroundColorFromDrawable(holder.itemView.getBackground());

                    onAnimalClickListenerVar.onAnimalClick(animal, backgroundColor);
                }
            });

            ((AnimalViewHolder)holder).bind(animal);
        }

        Log.e("Adapter", "onBindViewHolder; position=" + position);
    }

    private int getBackgroundColorFromDrawable(Drawable drawable)
    {
        if (drawable instanceof ColorDrawable)
        {
            return ((ColorDrawable) drawable).getColor();
        }
        else if (drawable instanceof GradientDrawable)
        {
            GradientDrawable gradientDrawable = (GradientDrawable) drawable;
            ColorStateList colorStateList = gradientDrawable.getColor();

            if (colorStateList != null)
            {
                return colorStateList.getDefaultColor();
            }
        }
        return Color.TRANSPARENT;
    }

    public interface OnAnimalClickListener
    {
        void onAnimalClick(Animal animal, int color);
        void onDeleteButtonClick(Animal animal);
    }

    class AnimalViewHolder extends RecyclerView.ViewHolder
    {
        TextView animalNameTextView;
        TextView animalContinentTypeTextView;
        ImageButton deleteItemButton;

        public AnimalViewHolder(@NonNull View itemView)
        {
            super(itemView);

            animalNameTextView = itemView.findViewById(R.id.animal_name);
            animalContinentTypeTextView = itemView.findViewById(R.id.continent_name);
            deleteItemButton = itemView.findViewById(R.id.deleteItemButton);
        }

        public void bind(Animal animal)
        {
            animalNameTextView.setText(animal.getName());
            animalContinentTypeTextView.setText(animal.getContinent().toString());

            deleteItemButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    onAnimalClickListenerVar.onDeleteButtonClick(animal);
                }
            });
        }
    }

    @Override
    public int getItemCount()
    {
        return animals.size();
    }
}
