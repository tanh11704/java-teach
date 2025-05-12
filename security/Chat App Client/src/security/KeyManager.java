package security;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class KeyManager {
    private KeyPair rsaKeyPair;
    private KeyPair dsaKeyPair;
    private PublicKey serverRSAPublicKey;
    private PublicKey serverDSAPublicKey;
    
    public void generateKeyPairs() throws Exception {
        rsaKeyPair = CryptoUtils.generateRSAKeyPair();
        dsaKeyPair = CryptoUtils.generateDSAKeyPair();
        System.out.println("Cặp khóa RSA và DSA được tạo ra");
    }
    
    public void setServerPublicKeys(PublicKey rsaPublicKey, PublicKey dsaPublicKey) {
        this.serverRSAPublicKey = rsaPublicKey;
        this.serverDSAPublicKey = dsaPublicKey;
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
    
    public PublicKey getServerRSAPublicKey() {
        return serverRSAPublicKey;
    }
    
    public PublicKey getServerDSAPublicKey() {
        return serverDSAPublicKey;
    }
    
    public byte[] encrypt(byte[] data) {
        try {
            return CryptoUtils.encrypt(data, serverRSAPublicKey);
        } catch (Exception e) {
            System.out.println("Encryption failed" + e);
            return null;
        }
    }
    
    public byte[] decrypt(byte[] encryptedData) {
        try {
            return CryptoUtils.decrypt(encryptedData, rsaKeyPair.getPrivate());
        } catch (Exception e) {
            System.out.println("Decryption failed" + e);
            return null;
        }
    }
    
    public byte[] sign(byte[] data) {
        try {
            return CryptoUtils.sign(data, dsaKeyPair.getPrivate());
        } catch (Exception e) {
            System.out.println("Signing failed" + e);
            return null;
        }
    }
    
    public boolean verify(byte[] data, byte[] signature) {
        try {
            return CryptoUtils.verify(data, signature, serverDSAPublicKey);
        } catch (Exception e) {
            System.out.println("Verification failed" + e);
            return false;
        }
    }
}