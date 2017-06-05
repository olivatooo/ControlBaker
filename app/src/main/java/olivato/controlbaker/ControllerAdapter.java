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

public class ControllerAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] bakingDisplay;
    //private final String[] rootRoute;

    public ControllerAdapter(Activity context, String[] bakingDisplay) {
        super(context, R.layout.controller_adapter, bakingDisplay);
        this.context = context;
        this.bakingDisplay = bakingDisplay;
        //this.rootRoute = rootRoute;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.controller_adapter,parent, false);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.bakedDisplay2);
        txtTitle.setText(bakingDisplay[position]);
        return rowView;
    }




}
