    package org.nsu.oop.calculator;


    import org.nsu.oop.calculator.exception.stream.ErrorOfReadingException;

    import java.io.BufferedReader;


    import java.io.IOException;
    import java.util.List;
    import java.util.Arrays;
    import java.util.ArrayList;
    import java.util.logging.Logger;


    public class InstructionParser {

        private static final Logger log = Logger.getLogger(Calculator.class.getName());

        private String line;

        public List<String> parse(BufferedReader bufferedReader) {
            log.info("Read line.");
            try {
                line = bufferedReader.readLine();
            } catch (IOException e) {
                log.warning("ErrorOfReadingException.");
                throw new ErrorOfReadingException();
            }
            if (line == null) {
                return null;
            }
            return parseLine();
        }

        private List<String> parseLine() {
            log.info("Parse line.");
            String[] lineSplit = line.split(" ");
            return new ArrayList<>(Arrays.asList(lineSplit));
        }
    }

