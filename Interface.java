import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

// Interface class to interact with the user.
public class Interface {
    public static void main(String[] args) {
        // Check if the input file is provided.
        if (args.length != 1) {
            System.out.println("Please provide the input file.");
            return;
        }

        String inputFile = args[0];
        BufferedReader fileReader = null;

        try {
            // Attempt to open the input file.
            fileReader = new BufferedReader(new FileReader(new File(inputFile)));
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + inputFile);
            return;
        }

        BSTDictionary dictionary = new BSTDictionary();

        // Read the input file and store records in the dictionary.
        try {
            String label;
            while ((label = fileReader.readLine()) != null) {
                label = label.toLowerCase();
                String typeAndData = fileReader.readLine();
                if (typeAndData != null) {
                    Record record = createRecord(label, typeAndData);
                    if (record != null) {
                        try {
                            dictionary.put(record);
                        } catch (DictionaryException e) {
                            System.out.println("A record with the given key (" + label + "," + determineType(typeAndData) + ") is already in the ordered dictionary.");
                        }
                    } else {
                        System.out.println("Invalid record.");
                    }
                }
            }
            fileReader.close();
        } catch (IOException e) {
            System.out.println("Error reading the file: " + inputFile);
        }

        StringReader keyboard = new StringReader();
        String line;

