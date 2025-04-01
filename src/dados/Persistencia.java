package dados;

import java.io.*;
import java.util.ArrayList;

public class Persistencia {

    public static <T> void salvar(String nomeArquivo, ArrayList<T> lista) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            out.writeObject(lista);
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    public static <T> ArrayList<T> carregar(String nomeArquivo) {
        File arquivo = new File(nomeArquivo);
        if (!arquivo.exists()) return new ArrayList<>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            return (ArrayList<T>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static <T> void salvarAutomaticamente(String nomeArquivo, ArrayList<T> lista) {
        salvar(nomeArquivo, lista);
    }
}