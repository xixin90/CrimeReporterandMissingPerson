package sg.edu.rp.c346.id20019652.crimereporterandmissingperson;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModifyCrimeActivity extends AppCompatActivity {

    EditText etID, etName, etDescription, etYear;
    Button btnCancel, btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_crime);

        setTitle(getTitle().toString() + " ~ " + getResources().getText(R.string.title_activity_third));

        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        etID = (EditText) findViewById(R.id.etID);
        etName = (EditText) findViewById(R.id.etName);
        etDescription = (EditText) findViewById(R.id.etDescription);
        etYear = (EditText) findViewById(R.id.etYear);

        Intent i = getIntent();
        final Crime currentCrime = (Crime) i.getSerializableExtra("crime");

        etID.setText(currentCrime.getId()+"");
        etName.setText(currentCrime.getName());
        etDescription.setText(currentCrime.getDescription());
        etYear.setText(currentCrime.getYearOfCrime()+"");

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyCrimeActivity.this);
                currentCrime.setName(etName.getText().toString().trim());
                currentCrime.setDescription(etDescription.getText().toString().trim());
                int year = 0;
                try {
                    year = Integer.valueOf(etYear.getText().toString().trim());
                } catch (Exception e){
                    Toast.makeText(ModifyCrimeActivity.this, "Invalid year", Toast.LENGTH_SHORT).show();
                    return;
                }
                currentCrime.setYearOfCrime(year);

                int result = dbh.updateCrime(currentCrime);
                if (result>0){
                    Toast.makeText(ModifyCrimeActivity.this, "Crime updated", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ModifyCrimeActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ModifyCrimeActivity.this);

                //Set the dialog details
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete the crime? " + etName.getText());
                myBuilder.setCancelable(false);

                //Configure the 'positive' button
                myBuilder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                //Configure the 'negative' button
                myBuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbh = new DBHelper(ModifyCrimeActivity.this);
                        int result = dbh.deleteCrime(currentCrime.getId());
                        if (result>0){
                            Toast.makeText(ModifyCrimeActivity.this, "Crime deleted", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(ModifyCrimeActivity.this, "Delete failed", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ModifyCrimeActivity.this);

                //Set the dialog details
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to discard changes?");
                myBuilder.setCancelable(false);

                //Configure the 'positive' button
                myBuilder.setPositiveButton("Do not discard", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                //Configure the 'negative' button
                myBuilder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(ModifyCrimeActivity.this, ShowCrimesActivity.class);
                        startActivity(i);
                        dialog.dismiss();
                    }
                });

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });
    }
}