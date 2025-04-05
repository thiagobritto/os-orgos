package com.orgos.os.util;

import com.orgos.os.model.Funcionalidade;
import com.orgos.os.model.Usuario;

public class Permissao {

	public static boolean temPermissao(Usuario usuario, Funcionalidade funcionalidade) {
		if (usuario == null || usuario.getPermissoes() == null) {
			return false;
		}
		return usuario.getPermissoes().stream().anyMatch(p -> p.getFuncionalidade().equals(funcionalidade));
	}

}
