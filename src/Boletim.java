import java.util.Scanner;

public class Boletim {

    public static final int NUM_ALUNOS = 5;
    private static Scanner scan = new Scanner (System.in);
    private String[][] boletim;

    public Boletim() {
        this.boletim = new String[NUM_ALUNOS][5];
    }

    public void setNota() {
        for (int index = 0; index < NUM_ALUNOS; index++) {
            System.out.println ("Aluno " + (index + 1));
            System.out.print ("Nome: ");
            this.boletim[index][0] = scan.nextLine ();
            System.out.print ("Nota 1: ");
            this.boletim[index][1] = scan.nextLine ();
            System.out.print ("Nota 2: ");
            this.boletim[index][2] = scan.nextLine ();

            double media = (Double.parseDouble (this.boletim[index][1]) + Double.parseDouble (this.boletim[index][2])) / 2;

            if (media >= 0 && media < 4) {
                boletim[index][4] = "REPROVADO";
            } else if (media >= 7 && media <= 10) {
                boletim[index][4] = "APROVADO";
            } else {
                boletim[index][4] = realizarAvaliacaoFinal (index, media);
            }
            this.boletim[index][3] = Double.toString (media);
        }
    }

    public double getMedia(int index) {
        return Double.parseDouble (this.boletim[index][3]);
    }

    private String realizarAvaliacaoFinal(int index, double media) {
        System.out.println ("Aluno " + this.boletim[index][0] + " precisa de avaliação final.");
        System.out.print ("Digite a nota da avaliação final: ");
        String notaAvaliacaoFinal = scan.nextLine ();

        double mediaAvaliacaoFinal = (media +
                Double.parseDouble (notaAvaliacaoFinal)) / 2;

        if (mediaAvaliacaoFinal < 5) {
            return "REPROVADO";
        } else if (mediaAvaliacaoFinal >= 5 && mediaAvaliacaoFinal < 8.5) {
            return "APROVADO";
        }
        return " ";
    }

    public void ordenarBoletim() {
        double[] medias = new double[NUM_ALUNOS];
        for (int i = 0; i < NUM_ALUNOS; i++) {
            medias[i] = getMedia (i);
        }

        // Chame aqui o método de ordenação desejado
        insertionSort (medias);
        //selectionSort (medias);
        //bubbleSort (medias);

        // Atualize o boletim de acordo com as médias ordenadas
        String[][] boletimOrdenado = new String[NUM_ALUNOS][5];

        for (int i = 0; i < NUM_ALUNOS; i++) {
            int index = getIndexByMedia (medias[i]);

            for (int k = 0; k < 5; k++) {
                boletimOrdenado[i][k] = boletim[index][k];
            }
        }
        this.boletim = boletimOrdenado;
    }

    private int getIndexByMedia(double media) {
        for (int i = 0; i < NUM_ALUNOS; i++) {
            if (getMedia (i) == media) {
                return i;
            }
        }
        return -1; // Caso não encontre a média no boletim
    }

    private void insertionSort(double[] medias) {
        int i, j;

        for (i = 1; i < NUM_ALUNOS; i++) {
            double aux = medias[i];

            for (j = i - 1; (j >= 0) && medias[j] > aux; j--) {
                medias[j + 1] = medias[j];
            }
            medias[j + 1] = aux;
        }
    }

    private void selectionSort(double[] medias) {
        for (int i = 0; i < Boletim.NUM_ALUNOS - 1; i++) {
            int minIndex = i;

            for (int j = minIndex + 1; j < Boletim.NUM_ALUNOS; j++) {
                if (medias[j] < medias[minIndex]) {
                    minIndex = j;
                }
            }
            double aux = medias[i];
            medias[i] = medias[minIndex];
            medias[minIndex] = aux;
        }
    }

    public void bubbleSort(double[] medias) {
        boolean troca = true;

        while (troca) {
            troca = false;
            for (int i = 0; i < Boletim.NUM_ALUNOS - 1; i++) {
                if (medias[i] > medias[i + 1]) {
                    double aux = medias[i];
                    medias[i] = medias[i + 1];
                    medias[i + 1] = aux;
                    troca = true;
                }
            }
        }
    }

    public void imprimirBoletim() {
        System.out.println ("\nBoletim: ");
        System.out.printf ("%-15s%-8s%-8s%-8s%-8s\n", "Nome", "Nota 1", "Nota 2", "Média", "Aval. Final");

        for (int i = 0; i < NUM_ALUNOS; i++) {
            System.out.printf ("%-15s%-8s%-8s%-8s%-8s\n", boletim[i][0], boletim[i][1], boletim[i][2],
                    boletim[i][3], boletim[i][4]);
        }
    }

    public void fecharScanner() {
        scan.close ();
    }
}
