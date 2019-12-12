public class Producer implements Runnable {

    private DataBuffer dataBuffer;

    public Producer(DataBuffer dataBuffer) {
        this.dataBuffer = dataBuffer;
    }

    @Override
    public void run() {
        int count = 0;
        while (dataBuffer.checkPossibilityToAddData() == true) {
            try {
                Thread.sleep(1000);
                dataBuffer.generateData("some data" + ++count);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Thread.yield();
    }

}
