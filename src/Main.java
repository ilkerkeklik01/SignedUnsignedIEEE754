

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;



/*
    Input file name : ./src/input.txt
    Byte ordering : l for little endian else : big endian
    Data Type : float (floating point literals IEEE 754), int(signed) , unsigned

 */

public class Main {
    public static void main(String[] args) {
        InputData.getInput();
        String[] binaryList = BinaryConverter.generateBinaryList(InputData.input);
        ArrayList<String> outputArrayList = new ArrayList<String>();

        for (int i = 0; i<binaryList.length; i++){
            if(InputData.dataType == InputData.DataType.UnsignedInteger){
                outputArrayList.add(Integer.toString(DecimalConverter.binaryToUnsigned(binaryList[i])));
            }
            else if(InputData.dataType == InputData.DataType.SignedInteger){
                outputArrayList.add(Integer.toString(DecimalConverter.binaryToSigned(binaryList[i])));
            }
            else{
                outputArrayList.add(DecimalConverter.convertToFloat(binaryList[i], InputData.dataSize));
            }
        }

        String output = GetOutPutText(outputArrayList, InputData.dataSize);
        writeOutput(output);
        System.out.println(output);
    }

    public static void printArrayList(ArrayList<String> array, int dataSize){   // Print 12 / size numbers per line

        int maxLineContent = 12 / dataSize;
        int lineContentCounter = 0;

        for(String each : array){
            lineContentCounter++;
            if(lineContentCounter % maxLineContent != 0)
                System.out.print(each + " ");
            else{
                System.out.println(each);
                lineContentCounter = 0;
            }
        }
    }


    public static String GetOutPutText(ArrayList<String> array, int dataSize){   // Return String with 12 / size numbers per line
        String output = "";
        int maxLineContent = 12 / dataSize;
        int lineContentCounter = 0;

        for(String each : array){
            lineContentCounter++;
            if(lineContentCounter % maxLineContent != 0)
                output += (each + " ");
            else{
                output += (each + "\n");
                lineContentCounter = 0;
            }
        }
        output = output.substring(0,output.length() - 1); // Remove the last redundant newline
        return output;
    }


    public static void writeOutput(String output){
        try {
            FileWriter fileWriter = new FileWriter("./src/output.txt");
            fileWriter.write(output);
            fileWriter.close();

        } catch (IOException e) {
            System.out.println("Can not write to the file. " + e.getMessage());
        }
    }
}