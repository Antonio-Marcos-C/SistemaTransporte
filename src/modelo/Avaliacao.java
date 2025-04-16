package modelo;

public class Avaliacao {
    private String avaliador;
    private String avaliado;
    private int nota;
    private String comentario;

    public Avaliacao(String avaliador, String avaliado, int nota, String comentario) {
        this.avaliador = avaliador;
        this.avaliado = avaliado;
        this.nota = nota;
        this.comentario = comentario;
    }


    @Override
    public String toString() {
        return "Nota: " + nota + " | De: " + avaliador + " | Para: " + avaliado +
                (comentario != null && !comentario.isEmpty() ? " | Comentário: " + comentario : "");
    }
}
