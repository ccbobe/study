package com.ccbobe;

import akka.actor.ActorPath;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Slf4j
@SpringBootApplication
public class AkkaApplication  implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AkkaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ActorSystem system = ActorSystem.create();
        log.info(system.log().toString());
        ActorRef actor = system.actorOf(IotSupervisor.props(), "iot-supervisor");
        System.in.read();


        ActorRef msg = system.actorOf(Props.create(MessageActor.class), "message");

        ActorPath path = actor.path();

        System.out.println(path.getElements());

        msg.tell("hello message",actor);
    }

    @Scheduled(fixedDelay = 1000)
    public void sendMsg()throws Exception{
        ActorSystem system = ActorSystem.create();
        ActorRef actor = system.actorOf(IotSupervisor.props(), "iot-supervisor");
        ActorRef msg = system.actorOf(Props.create(MessageActor.class), "message");

        ActorPath path = actor.path();

        System.out.println(path.name());


    }
}
