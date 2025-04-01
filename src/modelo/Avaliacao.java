package modelo;

public class Avaliacao {
    private String avaliador; // Nome ou ID do avaliador
    private String avaliado;  // Nome ou ID do avaliado
    private int nota; // de 1 a 5
    private String comentario;

    public Avaliacao(String avaliador, String avaliado, int nota, String comentario) {
        this.avaliador = avaliador;
        this.avaliado = avaliado;
        this.nota = nota;
        this.comentario = comentario;
    }

    // Getters e Setters

    @Override
    public String toString() {
        return "Nota: " + nota + " | De: " + avaliador + " | Para: " + avaliado +
                (comentario != null && !comentario.isEmpty() ? " | Comentário: " + comentario : "");
    }
}
