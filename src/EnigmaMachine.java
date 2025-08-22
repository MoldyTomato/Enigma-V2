public class EnigmaMachine {
    private EnigmaVersion enigma_model=null;
    private Stator plugboard=null;
    private Stator entrystator=null;
    private Rotor firstRotor=null;
    private Rotor secondRotor=null;
    private Rotor thirdRotor=null;
    private Stator reflector=null;
    //general flow is input->plugboard->entry stator->rotors->reflector->rotors->entry stator->plugboard->output

    /**
     * Empty Construcor
     */
    public EnigmaMachine(){}

    /**
     * Constructor with only model provided
     * @param enigma_model
     */
    public EnigmaMachine(EnigmaVersion enigma_model){
        this.enigma_model=enigma_model;
        char [][] wiring = RotorWirings.returnWiring(enigma_model, 1,2,3);
        this.plugboard= new Stator(wiring[1], wiring[0]);
        this.entrystator = new Stator(wiring[2], wiring[0]);
        this.reflector = new Stator(wiring[3], wiring[0]);
        this.firstRotor = new Rotor(wiring[4], wiring[0]);
    }

    /**
     * Constructor with the model, plugboard, stator and reflector specified
     * @param enigma_model
     * @param plugboard
     * @param stator
     * @param reflector
     */
    /*public EnigmaMachine(EnigmaVersion enigma_model, Plugboard plugboard, Stator stator, Reflector reflector){
        this.enigma_model = enigma_model;
        this.plugboard = plugboard;
        this. entrystator= stator;
        this.reflector = reflector;
    }*/
    /**
     * Constructor with all parameters specified
     * enigma_model: Enum with the specific enigma model (see EnigmaVersion.java)
     * plugboard: Wiring of the plugboard
     * stator: Wiring of the entrystator
     * firstrotor: Wiring of the first rotor
     * secondrotor: Wiring of the second rotor
     * thirdrotor: Wiring of the third rotor
     * reflector: Wiring of the reflector
     */
    /*public EnigmaMachine(EnigmaVersion enigma_model, Plugboard plugboard, Stator stator, Rotor firstRotor, Rotor secondRotor, Rotor thirdRotor, Reflector reflector){
        this.enigma_model = enigma_model;
        this.plugboard = plugboard;
        this.entrystator = stator;
        this.firstRotor = firstRotor;
        this.secondRotor = secondRotor;
        this.thirdRotor = thirdRotor;
        this.reflector = reflector;
    }*/

    public EnigmaVersion getModel(){
        return this.enigma_model;
    }

    String encodingpath="";
    char A = 'A';
    public char encode_char(char c){
        if(this.enigma_model==null){
            return c;
        }
        int input = (int) (c-A);
        encodingpath = "";
        encodingpath = "Encodingpath:\n";
        //PLUGBOARD FORWARD
        int after_plugboard_forw = encode_int_plugboard(input, true);
        encodingpath = encodingpath+"Plugboard (forward)"+(this.plugboard==null?"(NULL)":"")+": ("+(char)(input+A)+":"+Integer.toString(input)+") ->";
        encodingpath = encodingpath+"("+(char)(after_plugboard_forw+A)+":"+Integer.toString(after_plugboard_forw)+")\n";
        //ENTRYSTATOR FORWARD
        int after_entrystator_forw = encode_int_entrystator(after_plugboard_forw, true);
        encodingpath = encodingpath+"Entrystator (forward)"+(this.entrystator==null?"(NULL)":"")+": ("+(char)(after_plugboard_forw+A)+":"+Integer.toString(after_plugboard_forw)+") ->";
        encodingpath = encodingpath+"("+(char)(after_entrystator_forw+A)+":"+Integer.toString(after_entrystator_forw)+")\n";
        //FIRST ROTOR FORWARD
        int after_firstrotor_forw = encode_int_firstrotor(after_entrystator_forw, true);
        encodingpath = encodingpath+"First rotor (forward)"+(this.firstRotor==null?"(NULL)":"")+": ("+(char)(after_entrystator_forw+A)+":"+Integer.toString(after_entrystator_forw)+") ->";
        encodingpath = encodingpath+"("+(char)(after_firstrotor_forw+A)+":"+Integer.toString(after_firstrotor_forw)+")\n";
        //SECOND ROTOR FORWARD
        int after_secondrotor_forw = encode_int_secondrotor(after_firstrotor_forw, true);
        encodingpath = encodingpath+"Second rotor (forward)"+(this.secondRotor==null?"(NULL)":"")+": ("+(char)(after_firstrotor_forw+A)+":"+Integer.toString(after_firstrotor_forw)+") ->";
        encodingpath = encodingpath+"("+(char)(after_secondrotor_forw+A)+":"+Integer.toString(after_secondrotor_forw)+")\n";
        //THIRD ROTOR FORWARD
        int after_thirdrotor_forw = encode_int_thirdrotor(after_secondrotor_forw, true);
        encodingpath = encodingpath+"Third rotor (forward)"+(this.thirdRotor==null?"(NULL)":"")+": ("+(char)(after_secondrotor_forw+A)+":"+Integer.toString(after_secondrotor_forw)+") ->";
        encodingpath = encodingpath+"("+(char)(after_secondrotor_forw+A)+":"+Integer.toString(after_secondrotor_forw)+")\n";
        //REFLECTOR
        int after_reflector = encode_int_reflector(after_thirdrotor_forw, true);
        encodingpath = encodingpath+"Reflector "+(this.reflector==null?"(NULL)":"")+": ("+(char)(after_thirdrotor_forw+A)+":"+Integer.toString(after_thirdrotor_forw)+") ->";
        encodingpath = encodingpath+"("+(char)(after_reflector+A)+":"+Integer.toString(after_reflector)+")\n";
        //THIRD ROTOR
        int after_thirdrotor_backw = encode_int_thirdrotor(after_reflector, false);
        encodingpath = encodingpath+"Third rotor (backward)"+(this.thirdRotor==null?"(NULL)":"")+": ("+(char)(after_reflector+A)+":"+Integer.toString(after_reflector)+") ->";
        encodingpath = encodingpath+"("+(char)(after_thirdrotor_backw+A)+":"+Integer.toString(after_thirdrotor_backw)+")\n";
        //SECOND ROTOR
        int after_secondrotor_backw = encode_int_secondrotor(after_thirdrotor_backw, false);
        encodingpath = encodingpath+"Second rotor (backward)"+(this.secondRotor==null?"(NULL)":"")+": ("+(char)(after_thirdrotor_backw+A)+":"+Integer.toString(after_thirdrotor_backw)+") ->";
        encodingpath = encodingpath+"("+(char)(after_secondrotor_backw+A)+":"+Integer.toString(after_secondrotor_backw)+")\n";
        //FIRST ROTOR
        int after_firstrotor_backw = encode_int_firstrotor(after_secondrotor_backw, false);
        encodingpath = encodingpath+"First rotor (backward)"+(this.firstRotor==null?"(NULL)":"")+": ("+(char)(after_secondrotor_backw+A)+":"+Integer.toString(after_secondrotor_backw)+") ->";
        encodingpath = encodingpath+"("+(char)(after_firstrotor_backw+A)+":"+Integer.toString(after_firstrotor_backw)+")\n";
        //ENTRYSTATOR
        int after_entry_stator_backw = encode_int_entrystator(after_firstrotor_backw, false);
        encodingpath = encodingpath+"Entry stator (backward)"+(this.entrystator==null?"(NULL)":"")+": ("+(char)(after_firstrotor_backw+A)+":"+Integer.toString(after_firstrotor_backw)+") ->";
        encodingpath = encodingpath+"("+(char)(after_entry_stator_backw+A)+":"+Integer.toString(after_entry_stator_backw)+")\n";
        //PLUGBOARD
        int after_plugboard_backw = encode_int_plugboard(after_entry_stator_backw, false);
        encodingpath = encodingpath+"Plugboard (backward)"+(this.plugboard==null?"(NULL)":"")+": ("+(char)(after_entry_stator_backw+A)+":"+Integer.toString(after_entry_stator_backw)+") ->";
        encodingpath = encodingpath+"("+(char)(after_plugboard_backw+A)+":"+Integer.toString(after_plugboard_backw)+")\n";

        return (char) (after_plugboard_backw+A);
    }
    public void printencodingpath(){
        System.out.println(encodingpath);
    }
    private int encode_int_plugboard(int input, boolean forward){
        if(this.plugboard==null)
            return input;
        return this.plugboard.encode_int(input, forward);
    }
    private int encode_int_entrystator(int input, boolean forward){
        if(this.entrystator==null)
            return input;
        return this.entrystator.encode_int(input, forward);
    }
    private int encode_int_firstrotor(int input, boolean forward){
        if(this.firstRotor==null)
            return input;
        return this.firstRotor.encode_int(input, forward);
    }
    private int encode_int_secondrotor(int input, boolean forward){
        if(this.secondRotor==null)
            return input;
        return  this.secondRotor.encode_int(input, forward);
    }
    private int encode_int_thirdrotor(int input, boolean forward){
        if(this.thirdRotor==null)
            return input;
        return this.thirdRotor.encode_int(input, forward);
    }
    private int encode_int_reflector(int input, boolean forward){
        if(this.reflector==null)
            return input;
        return this.reflector.encode_int(input, forward);
    }
}
