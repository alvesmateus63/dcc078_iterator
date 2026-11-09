package br.com.alvesmateus;

import java.util.ArrayList;
import java.util.List;

public class ServicoEstoqueFabrica {
    public List<String> extrairRelatorioAprovados(LoteComponentes lote) {
        List<String> relatorio = new ArrayList<>();
        Iterator<Componente> iterator = lote.criarIteratorAprovados();

        while (iterator.temProximo()) {
            Componente aprovado = iterator.proximo();
            relatorio.add(aprovado.getDetalhes());
        }

        return relatorio;
    }

    public int contarAprovados(LoteComponentes lote) {
        int total = 0;
        Iterator<Componente> iterator = lote.criarIteratorAprovados();

        while (iterator.temProximo()) {
            iterator.proximo();
            total++;
        }

        return total;
    }
}
