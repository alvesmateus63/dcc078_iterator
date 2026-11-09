package br.com.alvesmateus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ServicoEstoqueFabricaTest {
    private LoteComponentes lote;
    private ServicoEstoqueFabrica servico;

    @BeforeEach
    void setUp() {
        lote = new LoteComponentes();
        servico = new ServicoEstoqueFabrica();
    }

    @Test
    @DisplayName("Deve iterar e retornar apenas componentes aprovados")
    void deveRetornarApenasComponentesAprovados() {
        lote.adicionarComponente(new Componente("Resistor 10k", "LOTE-A", true));
        lote.adicionarComponente(new Componente("Capacitor 100uF", "LOTE-A", false));
        lote.adicionarComponente(new Componente("Microcontrolador ESP32", "LOTE-B", true));
        lote.adicionarComponente(new Componente("Diodo 1N4007", "LOTE-B", false));

        List<String> relatorio = servico.extrairRelatorioAprovados(lote);

        assertEquals(2, relatorio.size());
        assertTrue(relatorio.get(0).contains("Resistor 10k"));
        assertTrue(relatorio.get(1).contains("Microcontrolador ESP32"));
    }

    @Test
    @DisplayName("Deve contar corretamente a quantidade de aprovados")
    void deveContarQuantidadeDeAprovados() {
        lote.adicionarComponente(new Componente("LED Vermelho", "LOTE-C", true));
        lote.adicionarComponente(new Componente("Transistor BC547", "LOTE-C", true));
        lote.adicionarComponente(new Componente("Relé 5V", "LOTE-C", false));

        int totalAprovados = servico.contarAprovados(lote);

        assertEquals(2, totalAprovados);
    }

    @Test
    @DisplayName("Deve retornar iterator vazio quando lote não possui aprovados")
    void deveRetornarIteratorVazioQuandoNenhumAprovado() {
        lote.adicionarComponente(new Componente("Fusível 2A", "LOTE-D", false));
        lote.adicionarComponente(new Componente("Cristal 16MHz", "LOTE-D", false));

        Iterator<Componente> iterator = lote.criarIteratorAprovados();

        assertFalse(iterator.temProximo());
        assertThrows(NoSuchElementException.class, iterator::proximo);
    }

    @Test
    @DisplayName("Deve lançar NoSuchElementException ao tentar avançar além do fim")
    void deveLancarExcecaoAoSuperarFimDoIterator() {
        lote.adicionarComponente(new Componente("Sensor DHT11", "LOTE-E", true));
        Iterator<Componente> iterator = lote.criarIteratorAprovados();

        assertTrue(iterator.temProximo());
        assertNotNull(iterator.proximo());

        assertFalse(iterator.temProximo());
        assertThrows(NoSuchElementException.class, iterator::proximo);
    }

    @Test
    @DisplayName("Deve manter o iterator funcional quando o primeiro elemento for reprovado")
    void deveIgnorarPrimeiroElementoSeReprovado() {
        lote.adicionarComponente(new Componente("Mosfet IRFZ44N", "LOTE-F", false));
        lote.adicionarComponente(new Componente("Optocoplador PC817", "LOTE-F", true));

        Iterator<Componente> iterator = lote.criarIteratorAprovados();

        assertTrue(iterator.temProximo());
        assertEquals("Optocoplador PC817", iterator.proximo().getNome());
        assertFalse(iterator.temProximo());
    }
}
