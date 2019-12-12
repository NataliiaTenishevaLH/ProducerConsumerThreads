
public class Main {

    public static void main(String args[]) throws InterruptedException {

        DataBuffer dataBuffer = new DataBuffer();

        do {
            try {
                Thread producer = new Thread(new Producer(dataBuffer));
                Thread consumer = new Thread(new Consumer(dataBuffer));
                producer.start();
                consumer.start();

                if (dataBuffer.getSize() == 0) {
                    System.out.println("Consumer waits");
                    producer.join();
                }

                if ((dataBuffer.checkPossibilityToAddData() == false)) {
                    System.out.println("Producer waits");
                    consumer.join();
                }

                Thread.sleep(2000);

                try {
                    dataBuffer.print();
                } catch (Exception e) {

                }
            } catch (IllegalThreadStateException i) {
                System.out.println(i.getStackTrace());
            }
        } while (dataBuffer.getSize() != 0);
    }
}
