package com.example.to_do_list_mad.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.to_do_list_mad.R;
import com.example.to_do_list_mad.viewmodel.TaskViewModel;
import com.example.to_do_list_mad.model.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class AddFragment extends Fragment {
    private EditText editText;
    private RadioGroup radioGroup;
    private TaskViewModel taskViewModel;
    private RadioButton low;
    private RadioButton high;
    TextView btnDatePicker, btnTimePicker;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");

    public AddFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_add, container, false);
        Button save = rootView.findViewById(R.id.save_button);
        editText = rootView.findViewById(R.id.edit_text_task_name);

        low = rootView.findViewById(R.id.low);
        high = rootView.findViewById(R.id.high);

        btnDatePicker =  rootView.findViewById(R.id.text_view_date);
       // btnTimePicker = rootView.findViewById(R.id.btn_time);
      //  txtTime=(EditText)findViewById(R.id.in_time);
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);


                    DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                                    btnDatePicker.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
//                if (view == btnTimePicker) {
//
//                    // Get Current Time
//                    final Calendar calendar = Calendar.getInstance();
//                    mHour = c.get(Calendar.HOUR_OF_DAY);
//                    mMinute = c.get(Calendar.MINUTE);

                    // Launch Time Picker Dialog
//                    TimePickerDialog timePickerDialog = new TimePickerDialog(this,
//                            new TimePickerDialog.OnTimeSetListener() {
//
//                                @Override
//                                public void onTimeSet(TimePicker view, int hourOfDay,
//                                                      int minute) {
//
//                                    txtTime.setText(hourOfDay + ":" + minute);
//                                }
//                            }, mHour, mMinute, false);
//                    timePickerDialog.show();
//                }
            }
            });


        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if task saved
                if (saveTask()) {
                    hideKeyboard();
                    // go to ListFragment
                    NavController navController = Navigation.findNavController(requireActivity(), R.id.fragment);
                    navController.navigate(R.id.action_addFragment_to_listFragment);
                }
            }
        });
        return rootView;
    }

    //Saving task if text field is filled
    private boolean saveTask() {
        String toDo = editText.getText().toString();
        String priority = "";
        if (low.isChecked()) {
            priority = "низкий";
        } else if (high.isChecked()) {
            priority = "высокий";
        } else {
            priority = "средний";
        }

        if (toDo.isEmpty()) {
            Toast.makeText(getContext(), "Заполните все поля!", Toast.LENGTH_SHORT).show();
            return false;
        } else {
////            Task task = new Task(to Do, priority, formatter.format(Calendar.getInstance().getTime()));
            Task task = new Task(toDo, priority,formatter.format(Calendar.getInstance().getTime()), btnDatePicker.getText().toString());
            taskViewModel.insert(task);
            return true;
        }
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }
}
