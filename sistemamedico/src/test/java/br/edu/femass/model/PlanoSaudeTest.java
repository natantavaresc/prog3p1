
package br.edu.femass.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;


public class PlanoSaudeTest {
    private PlanoSaude planoSaude;
    
    @BeforeEach
	void init() {
		planoSaude = new PlanoSaude("Amil","22222");
	}

    @Test
    public void testToString() {
        String esperado = "Plano de Saude: Amil, ans=22222";
        assertEquals(esperado, planoSaude.toString());
    }
    
}
