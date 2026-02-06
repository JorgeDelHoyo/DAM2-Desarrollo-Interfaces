import org.example.MasterMindLogic;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class MasterMindLogicTest {

    private final Color[] PALETTE = {Color.RED, Color.GREEN, Color.BLUE};
    private final String[] LABELS = {"R", "G", "B"};

    @Test
    public void testCheckGuessConSpy() {
        MasterMindLogic logic = spy(MasterMindLogic.class);
        Mockito.when(logic.generateSecret(4)).thenReturn(PALETTE);

        String secreto = logic.showSecret();

        assertNotNull(secreto, "El secreto no puede ser nulo");
        assertEquals(4, secreto.length(), "La longitud es 4");
        assertTrue(secreto.contains("R") || secreto.contains("G") || secreto.contains("B"));
    }
}