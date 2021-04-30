package br.com.erickoliveira.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import br.com.erickoliveira.agenda.R;
import br.com.erickoliveira.agenda.dao.ContatoDAO;
import br.com.erickoliveira.agenda.model.Contato;

import static br.com.erickoliveira.agenda.ui.activity.ConstantesActivities.CHAVE_CONTATO;

public class FormularioContatoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVO_CONTATO = "Novo Contato";
    private static final String TITULO_APPBAR_EDITA_CONTATO = "Editar Contato";
    private EditText campoNome;
    private EditText campoEndereco;
    private EditText campoTelefone1;
    private EditText campoTelefone2;
    private final ContatoDAO dao = new ContatoDAO();
    private Contato contato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_contato);
        inicializacaoDosCampos();
        carregaContato();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_contato_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        //verificaçãodo botão
        if(itemId == R.id.activity_formulario_contato_menu_salvar){
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaContato() {
        Intent dados = getIntent();
        //se tiver dados
        if (dados.hasExtra(CHAVE_CONTATO)) {
            setTitle(TITULO_APPBAR_EDITA_CONTATO);
            contato = (Contato) dados.getSerializableExtra(CHAVE_CONTATO);
            preencheCampos();
        } else {
            setTitle(TITULO_APPBAR_NOVO_CONTATO);
            contato = new Contato();
        }
    }

    private void preencheCampos() {
        campoNome.setText(contato.getNome());
        campoEndereco.setText(contato.getEndereco());
        campoTelefone1.setText(contato.getTelefone1());
        campoTelefone2.setText(contato.getTelefone2());

    }

    private void finalizaFormulario() {
        preencheContato();
        if (contato.temIdValido()) {
            dao.edita(contato);
        } else {
            dao.salva(contato);
        }
        finish();
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_contato_nome);
        campoEndereco = findViewById(R.id.activity_formulario_contato_endereco);
        campoTelefone1 = findViewById(R.id.activity_formulario_contato_telefone_1);
        campoTelefone2 = findViewById(R.id.activity_formulario_contato_telefone_2);
    }

    private void preencheContato() {
        String nome = campoNome.getText().toString();
        String endereco = campoEndereco.getText().toString();
        String telefone1 = campoTelefone1.getText().toString();
        String telefone2 = campoTelefone2.getText().toString();

        contato.setNome(nome);
        contato.setEndereco(endereco);
        contato.setTelefone1(telefone1);
        contato.setTelefone2(telefone2);
    }
}
