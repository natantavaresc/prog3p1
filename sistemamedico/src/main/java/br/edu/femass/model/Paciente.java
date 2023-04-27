package br.edu.femass.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.edu.femass.diversos.Validador;

public class Paciente {
    private Long id;
    private String cpf;
    private String nome;
    private List<String> telefones = new ArrayList<String>();
    private String endereco;
    private String email;
    private Boolean ativo;
    private PlanoSaude planoSaude;

    private static Long ultimoCodigo = 0L;

    public Paciente(){

    }

    public Paciente(String cpf, String nome, String telefone, PlanoSaude planoSaude) {
        if (Validador.validarCPF(cpf)==false) throw new IllegalArgumentException("CPF Inválido");
        this.cpf = cpf;
        this.nome = nome;
        this.planoSaude = planoSaude;
        this.telefones.add(telefone);
        this.ativo = true;
        this.id = ultimoCodigo+1;
        ultimoCodigo++;
    }

    public PlanoSaude getPlanoSaude() {
        return planoSaude;
    }

    public Long getId() {
        return id;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }



    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public void addTelefone(String telefone){
        telefones.add(telefone);
    }

    /*
    public int compareTo(Object obj){
        if (this == obj)
            return -1;
        if (obj == null)
            return -1;
        if (getClass() != obj.getClass())
            return -1;
        Paciente other = (Paciente) obj;
        return this.getNome().compareTo(other.getNome());
    }
    */

    public void removerTelefone(String telefone) throws Exception {
        if (telefones.size()==1) {
            throw new Exception("O Paciente tem que ter pelo menos um telefone");
        }
        this.telefones.remove(telefone);
    }

    
    public String getCpf() {
        return cpf;
    }


    public String getNome() {
        return nome;
    }


    public List<String> getTelefones() {
        return telefones;
    }


    public String getEndereco() {
        return endereco;
    }


    public String getEmail() {
        return email;
    }



    @Override
    public String toString() {
        return "cpf=" + cpf + ", nome=" + nome ;
    }

    public static void atualizarUltimoId(Set<Paciente> pacientes) {
        for (Paciente paciente: pacientes) {
            if (paciente.getId().longValue()>ultimoCodigo) {
                ultimoCodigo = paciente.getId();
            }
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
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
        Paciente other = (Paciente) obj;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        return true;
    }   


}
