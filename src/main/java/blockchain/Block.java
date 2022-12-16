package blockchain;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Date;

@Getter @Setter
public class Block {
    static int count = 1;

    private String[] transactions;
    private int blockHash;
    private int previousBlockHash;
    private Date createdDate=new Date(System.currentTimeMillis());

    //not parameters constructor
    public Block() {
        count++;
    }

    //parameters constructor
    public Block(String[] transactions, int previousBlockHash) {
        this.transactions = transactions;
        this.previousBlockHash = previousBlockHash;
        //bir önceki ile şimdikinini karması
        this.blockHash= Arrays.hashCode(new int[]{ Arrays.hashCode(transactions) , this.previousBlockHash});
    }

    @Override
    public String toString() {
        return "Block{" +
                "transactions=" + Arrays.toString(transactions) +
                ","+  new Block().count+ ".blockHash=" + blockHash +
                ", previousBlockHash=" + previousBlockHash +
                ", createdDate=" + createdDate +
                '}';
    }
}

