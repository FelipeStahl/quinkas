/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quinkas.manter;

import br.com.quinkas.entidade.Participante;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alunos
 */
public class ManterParticipante {
    private static List<Participante> participantes;

    public static List<Participante> getParticipantes() {
        return participantes;
    }

    public static void setParticipantes(List<Participante> participantes) {
        ManterParticipante.participantes = participantes;
    }
   
    public static void addParticipante(Participante participante){
        if(participantes == null){
            participantes = new ArrayList();
        }
        participantes.add(participante);
    }
}
