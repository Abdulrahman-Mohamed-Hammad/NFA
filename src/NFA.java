

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.*;

import static java.lang.System.exit;

public class NFA {
    HashMap<String, HashMap<String, List<String>>> hashMap = new HashMap<>();
    boolean VAlue = false;
    int y = 0;
    int count_LIST = 0;
    int count_pointer = 0;
    int counter = 0;
    int count = 0;
    int Number_OF_INPUT_STATE;
    int Nunmber_OF_end_State;
    int NumberOFstate;
    String startState;
    String EndStates[];
    String States[];
    String INPUT_STATEs[];
    HashMap<String, List<String>> hashMap1 = new HashMap<>();
    DefaultMutableTreeNode root;

    Scanner s;

    NFA(int Number_OF_INPUT_STATE, String startState, String EndStates[], int Nunmber_OF_end_State, int NumberOFstate, String States[], String INPUT_STATEs[], Scanner s) {
        this.startState = startState;
        this.NumberOFstate = NumberOFstate;
        this.Nunmber_OF_end_State = Nunmber_OF_end_State;
        this.EndStates = EndStates;
        this.Number_OF_INPUT_STATE = Number_OF_INPUT_STATE;
        this.States = States;
        this.INPUT_STATEs = INPUT_STATEs;
        this.s = s;
        root = new DefaultMutableTreeNode(startState);
    }

    void INPUT_TRANSITION_TABLE() {

        count = 0;
        System.out.println(" Enter TRANSITION TABLE : ");

        while (count < NumberOFstate) {
            List<String> Transition = new ArrayList<>();
            int i = 0;
            while (i < Number_OF_INPUT_STATE) {

                System.out.print(States[count] + " " + INPUT_STATEs[i] + " : ");
                String y = s.nextLine();

                if (y.isEmpty()) {
                    i++;
                    setHashMap1(Transition);
                    Transition.clear();

                } else {
                    Transition.add(y);

                }

            }
            set();
            count++;
            hashMap1.clear();

        }
    }

    void setHashMap1(List<String> Transition) {

        hashMap1.put(INPUT_STATEs[counter], new ArrayList<>(Transition));
        counter++;
        if (counter >= Number_OF_INPUT_STATE) {
            counter = 0;
        }

    }

    void set() {

        if (count < NumberOFstate) {
            hashMap.put(States[count], new HashMap<>(hashMap1));
        }
    }

    void DIVID_TEST(String TEST_STRING, int End) {
        int i = 0;
        String state = startState;


        String[] splitString = TEST_STRING.trim().split("");

        RUN(splitString, state, root, 0);
        printTree(root, " ", false);
        if (VAlue == true) {
            System.out.println("ACCEPTED");
            VAlue = false;
        } else {
            System.out.println("NOT ACCEPTED");
        }
        root.removeAllChildren();
        Main.ENTER_TEST();
    }

    List<String> search_HASHMAP(String split, String state) {

        HashMap<String, List<String>> hashMap1 = hashMap.get(state);
        List<String> transitions = hashMap1.get(split);
        if ( transitions == null ||transitions.isEmpty()) {
            return null;
        } else return transitions;

    }

    void RUN(String[] split, String state, DefaultMutableTreeNode parent, int y) {

        int count = 0;
        if (y < split.length) {
            List<String> search = search_HASHMAP(split[y], state);
            List<String> search1 = search_HASHMAP("E", state);
            while (search != null && count < search.size()) {

                state = search.get(count);
                DefaultMutableTreeNode child1 = new DefaultMutableTreeNode(state);
                parent.add(child1);
                if (y == split.length - 1) {
                    Accept_OR_NOT(child1);
                }
                RUN(split, state, child1, y + 1);
                if (search1 != null && count < search1.size()) {
                    DefaultMutableTreeNode child2 = new DefaultMutableTreeNode(state);
                    parent.add(child2);
                    if (y == split.length - 1) {
                        Accept_OR_NOT(child2);
                    }
                    RUN(split, state, child2, y);
                }
                count++;
            }

        }

    }

    void Accept_OR_NOT(DefaultMutableTreeNode node) {
        int i = 0;
        while (i < Nunmber_OF_end_State) {
            if (node.getUserObject().equals(EndStates[i])) {
                VAlue = true;
            }
            i++;
        }
    }

    public static void printTree(DefaultMutableTreeNode node, String indent, boolean isLastChild) {
        // Print the current node with appropriate prefix (├── or └──)
        String prefix = isLastChild ? "└── " : "├── ";
        System.out.println(indent + prefix + node.getUserObject());

        // Recursively print all children with the appropriate indentations
        for (int i = 0; i < node.getChildCount(); i++) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) node.getChildAt(i);
            printTree(child, indent + (isLastChild ? "    " : "│   "), i == node.getChildCount() - 1);
        }
    }


    void displayHashMap() {
        // Print each state's transitions from the hashMap
        for (String state : hashMap.keySet()) {
            System.out.print("State: " + state + " -> Transitions: ");

            // Cast the value of the state to a nested HashMap
            HashMap<String, List<String>> transitions = (HashMap<String, List<String>>) hashMap.get(state);

            // Iterate over the input states and their corresponding transitions
            for (String input : transitions.keySet()) {
                System.out.print(input + ": " + transitions.get(input) + "; ");
            }
            System.out.println();
        }
    }
}