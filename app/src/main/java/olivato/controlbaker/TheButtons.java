package olivato.controlbaker;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by olivato on 13/05/17.
 */
//TODO Editar botões
//TODO Excluir botões
public class TheButtons extends AppCompatActivity {
    private ListView lv;
    private String listName;
    private String[] routes;
    private String[] names;
    private String mainRoute;
    private OkHttpClient client;
    private String longSelected="";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_store);
        client = new OkHttpClient();
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                listName="Control Baker";
                mainRoute=null;
            } else {
                listName= extras.getString("ListName");
                mainRoute=extras.getString("MainRoute");
            }
        } else {
            listName= (String) savedInstanceState.getSerializable("ListName");
            mainRoute= (String) savedInstanceState.getSerializable("MainRoute");
        }

        OkHttpClient client = new OkHttpClient();
        lv = (ListView)findViewById(R.id.listOfControllers);
        ContextCompat.getDrawable(getApplicationContext(), R.drawable.simple_listview);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(),mainRoute.trim()+routes[position],Toast.LENGTH_LONG).show();
                new JSONTask().execute(mainRoute.trim()+routes[position]);
            }
        });
        try {
            CarregarListaX(listName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        getSupportActionBar().setTitle(listName);
        registerForContextMenu(lv);
        final DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        //Yes button clicked
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked
                        break;
                }
            }
        };
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,int pos, long id) {
                // TODO Auto-generated method stub
                longSelected = lv.getItemAtPosition(pos).toString();

                AlertDialog.Builder builder = new AlertDialog.Builder(arg1.getContext());
                builder.setMessage(R.string.opt)
                        .setPositiveButton(R.string.edita1, dialogClickListener)
                        .setNegativeButton(R.string.delete12, dialogClickListener).show();

                return true;
            }
        });
    }

    public void CarregarListaX(String nomelista) throws Exception
    {
        File file = new File(this.getFilesDir()+"/"+nomelista.trim()+".png");
        BufferedReader bf = new BufferedReader(new FileReader(file));
        String saida="";
        String linha;
        while((linha=bf.readLine()) != null)
        {
            saida = saida+linha;
        }
        bf.close();
        String[] listas=saida.split(";");
        routes = new String[(listas.length/2)];
        names = new String[(listas.length/2)];
        int j=0,k=0;
        for(int i=0;i<listas.length;i++)
        {
            if(i%2==0) {
                names[j]=listas[i];
                j++;
            }else
            {
                routes[k]=listas[i];
                k++;
            }
        }
        BakerAdapter adapter = new BakerAdapter(this,names,routes);
        lv.setAdapter(adapter);
    }
    //CAPOEIRA
    private class JSONTask extends AsyncTask<String,String,String> {
        ProgressDialog myDialog;


        @Override
        protected void onPreExecute() {
            myDialog = ProgressDialog.show(TheButtons.this, getString(R.string.loading), getString(R.string.active_internet), true);


        }

        protected String doInBackground(String... params) {


            try {
            sendNudes(params[0]);
                return "Ok";
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "Ops";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            myDialog.dismiss();


        }
    }
    public void Excluir(String excluir) {

    }
    public boolean sendNudes(String nudes) {
        Request request = new Request.Builder()
                .url(nudes)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}
