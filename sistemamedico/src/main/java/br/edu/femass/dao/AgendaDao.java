package br.edu.femass.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.Agenda;

public class AgendaDao extends Persist implements Dao<Agenda> {

    public AgendaDao() {
        super("agenda.json");
    }

    @Override
    public boolean gravar(Agenda objeto) throws StreamWriteException, IOException {
        

        Set<Agenda> agendas = buscar();
        boolean gravou = agendas.add(objeto);

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, agendas);
        return gravou;
        
    }

    @Override
    public boolean excluir(Agenda objeto) throws StreamWriteException, IOException {
        Set<Agenda> agendas = buscar();
        for (Agenda agendaSelecionado : agendas) {
            if (agendaSelecionado.equals(objeto)) {
                agendaSelecionado.setAtivo(false);
            }
        }

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, agendas);
        return true;
    }

    @Override
    public Set<Agenda> buscar() throws DatabindException {
        try {
            Set<Agenda> agendas = objectMapper.readValue(arquivo, new TypeReference<Set<Agenda>>() {
            });
            Agenda.atualizarUltimoId(agendas);
            return agendas;
        } catch (IOException ex) {
            return new HashSet<Agenda>();
        }
    }

    @Override
    public List<Agenda> buscarAtivos() throws DatabindException {
        Set<Agenda> agendas = buscar();

        List<Agenda> agendasAtivos = agendas
                .stream()
                .filter(agenda -> agenda.getAtivo().equals(true))
                .collect(Collectors.toList());

        return agendasAtivos;
     }
    
}
