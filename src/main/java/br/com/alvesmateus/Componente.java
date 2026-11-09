package br.com.alvesmateus;

public class Componente {
    private final String nome;
    private final String lote;
    private final boolean aprovadoNoTeste;

    public Componente(String nome, String lote, boolean aprovadoNoTeste) {
        this.nome = nome;
        this.lote = lote;
        this.aprovadoNoTeste = aprovadoNoTeste;
    }

    public String getNome() {
        return nome;
    }

    public String getLote() {
        return lote;
    }

    public boolean isAprovadoNoTeste() {
        return aprovadoNoTeste;
    }

    public String getDetalhes() {
        return String.format("[%s - Lote: %s] Status: %s",
                nome, lote, aprovadoNoTeste ? "APROVADO" : "REPROVADO");
    }
}
