/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dao.CustomerCollectionsDAO;
import dao.CustomerDAO;
import dao.DAOInterface;
import dao.ProductDatabase;
import java.util.concurrent.CompletableFuture;
import org.jooby.Jooby;
import org.jooby.json.Gzon;

/**
 *
 * @author peani371
 */
public class Server extends Jooby {

    private DAOInterface dao = new ProductDatabase();
    private CustomerDAO custDao = new CustomerCollectionsDAO();

    public Server() {
        port(2147);
        use(new Gzon());
        use(new ProductModule(dao));
        use(new CustomerModule(custDao));
        use(new AssetModule());
    }

    public static void main(String[] args) throws Exception {

        System.out.println("\nStarting Server.");
        Server server = new Server();

        CompletableFuture.runAsync(() -> {
            server.start();
        });

        server.onStarted(() -> {
            System.out.println("\nPress Enter to stop the server.");
        });

        // wait for user to hit the Enter key
        System.in.read();
        System.exit(0);
    }
}
