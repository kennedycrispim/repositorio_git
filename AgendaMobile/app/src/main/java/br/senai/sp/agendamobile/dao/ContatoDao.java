package br.senai.sp.agendamobile.dao;

import
        android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.senai.sp.agendamobile.modelo.Contato;

public class ContatoDao extends SQLiteOpenHelper
{
    public ContatoDao(Context context){super(context, "db_Contato", null, 1);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE tbl_contato " +
                "( id INTEGER PRIMARY KEY, " +
                "nome TEXT NOT NULL, " +
                "endereco TEXT NOT NULL, " +
                "telefone INTEGER NOT NULL, "+
                "email TEXT NOT NULL, " +
                "endereco_linkedin INTEGER NOT NULL)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void Salvar(Contato contato)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();

        dados.put("nome", contato.getNome());
        dados.put("endereco", contato.getEndereco());
        dados.put("telefone", contato.getTelefone());
        dados.put("email", contato.getEmail());
        dados.put("endereco_linkedin", contato.getEnderecolikedin());

        db.insert("tbl_contato", null, dados);
    }

    public List<Contato> getContatos()
    {

        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM tbl_contato";

        Cursor c = db.rawQuery(sql, null);

        List<Contato> contatos = new ArrayList<>();

        while (c.moveToNext())
        {
            Contato contato = new Contato();
            contato.setId(c.getInt(c.getColumnIndex("id")));
            contato.setNome(c.getString(c.getColumnIndex("nome")));
            contato.setEndereco(c.getString(c.getColumnIndex("endereco")));
            contato.setEmail(c.getString(c.getColumnIndex("email")));
            contato.setEnderecolikedin(c.getString(c.getColumnIndex("endereco_linkedin")));
            contatos.add(contato);
        }

        return contatos;
    }

    public  void excluir(Contato contato)
    {
        SQLiteDatabase db = getWritableDatabase();

        String[] params = {String.valueOf(contato.getId())};

        db.delete("tbl_contato", "id = ?", params);
    }

    public void atualizar(Contato contato) {
        SQLiteDatabase db = getWritableDatabase();

        String[] params = {String.valueOf(contato.getId())};
        ContentValues dados = new ContentValues();
        db.update("tbl_contato", dados, "id = ?", params);
    }
}
