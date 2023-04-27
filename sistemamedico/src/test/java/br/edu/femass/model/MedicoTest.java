
package br.edu.femass.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;


public class MedicoTest {
    private Medico medico;
    
    @BeforeEach
	void init() {
		medico = new Medico("Mario","22222");
	}

    @Test
    public void testToString() {
        String esperado = "Medico crm=22222, nome=Mario]";
        assertEquals(esperado, medico.toString());
    }
    
}
