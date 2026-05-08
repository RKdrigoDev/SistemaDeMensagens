package aplicacao;

import entidades.Mensagem;
import fila.FilaMensagens;

import java.util.Scanner;

public class AtendimentoMensagem {
    static Scanner sc=new Scanner(System.in);
    static FilaMensagens filaReclamacao=new FilaMensagens();
    static FilaMensagens filaSugestao=new FilaMensagens();
    static FilaMensagens filaResolucao=new FilaMensagens();

    public static void main(String[] args) {
        int opcao;
        filaResolucao.init();
        filaReclamacao.init();
        filaSugestao.init();

        do{
            System.out.println("""
                    Bem Vindo a SecretáriaVirtual!!!!!
                Digite uma das opções embaixo -->
                [0]-Encerrar o Atendimento
                [1]-Receber Mensagem
                [2]-Atender Mensagem
                [3]-Receber e Encaminhar a Resolução
                """);
            opcao=sc.nextInt();

            switch (opcao){
                case 0 -> System.out.println("Processo encerrado!");
                case 1 -> receberMensagem();
                case 2 -> atenderMensagem();
                case 3 -> receberEncaminhar();
            }
        }while(opcao!=0);
    }
    public static void receberMensagem(){
        String nome;
        String emailTel;
        int motivo;
        String texto;
        System.out.println("Qual é o seu nome?");
        nome=sc.next();
        System.out.println("Digite o seu email ou o número de telefone?");
        emailTel=sc.next();
        System.out.println("qual o motivo do contato? 1-reclamação/ 2-sugestão");
        motivo=sc.nextInt();
        System.out.println("digite a mensagem aqui --> ");
        texto=sc.nextLine();
        Mensagem mensagem=new Mensagem(nome,emailTel,motivo,texto);
        if (mensagem.getMotivo()==1){
           filaReclamacao.enqueue(mensagem);
        }
        else {
            filaSugestao.enqueue(mensagem);
        }

    }
    public static void atenderMensagem(){
        int opcao;
        Mensagem aux;
        System.out.println("Qual mensagem que lidar primeiro?");
        opcao=sc.nextInt();
        if (opcao==2){
            filaSugestao.dequeue();
            System.out.println("Enviada resposta para cliente: Sua solicitação já foi resolvida. Obrigado!!!");
        }
        else {
           aux=filaReclamacao.dequeue();
           filaResolucao.enqueue(aux);
           System.out.println();
            System.out.println("Enviada resposta para cliente: Sua solicitação estásendo analisado pelo setor responsável, pode levar um tempo. Obrigado pela compreenção!!!! ");
        }

    }
    public static void receberEncaminhar(){
        filaResolucao.dequeue();
        System.out.println("Enviada resposta para cliente: sua solicitação já foi resolvida pelo setor responsável. Obrigado!!!");
    }
}
