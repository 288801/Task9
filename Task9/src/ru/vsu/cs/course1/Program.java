package ru.vsu.cs.course1;

import org.apache.commons.cli.CommandLine;
import ru.vsu.cs.util.ArrayUtils;
import ru.vsu.cs.util.SwingUtils;

import javax.swing.*;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Locale;


public class Program {

    public static class CmdParams {
        public String inputFile;
        public String outputFile;
        public boolean input;
        public boolean output;
        public boolean error;
        public boolean help;
        public boolean window;
    }

    public static CmdParams parseArgs(String[] args) {
        CmdParams params = new CmdParams();
        if (args.length > 0) {
            if (args[0].equals("--help")) {
                params.help = true;
                return params;
            }
            if (args[0].equals("--window")) {
                params.window = true;
                return params;
            }
            if (!args[0].equals("-i")) {
                params.error = true;
                params.help = true;
                return params;
            }
            if (args[0].equals("-i")) {
                params.input = true;
            }
            if (args.length < 2) {
                params.help = true;
                params.error = true;
                return params;
            }

            params.inputFile = args[1];
            if (args.length > 2) {
                if (!args[2].equals("-o")) {
                    params.error = true;
                    params.help = true;
                    return params;
                }
                if (args[2].equals("-o")) {
                    params.output = true;
                }
                params.outputFile = args[3];
            }
        } else {
            params.help = true;
            params.error = true;
        }
        return params;
    }

    public static void winMain() throws Exception {
        //SwingUtils.setLookAndFeelByName("Windows");
        Locale.setDefault(Locale.ROOT);
        //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtils.setDefaultFont("Microsoft Sans Serif", 18);

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameMain().setVisible(true);
            }
        });
    }

    public static void main(String[] args) throws Exception {
        CmdParams params = parseArgs(args);
        if (params.help) {
            PrintStream out = params.error ? System.err : System.out;
            out.println("Usage:");
            out.println("  <cmd> args <input-file> (<output-file>)");
            out.println("    -r  // reverse rows");
            out.println("    -c  // reverse columns");
            out.println("  <cmd> --help");
            out.println("  <cmd> --window  // show window");
            System.exit(params.error ? 1 : 0);
        }
        if (params.window) {
            winMain();
        } else {
            int[][] matrix = ArrayUtils.readIntArray2FromFile(params.inputFile);
            if(matrix.length<2 || matrix.length>2){
                System.err.printf("Can't read lists from \"%s\"%n", params.inputFile);
                System.exit(2);
            }
            ArrayList<Integer> list1 = Task.ArrToList(matrix[0]);
            ArrayList<Integer> list2 = Task.ArrToList(matrix[1]);
            ArrayList<Integer> list = Task.solution(list1, list2);

            PrintStream out = (params.outputFile != null) ? new PrintStream(params.outputFile) : System.out;
            out.println(ArrayUtils.toString(ArrayUtils.toPrimitive(list.toArray(new Integer[0]))));
            out.close();
        }
    }
}
