import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {

    //定义报错所有socket的Arraylist
    public static ArrayList<Socket> sockets = new ArrayList<Socket>();
    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(30000);
        while (true){
            Socket s  = ss.accept();
           /* OutputStream os =s.getOutputStream();
            os.write("您好，您收到了服务器的祝福\n".getBytes("UTF-8"));*/
            sockets.add(s);
            new Thread(new ServerThreas(s)).start();

              /*  os.close();*//*
            s.close();*/
        }

    }
}
