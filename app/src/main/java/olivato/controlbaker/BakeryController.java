package olivato.controlbaker;

/**
 * Created by olivato on 13/05/17.
 */

public class BakeryController {
    //UNUSED
    /*private SQLiteDatabase db;
    private BakeryDatabase banco;
    private String[] columns = {BakeryDatabase.ID , BakeryDatabase.TITULO, BakeryDatabase.ROOTROUTE};

    public BakeryController(Context context){
        banco = new BakeryDatabase(context);
    }
    public void open () throws SQLException {
        db = banco.getWritableDatabase ();
    }
    public void close () {
        banco.close();
    }

    //I Think it's done, if anyone know to make this better please tell me
    public Note create ( String note ) {
        ContentValues values = new ContentValues();
        values.put(BakeryDatabase.TITULO, note);
        long insertId = db.insert(BakeryDatabase.ID, null, values);

        Cursor cursor = db.query(BakeryDatabase.ID,
                columns, BakeryDatabase. + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Note newNote = new Note();
        newNote.setId(cursor.getLong(0));
        newNote.setNote(cursor.getString(1));
        cursor.close();
        return newNote;
    }


    public Cursor loadData(){
        Cursor cursor;
        String[] campos =  {banco.getID(),banco.getTITULO()};
        db = banco.getReadableDatabase();
        cursor = db.query(banco.getTABELA(), campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }*/
}
