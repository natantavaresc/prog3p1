package br.edu.femass.model;

public class PlanoSaude {
    private String nome;
    private String ans;
    

    public PlanoSaude(){

    }

    public PlanoSaude(String nome, String ans) {
        this.nome = nome;
        this.ans = ans;
    }


    public String getNome() {
        return nome;
    }

    public String getAns() {
        return ans;
    }




    @Override
    public String toString() {
        return "Plano de Saude: " + nome + ", ans=" + ans + "";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ans == null) ? 0 : ans.hashCode());
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
        PlanoSaude other = (PlanoSaude) obj;
        if (ans == null) {
            if (other.ans != null)
                return false;
        } else if (!ans.equals(other.ans))
            return false;
        return true;
    }

    



    

    
}
