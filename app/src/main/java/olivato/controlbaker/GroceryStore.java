package olivato.controlbaker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by olivato on 13/05/17.
 */
//TODO Editar botões
//TODO Excluir botões
public class GroceryStore extends AppCompatActivity {

    private String[] listas;
    private ListView lv;
    String[] routes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocery_store);

        lv = (ListView)findViewById(R.id.listOfControllers);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(),TheButtons.class);
                i.putExtra("ListName",lv.getItemAtPosition(position).toString());
                i.putExtra("MainRoute",routes[position].toString());
                startActivity(i);

            }
        });
        try {
            CarregarListas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void CarregarListas() throws Exception
    {
        File file = new File(this.getFilesDir()+"/buttonPackList.baked");
        BufferedReader bf = new BufferedReader(new FileReader(file));
        String saida="";
        String linha;

        while((linha=bf.readLine()) != null)
        {
            if((linha!="temp")&&(linha!=null)&&(linha!="null")) {
                saida = saida + linha;
            }
        }
        bf.close();
        listas=saida.split(";");
        String[] names;

        routes = new String[listas.length/2];
        names = new String[listas.length/2];
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
        ControllerAdapter adapter = new ControllerAdapter(this,names);
        lv.setAdapter(adapter);
    }

}
