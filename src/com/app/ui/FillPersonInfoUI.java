package com.app.ui;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.app.base.BaseUI;
import com.app.base.R;

public class FillPersonInfoUI extends BaseUI {
	
	private EditText nameEditText, phoneEditText, QQEditText;
	private Spinner majorGroup, gradeGroup;
	private ArrayAdapter<CharSequence> majorGroupAdapter, gradeGroupAdapter;
	private RadioGroup sexRadioGroup;
	private RadioButton male, female;
	private Button personInfoSubmit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fill_person_info);
		initViews();
		initEvents();
	}
	
	private void initViews() {
		nameEditText = (EditText)findViewById(R.id.nameEditText);
		phoneEditText = (EditText)findViewById(R.id.phoneEditText);
		QQEditText = (EditText)findViewById(R.id.QQEditText);
		majorGroup = (Spinner)findViewById(R.id.majorGroup);
		gradeGroup = (Spinner)findViewById(R.id.gradeGroup);
		sexRadioGroup = (RadioGroup)findViewById(R.id.sexRadioGroup);
		male = (RadioButton)findViewById(R.id.male);
		female = (RadioButton)findViewById(R.id.female);
		personInfoSubmit = (Button)findViewById(R.id.personInfoSubmit);
	}
	
	private void initEvents() {
		majorGroupAdapter = ArrayAdapter.createFromResource(this, R.array.major_group, R.layout.spinner_text);
		majorGroup.setAdapter(majorGroupAdapter);
		gradeGroupAdapter = ArrayAdapter.createFromResource(this, R.array.grade_group, R.layout.spinner_text);
		gradeGroup.setAdapter(gradeGroupAdapter);
		
		personInfoSubmit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				FillPersonInfoUI.this.forward(MainUI.class);
			}
		});
	}

}
