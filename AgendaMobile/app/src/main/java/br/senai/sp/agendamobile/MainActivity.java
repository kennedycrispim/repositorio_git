package br.senai.sp.agendamobile;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.senai.sp.agendamobile.dao.ContatoDao;
import br.senai.sp.agendamobile.modelo.Contato;

public class MainActivity extends AppCompatActivity {

    private ListView ListaContatos;
    private Button btnNovoContato;

    private EditText txt_nome;
    private EditText txt_telefone;
    private EditText txt_email;
    private EditText txt_endereco;
    private EditText txt_enderecolinkedin;

    private TextInputLayout layout_txt_nome;
    private TextInputLayout layout_txt_email;
    private TextInputLayout layout_txt_telefone;
    private TextInputLayout layout_txt_endereco;
    private TextInputLayout layout_txt_enderecolinkedin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListaContatos = findViewById(R.id.lista_contatos);

        txt_nome = findViewById(R.id.txt_nome);
        txt_endereco = findViewById(R.id.txt_endereco);
        txt_telefone = findViewById(R.id.txt_telefone);
        txt_email = findViewById(R.id.txt_email);
        txt_enderecolinkedin = findViewById(R.id.txt_enderecolinkedin);

        layout_txt_nome = findViewById(R.id.layout_txt_nome);
        layout_txt_endereco = findViewById(R.id.layout_txt_endereco);
        layout_txt_telefone = findViewById(R.id.layout_txt_telefone);
        layout_txt_email = findViewById(R.id.layout_txt_email);
        layout_txt_enderecolinkedin = findViewById(R.id.layout_txt_enderecolinkedin);

        btnNovoContato = findViewById(R.id.btn_cadastrar);
        btnNovoContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cadastroContato = new Intent(MainActivity.this,CadastroContatoActivity.class);
                startActivity(cadastroContato);
            }
        });
        registerForContextMenu(ListaContatos);

        ListaContatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contato contato = (Contato) ListaContatos.getItemAtPosition(position);
                Intent cadastro = new Intent (MainActivity.this, CadastroContatoActivity.class);
                cadastro.putExtra("contato", contato);
                startActivity(cadastro);
                Toast.makeText(MainActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater  = getMenuInflater();
        menuInflater.inflate(R.menu.menu_context_contato, menu);

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        final ContatoDao dao = new ContatoDao(this);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Contato  contato = (Contato) ListaContatos.getItemAtPosition(info.position);

        Toast.makeText(this, contato.getNome(), Toast.LENGTH_SHORT).show();

        new AlertDialog.Builder(this)
                .setTitle("Excluir Contato")
                .setMessage("Tem certeza que deseja deletar " + contato.getNome() + "?")
                .setPositiveButton("Sim",

                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dao.excluir(contato);
                                dao.close();
                                carregarLista();
                            }
                        }).setNegativeButton("Não", null).show();

        //Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume()
    {
        Toast.makeText(this, "Voltei", Toast.LENGTH_LONG).show();
        carregarLista();
        super.onResume();
    }

    private void carregarLista()
    {
        ContatoDao dao = new ContatoDao(this);
        List<Contato> contatos = dao.getContatos();
        dao.close();

        ArrayAdapter<Contato> listaContatosAdapter = new ArrayAdapter<Contato>(this, android.R.layout.simple_list_item_1, contatos);

        ListaContatos.setAdapter(listaContatosAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
         //Toast.makeText(Ca item.getItemId() , Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    private boolean validar() {
        boolean validado = true;
        if(txt_nome.getText().toString().isEmpty()){
            layout_txt_nome.setErrorEnabled(true);
            layout_txt_nome.setError("Por favor digite o seu nome");
            validado = false;
        }else{
            layout_txt_nome.setErrorEnabled(false);
        }

        if(txt_telefone.getText().toString().isEmpty()){
            layout_txt_telefone.setErrorEnabled(true);
            layout_txt_telefone.setError("Por favor digite o seu nome");
            validado = false;
        }else{
            layout_txt_telefone.setErrorEnabled(false);
        }

        if(txt_email.getText().toString().isEmpty()){
            layout_txt_email.setErrorEnabled(true);
            layout_txt_email.setError("Por favor digite o seu nome");
            validado = false;
        }else{
            layout_txt_email.setErrorEnabled(false);
        }

        if(txt_endereco.getText().toString().isEmpty()){
            layout_txt_endereco.setErrorEnabled(true);
            layout_txt_endereco.setError("Por favor digite o seu nome");
            validado = false;
        }else{
            layout_txt_endereco.setErrorEnabled(false);
        }

        if(txt_enderecolinkedin.getText().toString().isEmpty()){
            layout_txt_enderecolinkedin.setErrorEnabled(true);
            layout_txt_enderecolinkedin.setError("Por favor digite o seu nome");
            validado = false;
        }else{
            layout_txt_enderecolinkedin.setErrorEnabled(false);
        }
        return validado;
    }
}
