package com.atividade.security_data.ui;

import com.atividade.security_data.model.Nota;
import com.atividade.security_data.service.NotaService;
import com.atividade.security_data.util.constants.Prioridade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleInterface implements CommandLineRunner {

    @Autowired
    private NotaService service;

    @Autowired
    private ConfigurableApplicationContext context;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Gerenciador de Notas ===");

        while (running) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Criar Nota");
            System.out.println("2. Listar Notas");
            System.out.println("3. Ver Nota por ID");
            System.out.println("4. Deletar Nota");
            System.out.println("5. Sair");
            System.out.print("Opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    criarNota(scanner);
                    break;
                case "2":
                    listarNotas();
                    break;
                case "3":
                    verNota(scanner);
                    break;
                case "4":
                    deletarNota(scanner);
                    break;
                case "5":
                    running = false;
                    System.out.println("Saindo...");
                    scanner.close();
                    context.close();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void criarNota(Scanner scanner) {
        System.out.print("Conteúdo da nota: ");
        String conteudo = scanner.nextLine();
        System.out.println("Prioridade (1-BAIXA, 2-MEDIA, 3-ALTA): ");
        String p = scanner.nextLine();
        Prioridade prioridade = Prioridade.BAIXA;
        if (p.equals("2")) prioridade = Prioridade.MEDIA;
        else if (p.equals("3")) prioridade = Prioridade.ALTA;

        Nota nota = new Nota();
        nota.setConteudo(conteudo);
        nota.setPrioridade(prioridade);
        Nota saved = service.create(nota);
        System.out.println("Nota criada com sucesso! ID: " + saved.getId());
    }

    private void listarNotas() {
        System.out.println("\n--- Lista de Notas ---");
        List<Nota> notas = service.listAll();
        if (notas.isEmpty()) {
            System.out.println("Nenhuma nota encontrada.");
        } else {
            notas.forEach(n -> {
                System.out.println("ID: " + n.getId() + " | Prioridade: " + n.getPrioridade() + " | Conteudo: " + n.getConteudo());
            });
        }
    }

    private void verNota(Scanner scanner) {
        System.out.print("ID da nota: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            service.findById(id).ifPresentOrElse(
                n -> System.out.println("ID: " + n.getId() + "\nConteudo: " + n.getConteudo() + "\nPrioridade: " + n.getPrioridade()),
                () -> System.out.println("Nota não encontrada.")
            );
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
        }
    }

    private void deletarNota(Scanner scanner) {
        System.out.print("ID da nota para deletar: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            System.out.print("Tem certeza que deseja deletar a nota " + id + "? (s/n): ");
            String confirmacao = scanner.nextLine();
            if (confirmacao.equalsIgnoreCase("s")) {
                boolean deletado = service.delete(id);
                if (deletado) {
                    System.out.println("Nota deletada com sucesso.");
                } else {
                    System.out.println("Erro: Nota não encontrada com o ID " + id);
                }
            } else {
                System.out.println("Operação cancelada.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
        }
    }
}
