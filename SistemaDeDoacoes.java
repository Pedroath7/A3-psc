import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Doacao {
    private String tipo;
    private int quantidade;
    private String data;

    public Doacao(String tipo, int quantidade, String data) {
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

class GerenciadorDoacoes {
    private List<Doacao> listaDeDoacoes;

    public GerenciadorDoacoes() {
        this.listaDeDoacoes = new ArrayList<>();
    }

    public void adicionarDoacao(Doacao doacao) {
        listaDeDoacoes.add(doacao);
    }

    public int calcularTotalDoacoes() {
        int total = 0;
        for (Doacao doacao : listaDeDoacoes) {
            total += doacao.getQuantidade();
        }
        return total;
    }

    public void salvarDoacoes(String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Doacao doacao : listaDeDoacoes) {
                writer.write(doacao.getTipo() + "," + doacao.getQuantidade() + "," + doacao.getData() + "\n");
            }
            System.out.println("Doações salvas com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar doações: " + e.getMessage());
        }
    }
}

public class SistemaDeDoacoes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorDoacoes gerenciador = new GerenciadorDoacoes();

        // Adicionando doações através de interação com o usuário
        while (true) {
            System.out.println("Digite o tipo da doação (ou 'sair' para encerrar):");
            String tipo = scanner.nextLine();
            if (tipo.equalsIgnoreCase("sair")) {
                break;
            }

            System.out.println("Digite a quantidade da doação:");
            int quantidade = Integer.parseInt(scanner.nextLine());

            System.out.println("Digite a data da doação (formato yyyy-MM-dd):");
            String data = scanner.nextLine();

            // Criando objeto Doacao e adicionando ao gerenciador
            Doacao novaDoacao = new Doacao(tipo, quantidade, data);
            gerenciador.adicionarDoacao(novaDoacao);
        }

        scanner.close();

        // Mostrando total de doações
        System.out.println("Total de doações: " + gerenciador.calcularTotalDoacoes());

        // Salvando as doações em arquivo
        gerenciador.salvarDoacoes("doacoes.txt");
    }
}