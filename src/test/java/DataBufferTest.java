import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DataBufferTest  {

    @Test
    public void testMethods() throws InterruptedException {

        DataBuffer mock = mock(DataBuffer.class);
        DataBuffer dataBuffer = new DataBuffer();

        for(int i = 0; i < 5; i++){
            dataBuffer.generateData("line");
        }
        //check possibility to add data
        when(mock.checkPossibilityToAddData()).thenReturn(dataBuffer.checkPossibilityToAddData());
        Thread.sleep(1000);
        Assertions.assertEquals(mock.checkPossibilityToAddData(), false);

        //check ability to consume data
        for(int j = 0; j < 5; j++){
            dataBuffer.consumeData();
        }
        when(mock.getSize()).thenReturn(dataBuffer.getSize());
        Thread.sleep(1000);
        Assertions.assertEquals(mock.getSize(), 0);
    }

    @RepeatedTest(10)
    void repeatedTest(TestInfo testInfo) throws InterruptedException {
        DataBuffer mock = mock(DataBuffer.class);
        DataBuffer dataBuffer = new DataBuffer();

        for(int i = 0; i < 5; i++){
            dataBuffer.generateData("line");
        }
        when(mock.checkPossibilityToAddData()).thenReturn(dataBuffer.checkPossibilityToAddData());
        Thread.sleep(1000);
        Assertions.assertEquals(mock.checkPossibilityToAddData(), false);

        for(int j = 0; j < 5; j++){
            dataBuffer.consumeData();
        }
        when(mock.getSize()).thenReturn(dataBuffer.getSize());
        Thread.sleep(1000);
        Assertions.assertEquals(mock.getSize(), 0);
    }

    @Test
    void testExpectedException() {

        DataBuffer mock = mock(DataBuffer.class);
        //Проверка на RuntimeException
        when(mock.checkPossibilityToAddData()).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> {
            for(int i = 0; i < 4; i++){
                mock.generateData("line");
            }
            Assertions.assertEquals(mock.checkPossibilityToAddData(), true);

            mock.generateData("line");
            Assertions.assertEquals(mock.checkPossibilityToAddData(), false);

        });
    }

}
