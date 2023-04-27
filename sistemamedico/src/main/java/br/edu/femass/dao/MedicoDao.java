package br.edu.femass.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import br.edu.femass.model.Medico;

public class MedicoDao extends Persist implements Dao<Medico> {

    public MedicoDao() {
        super("medico.json");
    }

    public boolean gravar(Medico objeto) throws StreamWriteException, IOException {
        Set<Medico> medicos = buscar();
        boolean gravou = medicos.add(objeto);
        if (!gravou) {
            medicos.remove(objeto);
            medicos.add(objeto);
        }

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, medicos);
        return gravou;
    }

    public boolean excluir(Medico objeto) throws StreamWriteException, IOException {
        Set<Medico> medicos = buscar();
        boolean gravou = medicos.remove(objeto);

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, medicos);
        return gravou;
    }

    public Set<Medico> buscar() throws DatabindException {
        try {
            Set<Medico> medicos = objectMapper.readValue(arquivo, new TypeReference<Set<Medico>>() {
            });
            return medicos;
        } catch (IOException ex) {
            return new HashSet<Medico>();
        }
    }

    public List<Medico> buscarAtivos() throws DatabindException {
        Set<Medico> medicos = buscar();

        List<Medico> medicosAtivos = medicos
                .stream()
                //.filter(paciente -> paciente.getAtivo().equals(true))
                .collect(Collectors.toList());

        return medicosAtivos;}

        /*public List<Medico> buscarEspecialidade(Especialidade especialidade) throws DatabindException {
            Set<Medico> medicos = buscar();
    
            List<Medico> medicosEspecialidade = medicos
                    .stream()
                    .filter(medico -> medico.getEspecialidade().equals(Especialidade especialidade))
                    .collect(Collectors.toList());
    
            return medicosEspecialidade;}

            */
}
