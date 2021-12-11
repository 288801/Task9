package ru.vsu.cs.course1;


import java.util.ArrayList;
import java.util.Scanner;

public class Task {
    public static ArrayList<Integer> ArrToList(int[] arr){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i: arr) {
            list.add(i);
        }
        return list;
    }

    public static ArrayList<Integer> readListFromFile(String fileName){
        Scanner scanner = new Scanner(fileName);
        ArrayList<Integer> list = new ArrayList<>();
        while (scanner.hasNext()){
            list.add(Integer.parseInt(scanner.next()));
        }
        return list;
    }

    public static ArrayList<Integer> solution(ArrayList<Integer> list1, ArrayList<Integer> list2){
        int size1 = list1.size();
        int size2 = list2.size();
        ArrayList<Integer> newList = new ArrayList<>();

        if(size1 == size2){
            for(int i = 0; i < size1; i++){
                if(i%2 == 0){
                    newList.add(i, list1.get(i));
                } else{
                    newList.add(i, list2.get(i));
                }
            }
        } else if(size1 > size2){
            for(int i = 0; i < size1; i++){
                if(i%2 == 1 && i<size2){
                    newList.add(i, list2.get(i));
                } else{
                    newList.add(i, list1.get(i));
                }
            }
        } else{
            for(int i = 0; i < size2; i++){
                if(i%2 == 0 && i<size1){
                    newList.add(i, list1.get(i));
                } else{
                    newList.add(i, list2.get(i));
                }
            }
        }

        return newList;
    }
}
