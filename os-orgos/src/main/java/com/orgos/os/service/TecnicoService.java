package com.orgos.os.service;

import java.util.List;
import java.util.Objects;

import com.orgos.os.dao.TecnicoDao;
import com.orgos.os.model.Tecnico;
import com.orgos.os.util.OperacaoResultado;
import com.orgos.os.util.TecnicoValidator;

public class TecnicoService {

	private TecnicoValidator validator;
	private TecnicoDao dao;

	public TecnicoService(TecnicoValidator validator, TecnicoDao dao) {
		this.validator = validator;
		this.dao = dao;
	}

	public OperacaoResultado salvar(Tecnico tecnico) {
		validator.validar(tecnico);
		
		Tecnico tecnicoBanco = dao.buscarPorNome(tecnico.getNome());
		if (Objects.nonNull(tecnicoBanco)) {
			throw new IllegalArgumentException("Já existe um Técnico com esse nome!");
		}

		prepararDadosTecnico(tecnico);
		return dao.salvar(tecnico);
	}

	public OperacaoResultado atualizar(Tecnico tecnico) {
		validator.validar(tecnico);
		
		Tecnico tecnicoBanco = dao.buscarPorNome(tecnico.getNome());
		if (Objects.nonNull(tecnicoBanco)) {
			if (tecnico.getId() != tecnicoBanco.getId()) {
				throw new IllegalArgumentException("Já existe um Técnico com esse nome!");
			}
		}

		prepararDadosTecnico(tecnico);
		return dao.atualizar(tecnico);
	}

	public OperacaoResultado remover(Tecnico tecnico) {
		return dao.remover(tecnico.getId());
	}

	public List<Tecnico> listarTodos() {
		return dao.listarTodos();
	}

	public List<Tecnico> listarPorNome(String nome) {
		return dao.listarPorNome(nome);
	}

	public Tecnico buscarPorId(int id) {
		return dao.buscarPorId(id);
	}

	public Tecnico buscarPorNome(String nome) {
		return dao.buscarPorNome(nome);
	}

	private void prepararDadosTecnico(Tecnico tecnico) {
		if (tecnico.getCpf().replaceAll("\\D", "").length() < 11) {
			tecnico.setCpf(null);
		}
		if (tecnico.getTelefone().replaceAll("\\D", "").length() < 10) {
			tecnico.setTelefone(null);
		}
		if (tecnico.getEmail().trim().isEmpty()) {
			tecnico.setEmail(null);
		}
		if (tecnico.getEspecializacao().trim().isEmpty()) {
			tecnico.setEspecializacao(null);
		}
	}

}
