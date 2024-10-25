package ar.unrn.tp.exception;

public class ClienteException {

    public static class ClienteNoEncontradoException extends RuntimeException {
        public ClienteNoEncontradoException(String mensaje) {
            super(mensaje);
        }
    }

    public static class DniDuplicadoException extends RuntimeException {
        public DniDuplicadoException(String mensaje) {
            super(mensaje);
        }
    }


}
