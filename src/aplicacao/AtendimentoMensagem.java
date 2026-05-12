/*

rm:563523 Breno Eiki
rm:565291 João Pedro Alves
rm:564200 Pedro Castro
rm:562502 Rodrigo Sun
rm:561977 Renan Silva

*/


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
            System.out.println();
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
                case 0: if(!filaResolucao.isEmpty()||!filaReclamacao.isEmpty()||!filaSugestao.isEmpty()){
                    System.out.println("Você ainda tem casos pendentes!");
                    opcao=-1;
                }
                else {
                    System.out.println("o atendimento foi encerrado");
                }
                break;
                case 1: receberMensagem();
                break;
                case 2: atenderMensagem();
                break;
                case 3: receberEncaminhar();
                break;
                default:
                    System.out.println("opção ínválida!!!");
            }
        }while(opcao!=0);
    }
    public static void receberMensagem(){
        String nome;
        String emailTel;
        int motivo;
        String texto;
        System.out.println("Qual é o seu nome?");
        sc.nextLine();
        nome=sc.nextLine();
        System.out.println("Digite o seu email ou o número de telefone?");
        emailTel=sc.next();
        do{
            System.out.println("Qual o motivo do contato?");
            System.out.println("[1] Reclamação");
            System.out.println("[2] Sugestão");

            motivo = sc.nextInt();

            if(motivo != 1 && motivo != 2){
                System.out.println("Opção inválida! Digite apenas 1 ou 2.");
            }

        }while(motivo != 1 && motivo != 2);
        System.out.println("digite a mensagem aqui --> ");
        sc.nextLine();
        texto=sc.nextLine();
        Mensagem mensagem=new Mensagem(nome,emailTel,motivo,texto);
        if (mensagem.getMotivo()==1) {
            if (filaReclamacao.isFull()) {
                System.out.println("A fila tá cheia, resolva as questôes pendentes primeiro");
            } else {
                filaReclamacao.enqueue(mensagem);
            }
        }
        else {
            if (filaSugestao.isFull()){
                System.out.println("A fila tá cheia, resolva as questôes pendentes primeiro");
            }
            else {
                filaSugestao.enqueue(mensagem);
            }
        }

    }
    public static void atenderMensagem(){
        String resposta;
        int opcao;
        String decisao;
        Mensagem aux;
        do{
            System.out.println("Qual mensagem resolver primeiro?");
            System.out.println("[1] Reclamação");
            System.out.println("[2] Sugestão");

            opcao = sc.nextInt();

            if(opcao!= 1 && opcao != 2){
                System.out.println("Opção inválida! Digite apenas 1 ou 2.");
            }

        }while(opcao != 1 && opcao != 2);

        if (opcao==2){
            if (filaSugestao.isEmpty()){
                System.out.println("não há nenhuma mensagem deste tipo");

            }
            else {
                aux=filaSugestao.dequeue();
                System.out.println(aux.getTexto());
                System.out.println();
                do {
                    System.out.println("como proceder? A => resolver/ B => encaminhar para outro setor");
                    decisao = sc.next();
                    if (!decisao.equalsIgnoreCase("a")&&!decisao.equalsIgnoreCase("b")){
                        System.out.println("digite apenas 'A' ou 'B'");
                    }
                }
                while (!decisao.equalsIgnoreCase("a")&&!decisao.equalsIgnoreCase("b"));
                if (decisao.equalsIgnoreCase("a")){
                    System.out.println("digite a sua resposta aqui -->");
                    sc.nextLine();
                    resposta=sc.nextLine();
                    System.out.println("Respostas enviadas: "+resposta);
                    System.out.println("Enviada resposta para cliente: Sua solicitação já foi resolvida. Obrigado!!!");
                }
                else {
                    if (filaResolucao.isFull()){
                        System.out.println("A fila de resolução está cheia!!!");
                    }
                    else {
                        filaResolucao.enqueue(aux);
                        System.out.println();
                        System.out.println("Enviada resposta para cliente: Sua solicitação está sendo analisada pelo setor responsável");
                    }
                    }
            }
        }
        else {
            if (filaReclamacao.isEmpty()) {
                System.out.println("não há nenhuma mensagem deste tipo");

            }
            else {
                aux=filaReclamacao.dequeue();
                System.out.println(aux.getTexto());
                System.out.println();
                do {
                    System.out.println("como proceder? A => resolver/ B => encaminhar para outro setor");
                    decisao = sc.next();
                    if (!decisao.equalsIgnoreCase("a")&&!decisao.equalsIgnoreCase("b")){
                        System.out.println("digite apenas 'A' ou 'B'");
                    }
                }
                while (!decisao.equalsIgnoreCase("a")&&!decisao.equalsIgnoreCase("b"));
                if (decisao.equalsIgnoreCase("a")){
                    System.out.println("digite a sua resposta aqui -->");
                    sc.nextLine();
                    resposta=sc.nextLine();
                    System.out.println("Respostas enviadas: "+resposta);
                    System.out.println("Enviada resposta para cliente: Sua solicitação já foi resolvida. Obrigado!!!");
                }
                else {
                    if (filaResolucao.isFull()) {
                        System.out.println("A fila de resolução está cheia!!!");
                    } else {
                        filaResolucao.enqueue(aux);
                        System.out.println();
                        System.out.println("Enviada resposta para cliente: Sua solicitação está sendo analisada pelo setor responsável");
                    }
                }
            }
        }

    }
    public static void receberEncaminhar() {
        Mensagem aux;
        if (filaResolucao.isEmpty()) {
            System.out.println("não há mensagens encaminhadas!!!");
        } else {

            aux=filaResolucao.dequeue();
            System.out.println(aux.getTexto());
            System.out.println("Enviada resposta para cliente: sua solicitação já foi resolvida pelo setor responsável. Obrigado!!!");

        }
    }
}
