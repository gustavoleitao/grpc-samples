package br.ufrn.imd.client;

import br.ufrn.imd.PollingResponse;

public class ResponseObserver extends GenericPrintObserver<PollingResponse>  {

    public ResponseObserver(String nome) {
        super(nome);
    }

    @Override
    public void onNext(PollingResponse pollingResponse) {
        System.out.println("chegou resposta do pooling: "+pollingResponse.getStatus());
    }

}
