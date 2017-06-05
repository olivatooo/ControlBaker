package olivato.controlbaker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileWriter;

import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip;

/**
 * Created by olivato on 13/05/17.
 */

public class PreConfigure extends AppCompatActivity {
    private EditText rr;
    private EditText pn;
    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.baking_config);
        rr = (EditText)findViewById(R.id.rootRoute);
        pn = (EditText)findViewById(R.id.packName);
        mPrefs = getApplicationContext().getSharedPreferences("FirstTime", 0);
        rr.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){

                }else {
                    //LOST FOCUS
                    if(mPrefs.getInt("Tutorial",1)==1) {
                        new SimpleTooltip.Builder(v.getContext())
                                .anchorView(pn)
                                .text(R.string.pn)
                                .gravity(Gravity.BOTTOM)
                                .animated(true)
                                .transparentOverlay(false)
                                .build()
                                .show();
                    }
                }
            }
        });


        if(mPrefs.getInt("Tutorial",1)==1) {
            new SimpleTooltip.Builder(this)
                    .anchorView(rr)
                    .text(R.string.path)
                    .gravity(Gravity.BOTTOM)
                    .animated(true)
                    .transparentOverlay(false)
                    .build()
                    .show();
        }


    }

    public void onClick(final View v) throws Exception {
        if(v.getId()==R.id.nextAndSave)
        {
            savePackButtonName(pn.getText().toString(),rr.getText().toString());
            Intent i = new Intent(PreConfigure.this, Bakery.class);
            i.putExtra("PackName", pn.getText().toString());
            i.putExtra("RootRoute",rr.getText().toString());
            startActivity(i);
            finish();
        }
    }
    private void savePackButtonName(String nomelist, String route) throws Exception
    {
        File file = new File(this.getFilesDir().toString()+"/","buttonPackList.baked");
        FileWriter fw = new FileWriter(file,true);
        fw.write(nomelist+";"+route+";");
        fw.close();
    }
}
