package br.edu.femass.model;
import java.util.ArrayList;
import java.util.List;

public class Medico {
    private String crm;
    private String nome;
    private List<Especialidade> especialidades = new ArrayList<Especialidade>();

    public Medico() {
    }

    public Medico(String nome, String crm) {
        this.crm = crm;
        this.nome = nome;
    }
    public String getCrm() {
        return crm;
    }
    public String getNome() {
        return nome;
    }

    public void addEspecialidade(Especialidade especialidade) {
        especialidades.add(especialidade);
	}

    public List<Especialidade> getEspecialidade() {
		return especialidades;
	}




    @Override
    public String toString() {
        return "Medico crm=" + crm + ", nome=" + nome + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((crm == null) ? 0 : crm.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Medico other = (Medico) obj;
        if (crm == null) {
            if (other.crm != null)
                return false;
        } else if (!crm.equals(other.crm))
            return false;
        return true;
    }  

    

}
