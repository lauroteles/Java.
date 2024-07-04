package lauro.project.minhasfinancas.service.exception;

public class ErroAutenticacao extends RuntimeException{

    public ErroAutenticacao(String mensagam){
        super(mensagam);
    }

}
