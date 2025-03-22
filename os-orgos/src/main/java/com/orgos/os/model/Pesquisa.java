package com.orgos.os.model;

import java.util.List;

public interface Pesquisa<T, R> {

	List<T> buscar(R valor);
	
}
