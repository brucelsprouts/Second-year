import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Interface {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please provide the input file.");
            return;
        }

        String inputFile = args[0];
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(new File(inputFile));
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + inputFile);
            return;
        }

        BSTDictionary dictionary = new BSTDictionary();

        // Read input file and populate the dictionary
        while (fileScanner.hasNextLine()) {
            String label = fileScanner.nextLine().toLowerCase();
            if (fileScanner.hasNextLine()) {
                String typeAndData = fileScanner.nextLine();
                Record record = createRecord(label, typeAndData);
                if (record != null) {
                    try {
                        dictionary.put(record);
                    } catch (DictionaryException e) {
                        System.out.println("Error adding record: " + e.getMessage());
                    }
                }
            }
        }
        fileScanner.close();

        // Initialize StringReader to handle commands
        StringReader keyboard = new StringReader();
        String line;

        // Read and process user commands
        while (true) {
            line = keyboard.read("Enter next command: ");
            String[] parts = line.trim().split(" ", 2);
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
                    System.out.println("Exiting the program.");
                    return;
                default:
                    System.out.println("Invalid command.");
            }
        }
    }

    private static Record createRecord(String label, String typeAndData) {
        int type = determineType(typeAndData);
        if (type != -1) {
            return new Record(new Key(label, type), typeAndData);
        }
        return null;
    }

    private static int determineType(String typeAndData) {
        if (typeAndData.startsWith("-")) return 3;
        if (typeAndData.startsWith("+")) return 4;
        if (typeAndData.startsWith("*")) return 5;
        if (typeAndData.startsWith("/")) return 2;
        if (typeAndData.endsWith(".gif")) return 7;
        if (typeAndData.endsWith(".jpg")) return 6;
        if (typeAndData.endsWith(".html")) return 8;
        return 1; // Default type (definition)
    }

    private static void handleDefine(String[] parts, BSTDictionary dictionary) {
        if (parts.length < 2) {
            System.out.println("Invalid command.");
            return;
        }
        String label = parts[1].toLowerCase();
        Record record = dictionary.get(new Key(label, 1)); // type 1 for definition
        if (record != null) {
            System.out.println(record.getDataItem());
        } else {
            System.out.println("The word " + label + " is not in the ordered dictionary");
        }
    }

    private static void handleTranslate(String[] parts, BSTDictionary dictionary) {
        if (parts.length < 2) {
            System.out.println("Invalid command.");
            return;
        }
        String label = parts[1].toLowerCase();
        Record record = dictionary.get(new Key(label, 2)); // type 2 for translation
        if (record != null) {
            System.out.println(record.getDataItem());
        } else {
            System.out.println("There is no definition for the word " + label);
        }
    }

    private static void handleSound(String[] parts, BSTDictionary dictionary) {
        if (parts.length < 2) {
            System.out.println("Invalid command.");
            return;
        }
        String label = parts[1].toLowerCase();
        Record record = dictionary.get(new Key(label, 3)); // type 3 for sound
        if (record != null) {
            System.out.println("Playing sound: " + record.getDataItem());
        } else {
            System.out.println("There is no sound file for " + label);
        }
    }

    private static void handlePlay(String[] parts, BSTDictionary dictionary) {
        if (parts.length < 2) {
            System.out.println("Invalid command.");
            return;
        }
        String label = parts[1].toLowerCase();
        Record record = dictionary.get(new Key(label, 4)); // type 4 for music
        if (record != null) {
            System.out.println("Playing music: " + record.getDataItem());
        } else {
            System.out.println("There is no music file for " + label);
        }
    }

    private static void handleSay(String[] parts, BSTDictionary dictionary) {
        if (parts.length < 2) {
            System.out.println("Invalid command.");
            return;
        }
        String label = parts[1].toLowerCase();
        Record record = dictionary.get(new Key(label, 5)); // type 5 for voice
        if (record != null) {
            System.out.println("Saying voice: " + record.getDataItem());
        } else {
            System.out.println("There is no voice file for " + label);
        }
    }

    private static void handleShow(String[] parts, BSTDictionary dictionary) {
        if (parts.length < 2) {
            System.out.println("Invalid command.");
            return;
        }
        String label = parts[1].toLowerCase();
        Record record = dictionary.get(new Key(label, 6)); // type 6 for image
        if (record != null) {
            System.out.println("Showing image: " + record.getDataItem());
        } else {
            System.out.println("There is no image file for " + label);
        }
    }

    private static void handleAnimate(String[] parts, BSTDictionary dictionary) {
        if (parts.length < 2) {
            System.out.println("Invalid command.");
            return;
        }
        String label = parts[1].toLowerCase();
        Record record = dictionary.get(new Key(label, 7)); // type 7 for animated image
        if (record != null) {
            System.out.println("Animating image: " + record.getDataItem());
        } else {
            System.out.println("There is no animated image file for " + label);
        }
    }

    private static void handleBrowse(String[] parts, BSTDictionary dictionary) {
        if (parts.length < 2) {
            System.out.println("Invalid command.");
            return;
        }
        String label = parts[1].toLowerCase();
        Record record = dictionary.get(new Key(label, 8)); // type 8 for webpage
        if (record != null) {
            System.out.println("Browsing URL: " + record.getDataItem());
        } else {
            System.out.println("There is no webpage called " + label);
        }
    }

    private static void handleDelete(String[] parts, BSTDictionary dictionary) {
        if (parts.length < 3) {
            System.out.println("Invalid command.");
            return;
        }
        String label = parts[1].toLowerCase();
        int type;
        try {
            type = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid type.");
            return;
        }
        Key key = new Key(label, type);
        try {
            dictionary.remove(key);
            System.out.println("Record with key (" + label + ", " + type + ") deleted.");
        } catch (DictionaryException e) {
            System.out.println("No record in the ordered dictionary has key (" + label + ", " + type + ")");
        }
    }

    private static void handleAdd(String[] parts, BSTDictionary dictionary) {
        if (parts.length < 4) {
            System.out.println("Invalid command.");
            return;
        }
        String label = parts[1].toLowerCase();
        int type;
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
            System.out.println("Record added with key (" + label + ", " + type + ")");
        } catch (DictionaryException e) {
            System.out.println("A record with the given key (" + label + ", " + type + ") is already in the ordered dictionary");
        }
    }

    private static void handleList(String[] parts, BSTDictionary dictionary) {
        if (parts.length < 2) {
            System.out.println("Invalid command.");
            return;
        }
        String prefix = parts[1].toLowerCase();
        Record current = dictionary.smallest();
        while (current != null) {
            if (current.getKey().getLabel().startsWith(prefix)) {
                System.out.println(current.getKey().getLabel() + "," + current.getKey().getType() + "," + current.getDataItem());
            }
            current = dictionary.successor(current.getKey());
        }
    }

    private static void handleFirst(BSTDictionary dictionary) {
        Record first = dictionary.smallest();
        if (first != null) {
            System.out.println(first.getKey().getLabel() + "," + first.getKey().getType() + "," + first.getDataItem());
        } else {
            System.out.println("The dictionary is empty.");
        }
    }

    private static void handleLast(BSTDictionary dictionary) {
        Record last = dictionary.largest();
        if (last != null) {
            System.out.println(last.getKey().getLabel() + "," + last.getKey().getType() + "," + last.getDataItem());
        } else {
            System.out.println("The dictionary is empty.");
        }
    }

}
