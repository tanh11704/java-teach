package security;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class KeyManager {    
    private KeyPair rsaKeyPair;
    private KeyPair dsaKeyPair;
    
    public void generateKeyPair() throws Exception {
        rsaKeyPair = CryptoUtils.generateRSAKeyPair();
        dsaKeyPair = CryptoUtils.generateDSAKeyPair();
        System.out.println("Cặp khóa RSA và DSA được tạo ra");
    }
    
    public PublicKey getRSAPublicKey() {
        return rsaKeyPair.getPublic();
    }
    
    public PrivateKey getRSAPrivateKey() {
        return rsaKeyPair.getPrivate();
    }
    
    public PublicKey getDSAPublicKey() {
        return dsaKeyPair.getPublic();
    }
    
    public PrivateKey getDSAPrivateKey() {
        return dsaKeyPair.getPrivate();
    }
    
    public byte[] encrypt(byte[] data, PublicKey publicKey) {
        try {
            return CryptoUtils.encrypt(data, publicKey);
        } catch (Exception e) {
            System.out.println("Mã hóa không thành công: " + e);
            return null;
        }
    }
    
    public byte[] decrypt(byte[] encryptedData) {
        try {
            return CryptoUtils.decrypt(encryptedData, rsaKeyPair.getPrivate());
        } catch (Exception e) {
            System.out.println("Giải mã không thành công: " + e);
            return null;
        }
    }
    
    public byte[] sign(byte[] data) {
        try {
            return CryptoUtils.sign(data, dsaKeyPair.getPrivate());
        } catch (Exception e) {
            System.out.println("Sign không thành công" + e);
            return null;
        }
    }
}
