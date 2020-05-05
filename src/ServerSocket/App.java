package ServerSocket;

import Commands.*;
import FillCollection.ArrayCollection;
import FillCollection.HumanBeing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class App {
    static ServerReceiver receiver;
    static ServerSender sender;
    HumanCollection collection = new HumanCollection();


    public App(ServerSender sender, ServerReceiver receiver) {
        System.out.println("Работа сервера запущена");
        this.receiver = receiver;
        this.sender = sender;
    }

    public static ServerReceiver getReceiver() {
        return receiver;
    }

    public static ServerSender getSender() {
        return sender;
    }

    public void begin() throws IOException, ClassNotFoundException {
        sender.setPort(receiver.receive());
        String filename = receiver.receive();
        System.out.println("Клиент [" + sender.getPort() + "] подключен к серверу.\n");
        System.out.println("Для сохранения текущего состояния коллекции используйте команду\"save\".");
        ArrayCollection collection = new ArrayCollection();
        String message = collection.fillCollection(filename);
        System.out.println("Клиенту [" + sender.getPort() + "] отправлено сообщение:\nПрограмма подключена к серверу. " + message + "\n");
        sender.send("Программа подключена к серверу. " + message);
    }

    public void run() throws IOException, ClassNotFoundException {
        try {
            History history = new History();
            while (true) {
                this.checkForSaveCommand();
                String commandResult = "";
                int port = Integer.parseInt(receiver.receive());
                if (port != -1)
                sender.setPort(port);
                else {
                    this.begin();
                    this.run();
                }
                ArrayList commandAndArgument = receiver.receiveCommand();
                Commandable command = (Commandable) commandAndArgument.get(0);
                String arg = (String) commandAndArgument.get(1);
                System.out.println("Получена команда \"" + command.getName() + "\".");
                history.addToHistory(command.getName());
                try {
                    CommandWithObject commandWithObject = (CommandWithObject) command;
                    if (commandWithObject.check(arg)) {
                        sender.send("newHuman");
                        HumanBeing human = (HumanBeing) receiver.receiveHuman();
                        commandResult = (String) command.execute(human);
                    } else {
                        sender.send("nope");
                        commandResult = commandWithObject.whyFailed();
                    }
                } catch (Exception e) {
                    commandResult = (String) command.execute(arg);
                }
                sender.sendCollecton(collection.getArray());
                sender.send(commandResult);
                if (!commandResult.isEmpty())
                    System.out.println("Клиенту [" + sender.getPort() + "] отправлено сообщение:\n\t" + commandResult.replace("\n", "\n\t") + "\n");
                else System.out.println("");
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.begin();
            this.run();

        }
    }
//        } catch (EOFException e) {
//            this.begin();
//            this.run();
//        } catch (NullPointerException e) {
//            System.out.println("Пользователь вышел из программы\n\n");
//            this.begin();
//            this.run();
//        } catch (StreamCorruptedException e) {
//            e.printStackTrace();
//        } catch (NumberFormatException e) {
//            this.begin();
//            this.run();
//        }


    public void checkForSaveCommand() throws IOException {
        Thread backgroundReaderThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
                    while (!Thread.interrupted()) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        if (line.equalsIgnoreCase("save")) {
                            Save save = new Save();
                            save.execute(null);
                        }
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        backgroundReaderThread.setDaemon(true);
        backgroundReaderThread.start();
    }
}



