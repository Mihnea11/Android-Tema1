package com.example.zooapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.zooapp.models.Animal;
import java.util.List;

@Dao
public interface AnimalDao
{
    @Query("SELECT * FROM animals")
    LiveData<List<Animal>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Animal animal);

    @Query("DELETE FROM animals WHERE name = :name")
    void delete(String name);

    @Query("SELECT * FROM animals WHERE name = :animalName COLLATE NOCASE LIMIT 1")
    Animal getAnimalByName(String animalName);
}
