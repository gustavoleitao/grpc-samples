package br.ufrn.imd.client;

import br.ufrn.imd.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.stream.IntStream;

public class StreamClient {

    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8085)
                .usePlaintext()
                .build();

        StreamSampleGrpc.StreamSampleStub stub = StreamSampleGrpc.newStub(channel);

        stub.readData(DataRequest.newBuilder()
                .setName("client-grpc")
                .build(), new DataObserver("data-observer"));

        StreamObserver<PollingRequest> channelToServer = stub.updatePolling(new ResponseObserver("response-observer"));

        IntStream.rangeClosed(1,10).forEach(index -> {
            System.out.println("Enviando nova mensagem de polling update ao servidor");
            channelToServer.onNext(PollingRequest.newBuilder()
                    .setName("resquest-name")
                    .build());
            sleep(1_000);
        });

        channelToServer.onCompleted();

        System.out.println("Esperando finalizar...");

        sleep(30_000);

        channel.shutdown();
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
