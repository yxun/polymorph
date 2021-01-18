package message;

import java.util.ArrayList;

public class BlockTest {

    public static ArrayList<Block> chain = new ArrayList<Block>();
    public static int difficulty = 5;

    public static void main(String[] args) {
        chain.add(new Block("This is the first block", "0"));
        chain.get(0).Init(difficulty);
        chain.add(new Block("This is the second block", chain.get(chain.size()-1).hash));
        chain.get(1).Init(difficulty);
        chain.add(new Block("This is the third block", chain.get(chain.size()-1).hash));
        chain.get(2).Init(difficulty);

        System.out.println("Check chain is valid: " + isChainValid());
        for (Block b : chain) {
            System.out.println("hash: "+ b.hash + "  previousHash: " + b.previousHash);
        }
    }

    public static Boolean isChainValid() {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        for (int i = 1; i < chain.size(); i++) {
            currentBlock = chain.get(i);
            previousBlock = chain.get(i-1);
            // compare registered hash and calculated hash
            if (!currentBlock.hash.equals(currentBlock.CalculateHash())) {
                System.out.println("Current Hashes not equal");
                return false;
            }
            // compare previous hash and registered previous hash
            if (!previousBlock.hash.equals(currentBlock.previousHash)) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
            // check if hash is initialized
            if (!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been initialized");
                return false;
            }
        }
        return true;
    }
    
}
