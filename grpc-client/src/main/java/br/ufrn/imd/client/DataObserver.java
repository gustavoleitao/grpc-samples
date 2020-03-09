package br.ufrn.imd.client;

import br.ufrn.imd.Data;

public class DataObserver extends GenericPrintObserver<Data> {

    public DataObserver(String nome) {
        super(nome);
    }

    @Override
    public void onNext(Data data){
        System.out.println("Novo dado! tag="+data.getTag()+ " valor: "+data.getValor());
    }

}
