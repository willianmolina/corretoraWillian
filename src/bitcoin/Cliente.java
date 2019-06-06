package bitcoin;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente extends Thread {

    public String nome;
    public Double dinheiro;
    public Integer nbitcoins;
    public Corretora corretora;
    public Random gerador = new Random();
    public int quantidade;

    public Cliente(String nome, Corretora corretora) {
        this.nome = nome;
        this.corretora = corretora;
        this.dinheiro = 1000000.00;
        this.nbitcoins = 0;
    }

    @Override
    public void run() {
        while (true) {
            negociar();
            System.out.println("Cliente: " + nome + " - Quantidade de Bitcoins: " + nbitcoins + " - Dinheiro: " + dinheiro);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void comprar(int quantidade) {
        nbitcoins+=quantidade ;
        dinheiro -= corretora.venderBitcoin(quantidade);
    }

    public void vender(int quantidade) {
        nbitcoins-=quantidade;
        dinheiro += corretora.comprarBitcoin(quantidade);
    }

    public void negociar() {
        quantidade = gerador.nextInt(4) + 1;
        if (nbitcoins == 0 && dinheiro >= corretora.valorBTC * quantidade && corretora.numBitcoins>=quantidade) {
            this.comprar(quantidade);
        } else if (gerador.nextInt(3) == 1 && dinheiro >= (corretora.valorBTC * quantidade) && corretora.numBitcoins>=quantidade) {
            this.comprar(quantidade);
        } else if (nbitcoins>=quantidade && corretora.dinheiro >= corretora.valorBTC * quantidade) {
            this.vender(quantidade);
        }

    }

}