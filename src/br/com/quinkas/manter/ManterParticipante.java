/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.manter;

import br.com.quinkas.entidade.Participante;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Alunos
 */
public class ManterParticipante {
    private static Map<String, Participante> participantes;
    private static Participante participanteAtual;

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
        participantes.put(participante.getIpAndPorta().getIp(), participante);
    }
}
