package com.handong.word;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD {
    ArrayList<Word> list;
    Scanner s;
    String fname = "Dictionary.txt";

    WordCRUD(Scanner s) {
        list = new ArrayList<>();
        this.s = s;
    }

    @Override
    public Object add() {
        System.out.print("=> 난이도(1,2,3) & 새 단어 입력 : ");
        int level = s.nextInt();
        String word = s.nextLine();
        word = word.trim();
        System.out.print("=> 뜻 입력 : ");
        String meaning = s.nextLine();

        return new Word(0, level, word, meaning);
    }

    public void addItem() {
        Word one = (Word)add();
        list.add(one);
        System.out.println("새로운 단어가 단어장에 추가되었습니다. ");
    }

    public void listAll() {
        System.out.println("----------------------------------");
        for(int i = 0; i < list.size(); i++) {
            System.out.print(String.format("%02d",(i+1)) + " ");
            System.out.println(list.get(i).toString());
        }
        System.out.println("----------------------------------");
    }

    public ArrayList<Integer> listAll(String keyword) {
        ArrayList<Integer> idlist = new ArrayList<>();
        int j = 0;

        System.out.println("----------------------------------");
        for(int i = 0; i < list.size(); i++) {
            String word = list.get(i).getWord();
            if(!word.contains(keyword)) continue;
            System.out.print(String.format("%02d",(j+1)) + " ");
            System.out.println(list.get(i).toString());
            idlist.add(i);
            j++;
        }
        System.out.println("----------------------------------");

        return idlist;
    }

    public void listAll(int level) {
        int j = 0;

        System.out.println("----------------------------------");
        for(int i = 0; i < list.size(); i++) {
            int exlevel = list.get(i).getLevel();
            if(exlevel != level) continue;
            System.out.print((j+1) + " ");
            System.out.println(list.get(i).toString());
            j++;
        }
        System.out.println("----------------------------------");
    }

    public void updateItem() {
        System.out.print("=> 수정할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword);
        System.out.print("=> 수정할 번호 선택 : ");
        int id = s.nextInt();
        s.nextLine();
        System.out.print("=> 뜻 입력 : ");
        String meaning = s.nextLine();
        Word word = list.get(idlist.get(id-1));
        word.setMeaning(meaning);
        System.out.println("단어가 수정되었습니다.");
    }

    public void deleteItem() {
        System.out.print("=> 삭제할 단어 검색 : ");
        String keyword = s.next();
        ArrayList<Integer> idlist = this.listAll(keyword);
        System.out.print("=> 삭제할 번호 선택 : ");
        int id = s.nextInt();
        s.nextLine();
        System.out.print("=> 정말로 삭제하시겠습니까? (Y/N) ");
        String ans = s.next();
        if(ans.equalsIgnoreCase("Y")) {
            list.remove((int)idlist.get(id-1));
            System.out.println("단어가 삭제되었습니다.");
        } else
            System.out.println("취소되었습니다.");
    }

    public void loadFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fname));
            String line;
            int cnt = 0;
            while(true) {
                line = br.readLine(); // 한 줄씩 읽어오기
                if(line == null) break; // 반복 종료
                // 읽은 데이터를 "|" 를 기준으로 난이도, 단어, 뜻으로 나눠서 각각 저장
                String data[] = line.split("\\|");
                int level = Integer.parseInt(data[0]);
                String word = data[1];
                String meaning = data[2];
                list.add(new Word(0, level, word, meaning)); // 데이터 추가
                cnt++; // 개수 세기
            }
            br.close();
            System.out.println("=> " + cnt + "개 로딩 완료!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveFile() {
        PrintWriter pr = null;
        try {
            pr = new PrintWriter(new FileWriter(fname));
            for(Word one : list) {
                pr.write(one.toFileString() + "\n");
            }
            pr.close();
            System.out.println("=> 데이터 저장 완료!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchLevel() {
        System.out.print("=> 원하는 레벨은? (1~3) ");
        int level = s.nextInt();
        listAll(level);
    }

    public void searchWord() {
        System.out.print("=> 원하는 단어는? ");
        String keyword = s.next();
        listAll(keyword);
    }

    @Override
    public int update(Object obj) {
        return 0;
    }

    @Override
    public int delete(Object obj) {
        return 0;
    }

    @Override
    public void selectOne(int id) {

    }
}
