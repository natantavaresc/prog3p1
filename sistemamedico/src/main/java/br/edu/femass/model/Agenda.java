package br.edu.femass.model;
import java.util.Set;

public class Agenda {
    private Long id;
    private String  data;
    private Paciente paciente;
    private Medico medico;
    private Boolean ativo;
    private Especialidade especialidade;
    
    private static Long ultimoCodigo = 0L;
    
    public Agenda() {
    }

    public Agenda(String data, Paciente paciente, Medico medico) {
        this.data = data;
        this.paciente = paciente;
        this.medico = medico;
        this.id = ultimoCodigo+1;
        ultimoCodigo++;
        this.ativo = true;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }
    public String getData() {
        return data;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    public Medico getMedico() {
        return medico;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }
    
    public static Long getUltimoCodigo() {
        return ultimoCodigo;
    }





    @Override
    public String toString() {
        return "dt=" + data + " Pac="+ paciente +" Med=" + medico +"";
    }

    public static void atualizarUltimoId(Set<Agenda> agendas) {
        for (Agenda agenda: agendas) {
            if (agenda.getId().longValue()>ultimoCodigo) {
                ultimoCodigo = agenda.getId();
            }
        }
    }

    
    
    
}
