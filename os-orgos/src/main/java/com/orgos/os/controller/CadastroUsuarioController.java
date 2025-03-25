package com.orgos.os.controller;

import com.orgos.os.model.OperacaoResultado;
import com.orgos.os.service.UsuarioService;
import com.orgos.os.view.CadastroUsuarioScreen;

public class CadastroUsuarioController  implements Controller {
	private CadastroUsuarioScreen screen;
	private UsuarioService usuarioService;

	public CadastroUsuarioController(CadastroUsuarioScreen screen, UsuarioService usuarioService) {
		super();
		this.screen = screen;
		this.usuarioService = usuarioService;		
		iniciarController();
	}
	
	public void iniciarController(){
		screen.setController(this);		
	}
	
	/**
	 * É chamado sempre que a tela e carregada
	 */
	@Override
	public void inicializar() {
		
	}

	public void cadastrarUsuario(String username, String password) {
		OperacaoResultado resultado = usuarioService.cadastrarUsuario(username, password);
		if (resultado.isSucesso()) {
            screen.mostrarMensagem(resultado.getMensagem());
        } else {
            screen.mostrarMensagem(resultado.getMensagem());
        }
	}

	
	
}