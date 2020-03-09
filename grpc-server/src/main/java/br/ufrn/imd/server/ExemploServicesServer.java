package br.ufrn.imd.server;


import br.ufrn.imd.GreetingServiceGrpc;
import br.ufrn.imd.HelloRequest;
import br.ufrn.imd.HelloResponse;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;


import java.io.IOException;

/**
 * Created by rayt on 5/16/16.
 */
public class ExemploServicesServer {

    static public void main(String [] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8085)
                .addService(new GreetingServiceImpl())
                .build();

        System.out.println("Starting server...");
        server.start();
        System.out.println("Server started!");
        server.awaitTermination();
    }

    public static class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

        @Override
        public void greeting(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
            System.out.println(request);
            String greeting = "Hello there, " + request.getName();
            HelloResponse response = HelloResponse.newBuilder().setGreeting(greeting).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}