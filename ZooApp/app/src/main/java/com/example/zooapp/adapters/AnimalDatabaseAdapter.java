package com.example.zooapp.adapters;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import com.example.zooapp.dao.AnimalDao;
import com.example.zooapp.dao.AppDatabase;
import com.example.zooapp.models.Animal;

import java.util.List;

public class AnimalDatabaseAdapter
{
    private AnimalDao animalDao;
    private LiveData<List<Animal>> allAnimals;

    public AnimalDatabaseAdapter(Application application)
    {
        AppDatabase db = AppDatabase.getInstance(application);
        animalDao = db.animalDao();
        allAnimals = animalDao.getAll();
    }

    public LiveData<List<Animal>> getAllAnimals()
    {
        return allAnimals;
    }

    public Animal getAnimalByName(String animalName)
    {
        return animalDao.getAnimalByName(animalName);
    }

    public void insertOrUpdate(Animal animal)
    {
        new InsertOrUpdateAsyncTask(animalDao).execute(animal);
    }

    private static class InsertOrUpdateAsyncTask extends AsyncTask<Animal, Void, Void>
    {
        private AnimalDao animalDao;

        InsertOrUpdateAsyncTask(AnimalDao animalDao)
        {
            this.animalDao = animalDao;
        }

        @Override
        protected Void doInBackground(Animal... animals)
        {
            Animal existingAnimal = animalDao.getAnimalByName(animals[0].getName());

            if (existingAnimal != null)
            {
                existingAnimal.setContinent(animals[0].getContinent());
                animalDao.insert(existingAnimal);
            }
            else
            {
                animalDao.insert(animals[0]);
            }

            return null;
        }
    }

    public void delete(Animal animal)
    {
        new DeleteAsyncTask(animalDao).execute(animal);
    }

    private static class DeleteAsyncTask extends AsyncTask<Animal, Void, Void>
    {
        private AnimalDao animalDao;

        DeleteAsyncTask(AnimalDao animalDao)
        {
            this.animalDao = animalDao;
        }

        @Override
        protected Void doInBackground(Animal... animals)
        {
            animalDao.delete(animals[0].getName());
            return null;
        }
    }
}
