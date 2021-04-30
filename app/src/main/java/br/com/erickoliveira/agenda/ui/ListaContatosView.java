package br.com.erickoliveira.agenda.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import br.com.erickoliveira.agenda.dao.ContatoDAO;
import br.com.erickoliveira.agenda.model.Contato;
import br.com.erickoliveira.agenda.ui.adapter.ListaContatosAdapter;

public class ListaContatosView {

    private final ListaContatosAdapter adapter;
    private final ContatoDAO dao;
    private final Context context;

    public ListaContatosView(Context context) {
        this.context = context;
        this.adapter = new ListaContatosAdapter(this.context);
        this.dao = new ContatoDAO();
    }

    //dialog de confirmação de remoção
    public void confirmaRemocao(final MenuItem item) {
        new AlertDialog
                .Builder(context)
                .setTitle("Removendo Contato")
                .setMessage("Tem certeza que deseja remover o contato?")
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo =
                            (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Contato contatoEscolhido = adapter.getItem(menuInfo.position);
                    remove(contatoEscolhido);
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void atualizaContatos() {
        adapter.atualiza(dao.todos());
    }

    private void remove(Contato contato) {
        dao.remove(contato);
        adapter.remove(contato);
    }

    public void configuraAdapter(ListView listaDeContatos) {
        listaDeContatos.setAdapter(adapter);
    }
}
