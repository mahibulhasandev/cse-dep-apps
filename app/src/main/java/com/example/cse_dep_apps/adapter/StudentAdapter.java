package com.example.cse_dep_apps.adapter;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cse_dep_apps.R;
import com.example.cse_dep_apps.model.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_STUDENT = 1;

    private final List<Object> items;

    public StudentAdapter(List<Object> items) {
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof String) {
            return TYPE_HEADER;
        }
        return TYPE_STUDENT;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student_header, parent, false);
            return new HeaderViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
            return new StudentViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            ((HeaderViewHolder) holder).tvTitle.setText((String) items.get(position));
        } else if (holder instanceof StudentViewHolder) {
            StudentViewHolder studentHolder = (StudentViewHolder) holder;
            Student student = (Student) items.get(position);
            studentHolder.tvName.setText(student.getName());
            studentHolder.tvRoll.setText("Roll: " + student.getRoll());

            // Reset to default styles
            studentHolder.tvName.setTypeface(null, Typeface.NORMAL);
            studentHolder.tvRoll.setTextColor(Color.parseColor("#757575"));
            studentHolder.badge.setVisibility(View.GONE);
            studentHolder.cardView.setCardBackgroundColor(ContextCompat.getColor(studentHolder.itemView.getContext(), R.color.white));
            studentHolder.cardView.setStrokeColor(Color.parseColor("#F0F0F0"));

            // Special Highlight for MAHIBUL HASAN (if still in list, though we aim to filter him out)
            if (student.getRoll().equals("777408")) {
                studentHolder.cardView.setCardBackgroundColor(ContextCompat.getColor(studentHolder.itemView.getContext(), R.color.blue_50));
                studentHolder.cardView.setStrokeColor(ContextCompat.getColor(studentHolder.itemView.getContext(), R.color.blue_700));
                studentHolder.tvName.setTypeface(null, Typeface.BOLD);
                studentHolder.tvRoll.setTextColor(ContextCompat.getColor(studentHolder.itemView.getContext(), R.color.blue_700));
                studentHolder.badge.setVisibility(View.VISIBLE);
                studentHolder.badge.setText("Developer");
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvRoll, badge;
        com.google.android.material.card.MaterialCardView cardView;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.studentName);
            tvRoll = itemView.findViewById(R.id.studentRoll);
            badge = itemView.findViewById(R.id.developerBadge);
            cardView = itemView.findViewById(R.id.studentCard);
        }
    }

    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.headerTitle);
        }
    }
}
