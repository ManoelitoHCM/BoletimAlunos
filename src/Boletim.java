import java.util.Scanner;

public class Boletim {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Definindo o tamanho da matriz para 5 alunos
        final int NUM_ALUNOS = 5;
        String[][] boletim = new String[NUM_ALUNOS][5];

        // Preenchendo a matriz com dados do usuário
        for (int i = 0; i < NUM_ALUNOS; i++) {
            System.out.println("Aluno " + (i + 1));
            System.out.print("Nome: ");
            boletim[i][0] = scanner.nextLine();

            System.out.print("Nota 1: ");
            boletim[i][1] = scanner.nextLine();

            System.out.print("Nota 2: ");
            boletim[i][2] = scanner.nextLine();
        }

        // Calculando a média e preenchendo as colunas 4 e 5
        for (int i = 0; i < NUM_ALUNOS; i++) {
            double nota1 = Double.parseDouble(boletim[i][1]);
            double nota2 = Double.parseDouble(boletim[i][2]);

            double media = (nota1 + nota2) / 2;
            boletim[i][3] = String.valueOf(media);

            if (media >= 0 && media < 4) {
                System.out.println("Aluno " + boletim[i][0] + " está reprovado com media: " + boletim[i][3] + ".");
                boletim[i][4] = "REPROVADO"; // Reprovado antes da avaliação final
            } else if (media >= 7 && media <= 10) {
                System.out.println("Aluno " + boletim[i][0] + " está aprovado com media: " + boletim[i][3] + ".");
                boletim[i][4] = "APROVADO"; // Não precisa de avaliação final
            } else {
                System.out.println("Aluno " + boletim[i][0] + " precisa de avaliação final.");
                System.out.print("Digite a nota da avaliação final: ");
                boletim[i][4] = scanner.nextLine();

                double mediaAvaliacaofinal = (Double.parseDouble(boletim[i][3]) + Double.parseDouble(boletim[i][4])) / 2;

                if(mediaAvalfinal < 5){
                    System.out.println("Aluno " + boletim[i][0] + " está reprovado com media final: " + mediaAvaliacaofinal + ".");
                } else if(mediaAvaliacaofinal >= 5 && mediaAvaliacaofinal < 8.5){
                    System.out.println("Aluno " + boletim[i][0] + " está aprovado com media final: " + mediaAvaliacaofinal + ".");
                }
            }
        }

        // Imprimindo o boletim
        System.out.println("\nBoletim: ");
        System.out.printf("%-15s%-8s%-8s%-8s%-8s\n", "Nome", "Nota 1", "Nota 2", "Média", "Aval. Final");

        for (int i = 0; i < NUM_ALUNOS; i++) {
            System.out.printf("%-15s%-8s%-8s%-8s%-8s\n", boletim[i][0], boletim[i][1], boletim[i][2],
                    boletim[i][3], boletim[i][4]);
        }

        scanner.close();
    }
}
