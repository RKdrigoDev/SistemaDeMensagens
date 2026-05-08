package fila;

import entidades.Mensagem;

public class FilaMensagens {

    private final int N = 12;
    private final Mensagem[] msg;
    private int ini;
    private int fim;
    private int cont;

    public FilaMensagens() {
        this.msg = new Mensagem[N];
        ini=0;
        fim =0;
        cont =0;
    }

    public void init() {
        ini = fim = cont = 0;
    }

    public boolean isEmpty() {
        return (cont == 0);
    }

    public boolean isFull() {
        return (cont == N);
    }

    public void enqueue(Mensagem mensagem) {
        if (isFull()) {
            System.out.println("Fila cheia!");
        } else {
            msg[fim] = mensagem;
            cont++;
            fim = (fim + 1) % N;
        }
    }

    public Mensagem dequeue() {
        if (isEmpty()) {
            return null;
        }
        else {
            Mensagem mensagem = msg[ini];
            cont--;
            ini = (ini + 1) % N;
            return mensagem;
        }


    }
}

