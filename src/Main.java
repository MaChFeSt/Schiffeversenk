import Network.Host;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        Host s = new Host(4444);
        s.schickeSchuss(12, 20);
        s.schickeSchuss(5, 2);
        s.schickeSchuss(9, 1);
        s.schickeSchuss(0, 3);
        s.schickeSchuss(9, 37);

    }
}
