package olivato.controlbaker;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by olivato on 12/05/17.
 */

public class BakerAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] bakingDisplay;
    private final String[] bakedValue;

    public BakerAdapter(Activity context, String[] bakingDisplay,String[] bakedValue) {
        super(context, R.layout.baker_adapter, bakingDisplay);
        this.context = context;
        this.bakingDisplay = bakingDisplay;
        this.bakedValue = bakedValue;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.baker_adapter,parent, false);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.bakedDisplay);
        txtTitle.setText(bakingDisplay[position]);
        return rowView;
    }

    public String getBakedValue(int pos) {
        return bakedValue[pos];
    }
}
