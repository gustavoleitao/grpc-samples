package br.ufrn.imd.client;

import io.grpc.stub.StreamObserver;

public abstract class GenericPrintObserver<T> implements StreamObserver<T> {

    private String nome;

    public GenericPrintObserver(String nome) {
        this.nome = nome;
    }

    @Override
    public void onCompleted() {
        System.out.println("Finalizou transmissão do "+nome);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Erro ao transmitir no " +nome );
    }


}
