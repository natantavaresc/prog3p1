package br.edu.femass.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;

import br.edu.femass.model.Especialidade;

public class EspecialidadeDao extends Persist implements Dao<Especialidade> {

    public EspecialidadeDao() {
        super("especialidade.json");
    }

    public boolean gravar(Especialidade objeto) throws StreamWriteException, IOException {
        Set<Especialidade> especialidades = buscar();
        boolean gravou = especialidades.add(objeto);

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, especialidades);
        return gravou;
    }

    public boolean excluir(Especialidade objeto) throws StreamWriteException, IOException {
        Set<Especialidade> especialidades = buscar();
        boolean removeu = especialidades.remove(objeto);

        objectMapper.writerWithDefaultPrettyPrinter().writeValue(arquivo, especialidades);
        return removeu;
    }

    public Set<Especialidade> buscar() throws DatabindException {
        try {
            Set<Especialidade> especialidades = objectMapper.readValue(arquivo, new TypeReference<Set<Especialidade>>() {
            });
            return especialidades;
        } catch (IOException ex) {
            return new HashSet<Especialidade>();
        }
    }

    public List<Especialidade> buscarAtivos() throws DatabindException {
        Set<Especialidade> especialidades = buscar();

        List<Especialidade> especialidadesAtivos = especialidades
                .stream()
                //.filter(paciente -> paciente.getAtivo().equals(true))
                .collect(Collectors.toList());

        return especialidadesAtivos;}

    
}
