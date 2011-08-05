package pl.froger.hellosharedpreferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final String PREFERENCES_NAME = "myPreferences";
	private static final String PREFERENCES_TEXT_FIELD = "textField";
	private EditText etToSave;
	private Button btnSave;

	private SharedPreferences preferences;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		preferences = getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
		etToSave = (EditText) findViewById(R.id.etToSave);
		btnSave = (Button) findViewById(R.id.btnSave);
		initButtonOnClick();
		restoreData();
	}

	private void initButtonOnClick() {
		btnSave.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				saveData();
				showToast("Data saved");
			}
		});
	}

	private void saveData() {
		SharedPreferences.Editor preferencesEditor = preferences.edit();
		String editTextData = etToSave.getText().toString();
		preferencesEditor.putString(PREFERENCES_TEXT_FIELD, editTextData);
		preferencesEditor.commit();
	}
		
	private void restoreData() {
		String textFromPreferences = preferences.getString(PREFERENCES_TEXT_FIELD, "");
		etToSave.setText(textFromPreferences);
	}
	
	private void showToast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
}