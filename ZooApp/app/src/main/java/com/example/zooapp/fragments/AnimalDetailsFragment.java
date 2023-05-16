package com.example.zooapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.zooapp.R;
import com.example.zooapp.models.Animal;

public class AnimalDetailsFragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_animal_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button exitButton = view.findViewById(R.id.button_exit_details);
        exitButton.setOnClickListener(v -> {
            requireActivity().finish();
        });

        TextView animalNameDetails = view.findViewById(R.id.animal_name_details);
        TextView animalContinentDetails = view.findViewById(R.id.animal_continent_details);

        // find the ConstraintLayout
        androidx.constraintlayout.widget.ConstraintLayout constraintLayout = view.findViewById(R.id.animal_continent_details);

        Animal animal = (Animal) getArguments().getSerializable("animal");
        int color = getArguments().getInt("color");

        Log.d("AnimalDetailsFragment", "onViewCreated: animal=" + animal + ", color=" + color);

        if (animal != null) {
            animalNameDetails.setText(animal.getName());
            animalContinentDetails.setText(animal.getContinent().toString());
        }

        // update the background color of the ConstraintLayout
        constraintLayout.setBackgroundColor(color);

        Button backButton = view.findViewById(R.id.button_back_details);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(R.id.action_animalDetailsFragment_to_animalsFragment);
            }
        });
    }
}