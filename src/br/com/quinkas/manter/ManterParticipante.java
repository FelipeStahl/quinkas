/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.manter;

import br.com.quinkas.entidade.Participante;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author Alunos
 */
public class ManterParticipante {
    private static Map<String, Participante> participantes;
    private static Participante participanteAtual;
    private static Integer numParticipante;

    public static Integer getNumParticipante() {
        if(numParticipante == null){
            numParticipante = 0;
        }
        return numParticipante;
    }

    public static void setNumParticipante(Integer numParticipante) {
        ManterParticipante.numParticipante = numParticipante;
    }
    
    
    public static Map<String, Participante> getParticipantes() {
        return participantes;
    }

    public static void setParticipantes(Map<String, Participante> participantes) {
        ManterParticipante.participantes = participantes;
    }

    public static Participante getParticipanteAtual() {
        return participanteAtual;
    }

    public static void setParticipanteAtual(Participante participanteAtual) {
        ManterParticipante.participanteAtual = participanteAtual;
    }

    public static void addParticipante(Participante participante){
        if(participantes == null){
            participantes = new HashMap();
        }
        if(numParticipante == null){
            numParticipante = 0;
        }
        numParticipante++;
        participantes.put(participante.getIpAndPorta().getIp(), participante);
    }
    
    public static void ordenarParticipantes() {
        Comparator<Entry<String, Participante>> valueComparator = new Comparator<Entry<String,Participante>>() {
            @Override
            public int compare(Entry<String, Participante> e1, Entry<String, Participante> e2) {
                Integer v1 = e1.getValue().getPontos();
                Integer v2 = e2.getValue().getPontos();
                return v2.compareTo(v1);
            }
        };
        Set<Entry<String, Participante>> entries = participantes.entrySet();
        List<Entry<String, Participante>> listOfEntries = new ArrayList<Entry<String, Participante>>(entries);
        Collections.sort(listOfEntries, valueComparator);
        Map<String, Participante> mapOrdenado = new HashMap<String, Participante>();
        for (Entry<String, Participante> entry : listOfEntries) {
            mapOrdenado.put(entry.getKey(), entry.getValue());
        }
        participantes = mapOrdenado;
    }
    
    public static List<Participante> listParticipantes() {
        return new ArrayList<Participante>(participantes.values());
    }
}
