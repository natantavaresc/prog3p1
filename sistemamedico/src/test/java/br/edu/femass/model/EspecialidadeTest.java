
package br.edu.femass.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;


public class EspecialidadeTest {
    private Especialidade especialidade;
    
    @BeforeEach
	void init() {
		especialidade = new Especialidade("Otorrino");
	}

    @Test
    public void testToString() {
        String esperado = "Especialidade: Otorrino";
        assertEquals(esperado, especialidade.toString());
    }
    
}
