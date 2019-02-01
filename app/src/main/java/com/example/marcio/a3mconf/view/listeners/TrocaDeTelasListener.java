package com.example.marcio.a3mconf.view.listeners;

import model.Carga;
import model.Lote;

public interface TrocaDeTelasListener {
    public void openTelaConference(Carga carga);
    public void openTelaAddLote();
    public void openTelaLote(Lote lote);
    public void openTelaHome();
    public void openTelaNovaConferencia();
}

