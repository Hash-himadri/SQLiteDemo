package com.example.sqlitedemo_b;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditFragment extends Fragment {


    View view;
    String ID;
    DatabaseOperations dop;

    public EditFragment() {
        // Required empty public constructor
    }
    public static EditFragment newInstance(String param1, String param2) {
        EditFragment fragment = new EditFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dop = new DatabaseOperations(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_edit, container, false);
        Button btnUpdate = view.findViewById(R.id.btnUpdate);
        Button btnDelete = view.findViewById(R.id.btnDelete);
        Button btnRetrieve = view.findViewById(R.id.btnRetreive);
        EditText edtIDEdit = view.findViewById(R.id.edtIDEdit);
        Button btnClear = view.findViewById(R.id.btnClear);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ID = edtIDEdit.getText().toString();
                dop.deleteInfo(dop, ID);
                Toast.makeText(getContext(), "Student with ID: "+ID+" is deleted", Toast.LENGTH_SHORT).show();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtIDEdit.setText("");
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ID = edtIDEdit.getText().toString();
                updatedialog();


            }
        });

        btnRetrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ID = edtIDEdit.getText().toString();
                retrieveDialog();
            }
        });

        return view;
    }

    private void retrieveDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final TextView txtView = new TextView(getContext());

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        txtView.setLayoutParams(lp);
        Cursor CR = dop.getInfo(dop, ID);


            CR.moveToFirst();

            String id = CR.getString(0);
            String name = CR.getString(1);
            String surname = CR.getString(2);
            String marks = CR.getString(3);

            txtView.setText("ID: "+id+"\nName: "+name+"\nSurname: "+surname+"\nMarks: "+marks);


        builder.setView(txtView)
                .setTitle("Student Info")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        builder.show();
    }

    private void updatedialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        final EditText edtTxt = new EditText(getContext());

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        edtTxt.setLayoutParams(lp);

        builder.setView(edtTxt)
                .setTitle("Update Marks of "+ID)
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dop.UpdateInfo(dop, edtTxt.getText().toString(), ID);
                        Toast.makeText(getContext(), "Student: "+ID+" marks Updated to "+edtTxt.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "Update Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
        builder.show();

    }
}