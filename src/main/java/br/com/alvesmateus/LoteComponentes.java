package br.com.alvesmateus;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class LoteComponentes implements ColecaoComponentes {
    private final List<Componente> componentes = new ArrayList<>();

    public void adicionarComponente(Componente componente) {
        this.componentes.add(componente);
    }

    @Override
    public Iterator<Componente> criarIteratorAprovados() {
        return new IteratorAprovados();
    }

    private class IteratorAprovados implements Iterator<Componente> {
        private int indiceAtual = 0;

        public IteratorAprovados() {
            avancarParaProximoAprovado();
        }

        private void avancarParaProximoAprovado() {
            while (indiceAtual < componentes.size() && !componentes.get(indiceAtual).isAprovadoNoTeste()) {
                indiceAtual++;
            }
        }

        @Override
        public boolean temProximo() {
            return indiceAtual < componentes.size();
        }

        @Override
        public Componente proximo() {
            if (!temProximo()) {
                throw new NoSuchElementException("Não há mais componentes aprovados no lote.");
            }
            Componente componente = componentes.get(indiceAtual);
            indiceAtual++;
            avancarParaProximoAprovado();
            return componente;
        }
    }
}
