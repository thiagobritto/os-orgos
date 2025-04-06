package com.orgos.os.util;

import com.orgos.os.model.Cliente;

public class ClienteSanitizer {

    public void sanitizar(Cliente cliente) {
        if (cliente == null) return;

        cliente.setCpfCnpj(sanitizarCpfCnpj(cliente.getCpfCnpj()));
        cliente.setTelefone(sanitizarTelefone(cliente.getTelefone()));
        cliente.setEmail(sanitizarTexto(cliente.getEmail()));
        cliente.setEndereco(sanitizarTexto(cliente.getEndereco()));
    }

    private String sanitizarCpfCnpj(String valor) {
        if (valor == null || valor.replaceAll("\\D", "").length() < 11) {
            return null;
        }
        return valor.trim();
    }

    private String sanitizarTelefone(String valor) {
        if (valor == null || valor.replaceAll("\\D", "").length() < 10) {
            return null;
        }
        return valor.trim();
    }

    private String sanitizarTexto(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return null;
        }
        return valor.trim();
    }
}
