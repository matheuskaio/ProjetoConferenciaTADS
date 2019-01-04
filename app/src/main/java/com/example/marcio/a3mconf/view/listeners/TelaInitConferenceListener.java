package com.example.marcio.a3mconf.view.listeners;

import java.util.List;

import control.Lote;

public interface TelaInitConferenceListener {
    public void openTela();
    public void finalizar();
    public List<Lote> getLotes();
    public void setLote(List<Lote> lotes);
}
