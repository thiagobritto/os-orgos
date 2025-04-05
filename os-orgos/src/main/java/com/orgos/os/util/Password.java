package com.orgos.os.util;

import org.mindrot.jbcrypt.BCrypt;

public class Password {
	 // Gera um hash para a senha
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    // Verifica se a senha corresponde ao hash
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
