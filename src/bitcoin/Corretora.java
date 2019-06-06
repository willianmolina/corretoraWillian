package bitcoin;

import java.util.Random;

public class Corretora {

    public Double dinheiro;
    public Integer numBitcoins;
    public Double valorBTC;
    public Random gerador = new Random();

    public Corretora() {
        this.dinheiro = 0.00;
        this.numBitcoins = 100;
        this.valorBTC = 35780.00;
    }

    public synchronized Double comprarBitcoin(int quantidade) {
        numBitcoins+= quantidade;
        dinheiro -= valorBTC * quantidade;
        Double aux = valorBTC * quantidade;
        int auxValor = gerador.nextInt(10001);
        if(valorBTC - auxValor >=0){
        valorBTC-= auxValor;
        }
        return aux;
    }

    public synchronized Double venderBitcoin(int quantidade) {

        while (numBitcoins == 0) {
            try {
                wait(3000);
            } catch (InterruptedException ex) {
            }
        }
       
            numBitcoins-= quantidade;
            dinheiro += valorBTC * quantidade;
            Double aux = valorBTC * quantidade;
            valorBTC += gerador.nextInt(10001);
            notify();
            return aux;
}
    
}