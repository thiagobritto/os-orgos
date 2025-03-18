package com.orgos.os.util;

import java.util.List;

import com.orgos.os.model.Funcionalidade;
import com.orgos.os.model.Permissao;
import com.orgos.os.model.Usuario;

public class PermissaoUtil {

	public static boolean temPermissao(Usuario usuario, Funcionalidade funcionalidade) {
		List<Permissao> permissoes = usuario.getPermissoes();
		return permissoes.stream().anyMatch(p -> p.getFuncionalidade().equals(funcionalidade));
	}
	
}
