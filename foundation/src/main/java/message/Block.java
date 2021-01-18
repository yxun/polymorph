package message;

import java.util.Date;

import util.StringUtil;

public class Block {
    public String hash;
    public String previousHash;
    private String data;
    private long timeStamp;   // as a number of milliseconds since 1/1/1970
    private int nonce;

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = CalculateHash(); // Make sure we call this after we set the other fields
    }

    public String CalculateHash() {
        String calculatedhash = StringUtil.ApplySHA256(
            previousHash + Long.toString(timeStamp) + data + Integer.toString(nonce)
        );
        return calculatedhash;
    }

    public void Init(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = CalculateHash();  // calculate hash until it starts with difficulty number of 0s
        }
        System.out.println("Initial block: " + hash);
    }
}
