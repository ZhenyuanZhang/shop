import java.util.UUID;

import org.junit.Test;

public class MainTest {

    @Test
    public void uuid() {
        for (int i = 0; i < 20; i++) {
            System.out.println(UUID.randomUUID());
        }
    }
}
