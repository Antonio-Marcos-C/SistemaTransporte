package negocio;

public class ValidadorUtils {

    public static boolean validarCPF(String cpf) {
        cpf = cpf.replaceAll("[^\\d]", "");

        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            int soma1 = 0, soma2 = 0;
            for (int i = 0; i < 9; i++) {
                int digito = Character.getNumericValue(cpf.charAt(i));
                soma1 += digito * (10 - i);
                soma2 += digito * (11 - i);
            }

            int dig1 = 11 - (soma1 % 11);
            dig1 = (dig1 >= 10) ? 0 : dig1;

            soma2 += dig1 * 2;
            int dig2 = 11 - (soma2 % 11);
            dig2 = (dig2 >= 10) ? 0 : dig2;

            return dig1 == Character.getNumericValue(cpf.charAt(9)) &&
                    dig2 == Character.getNumericValue(cpf.charAt(10));
        } catch (Exception e) {
            return false;
        }
    }
    public static boolean validarTelefone(String telefone) {
        telefone = telefone.replaceAll("[^\\d]", ""); // remove qualquer caractere não numérico

        return telefone.length() >= 10 && telefone.length() <= 11;
    }

}
