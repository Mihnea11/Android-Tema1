package com.example.zooapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.zooapp.R;
import com.example.zooapp.Utility.Animal;
import com.example.zooapp.Utility.AnimalsAdapter;
import com.example.zooapp.Utility.Continents;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AnimalsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnimalsFragment extends Fragment implements AnimalsAdapter.OnAnimalClickListener
{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AnimalsFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AnimalsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AnimalsFragment newInstance(String param1, String param2)
    {
        AnimalsFragment fragment = new AnimalsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_animals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_items);

        Button exitButton = view.findViewById(R.id.button_exit_animals);
        exitButton.setOnClickListener(v ->
        {
            requireActivity().finish();
        });

        Button menuButton = view.findViewById(R.id.button_menu_animals);
        menuButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Navigation.findNavController(getView()).navigate(R.id.action_animalsFragment_to_mainFragment);
            }
        });

        List<Animal> animals = new ArrayList<Animal>();
        animals.add(new Animal("Wolf", Continents.Europe));
        animals.add(new Animal("Brown bear", Continents.Europe));
        animals.add(new Animal("Lynx", Continents.Europe));
        animals.add(new Animal("Red deer", Continents.Europe));
        animals.add(new Animal("European bison", Continents.Europe));
        animals.add(new Animal("Golden eagle", Continents.Europe));
        animals.add(new Animal("Chamois", Continents.Europe));
        animals.add(new Animal("Bearded vulture", Continents.Europe));
        animals.add(new Animal("Scorpio", Continents.Europe));
        animals.add(new Animal("Jackal", Continents.Europe));

        animals.add(new Animal("Lion", Continents.Africa));
        animals.add(new Animal("Elephant", Continents.Africa));
        animals.add(new Animal("African buffalo", Continents.Africa));
        animals.add(new Animal("Giraffe", Continents.Africa));
        animals.add(new Animal("Impala", Continents.Africa));
        animals.add(new Animal("Oryx", Continents.Africa));
        animals.add(new Animal("Baboon", Continents.Africa));
        animals.add(new Animal("African penguin", Continents.Africa));
        animals.add(new Animal("Chimpanzee", Continents.Africa));
        animals.add(new Animal("Termite", Continents.Africa));

        animals.add(new Animal("Bald eagle", Continents.America));
        animals.add(new Animal("Raccoon", Continents.America));
        animals.add(new Animal("Mountain lion", Continents.America));
        animals.add(new Animal("American alligator", Continents.America));
        animals.add(new Animal("Pronghorn", Continents.America));
        animals.add(new Animal("Groundhog", Continents.America));
        animals.add(new Animal("American bison", Continents.America));
        animals.add(new Animal("Beaver", Continents.America));
        animals.add(new Animal("White-tailed deer", Continents.America));
        animals.add(new Animal("Striped skunk", Continents.America));

        animals.add(new Animal("Kangaroo", Continents.Australia));
        animals.add(new Animal("Wombat", Continents.Australia));
        animals.add(new Animal("Koala", Continents.Australia));
        animals.add(new Animal("Quokka", Continents.Australia));
        animals.add(new Animal("Possum", Continents.Australia));
        animals.add(new Animal("Saltwater crocodile", Continents.Australia));
        animals.add(new Animal("Quoll", Continents.Australia));
        animals.add(new Animal("Cockatoos", Continents.Australia));
        animals.add(new Animal("Redback spider", Continents.Australia));
        animals.add(new Animal("Huntsman spider", Continents.Australia));

        animals.add(new Animal("Asian black bear", Continents.Asia));
        animals.add(new Animal("Tiger", Continents.Asia));
        animals.add(new Animal("Asian elephant", Continents.Asia));
        animals.add(new Animal("Red panda", Continents.Asia));
        animals.add(new Animal("King cobra", Continents.Asia));
        animals.add(new Animal("Leopard", Continents.Asia));
        animals.add(new Animal("Indian cobra", Continents.Asia));
        animals.add(new Animal("Bengal tiger", Continents.Asia));
        animals.add(new Animal("Malayan tapir", Continents.Asia));
        animals.add(new Animal("Urial", Continents.Asia));

        AnimalsAdapter adapter = new AnimalsAdapter(animals, this);

        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onAnimalClick(Animal animalCard, int color)
    {
        Bundle bundle = new Bundle();

        bundle.putSerializable("animal", animalCard);
        bundle.putInt("color", color);

        Navigation.findNavController(getView()).navigate(R.id.action_animalsFragment_to_animalDetailsFragment, bundle);
    }
}