import java.io.IOException;

public class Counter implements AutoCloseable{

    private int counter;
    private boolean isOpen;

    public int getCounter() {
        return counter;
    }

    /**
     *
     * @return
     * @throws CloseCounterException исключение 1
     * @throws CounterException исключение 2
     * @throws CounterV2Exception исключение 3
     */
    public int add() throws CloseCounterException, CounterException, CounterV2Exception{
        if (!isOpen){
            throw new CloseCounterException("Счетчик закрыт.");
        }
        return ++counter;
    }

    public Counter(){
        isOpen = true;
    }

    public Counter(int counter){
        this.counter = counter;
        isOpen = true;
    }

    public void closeCounter(){
        isOpen = false;
    }

    @Override
    public void close() {
        closeCounter();
    }
}

class CloseCounterException extends IOException{
    public CloseCounterException(String message) {
        super(message);
    }
}

class CounterException extends Exception{
    public CounterException(String message) {
        super(message);
    }
}

class CounterV2Exception extends RuntimeException{
    public CounterV2Exception(String message) {
        super(message);
    }
}