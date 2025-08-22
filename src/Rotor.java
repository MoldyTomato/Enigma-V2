public class Rotor {
    private int[][] internalwiring_forw= new int[26][26];
    private int[][] internalwiring_backw = new int[26][26];

    private int rotorposition=0;

    public Rotor(char[] wiring, char[] alphabet){
        super();
        for(int i = 0;i<26;i++){
            int dif = (wiring[i]- alphabet[i]+26)%26;
            internalwiring_forw[0][i]=dif; //FORWARD
            internalwiring_backw[0][(i+dif)%26]=(-dif+26)%26; //BACKWARD
        }
        //PRECOMPUTE ROTATIONS
        for(int i = 1; i<26;i++){
            for(int j=0;j<26;j++){
                internalwiring_forw[i][j]=internalwiring_forw[0][(j+i)%26];
                internalwiring_backw[i][j]=internalwiring_backw[0][(j+i)%26];
            }
        }
    }
    public int encode_int(int i, boolean forward) {
        return (i+(forward?internalwiring_forw[rotorposition][i]:internalwiring_backw[rotorposition][i]))%26;
    }
    public void setRotorposition(int position){
        if(position>=0 && position<26)
            this.rotorposition = position;
    }
}
