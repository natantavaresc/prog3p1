
package br.edu.femass.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;


public class PacienteTest {
    private Paciente paciente;
    
    @BeforeEach
	void init() {
		paciente = new Paciente("46260191456", "Maria","222",new PlanoSaude ("Unimed","2222"));
	}

    @Test
	public void testCpf()  {
		String esperado = "46260191456";
		assertEquals(esperado, paciente.getCpf());

	}

    @Test
    public void testToString() {
        String esperado = "cpf=46260191456, nome=Maria";
        assertEquals(esperado, paciente.toString());
    }
    
}
