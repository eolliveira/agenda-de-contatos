package br.com.erickoliveira.agenda;

import android.app.Application;

import br.com.erickoliveira.agenda.dao.ContatoDAO;
import br.com.erickoliveira.agenda.model.Contato;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        criaContatosDeTeste();
    }

    private void criaContatosDeTeste() {
        ContatoDAO dao = new ContatoDAO();
        dao.salva(new Contato("Alex Fernado", "Rua Avelino Anel n-547", "044998262081", "044999132779"));
        dao.salva(new Contato("Maria Fernanda", "Rua Esperança n-485", "04484562081", "0265491132779"));
    }
}
