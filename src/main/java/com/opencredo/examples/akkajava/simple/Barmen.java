package com.opencredo.examples.akkajava.simple;

import akka.actor.AbstractLoggingActor;
import akka.actor.Props;
import com.opencredo.examples.akkajava.Coffee;
import com.opencredo.examples.akkajava.Order;

import static akka.japi.pf.ReceiveBuilder.match;

public class Barmen extends AbstractLoggingActor {

    public static Props props() {
        return Props.create(Barmen.class, () -> new Barmen());
    }

    private Barmen() {
        final CoffeeMachine coffeeMachine = new CoffeeMachine();
        log().info("Кофейная машина готова");

        receive(
                match(Order.class, order -> {
                    log().info("Да {}, я готовлю {}", customerName(), order);

                    final Coffee coffee = coffeeMachine.prepare(order.type);
                    log().info("{}, ваш {} готов", customerName(), coffee);

                    sender().tell(coffee, self());
                })
                .matchAny(this::unhandled)
                .build()
        );
    }

    public String customerName() {
        return sender().path().elements().last();
    }
}
