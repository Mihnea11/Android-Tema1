package com.example.zooapp.Utility;

import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zooapp.R;

import java.util.List;

public class AnimalsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private List<Animal> animals;
    private OnAnimalClickListener onAnimalClickListener;

    public AnimalsAdapter(List<Animal> animals, OnAnimalClickListener onAnimalClickListener)
    {
        this.animals = animals;
        this.onAnimalClickListener = onAnimalClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        return animals.get(position).getContinent().getValue();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        AnimalViewHolder viewHolder;
        View view = null;

        Continents chosenContinent = Continents.values()[viewType];
        switch (chosenContinent)
        {
            case Europe:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.europa, parent, false);
                break;
            case Asia:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.asia, parent, false);
                break;
            case Africa:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.africa, parent, false);
                break;
            case America:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.america, parent, false);
                break;
            case Australia:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.australia, parent, false);
                break;
        }

        viewHolder = new AnimalViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        if(holder instanceof AnimalViewHolder)
        {
            Animal animal = animals.get(position);

            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int backgroundColor = ((ColorDrawable) holder.itemView.getBackground()).getColor();

                    onAnimalClickListener.onAnimalClick(animal, backgroundColor);
                }
            });

            ((AnimalViewHolder)holder).bind(animal);
        }
    }


    public interface OnAnimalClickListener {
        void onAnimalClick(Animal animalCard, int color);
    }

    class AnimalViewHolder extends RecyclerView.ViewHolder
    {

        TextView animalNameTextView;
        TextView animalContinentTypeTextView;

        public AnimalViewHolder(@NonNull View itemView)
        {
            super(itemView);

            animalNameTextView = itemView.findViewById(R.id.animal_name);
            animalContinentTypeTextView = itemView.findViewById(R.id.continent_name);
        }

        public void bind(Animal animal)
        {
            animalNameTextView.setText(animal.getName());
            animalContinentTypeTextView.setText(animal.getContinent().toString());
        }
    }

    @Override
    public int getItemCount()
    {
        return animals.size();
    }
}
