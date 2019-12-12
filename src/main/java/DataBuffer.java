import java.util.ArrayList;

public class DataBuffer {

    private static ArrayList<String> dataBuffer = new ArrayList<String>();

    //сгенерировать данные
    public  synchronized void generateData(String line) {

        System.out.println("Producer generate data");
        dataBuffer.add(line);
    }

    //потребить данные
    public  synchronized String consumeData() {

        System.out.println("Consumer consume data");
        return dataBuffer.remove(0);
    }

    //буфер ограничен 5 записями
    public  synchronized boolean checkPossibilityToAddData() {
        if (dataBuffer.size() == 5) return false;
        return true;
    }

    public synchronized int getSize() {
        return dataBuffer.size();
    }

    public  void print() {
        System.out.println("Final print " + dataBuffer.toString());
    }

}
