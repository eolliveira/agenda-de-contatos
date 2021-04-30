package br.com.erickoliveira.agenda.dao;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.com.erickoliveira.agenda.model.Contato;

public class ContatoDAO {

    private final static List<Contato> contatos = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salva(Contato contato) {
        contato.setId(contadorDeIds);
        contatos.add(contato);
        atualizaIds();
    }

    private void atualizaIds() {
        contadorDeIds++;
    }

    public void edita(Contato contato) {
        Contato contatoEncontrado = buscaContatoPeloId(contato);
        if (contatoEncontrado != null) {
            int posicaoDoContato = contatos.indexOf(contatoEncontrado);
            contatos.set(posicaoDoContato, contato);
        }
    }

    @Nullable
    private Contato buscaContatoPeloId(Contato contato) {
        for (Contato a : contatos) {
            if (a.getId() == contato.getId()) {
                return a;
            }
        }
        return null;
    }

    //traz todos os contatos
    public List<Contato> todos() {
        return new ArrayList<>(contatos);
    }

    public void remove(Contato contato) {
        Contato contatoDevolvido = buscaContatoPeloId(contato);
        if(contatoDevolvido != null){
            contatos.remove(contatoDevolvido);
        }
    }
}
