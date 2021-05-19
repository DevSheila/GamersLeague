package com.example.gamersleague;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import androidx.fragment.app.DialogFragment;

public class ReviewsActivity extends DialogFragment{

    private EditText editTextComment;
    private RadioGroup surveyRadioGroup;
    private ExampleDialogListener listener;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_reviews, null);

        builder.setView(view)
                .setTitle("Comment")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dismiss();
                    }
                }).setPositiveButton("Submit", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int i) {

                String comment = editTextComment.getText().toString();
                int selectedId = surveyRadioGroup.getCheckedRadioButtonId();

                if(comment.isEmpty()){
                    Toast.makeText(getContext(), "Please Enter A Comment", Toast.LENGTH_LONG).show();
                    editTextComment.setError("Please Enter Your Username");

                }else if (selectedId == -1) {
                    Toast.makeText(getContext(), "Please Choose One Rating", Toast.LENGTH_LONG).show();
                    Log.i("value",selectedId +"");

                }else if(comment.isEmpty() && selectedId == -1) {
                    Toast.makeText(getContext(), "Please Enter A Comment", Toast.LENGTH_LONG).show();

                }else{

                    RadioButton radioButton = (RadioButton) surveyRadioGroup.findViewById(selectedId);
                    String rating = radioButton.getText().toString();

                    surveyRadioGroup.clearCheck();
                    editTextComment.getText().clear();
                    listener.applyText(comment, rating);

                }

            }
        });

        editTextComment = view.findViewById(R.id.edit_comment);
        surveyRadioGroup= view.findViewById(R.id.ratingRadioGroup);
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener =(ExampleDialogListener) context;

        }catch(ClassCastException  e){
            throw new ClassCastException(context.toString() + "must implement ExampleDialogListener");
        }
    }

    public interface ExampleDialogListener{
        void applyText(String comment,String rating);
    }
}

