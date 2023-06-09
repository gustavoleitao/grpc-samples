package br.ufrn.imd.client;

/**
 * Created by gustavoleitao on 21/04/2018.
 */

import br.ufrn.imd.GreetingServiceGrpc;
import br.ufrn.imd.HelloRequest;
import br.ufrn.imd.HelloResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * Created by rayt on 5/16/16.
 */
public class SimpleClient {
    public static void main(String[] args) throws InterruptedException {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8085)
                .usePlaintext()
                .build();

        GreetingServiceGrpc.GreetingServiceBlockingStub stub =
                GreetingServiceGrpc.newBlockingStub(channel);

        var request = HelloRequest.newBuilder()
                .setName("TRE")
                .build();

        HelloResponse helloResponse = stub.greeting(request);

        System.out.println(helloResponse);

        channel.shutdown();
    }
}