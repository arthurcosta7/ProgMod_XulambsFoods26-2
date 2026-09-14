/** 
 * MIT License
 *
 * Copyright(c) 2026 João Caram <caram@pucminas.br>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

/**
 * Pizza: com preço base fixo e preço final definido pela quantidade de ingredientes.
 */
import java.util.Locale;

public class Pizza {
    private static final double PRECO_BASE;
	private static final int MAXIMO_INGREDIENTES;
	private static final double VALOR_INGREDIENTE;

    private static int pizzasVendidas;
    
    private int quantidadeIngredientes;

    /**
     * Inicializador static
     */
    static{
        PRECO_BASE = 29;
	    MAXIMO_INGREDIENTES = 8;
	    VALOR_INGREDIENTE = 5;
        pizzasVendidas = 0;
    }

    public static int getPizzasVendidas(){
        return pizzasVendidas;
    }

    private void init(int adicionais) {
		 adicionarIngredientes(adicionais);
         pizzasVendidas++;
    }

    /**
     * Cria uma pizza sem adicionais.
     */
	public Pizza() {
		init(0);
	}

    /**
     * Cria uma pizza com os adicionais especificados. Se o valor for inválido,
     * a pizza fica sem adicionais
     * @param adicionais Adicionais para a pizza (>=0 e <=8)
     */
	public Pizza(int adicionais) {
		init(adicionais);
	}

    /**
     * Retorna o preço a se pagar por uma pizza. (base + valor dos adicionais)
     * @return Double positivo com o valor da pizza.
     */
	public double valorFinal() {
		return PRECO_BASE + valorAdicionais();
	}

	private double valorAdicionais() {
		return quantidadeIngredientes * VALOR_INGREDIENTE;
	}

    /**
     * Gera um cupom descritivo, contendo quantidade de ingredientes, preço base, valor
     * dos adicionais e valor final.
     * @return String com as informações descritas.
     */
	public String gerarCupom() {
		String cupom = "Xulambs Pizza!!!\n";
        
        cupom += String.format("Pizza com %d ingredientes\n",      quantidadeIngredientes);

    cupom += String.format(Locale.forLanguageTag("pt-BR"), "\tPreço base: R$ %.2f\n", PRECO_BASE);
    cupom += String.format(Locale.forLanguageTag("pt-BR"), "\tAdicionais: R$ %.2f\n", valorAdicionais());
    cupom += String.format(Locale.forLanguageTag("pt-BR"), "VALOR A PAGAR: R$ %.2f", valorFinal());

        return cupom;
	}

    /**
     * Verifica se a quantidade de ingredientes passada pode
     * ser adicionada na pizza, retornando true/false 
     * confore o resultado.
     * @param quantidade Ingredientes a serem adicionados
     * @return TRUE se a quantidade for >=0 e o total <=8, FALSE para casos contrários.
     */
	private boolean podeAdicionar(int quantidade) {
        return quantidade >=0
               && quantidade + quantidadeIngredientes <= MAXIMO_INGREDIENTES;
    }

    /**
     * Tenta adicionar a quantidade de ingredientes na pizza.
     * Em caso de valores inválidos, ignora a operação.
     * @param quantidade Inteiro não negativo
     * @return A quantatidade de adicionais na pizza após a execução do método.
     */
	public int adicionarIngredientes(int quantidade) {
		if(podeAdicionar(quantidade)){
            quantidadeIngredientes += quantidade;
        }
        return quantidadeIngredientes;
	}
}
