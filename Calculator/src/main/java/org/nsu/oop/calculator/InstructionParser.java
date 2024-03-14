    package org.nsu.oop.calculator;

    import org.nsu.oop.calculator.commands.Creator;
    import org.nsu.oop.calculator.commands.Command;
    import org.nsu.oop.calculator.commands.Executor;
    import org.nsu.oop.calculator.exception.stream.BufferedReaderNotCreatedException;
    import org.nsu.oop.calculator.exception.stream.ErrorOfReadingException;

    import java.io.BufferedReader;


    import java.io.FileReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.util.List;
    import java.util.Arrays;
    import java.util.ArrayList;
    import java.util.logging.Logger;


    public class InstructionParser {

        private static final Logger log = Logger.getLogger(Calculator.class.getName());

        private String line;
        private String commandName;



        public void parse(ExecutionContext currentContext, FileReader reader) {
            try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                launchCommand(currentContext, bufferedReader);
            } catch (IOException e) {
                log.warning("BufferedReaderNotCreatedException.");
                throw new BufferedReaderNotCreatedException();
            }
        }
        public void parse(ExecutionContext currentContext, InputStreamReader reader) {
            try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                launchCommand(currentContext, bufferedReader);
            } catch (IOException e) {
                log.warning("BufferedReaderNotCreatedException.");
                throw new BufferedReaderNotCreatedException();
            }
        }

        private void launchCommand(ExecutionContext currentContext, BufferedReader bufferedReader) {
            Creator commandCreator = new Creator();
            do {
                log.info("Read line.");
                try {
                    line = bufferedReader.readLine();
                } catch (IOException e) {
                    log.warning("ErrorOfReadingException.");
                    throw new ErrorOfReadingException();
                }
                if (line != null && !line.isEmpty()) {
                    List<String> args = parseLine();
                    if (commandName.equalsIgnoreCase("EXIT")) {
                        log.info("Exit.");
                        break;
                    }
                    Command command = commandCreator.create(commandName);
                    log.info("Initialization command.");
                    command.validateArgs(args);
                    log.info("Validated args.");
                    Executor executor = new Executor(command, currentContext);
                    log.info("Initialization executor.");
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

