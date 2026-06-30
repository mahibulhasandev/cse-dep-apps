package com.example.cse_dep_apps.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.cse_dep_apps.R;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);

        // Click listeners for Quick Access Cards
        view.findViewById(R.id.cardTeachers).setOnClickListener(v -> 
            navController.navigate(R.id.teacherFragment));

        view.findViewById(R.id.cardStudents).setOnClickListener(v -> 
            navController.navigate(R.id.studentFragment));

        view.findViewById(R.id.cardRoutine).setOnClickListener(v -> 
            navController.navigate(R.id.routineFragment));

        view.findViewById(R.id.cardSubjects).setOnClickListener(v -> 
            navController.navigate(R.id.bookFragment));

        view.findViewById(R.id.cardBooks).setOnClickListener(v -> 
            navController.navigate(R.id.bookFragment));

        view.findViewById(R.id.cardNotices).setOnClickListener(v -> 
            navController.navigate(R.id.noticeFragment));
    }
}
