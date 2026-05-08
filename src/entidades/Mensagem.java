package entidades;

public class Mensagem {
    private String nome;
    private String emailTel;
    private int motivo;
    private String texto;

    public Mensagem(String nome, String emailTel, int motivo, String texto) {
        this.nome = nome;
        this.emailTel = emailTel;
        this.motivo = motivo;
        this.texto = texto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmailTel() {
        return emailTel;
    }

    public void setEmailTel(String emailTel) {
        this.emailTel = emailTel;
    }

    public int getMotivo() {
        return motivo;
    }

    public void setMotivo(int motivo) {
        this.motivo = motivo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
