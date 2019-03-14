package br.senai.sp.agendamobile;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;
import br.senai.sp.agendamobile.modelo.Contato;

public class CadastroContatoHelper {

    private EditText txtNome;
    private EditText txtEndereco;
    private EditText txtTelefone;
    private EditText txtEmail;
    private EditText txtEnderecoLinkedin;
    private TextInputLayout layout_txt_nome;
    private TextInputLayout layout_txt_endereco;
    private TextInputLayout layout_txt_telefone;
    private TextInputLayout layout_txt_email;
    private TextInputLayout layout_txt_enderecolinkedin;
    private Contato contato;


    public CadastroContatoHelper(CadastroContatoActivity activity) {
        txtNome = activity.findViewById(R.id.txt_nome);
        txtEndereco = activity.findViewById(R.id.txt_endereco);
        txtTelefone = activity.findViewById(R.id.txt_telefone);
        txtEmail = activity.findViewById(R.id.txt_email);
        txtEnderecoLinkedin = activity.findViewById(R.id.txt_enderecolinkedin);

        layout_txt_nome = activity.findViewById(R.id.layout_txt_nome);
        layout_txt_email = activity.findViewById(R.id.layout_txt_email);
        layout_txt_endereco = activity.findViewById(R.id.layout_txt_endereco);
        layout_txt_telefone = activity.findViewById(R.id.layout_txt_telefone);
        layout_txt_enderecolinkedin = activity.findViewById(R.id.layout_txt_enderecolinkedin);
    }

    public Contato getContato()
    {
        Contato contato = new Contato();
        contato.setNome(txtNome.getText().toString());
        contato.setEndereco(txtEndereco.getText().toString());
        contato.setTelefone(txtTelefone.getText().toString());
        contato.setEmail(txtEmail.getText().toString());
        contato.setEnderecolikedin(txtEnderecoLinkedin.getText().toString());

        return contato;
    }

    public boolean validar(Context activity) {
        boolean validado = true;
        if(txtNome.getText().toString().isEmpty()){
            layout_txt_nome.setErrorEnabled(true);
            layout_txt_nome.setError("erro");
            validado = false;
        }else{
            layout_txt_nome.setErrorEnabled(false);
        }

        if(txtTelefone.getText().toString().isEmpty()){
            layout_txt_telefone.setErrorEnabled(true);
            layout_txt_telefone.setError("erro");
            validado = false;
        }else{
            layout_txt_telefone.setErrorEnabled(false);
        }


        if(txtEmail.getText().toString().isEmpty()){
            layout_txt_email.setErrorEnabled(true);
            layout_txt_email.setError("erro");
            validado = false;
        }else{
            layout_txt_email.setErrorEnabled(false);
        }


        if(txtEndereco.getText().toString().isEmpty()){
            layout_txt_endereco.setErrorEnabled(true);
            layout_txt_endereco.setError("erro");
            validado = false;
        }else{
            layout_txt_endereco.setErrorEnabled(false);
        }


        if(txtEnderecoLinkedin.getText().toString().isEmpty()){
            layout_txt_enderecolinkedin.setErrorEnabled(true);
            layout_txt_enderecolinkedin.setError("erro");
            validado = false;
        }else{
            layout_txt_enderecolinkedin.setErrorEnabled(false);
        }
        return validado;
    }

    public void preencherFormulario(Contato contato) {
        txtNome.setText(contato.getNome());
        txtEndereco.setText(contato.getEndereco());

        this.contato = contato;
    }
}
