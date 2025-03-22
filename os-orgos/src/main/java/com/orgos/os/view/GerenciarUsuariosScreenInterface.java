package com.orgos.os.view;

import java.util.List;

import com.orgos.os.model.PesquisaUsuario;
import com.orgos.os.model.Usuario;

public interface GerenciarUsuariosScreenInterface {
	void exibirMensagem(String menssagem);
	void exibirUsuarios(List<Usuario> usuarios);
	void setPesquisa(PesquisaUsuario[] pesquisa);
}
