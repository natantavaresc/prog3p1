package br.edu.femass.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import br.edu.femass.model.PlanoSaude;

public class PlanoSaudeDao extends Persist implements Dao<PlanoSaude> {

    public PlanoSaudeDao() {
        super("planosaude.json");
    }

    public boolean gravar(PlanoSaude objeto) throws StreamWriteException, IOException {
        Set<PlanoSaude> planosaudes = buscar();
        boolean gravou = planosaudes.add(objeto);

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, planosaudes);
        return gravou;
    }

    public boolean excluir(PlanoSaude objeto) throws StreamWriteException, IOException {
        Set<PlanoSaude> planosaudes = buscar();
        boolean removeu = planosaudes.remove(objeto);

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, planosaudes);
        return removeu;
    }

    public Set<PlanoSaude> buscar() throws DatabindException {
        try {
            Set<PlanoSaude> planosaudes = objectMapper.readValue(arquivo, new TypeReference<Set<PlanoSaude>>() {
            });
            return planosaudes;
        } catch (IOException ex) {
            return new HashSet<PlanoSaude>();
        }
    }

    public List<PlanoSaude> buscarAtivos() throws DatabindException {
        Set<PlanoSaude> pacientes = buscar();

        List<PlanoSaude> pacientesAtivos = pacientes
                .stream()
                //.filter(paciente -> paciente.getAtivo().equals(true))
                .collect(Collectors.toList());

        return pacientesAtivos;}

    
}
