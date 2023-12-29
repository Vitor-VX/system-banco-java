package banco.utils;

public class Usuario {
    private String nomeUsuario;
    private int senhaUsuario = 0;
    private int idUsuario;
    private boolean cadastradoUsuario;

    private int valorSaldoBanco = 0, valorSaldoBruto = 0;

    public Usuario(String nomeUsuario, int senhaUsuario, int valorSaldoBanco, int valorSaldoBruto) {
        validarCadastro(nomeUsuario, senhaUsuario);
        this.idUsuario = (int) (Math.random() * 100000);

        this.valorSaldoBanco = valorSaldoBanco;
        this.valorSaldoBruto = valorSaldoBruto;

        this.cadastradoUsuario = true;
    }

    private void validarCadastro(String nomeUsuario, int senhaUsuario) {
        if (!nomeUsuario.isEmpty() && senhaUsuario != 0 && (int) Math.log10(senhaUsuario) + 1 >= 4) {
            this.nomeUsuario = nomeUsuario;
            this.senhaUsuario = senhaUsuario;
        } else {
            throw new IllegalArgumentException("Erro ao cadastrar o usuário. Verifique os dados informados.");
        }
    }

    private void validarSenhaUsuario(int senhaUsuario) {
        if (!(this.senhaUsuario == senhaUsuario)) {
            throw new Error("Senha incorreta!");
        }
    }

    public void depositarDinheiro(int valorAdicionarDinheiro, int senhaUsuario) {
        validarSenhaUsuario(senhaUsuario);

        if (valorSaldoBruto > 0 && valorAdicionarDinheiro <= valorSaldoBruto) {
            valorSaldoBanco += valorAdicionarDinheiro;

            // subtrair o valor que ele depositou, do valor bruto
            valorSaldoBruto -= valorAdicionarDinheiro;
        } else {
            System.out.println("Saldo insuficiente para depositar.");
        }
    }

    public void sacarDinheiro(int valorASacarDinheiro, int senhaUsuario) {
        validarSenhaUsuario(senhaUsuario);

        if (valorSaldoBanco > 0 && valorASacarDinheiro <= valorSaldoBanco) {
            valorSaldoBanco -= valorASacarDinheiro;
            valorSaldoBruto += valorASacarDinheiro;
        } else {
            System.out.println("Saldo insuficiente para sacar.");
        }
    }

    public void transferirDinheiro(int valorATransferir, int senhaUsuarioATransferir, Usuario destinatario) {
        validarSenhaUsuario(senhaUsuarioATransferir);
        validarTransferencia(destinatario);

        if (valorSaldoBanco > 0 && valorATransferir <= valorSaldoBanco) {
            valorSaldoBanco -= valorATransferir;
            destinatario.valorSaldoBanco += valorATransferir;
        } else {
            System.out.println("Saldo insuficiente para a transferencia.");
        }
    }

    private void validarTransferencia(Usuario destinatario) {
        if (destinatario == null || this.equals(destinatario)) {
            throw new IllegalArgumentException("Transferência não realizada. Verifique as condições.");
        }
    }

    public void dadosConta() {
        System.out.println("Seu nome: " + this.nomeUsuario);
        System.out.println("Seu saldo bancário: " + valorSaldoBanco);
        System.out.println("Seu saldo bruto: " + valorSaldoBruto);
        System.out.println("Seu id: " + this.idUsuario);
        System.out.println("==========================");
    }

    public void verificarUsuarioCadastrado() {
        if (!this.cadastradoUsuario) {
            System.out.println("Usuário não cadastrado!");
        } else {
            System.out.println("Usuário cadastrado!");
        }
    }

    public int getIdContaUsuario() {
        return idUsuario;
    }
}
