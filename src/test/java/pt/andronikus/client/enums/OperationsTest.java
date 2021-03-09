package pt.andronikus.client.enums;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OperationsTest {

    @Test
    public void shouldOrderEnumHaveOperationCreate(){
        assertEquals(OperationType.CREATE.getOperation(), "CREATE");
        assertEquals(OperationType.UPDATE.getOperation(), "UPDATE");
        assertEquals(OperationType.DELETE.getOperation(), "DELETE");
    }

}