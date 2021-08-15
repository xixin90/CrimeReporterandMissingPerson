package sg.edu.rp.c346.id20019652.crimereporterandmissingperson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ShowCrimesActivity extends AppCompatActivity {

    ListView lv;
    Button btnAdd;
    ArrayList<Crime> crimeList;
    CrimeAdapter crimeAdapter;

    ArrayList<String> years;
    Spinner spinner;
    ArrayAdapter<String> spinnerAdapter;

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(this);
        crimeList.clear();
        crimeList.addAll(dbh.getAllCrimes());
        crimeAdapter.notifyDataSetChanged();

        years.clear();
        years.addAll(dbh.getYears());
        spinnerAdapter.notifyDataSetChanged();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_crimes);

        setTitle(getTitle().toString() + " ~ " + getResources().getText(R.string.title_activity_second));
        lv = (ListView) this.findViewById(R.id.lv);
        btnAdd = (Button) this.findViewById(R.id.buttonAdd);
        spinner = (Spinner) this.findViewById(R.id.spinnerYear);

        DBHelper dbh = new DBHelper(this);
        crimeList = dbh.getAllCrimes();
        years = dbh.getYears();
        dbh.close();

        crimeList = new ArrayList<>();

        crimeAdapter = new CrimeAdapter(this, R.layout.row, crimeList);
        lv.setAdapter(crimeAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ShowCrimesActivity.this, ModifyCrimeActivity.class);
                i.putExtra("crime", crimeList.get(position));
                startActivity(i);
            }
        });
        spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, years);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DBHelper dbh = new DBHelper(ShowCrimesActivity.this);
                crimeList.clear();
                crimeList.addAll(dbh.getAllCrimesByYear(Integer.valueOf(years.get(position))));
                crimeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShowCrimesActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}