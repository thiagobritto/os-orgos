---------------------------------------------------------------------------------------------------
-- ORDEM DE SERVIÇO
---------------------------------------------------------------------------------------------------
CREATE TABLE ordem_servico (
    id_ordem_servico INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    numero_os TEXT,
    data_abertura DATETIME DEFAULT CURRENT_TIMESTAMP,
    type INTEGER NOT NULL,
    status INTEGER NOT NULL,
    cliente_id INTEGER NOT NULL,
    tecnico_id INTEGER NOT NULL,
    equipamento TEXT NOT NULL,
    marca TEXT,
    servico TEXT,
    descricao_problema TEXT,
    solucao_problema TEXT,
    valor_servico REAL(10,2),
    CONSTRAINT ordem_servico_cliente_FK FOREIGN KEY (cliente_id) REFERENCES cliente(id_cliente) ON DELETE RESTRICT ON UPDATE SET DEFAULT,
    CONSTRAINT ordem_servico_tecnico_FK FOREIGN KEY (tecnico_id) REFERENCES tecnico(id_tecnico) ON DELETE RESTRICT ON UPDATE SET DEFAULT
);

CREATE TRIGGER set_numero_os AFTER INSERT ON ordem_servico
BEGIN
    UPDATE ordem_servico 
    SET numero_os = 'OS-' || STRFTIME('%Y%m%d', 'NOW') || '-' || printf('%06d', NEW.id_ordem_servico)
    WHERE id_ordem_servico = NEW.id_ordem_servico;
END;

---------------------------------------------------------------------------------------------------
-- ITEM DE SERVIÇO
---------------------------------------------------------------------------------------------------

CREATE TABLE item_servico(
	id_item_servico INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	ordem_servico_id INTEGER NOT NULL,
	descricao TEXT NOT NULL,
	valor REAL(10,2) NOT NULL,
	CONSTRAINT item_servico_ordem_servico_FK FOREIGN KEY (ordem_servico_id) REFERENCES ordem_servico(id_ordem_servico) ON DELETE CASCADE ON UPDATE CASCADE
);