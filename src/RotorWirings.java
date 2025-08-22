public final class RotorWirings {
    private static char[][] EnigmaK_Wiring = {
        {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'}, //Standard alphabet
        {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'}, //Plugboard, default alphabet unless changed
        {'Q','W','E','R','T','Z','U','I','O','A','S','D','F','G','H','J','K','P','Y','X','C','V','B','N','M','L'}, //Entrystator
        {'I','M','E','T','C','G','F','R','A','Y','D','Q','B','Z','X','W','L','H','K','D','V','U','P','O','J','N'}, //Reflector
        {'P','E','Z','U','O','H','X','S','C','V','F','M','T','B','G','L','R','I','N','Q','J','W','A','Y','D','K'}, //Rotor I
        {'Z','O','U','E','S','Y','D','K','F','W','P','C','I','Q','X','H','M','V','B','L','G','N','J','R','A','T'}, //Rotor II
        {'E','H','R','V','X','G','A','O','B','Q','U','S','I','M','Z','F','L','Y','N','W','K','T','P','D','J','C'}, //Rotor III
    };

    /**
     *
     * @param model the given model of the enigma
     * @return 2d array of the form
     * { Alphabet,
     *   Plugboardwiring,
     *   Entrystator,
     *   Reflector,
     *   Rotor1, selectable
     *   Rotor2, selectable
     *   Rotor3, selectable
     * }
     */
    public static char[][] returnWiring(EnigmaVersion model, int rotor1, int rotor2, int rotor3){
        //TODO SANITIZE INPUTS
        switch(model){
            case MODEL_K:
                char[][] wiring;
                wiring = new char[][]{EnigmaK_Wiring[0], EnigmaK_Wiring[1], EnigmaK_Wiring[2], EnigmaK_Wiring[3], EnigmaK_Wiring[3+rotor1], EnigmaK_Wiring[3+rotor2], EnigmaK_Wiring[3+rotor3]};
                return wiring;
            default: return new char[26][26];
        }
        //return new int[26];
    }

}
