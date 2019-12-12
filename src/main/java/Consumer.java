import java.util.ArrayList;

public class Consumer implements Runnable {
    private ArrayList<String> receivedData;
    private DataBuffer dataBuffer;

    public Consumer(DataBuffer dataBuffer) {

        this.receivedData = new ArrayList<>();
        this.dataBuffer = dataBuffer;

    }

    @Override
    public void run() {

        while (dataBuffer.getSize() != 0) {
            String getLine = dataBuffer.consumeData();
            this.receivedData.add(getLine);
        }
        Thread.yield();

        print();
    }

    public void print() {
        System.out.println(receivedData.toString());
    }
}
