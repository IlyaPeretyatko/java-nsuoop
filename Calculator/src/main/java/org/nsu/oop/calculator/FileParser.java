    package org.nsu.oop.calculator;

    import org.nsu.oop.calculator.commands.Creator;
    import org.nsu.oop.calculator.commands.Command;
    import org.nsu.oop.calculator.commands.Executor;

    import java.io.BufferedReader;


    import java.io.FileReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.List;
    import java.util.Arrays;
    import java.util.ArrayList;
    import java.util.logging.Logger;


    public class FileParser {

        private static final Logger log = Logger.getLogger(Calculator.class.getName());

        private String line;
        private String commandName;
        private final ExecutionContext currentContext;


        public FileParser() {
            currentContext = new ExecutionContext();
            log.info("Initialization FileParser.");
        }

        public void parse(FileReader reader) {
            try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                launchCommand(bufferedReader);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public void parse(InputStreamReader reader) {
            try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                launchCommand(bufferedReader);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void launchCommand(BufferedReader bufferedReader) {
            Creator commandCreator = new Creator();
            do {
                log.info("Read line.");
                try {
                    line = bufferedReader.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (line != null && !line.isEmpty()) {
                    List<String> args = parseLine();
                    if (commandName.equalsIgnoreCase("EXIT")) {
                        break;
                    }
                    Command command = commandCreator.create(commandName);
                    log.info("Initialization command.");
                    command.initial(args, currentContext);
                    log.info("Initial command.");
                    Executor executor = new Executor(command, args);
                    executor.searchMethod();
                    args.clear();
                }
            } while (line != null);
        }

        private List<String> parseLine() {
            log.info("Parse line.");
            String[] lineSplit = line.split(" ");
            commandName = lineSplit[0];
            List<String> args = new ArrayList<>(Arrays.asList(lineSplit));
            args.remove(commandName);
            return args;
        }
    }

