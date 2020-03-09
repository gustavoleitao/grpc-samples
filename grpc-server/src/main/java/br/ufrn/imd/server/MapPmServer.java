package br.ufrn.imd.server;

import br.ufrn.imd.*;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;


public class MapPmServer {

    static public void main(String [] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8085)
                .addService(new MapServerImpl())
                .build();

        System.out.println("Starting server...");
        server.start();
        System.out.println("Server started at 8085");
        server.awaitTermination();
    }

    public static class MapServerImpl extends MapPMGrpc.MapPMImplBase{

        @Override
        public void readData(DataRequest request, StreamObserver<Data> responseData) {

            while (true){
                responseData.onNext(generateRandomData(request));
                trySleep(1_000);
            }

        }

        private void trySleep(int timeMillis) {
            try {
                Thread.sleep(timeMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private Data generateRandomData(DataRequest request) {
            return Data.newBuilder()
                    .setTag(request.getName())
                    .setValor(String.valueOf(Math.random()))
                    .build();
        }

        @Override
        public StreamObserver<PollingRequest> updatePolling(StreamObserver<PollingResponse> responsePolling) {
            return new StreamObserver<PollingRequest>() {
                @Override
                public void onNext(PollingRequest pollingRequest) {
                    System.out.println("Updating my request...");
                    responsePolling.onNext(PollingResponse.newBuilder()
                            .setStatus(Status.OK)
                            .build());
                }
                @Override
                public void onError(Throwable throwable) {
                    System.out.println("Some error happend!");
                }

                @Override
                public void onCompleted() {
                    System.out.println("client closed connection");
                    responsePolling.onCompleted();
                }
            };
        }

    }

}