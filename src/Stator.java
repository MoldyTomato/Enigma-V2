public class Stator extends AbstractRotor {
    private int[] internalwiring_forw = new int[26];
    private int[] internalwiring_backw = new int[26];

    public Stator(){
    }
    public Stator(char[] wiring, char[] alphabet){
        super();
        for(int i = 0;i<26;i++){
            int dif = (wiring[i]- alphabet[i]+26)%26;
            internalwiring_forw[i]=dif;
            //System.out.println("Diff: \'"+alphabet[i]+" : "+Integer.toString(dif));
            //for now
            internalwiring_backw[(i+dif)%26]=(-dif+26)%26;
        }
    }

    public int encode_int(int i, boolean forward) {
        return (i+(forward?internalwiring_forw[i]:internalwiring_backw[i]))%26;
    }
}
