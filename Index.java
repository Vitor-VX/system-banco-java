package banco;

import banco.utils.Usuario;

public class Index {
    public static void main(String[] args) {
        Usuario Victor = new Usuario("Victor", 5569, 0, 500);
        Usuario Ana = new Usuario("Ana", 54321, 100, 700);

        Ana.transferirDinheiro(100, 54321, Victor);

        Victor.sacarDinheiro(100, 5569);

        Victor.dadosConta();
        Ana.dadosConta();
    }
}