package com.example.cse_dep_apps.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cse_dep_apps.R;
import com.example.cse_dep_apps.adapter.StudentAdapter;
import com.example.cse_dep_apps.model.Student;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class StudentFragment extends Fragment {

    private RecyclerView recyclerView;
    private StudentAdapter adapter;
    private List<Object> displayList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_student, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerViewStudents);
        displayList = new ArrayList<>();

        loadStudents();

        adapter = new StudentAdapter(displayList);
        recyclerView.setAdapter(adapter);
    }

    private void loadStudents() {
        try {
            InputStream is = requireContext().getAssets().open("students.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, StandardCharsets.UTF_8);

            JSONObject obj = new JSONObject(json);
            JSONArray semesters = obj.getJSONArray("semesters");

            for (int i = 0; i < semesters.length(); i++) {
                JSONObject semester = semesters.getJSONObject(i);
                String semesterName = semester.getString("semester_name");
                JSONArray shifts = semester.getJSONArray("shifts");

                for (int j = 0; j < shifts.length(); j++) {
                    JSONObject shift = shifts.getJSONObject(j);
                    String shiftName = shift.getString("shift_name");
                    JSONArray students = shift.getJSONArray("students");

                    // Add Header: "7th Semester - 1st Shift"
                    displayList.add(semesterName + " - " + shiftName);

                    for (int k = 0; k < students.length(); k++) {
                        JSONObject studentObj = students.getJSONObject(k);
                        String roll = studentObj.getString("roll");
                        String name = studentObj.getString("name");

                        // Filter out the Project Developer from the list
                        if (!roll.equals("777408")) {
                            displayList.add(new Student(roll, name));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
