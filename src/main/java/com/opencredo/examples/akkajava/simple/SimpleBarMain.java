package com.opencredo.examples.akkajava.simple;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.opencredo.examples.akkajava.Customer;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class SimpleBarMain {

    public static void main(String[] args) throws Exception {
        final Config config = ConfigFactory.load();
        final ActorSystem system = ActorSystem.create("SimpleBarSystem", config);
        try {
            // Создать бармена
            ActorRef barmen = system.actorOf(Barmen.props(), "Barmen");
//            ActorRef barista = system.actorOf(OldBarista.props(), "Old_Barista");

            // Спросить клиентов
//            system.actorOf(Customer.props(barmen), "Alice");
            system.actorOf(Customer.props(barmen), "Bob");
//            system.actorOf(Customer.props(barmen), "Charlie");
        } catch (Exception e) {
            system.terminate();
            throw e;
        }
    }
}
