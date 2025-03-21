package com.orgos.os.view;

import java.util.List;

import com.orgos.os.model.Usuario;

public interface GerenciarUsuariosScreenInterface {
	void exibirMensagem(String menssagem);
	void exibieUsuarios(List<Usuario> usuarios);
	void exibirChavesPesquisa(String[] chaves);
}
