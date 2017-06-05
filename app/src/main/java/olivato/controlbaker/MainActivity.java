package olivato.controlbaker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;

import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip;

public class MainActivity extends AppCompatActivity {
    //Declarations
    private SharedPreferences mPrefs;
    private ImageButton bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = (ImageButton)findViewById(R.id.addButton);
        mPrefs = getApplicationContext().getSharedPreferences("FirstTime", 0);
        if(mPrefs.getInt("Tutorial",1)==1) {
            new SimpleTooltip.Builder(this)
                    .anchorView(bt)
                    .text(R.string.AddNewButton)
                    .gravity(Gravity.END)
                    .animated(true)
                    .transparentOverlay(false)
                    .build()
                    .show();
        }


    }

    public void createNewButtonPack(View view)
    {
        Intent i = new Intent(this,PreConfigure.class);
        startActivity(i);
    }

    public void listMyButtons(View view)
    {
        Intent i = new Intent(this,GroceryStore.class);
        startActivity(i);
    }

}
