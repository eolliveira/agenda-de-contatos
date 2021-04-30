package br.com.erickoliveira.agenda.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.erickoliveira.agenda.R;
import br.com.erickoliveira.agenda.model.Contato;

public class ListaContatosAdapter extends BaseAdapter {

    private final List<Contato> contatos = new ArrayList<>();
    private final Context context;

    public ListaContatosAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return contatos.size();
    }

    @Override
    public Contato getItem(int posicao) {
        return contatos.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return contatos.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        View viewCriada = criaView(viewGroup);
        Contato contatoDevolvido = contatos.get(posicao);
        vincula(viewCriada, contatoDevolvido);
        return viewCriada;
    }

    private void vincula(View view, Contato contato) {
        TextView nome = view.findViewById(R.id.item_contato_nome);
        nome.setText(contato.getNome());

        TextView endereco = view.findViewById(R.id.item_contato_endereco);
        endereco.setText(contato.getEndereco());

        TextView telefone1 = view.findViewById(R.id.item_contato_telefone_1);
        telefone1.setText("Tel.:" + contato.getTelefone1());

        TextView telefone2 = view.findViewById(R.id.item_contato_telefone_2);
        telefone2.setText("Tel.:" + contato.getTelefone2());

    }

    private View criaView(ViewGroup viewGroup) {
        return LayoutInflater
                .from(context)
                .inflate(R.layout.item_contato, viewGroup, false);
    }

    public void atualiza(List<Contato> contatos){
        //limpa e adiciona contatos
        this.contatos.clear();
        this.contatos.addAll(contatos);
        //notifica dataSet
        notifyDataSetChanged();
    }

    public void remove(Contato contato) {
        contatos.remove(contato);
        notifyDataSetChanged();
    }
}
