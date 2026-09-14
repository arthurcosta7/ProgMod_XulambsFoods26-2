import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class PedidoTest {
    Pedido pedido = new Pedido();

    @Test
    public void adicionaVariasPizzasCorretamente() {
        // Arrange
        Pedido pedido = new Pedido();

        // Act
        pedido.adicionarPizza(new Pizza());
        int quantidade = pedido.adicionarPizza(new Pizza());

        // Assert
        assertEquals(2, quantidade);
    }

    @Test
    public void naoAdicionaPizzaEmPedidoFechado() {
        // Arrange
        Pedido pedido = new Pedido();
        pedido.adicionarPizza(new Pizza());
        pedido.fecharPedido();

        // Act
        int quantidade = pedido.adicionarPizza(new Pizza());

        // Assert
        assertEquals(1, quantidade);
    }

    @Test
    public void calculaValorPedidoCorretamente() {
        // Act
        double preco = pedido.precoAPagar();

        // Assert
        assertEquals(29d, preco, 0.01);
    }

    @Test
    public void calculaPrecoDePedidoComVariasPizzas() {
        // Arrange
        Pizza pizza2Ingredientes = new Pizza(2);
        
        pedido.adicionarPizza(pizza2Ingredientes);
        // Act
        double preco = pedido.precoAPagar();
        // Assert
        assertEquals(68d, preco, 0.01);
    }

    @Test
    public void geraRelatorioDePedido(){
        String cupom = pedido.relatorio();
        assertTrue(
            cupom.contains("29,00") &&
            cupom.contains("1") &&
            cupom.contains("aberto")

        );
    }

}
