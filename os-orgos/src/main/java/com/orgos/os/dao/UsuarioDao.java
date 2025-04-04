package com.orgos.os.dao;

import java.util.List;

import com.orgos.os.model.Usuario;
import com.orgos.os.util.OperacaoResultado;

public interface UsuarioDao {
	OperacaoResultado salvar(Usuario usuario);
	OperacaoResultado atualizar(Usuario usuario);
	OperacaoResultado remover(int id);
	List<Usuario> listarTodos();
	List<Usuario> listarPorNome(String nome);
	Usuario buscarPorId(int id);
	Usuario buscarPorNome(String nome);
	Usuario autenticar(String username, String password);
}
