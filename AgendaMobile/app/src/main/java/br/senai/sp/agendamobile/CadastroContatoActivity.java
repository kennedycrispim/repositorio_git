package br.senai.sp.agendamobile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import br.senai.sp.agendamobile.dao.ContatoDao;
import br.senai.sp.agendamobile.modelo.Contato;

public class CadastroContatoActivity  extends AppCompatActivity {

    private CadastroContatoHelper helper;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_contato);

        helper = new CadastroContatoHelper(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_cadastro_contato, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        Contato contato = helper.getContato();

        ContatoDao dao = new ContatoDao(this);

        switch (item.getItemId()){
            case R.id.item_salvar:

                if(contato.getId()==0)
                {
                    dao.Salvar(contato);
                }else
                {
                    dao.atualizar(contato);
                }
                    dao.close();
                    Toast.makeText(this, contato.getNome() + " gravado com sucesso",Toast.LENGTH_SHORT).show();
                    finish();
                break;
            case R.id.item_del:
                Toast.makeText(CadastroContatoActivity.this, "DELETAR", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_configuracoes:
                Toast.makeText(CadastroContatoActivity.this, "CONFIGURAÇÕES", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_favoritos:
                Toast.makeText(CadastroContatoActivity.this, "FAVORITOS", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(CadastroContatoActivity.this, "OPÇÃO NÃO ENCONTRADA", Toast.LENGTH_SHORT).show();
        }
        
//                if(item.getItemId() == R.id.menu_salvar)
//        {
//            Toast.makeText(CadastroFilmeActivity.this, "SALVAR", Toast.LENGTH_SHORT).show();
//        }
        return super.onOptionsItemSelected(item);
    }
}

