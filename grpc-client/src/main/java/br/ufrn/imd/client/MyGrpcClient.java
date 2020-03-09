package br.ufrn.imd.client;

/**
 * Created by gustavoleitao on 21/04/2018.
 */

import br.ufrn.imd.GreetingServiceGrpc;
import br.ufrn.imd.HelloRequest;
import br.ufrn.imd.HelloResponse;
import br.ufrn.imd.Sentiment;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * Created by rayt on 5/16/16.
 */
public class MyGrpcClient {
    public static void main(String[] args) throws InterruptedException {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8085)
                .usePlaintext()
                .build();

        GreetingServiceGrpc.GreetingServiceBlockingStub stub =
                GreetingServiceGrpc.newBlockingStub(channel);

        HelloResponse helloResponse = stub.greeting(
                HelloRequest.newBuilder()
                        .setName("Ray")
                        .setAge(18)
                        .setSentiment(Sentiment.HAPPY)
                        .build());

        System.out.println(helloResponse);

        channel.shutdown();
    }
}