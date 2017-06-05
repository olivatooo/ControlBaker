package olivato.controlbaker;

/**
 * Created by olivato on 13/05/17.
 */
    //UNUSED
    public class BakeryDatabase {
    }
    //public class BakeryDatabase extends SQLiteOpenHelper {
    /*
    {    public static String NOME_BANCO = "buttons.db";
    public static String TABELA = "button_packs";
    public static String ID = "id";
    public static String TITULO = "titulo";
    public static String ROOTROUTE = "rootRoute";
    public static int VERSAO = 1;

    public BakeryDatabase(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+TABELA+" ("
                + ID + " integer primary key autoincrement,"
                + TITULO + " text not null,"
                + ROOTROUTE + " text not null"
                +")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABELA);
        onCreate(db);
    }*/


