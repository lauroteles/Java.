import toastr from 'toastr';
import 'toastr/build/toastr.min.css';



toastr.options = {
  "closeButton": true,
  "debug": false,
  "newestOnTop": true,
  "progressBar": true,
  "positionClass": "toast-bottom-right",
  "preventDuplicates": false,
  "onclick": null,
  "showDuration": "300",
  "hideDuration": "1000",
  "timeOut": "5000",
  "extendedTimeOut": "1000",
  "showEasing": "swing",
  "hideEasing": "linear",
  "showMethod": "fadeIn",
  "hideMethod": "fadeOut"
}

export function mostrarMensagem(titulo,mensagem,tipo){

    toastr[tipo](mensagem,titulo)
    
}

export function mensagemErro(mensagem) {
    mostrarMensagem('Erro',mensagem,'error')
}

export function mensagemSucesso(mensagem) {
    mostrarMensagem('Sucesso',mensagem,'success')
}

export function mensagemAlerta(mensagem) {
    mostrarMensagem('Alerta',mensagem,'warning')
}








