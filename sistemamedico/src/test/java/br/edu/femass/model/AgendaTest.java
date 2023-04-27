
package br.edu.femass.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;


public class AgendaTest {
    private Agenda agenda;
    
    @BeforeEach
	void init() {
		agenda = new Agenda("20/12/23",new Paciente ("46260191456", "Maria","222",new PlanoSaude ("Unimed","2222")), new Medico("Mario","22222"));
	}

    @Test
    public void testToString() {
        String esperado = "dt=20/12/23 Pac=cpf=46260191456, nome=Maria Med=Medico crm=22222, nome=Mario]";
        assertEquals(esperado, agenda.toString());
    }
    
}
