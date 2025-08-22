//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        EnigmaMachine Enigma_K = new EnigmaMachine(EnigmaVersion.MODEL_K);
        System.out.println("Enigma successfully set up");
        char input = 'A';
        char output = Enigma_K.encode_char(input);
        System.out.println("Enigma encodes the char \'"+input+"\' as \'"+output+"\'.");
        Enigma_K.printencodingpath();

        System.out.println("Success");
        return;
    }
}