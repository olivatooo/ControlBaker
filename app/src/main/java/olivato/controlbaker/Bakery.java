package olivato.controlbaker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip;

/**
 * Created by olivato on 13/05/17.
 */

public class Bakery extends AppCompatActivity {

    private EditText bn;
    private EditText br;
    private ListView lv;
    private Button btAdd;
    private String packName;
    private ArrayList buttonName;
    private ArrayList buttonRoute;
    private SharedPreferences mPrefs;
    private String rootRoute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baking_process);
        bn = (EditText)findViewById(R.id.buttonName);
        br = (EditText)findViewById(R.id.buttonRoute);
        lv = (ListView)findViewById(R.id.bakingList);
        btAdd = (Button)findViewById(R.id.buttonAdd);
        buttonName = new ArrayList();
        buttonRoute = new ArrayList();
        mPrefs = getApplicationContext().getSharedPreferences("FirstTime", 0);
        if(mPrefs.getInt("Tutorial",1)==1) {
            new SimpleTooltip.Builder(this)
                    .anchorView(bn)
                    .text(R.string.tuto)
                    .gravity(Gravity.BOTTOM)
                    .animated(true)
                    .transparentOverlay(false)
                    .build()
                    .show();
        }
        //SET ROUTE TESTING
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),rootRoute+buttonRoute.get(position),Toast.LENGTH_LONG).show();

            }
        });
        //GET THE STRINGS FROM ANOTHER CLASS
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                packName= null;
                rootRoute=null;
            } else {
                packName= extras.getString("PackName");
                rootRoute= extras.getString("RootRoute");
            }
        } else {
            packName= (String) savedInstanceState.getSerializable("PackName");
            rootRoute= (String) savedInstanceState.getSerializable("RootRoute");
        }
    }


    public void onClick(View v) {

            if (mPrefs.getInt("Tutorial", 1) == 1) {
                new SimpleTooltip.Builder(v.getContext())
                        .anchorView(lv)
                        .text(R.string.tuto2)
                        .gravity(Gravity.TOP)
                        .animated(true)
                        .transparentOverlay(false)
                        .build()
                        .show();
            }
        try {
            bakeButton(packName, bn.getText().toString(), br.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        addList(bn.getText().toString(), br.getText().toString());
        bn.setText("");
        br.setText("");
    }
    public void Continue(View view)
    {
        SharedPreferences.Editor mEditor = mPrefs.edit();
        mEditor.putInt("Tutorial", 0);
        mEditor.commit();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
    private void bakeButton(String nomelist,String item,String route) throws Exception
    {
        File file = new File(this.getFilesDir().toString(),nomelist+".png");
        FileWriter fw = new FileWriter(file,true);
        fw.write(item+";"+route+";");
        fw.close();
    }
    private void addList(String name , String route)
    {
         buttonName.add(name);
         buttonRoute.add(route);
         String[] btNames = new String[buttonRoute.size()];
         String[] btRoutes = new String[buttonRoute.size()];
         for(int i =0;i<buttonRoute.size();i++) {
              btNames[i]=buttonName.get(i).toString();
              btRoutes[i]=buttonRoute.get(i).toString();
         }
         BakerAdapter bakerAdapter = new BakerAdapter(this,btNames,btRoutes);
         lv.setAdapter(bakerAdapter);

    }
}