        // Loop to process user commands.
        while (true) {
            line = keyboard.read("Enter next command: ");
            String[] parts = line.trim().split("\\s+", 4);            
            String command = parts[0].toLowerCase();

            switch (command) {
                case "define":
                    handleDefine(parts, dictionary);
                    break;
                case "translate":
                    handleTranslate(parts, dictionary);
                    break;
                case "sound":
                    handleSound(parts, dictionary);
                    break;
                case "play":
                    handlePlay(parts, dictionary);
                    break;
                case "say":
                    handleSay(parts, dictionary);
                    break;
                case "show":
                    handleShow(parts, dictionary);
                    break;
                case "animate":
                    handleAnimate(parts, dictionary);
                    break;
                case "browse":
                    handleBrowse(parts, dictionary);
                    break;
                case "delete":
                    handleDelete(parts, dictionary);
                    break;
                case "add":
                    handleAdd(parts, dictionary);
                    break;
                case "list":
                    handleList(parts, dictionary);
                    break;
                case "first":
                    handleFirst(dictionary);
                    break;
                case "last":
                    handleLast(dictionary);
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Invalid command.");
            }
        }
    }

    // Create a record based on label and typeAndData.
    private static Record createRecord(String label, String typeAndData) {
        int type = determineType(typeAndData);
        String data;
        if (type == 1 || type == 6 || type == 7 || type == 8) {
            data = typeAndData;
        } else {
            data = typeAndData.substring(1);
        }
        return new Record(new Key(label, type), data);
    }

    // Determine the type of the record based on typeAndData.
    private static int determineType(String typeAndData) {
        if (typeAndData.startsWith("-")) return 3;
        if (typeAndData.startsWith("+")) return 4;
        if (typeAndData.startsWith("*")) return 5;
        if (typeAndData.startsWith("/")) return 2;
        if (typeAndData.endsWith(".gif")) return 7;
        if (typeAndData.endsWith(".jpg")) return 6;
        if (typeAndData.endsWith(".html")) return 8;
        return 1;
    }

    // Handle the 'define' command.
    private static void handleDefine(String[] parts, BSTDictionary dictionary) {
        // Check if the command is valid.
        if (parts.length < 2) {
            System.out.println("Invalid command.");
            return;
        }

        String label = parts[1].toLowerCase();
        Record record = dictionary.get(new Key(label, 1));
        
        if (record != null) {
            System.out.println(record.getDataItem());
        } else {
            System.out.println("The word " + label + " is not in the dictionary");
        }
    }

    // Handle the 'translate' command.
    private static void handleTranslate(String[] parts, BSTDictionary dictionary) {
        // Check if the command is valid. 
        if (parts.length < 2) {
            System.out.println("Invalid command.");
            return;
        }

        String label = parts[1].toLowerCase();
        Record record = dictionary.get(new Key(label, 2));

        if (record != null) {
            System.out.println(record.getDataItem());
        } else {
            System.out.println("There is no definition for the word " + label + ".");
        }
    }

    // Handle the 'sound' command.
    private static void handleSound(String[] parts, BSTDictionary dictionary) {
        // Check if the command is valid.
        if (parts.length < 2) {
            System.out.println("Invalid command.");
            return;
        }

        String label = parts[1].toLowerCase();
        Record record = dictionary.get(new Key(label, 3));

        if (record != null) {
            System.out.println("Playing sound file: " + record.getDataItem());
        } else {
            System.out.println("There is no sound file for " + label + ".");
        }
    }

    // Handle the 'play' command.
    private static void handlePlay(String[] parts, BSTDictionary dictionary) {
        // Check if the command is valid.
        if (parts.length < 2) {
            System.out.println("Invalid command.");
            return;
        }

        String label = parts[1].toLowerCase();
        Record record = dictionary.get(new Key(label, 4));

        if (record != null) {
            System.out.println("Playing music file: " + record.getDataItem());
        } else {
            System.out.println("There is no music file for " + label + ".");
        }
    }

    // Handle the 'say' command.
    private static void handleSay(String[] parts, BSTDictionary dictionary) {
        // Check if the command is valid.
        if (parts.length < 2) {
            System.out.println("Invalid command.");
            return;
        }

        String label = parts[1].toLowerCase();
        Record record = dictionary.get(new Key(label, 5));

        if (record != null) {
            System.out.println("Playing voice file: " + record.getDataItem());
        } else {
            System.out.println("There is no voice file for " + label + ".");
        }
    }

    // Handle the 'show' command.
    private static void handleShow(String[] parts, BSTDictionary dictionary) {
        // Check if the command is valid.
        if (parts.length < 2) {
            System.out.println("Invalid command.");
            return;
        }

        String label = parts[1].toLowerCase();
        Record record = dictionary.get(new Key(label, 6));

        if (record != null) {
            System.out.println("Showing image file: " + record.getDataItem());
        } else {
            System.out.println("There is no image file for " + label + ".");
        }
    }

    // Handle the 'animate' command.
    private static void handleAnimate(String[] parts, BSTDictionary dictionary) {
        // Check if the command is valid.
        if (parts.length < 2) {
            System.out.println("Invalid command.");
            return;
        }

        String label = parts[1].toLowerCase();
        Record record = dictionary.get(new Key(label, 7));

        if (record != null) {
            System.out.println("Succesfully called show with " + record.getDataItem());
        } else {
            System.out.println("There is no animated image file for " + label + ".");
        }
    }

    // Handle the 'browse' command.
    private static void handleBrowse(String[] parts, BSTDictionary dictionary) {
        // Check if the command is valid.
        if (parts.length < 2) {
            System.out.println("Invalid command.");
            return;
        }

        String label = parts[1].toLowerCase();
        Record record = dictionary.get(new Key(label, 8));

        if (record != null) {
            System.out.println("Showing webpage: " + record.getDataItem());
        } else {
            System.out.println("There is no webpage called " + label + ".");
        }
    }

    // Handle the 'delete' command.
    private static void handleDelete(String[] parts, BSTDictionary dictionary) {
        // Check if the command is valid.
        if (parts.length < 3) {
            System.out.println("Invalid command.");
            return;
        }

        String label = parts[1].toLowerCase();
        int type;

        // Check if the type is valid then removes it.
        try {
            type = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid type.");
            return;
        }
        Key key = new Key(label, type);
        try {
            dictionary.remove(key);
        } catch (DictionaryException e) {
            System.out.println("No record in the ordered dictionary has key (" + label + "," + type + ").");
        }
    }

    // Handle the 'add' command.
    private static void handleAdd(String[] parts, BSTDictionary dictionary) {
        // Check if the command is valid.
        if (parts.length < 4) {
            System.out.println("Invalid command.");
            return;
        }

        String label = parts[1].toLowerCase();
        int type;

        // Check if the type is valid then adds it.
        try {
            type = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid type.");
            return;
        }
        String data = parts[3];
        Record newRecord = new Record(new Key(label, type), data);
        try {
            dictionary.put(newRecord);
        } catch (DictionaryException e) {
            System.out.println("A record with the given key (" + label + "," + type + ") is already in the ordered dictionary.");
        }
    }

    // Handle the 'list' command.
    private static void handleList(String[] parts, BSTDictionary dictionary) {
        // Check if the command is valid.
        if (parts.length < 2) {
            System.out.println("Invalid command.");
            return;
        }

        String prefix = parts[1].toLowerCase();
        Record current = dictionary.smallest();
        boolean found = false;

        // Iterate through dictionary and print labels starting with the given prefix.
        while (current != null) {
            if (current.getKey().getLabel().startsWith(prefix)) {
                System.out.print(current.getKey().getLabel() + ", ");
                found = true;
            }
            current = dictionary.successor(current.getKey());
        }
        if (!found) {
            System.out.println("No label attributes in the ordered dictionary start with prefix " + prefix + ".");
        } else {
            System.out.println();
        }
    }

    // Handle the 'first' command.
    private static void handleFirst(BSTDictionary dictionary) {
        // Get the smallest record in the dictionary.
        Record first = dictionary.smallest();
        if (first != null) {
            System.out.println(first.getKey().getLabel() + "," + first.getKey().getType() + "," + first.getDataItem());
        } else {
            System.out.println("The ordered dictionary is empty.");
        }
    }

    // Handle the 'last' command.
    private static void handleLast(BSTDictionary dictionary) {
        // Get the largest record in the dictionary.
        Record last = dictionary.largest();
        if (last != null) {
            System.out.println(last.getKey().getLabel() + "," + last.getKey().getType() + "," + last.getDataItem());
        } else {
            System.out.println("The ordered dictionary is empty.");
        }
    }
}