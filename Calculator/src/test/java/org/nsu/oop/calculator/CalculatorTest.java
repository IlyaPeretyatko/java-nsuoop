package org.nsu.oop.calculator;

import java.io.*;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nsu.oop.calculator.exception.stream.BufferedReaderNotCreatedException;
import org.nsu.oop.calculator.exception.stream.ReaderNotCreatedException;

public class CalculatorTest {

    private static final Logger log = Logger.getLogger(Calculator.class.getName());

    private ByteArrayOutputStream outContent;
    private PrintStream printStream;

    @BeforeEach
    public void setup() {
        outContent = new ByteArrayOutputStream();
        printStream = new PrintStream(outContent);
        System.setOut(printStream);
    }

    @AfterEach
    void tear(){
        printStream.close();
        try {
            outContent.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.setOut(System.out);
    }

    @Test
    public void test1() {
        Calculator calculator = new Calculator();
        try (FileReader reader = new FileReader("src/test/java/tests/instructions1.txt")) {
            log.info("Open stream for reading.");
            InstructionParser instructionParser = new InstructionParser();
            try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                List<String> arguments;
                String commandName;
                do {
                    arguments = instructionParser.parse(bufferedReader);
                    if (arguments == null) {
                        break;
                    }
                    commandName = arguments.getFirst();
                    arguments.remove(commandName);
                    calculator.run(commandName, arguments);
                } while (true);
            } catch (IOException e) {
                log.warning("BufferedReaderNotCreatedException.");
                throw new BufferedReaderNotCreatedException();
            }
        } catch (IOException e) {
            log.warning("ReaderNotCreatedException.");
            throw new ReaderNotCreatedException();
        }
        String rightOut = "30.0\n";
        Assertions.assertEquals(rightOut, outContent.toString());
    }

    @Test
    public void test2() {
        Calculator calculator = new Calculator();
        try (FileReader reader = new FileReader("src/test/java/tests/instructions2.txt")) {
            log.info("Open stream for reading.");
            InstructionParser instructionParser = new InstructionParser();
            try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                List<String> arguments;
                String commandName;
                do {
                    arguments = instructionParser.parse(bufferedReader);
                    if (arguments == null) {
                        break;
                    }
                    commandName = arguments.getFirst();
                    arguments.remove(commandName);
                    calculator.run(commandName, arguments);
                } while (true);
            } catch (IOException e) {
                log.warning("BufferedReaderNotCreatedException.");
                throw new BufferedReaderNotCreatedException();
            }
        } catch (IOException e) {
            log.warning("ReaderNotCreatedException.");
            throw new ReaderNotCreatedException();
        }
        String rightOut = "2.0\n";
        Assertions.assertEquals(rightOut, outContent.toString());
    }

    @Test
    public void test3() {
        Calculator calculator = new Calculator();
        try (FileReader reader = new FileReader("src/test/java/tests/instructions3.txt")) {
            log.info("Open stream for reading.");
            InstructionParser instructionParser = new InstructionParser();
            try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                List<String> arguments;
                String commandName;
                do {
                    arguments = instructionParser.parse(bufferedReader);
                    if (arguments == null) {
                        break;
                    }
                    commandName = arguments.getFirst();
                    arguments.remove(commandName);
                    calculator.run(commandName, arguments);
                } while (true);
            } catch (IOException e) {
                log.warning("BufferedReaderNotCreatedException.");
                throw new BufferedReaderNotCreatedException();
            }
        } catch (IOException e) {
            log.warning("ReaderNotCreatedException.");
            throw new ReaderNotCreatedException();
        }
        String rightOut = "21.0\n";
        Assertions.assertEquals(rightOut, outContent.toString());
    }

    @Test
    public void test4() {
        Calculator calculator = new Calculator();
        try (FileReader reader = new FileReader("src/test/java/tests/instructions4.txt")) {
            log.info("Open stream for reading.");
            InstructionParser instructionParser = new InstructionParser();
            try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                List<String> arguments;
                String commandName;
                do {
                    arguments = instructionParser.parse(bufferedReader);
                    if (arguments == null) {
                        break;
                    }
                    commandName = arguments.getFirst();
                    arguments.remove(commandName);
                    calculator.run(commandName, arguments);
                } while (true);
            } catch (IOException e) {
                log.warning("BufferedReaderNotCreatedException.");
                throw new BufferedReaderNotCreatedException();
            }
        } catch (IOException e) {
            log.warning("ReaderNotCreatedException.");
            throw new ReaderNotCreatedException();
        }
        String rightOut = "2.0\n";
        Assertions.assertEquals(rightOut, outContent.toString());
    }

    @Test
    public void test5() {
        Calculator calculator = new Calculator();
        try (FileReader reader = new FileReader("src/test/java/tests/instructions5.txt")) {
            log.info("Open stream for reading.");
            InstructionParser instructionParser = new InstructionParser();
            try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                List<String> arguments;
                String commandName;
                do {
                    arguments = instructionParser.parse(bufferedReader);
                    if (arguments == null) {
                        break;
                    }
                    commandName = arguments.getFirst();
                    arguments.remove(commandName);
                    calculator.run(commandName, arguments);
                } while (true);
            } catch (IOException e) {
                log.warning("BufferedReaderNotCreatedException.");
                throw new BufferedReaderNotCreatedException();
            }
        } catch (IOException e) {
            log.warning("ReaderNotCreatedException.");
            throw new ReaderNotCreatedException();
        }
        String rightOut = "456.0\n";
        Assertions.assertEquals(rightOut, outContent.toString());
    }

}
