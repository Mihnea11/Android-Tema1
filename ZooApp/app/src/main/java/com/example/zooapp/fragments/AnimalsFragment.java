package com.example.zooapp.fragments;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.zooapp.R;
import com.example.zooapp.ViewModels.AnimalViewModel;
import com.example.zooapp.adapters.AnimalItemAdapter;
import com.example.zooapp.models.Animal;
import com.example.zooapp.models.Continent;
import java.util.List;


public class AnimalsFragment extends Fragment implements AnimalItemAdapter.OnAnimalClickListener
{
    private EditText editTextAnimal, editTextContinent;
    private AnimalViewModel animalViewModel;
    private List<Animal> currentAnimals;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_animals, container, false);

        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        Button menuButton = view.findViewById(R.id.button_menu_animals);
        menuButton.setOnClickListener(v -> {
            Navigation.findNavController(getView()).navigate(R.id.action_animalsFragment_to_mainFragment);
        });

        Button exitButton = view.findViewById(R.id.button_exit_animals);
        exitButton.setOnClickListener(v -> {
            requireActivity().finish();
        });

        Button addAnimalButton = view.findViewById(R.id.addAnimalButton);
        addAnimalButton.setOnClickListener(v -> {
            addOrUpdateAnimal(editTextAnimal.getText().toString(), editTextContinent.getText().toString());
        });

        addAnimalButton.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN)
            {
                addAnimalButton.setTextSize(22);
                addAnimalButton.setTypeface(null, Typeface.BOLD);
            } else if (event.getAction() == MotionEvent.ACTION_UP)
            {
                addAnimalButton.setTextSize(17);
                addAnimalButton.setTypeface(null, Typeface.NORMAL);
            }
            return false; // return false so that the OnClickListener still works
        });

        editTextAnimal = view.findViewById(R.id.editTextAnimal);
        editTextContinent = view.findViewById(R.id.editTextContinent);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_items);

        AnimalItemAdapter adapter = new AnimalItemAdapter(this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        animalViewModel = new ViewModelProvider(requireActivity()).get(AnimalViewModel.class);
        animalViewModel.getAllAnimals().observe(getViewLifecycleOwner(), animals ->
        {
            currentAnimals = animals;
            adapter.setAnimals(animals);
        });
    }

    @Override
    public void onAnimalClick(Animal animal, int color)
    {

    }

    @Override
    public void onDeleteButtonClick(Animal animal)
    {
        animalViewModel.delete(animal);
        Toast.makeText(getContext(), "Animal deleted successfully.", Toast.LENGTH_SHORT).show();
    }

    private Animal findAnimalByName(List<Animal> animals, String animalName)
    {
        for (Animal animal : animals)
        {
            if (animal.getName().equalsIgnoreCase(animalName))
            {
                return animal;
            }
        }
        return null;
    }

    private void addOrUpdateAnimal(String animalName, String continentName)
    {
        boolean isAnimalNameValid = validateAnimalName(animalName);
        boolean isContinentNameValid = validateContinentName(continentName);

        if (isAnimalNameValid && isContinentNameValid) {
            Animal existingAnimal = findAnimalByName(currentAnimals, animalName);

            Animal animal = new Animal(animalName.trim(), Continent.valueOf(continentName.toUpperCase().trim()));
            animalViewModel.insertOrUpdate(animal);

            boolean isNewAnimal = existingAnimal == null;
            if (isNewAnimal) {
                Toast.makeText(getContext(), "Animal added successfully.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Animal updated successfully.", Toast.LENGTH_SHORT).show();
            }

            editTextAnimal.setText("");
            editTextContinent.setText("");
        }
    }

    private boolean validateAnimalName(String animalName)
    {
        if (TextUtils.isEmpty(animalName.trim())) {
            Toast.makeText(getContext(), "Invalid input. Please enter a valid animal name.", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean validateContinentName(String continentName)
    {
        if (TextUtils.isEmpty(continentName.trim())) {
            Toast.makeText(getContext(), "Invalid input. Please enter a valid continent.", Toast.LENGTH_LONG).show();
            return false;
        }

        try {
            Continent.valueOf(continentName.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            Toast.makeText(getContext(), "Invalid input. Please enter a valid continent.", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}