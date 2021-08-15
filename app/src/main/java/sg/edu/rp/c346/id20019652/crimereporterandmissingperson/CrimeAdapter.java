package sg.edu.rp.c346.id20019652.crimereporterandmissingperson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class CrimeAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Crime> crimes;

    public CrimeAdapter(Context context, int resource,
                        ArrayList<Crime> objects) {
        super(context, resource, objects);

        this.parent_context = context;
        this.layout_id = resource;
        this.crimes = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvName = rowView.findViewById(R.id.textViewName);
        TextView tvDescription = rowView.findViewById(R.id.textViewDescription);
        TextView tvYear = rowView.findViewById(R.id.textViewYear);

        // Obtain the Android Version information based on the position
        Crime currentCrime = crimes.get(position);

        // Set values to the TextView to display the corresponding information
        tvName.setText(currentCrime.getName());
        tvDescription.setText(currentCrime.getDescription());
        tvYear.setText(currentCrime.getYear());

        return rowView;
    }
}
