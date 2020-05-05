import ServerSocket.App;
import ServerSocket.ServerReceiver;
import ServerSocket.ServerSender;

import java.io.IOException;
import java.net.BindException;


public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try {
            ServerSender sender = new ServerSender(12413);
            ServerReceiver receiver = new ServerReceiver(1347);
            App app = new App(sender, receiver);
            receiver.receive();
            app.begin();
            app.run();
        }
        catch(BindException e){
            System.out.println("Прости сервер, но работает уже другой.");
        }

//        String received = "";
//        while ((received = receiver.receive()).equals("")){ }
//        System.out.println(received);
    }
}