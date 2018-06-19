package com.opencredo.examples.akkajava;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.Props;

import java.util.Random;

import static akka.japi.pf.ReceiveBuilder.match;

public class Customer extends AbstractLoggingActor {

    private final ActorRef barmen;
    private final Order order;

    public static Props props(final ActorRef barmen) {
        return Props.create(Customer.class, () -> new Customer(barmen));
    }

    @Override
    public void preStart() throws Exception {
        log().info("{}, пожалуйста", order);
        orderCoffee();
    }

    private Customer(final ActorRef barmen) {
        this.barmen = barmen;
        order = decideOrder();

        receive(
                match(Coffee.class, coffee -> {
                    log().info("Наслаждаюсь моим {}", coffee);
                    goodbye(); })
                .matchAny(this::unhandled)
                .build() );
    }

    private void orderCoffee() {
        log().info("{}, пожалуйста", order);
        barmen.tell(order, self());
    }

    private void goodbye() {
        log().info("Прощай");
        context().stop(self());
    }

    private static final Random orderRandomiser = new Random();

    // принять заказ
    private Order decideOrder() {
        // A considered decision...
        return Order.of(Coffee.types[orderRandomiser.nextInt(Coffee.types.length)]);
    }

}
