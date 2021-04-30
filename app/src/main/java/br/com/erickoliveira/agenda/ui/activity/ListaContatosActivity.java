package br.com.erickoliveira.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.erickoliveira.agenda.R;
import br.com.erickoliveira.agenda.model.Contato;
import br.com.erickoliveira.agenda.ui.ListaContatosView;

import static br.com.erickoliveira.agenda.ui.activity.ConstantesActivities.CHAVE_CONTATO;

public class ListaContatosActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR = "Lista de Contatos";
    private final ListaContatosView listaContatosView = new ListaContatosView(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contatos);
        setTitle(TITULO_APPBAR);
        configuraFabNovoContato();
        configuraLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater()
                .inflate(R.menu.activity_lista_contatos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        //verificação de botão
        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_contatos_menu_remover) {
            listaContatosView.confirmaRemocao(item);
        }

        return super.onContextItemSelected(item);
    }

    private void configuraFabNovoContato() {
        FloatingActionButton botaoNovoContato = findViewById(R.id.activity_lista_contatos_fab_novo_contato);
        botaoNovoContato.setOnClickListener(view -> abreFormularioModoInsereContato());
    }

    private void abreFormularioModoInsereContato() {
        startActivity(new Intent(this, FormularioContatoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaContatosView.atualizaContatos();
    }

    private void configuraLista() {
        ListView listaDeContatos = findViewById(R.id.activity_lista_contatos_listview);
        listaContatosView.configuraAdapter(listaDeContatos);
        configuraListenerDeCliquePorItem(listaDeContatos);
        registerForContextMenu(listaDeContatos);
    }

    private void configuraListenerDeCliquePorItem(ListView listaDeContatos) {
        listaDeContatos.setOnItemClickListener((adapterView, view, posicao, id) -> {
            Contato contatoEscolhido = (Contato) adapterView.getItemAtPosition(posicao);
            abreFormularioModoEditaContato(contatoEscolhido);
        });
    }

    private void abreFormularioModoEditaContato(Contato contato) {
        Intent vaiParaFormularioActivity = new Intent(ListaContatosActivity.this, FormularioContatoActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_CONTATO, contato);
        startActivity(vaiParaFormularioActivity);
    }

}
