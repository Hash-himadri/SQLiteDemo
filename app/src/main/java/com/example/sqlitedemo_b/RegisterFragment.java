package com.example.sqlitedemo_b;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    View view;
    DatabaseOperations dop;
    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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
        view =  inflater.inflate(R.layout.fragment_register, container, false);

        Button btnReg = view.findViewById(R.id.btnRegister);
        EditText edtName = view.findViewById(R.id.edtName);
        EditText edtSurname = view.findViewById(R.id.edtSurname);
        EditText edtID = view.findViewById(R.id.edtId);
        EditText edtMarks = view.findViewById(R.id.edtMarks);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String surname = edtSurname.getText().toString();
                String id = edtID.getText().toString();
                String marks = edtMarks.getText().toString();

                dop.putInformation(dop, name, surname, id, marks);
                Toast.makeText(getContext(), "Student registered", Toast.LENGTH_SHORT).show();
                edtID.setText("");
                edtMarks.setText("");
                edtName.setText("");
                edtSurname.setText("");

            }
        });
        return view;
    }
}