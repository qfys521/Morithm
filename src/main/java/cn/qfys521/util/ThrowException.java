package cn.qfys521;

public class NotThrowAsException {


    public void sneakyThrow(Throwable throwable) {
        this.<RuntimeException>sneakyThrow0(throwable);
    }

    public  <X extends Throwable> void sneakyThrow0(Throwable e) throws X {
        throw (X) e;
    }
}