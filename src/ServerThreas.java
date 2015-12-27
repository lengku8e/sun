import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Iterator;

/**
 * Created by Administrator on 2015/12/25.
 */
public class ServerThreas implements Runnable {

    BufferedReader br = null;
    //定义当前现成所处理的socket
    Socket s = null;
    public ServerThreas(Socket s) throws IOException {
        this.s =s;
        //初始化socket对应的输入流
        br = new BufferedReader(new InputStreamReader(s.getInputStream()));



    }

    @Override
    public void run() {

            String content = null;
            while ((content = readFromClient()) != null) {
                for (Iterator<Socket> id = Main.sockets.iterator(); id.hasNext(); ) {

                    Socket s = id.next();
                    try {
                        OutputStream os = s.getOutputStream();
                        os.write((content + "\n").getBytes("utf-8"));
                    } catch (IOException e) {
                        e.printStackTrace();
                        id.remove();
                        System.out.println(Main.sockets);
                    }


                }


            }


    }
    private String readFromClient() {
        try {
            return br.readLine();

        } catch (IOException e) {
            //人如果捕获到异常 则说明客户端断开了链接
            e.printStackTrace();
            Main.sockets.remove(s);
        }
return  null;

    }
}
