import java.util.Scanner;

import static java.lang.System.exit;


public class Main {

    static NFA nfa;
    static Scanner s = new Scanner(System.in);

    // public static TEST T =new TEST();
    public static void main(String[] args) {
        DETALSE();
    }

    static void DETALSE() {

        System.out.println(" ENTER NUMBER of STATE : ");
        int NumberOFstate = s.nextInt();
        s.nextLine();
        System.out.println(" ENTER STATES :  ");
        String States[] = INPUT(NumberOFstate);

        System.out.println(" ENTER NUMBER OF END STATE :  ");
        int Nunmber_OF_end_State = s.nextInt();
        s.nextLine();
        System.out.println(" ENTER END STATES :  ");
        String EndStates[] = INPUT(Nunmber_OF_end_State);


        System.out.println(" ENTER START STATE  :  ");
        String startState = s.nextLine();

        System.out.println(" ENTER NUMBER OF INPUT  STATE :  ");
        int Number_OF_INPUT_STATE = s.nextInt();
        s.nextLine();
        System.out.println(" ENTER INPUT STATE :  ");
        String INPUT_STATEs[] = INPUT(Number_OF_INPUT_STATE);

        nfa = new NFA(Number_OF_INPUT_STATE, startState, EndStates, Nunmber_OF_end_State, NumberOFstate, States, INPUT_STATEs, s);
        nfa.INPUT_TRANSITION_TABLE();
        nfa.displayHashMap();

        System.out.println("Enter TEST CASES : ");
        //TEST test =new TEST( Number_OF_INPUT_STATE,startState,EndStates,Nunmber_OF_end_State,NumberOFstate, States,  INPUT_STATEs,  s);
        //test.CHECK();
        ENTER_TEST();

    }

    static String[] INPUT(int END) {
        int count = 0;
        String States[] = new String[END];


        while (count < END) {

            States[count] = s.nextLine();
            count++;

        }
        return States;

    }

    static void ENTER_TEST() {


        String Test = s.nextLine();
        if (Test.equals("k")) {
            exit(1);
        }
        int length = Test.length();
        nfa.DIVID_TEST(Test, length);
    }

}