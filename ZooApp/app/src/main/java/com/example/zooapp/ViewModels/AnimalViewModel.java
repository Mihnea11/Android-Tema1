package com.example.zooapp.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.zooapp.adapters.AnimalDatabaseAdapter;
import com.example.zooapp.models.Animal;

import java.util.List;

public class AnimalViewModel extends AndroidViewModel
{
    private final AnimalDatabaseAdapter animalDatabaseAdapter;
    private final LiveData<List<Animal>> allAnimals;

    public AnimalViewModel(@NonNull Application application)
    {
        super(application);
        animalDatabaseAdapter = new AnimalDatabaseAdapter(application);
        allAnimals = animalDatabaseAdapter.getAllAnimals();
    }

    public LiveData<List<Animal>> getAllAnimals()
    {
        return allAnimals;
    }

    public void insertOrUpdate(Animal animal)
    {
        animalDatabaseAdapter.insertOrUpdate(animal);
    }

    public Animal getAnimalByName(String animalName)
    {
        return animalDatabaseAdapter.getAnimalByName(animalName);
    }

    public void delete(Animal animal) {
        animalDatabaseAdapter.delete(animal);
    }
}
